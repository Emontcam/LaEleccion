package com.example.laeleccion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

// Clase controladora de la base de datos
public class BDHelper extends SQLiteOpenHelper {

    // El contexto sirve para acceder a los ficheros del SO (entre otros, como ajustes, etc)
    private Context contexto;

    // Preparamos sentencias SQL
    private final String SQLCREATE = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, nivel TEXT,  puntos INTEGER)";
    private final String SQLDROP = "DROP TABLE IF EXISTS Usuarios";

    // Creamos la base de datos vacía en principio

    private SQLiteDatabase bd = null;

    //Datos de la BBDD y la tabla
    public static final int DATABASE_VERSION = 1; // Variable para contabilizar las veces que vamos actualizando la BBDD
    public static final String DATABASE_NOMBRE = "REGISTRO DE PUNTOS"; // Variable con el nombre de la BBDD

    // Constructor

    public BDHelper(Context contexto) {
        super(contexto, DATABASE_NOMBRE, null, DATABASE_VERSION);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(SQLCREATE); // SIEMPRE QUE QUERAMOS EJECUTAR UNA SENTENCIA SQL, UTILIZAMOS EL METODO EXECSQL Y LE PASAMOS LA SENTENCIA SQL
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL(SQLDROP);
        bd.execSQL(SQLCREATE);
    }

    public void cerrarBD() {
        if (bd != null) {
            bd.close();
        }
    }

    public void insertarUsuario(int puntos, String nivel, String nombre) throws Exception {
        bd = getWritableDatabase(); // Obtener la base de datos en modo edición

        if (bd != null) {

            try {
                ContentValues valores = new ContentValues();
                valores.put("nombre", nombre);
                valores.put("nivel", nivel);
                valores.put("puntos", puntos);

                long nuevaFila = bd.insert("Usuarios", null, valores);
                Toast.makeText(contexto,"" + nuevaFila + "", Toast.LENGTH_SHORT).show();
                if (nuevaFila != -1) {
                    Toast.makeText(contexto, "Puntos guardados correctamente " + puntos, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contexto, "Error al guardar los puntos", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarBD(); // Asegúrate de cerrar la base de datos en cualquier caso
            }
        }
    }


    //con esto devolvemos un arrayList
    public ArrayList<String> mostrarListaUsuarios() {

        //como solo queremos leer será suficiente con Readable
        bd = getReadableDatabase();

        String SQLConsulta = "SELECT * FROM Usuarios ORDER BY id DESC";

        //Cursor donde APUNTA a cada fila
        Cursor cursor = bd.rawQuery(SQLConsulta, null);

        //Creamos lista
        ArrayList<String> listaUsuarios = new ArrayList<>();

        //Mientras el cursor pueda avanzar (haya más registros) guardamos y añadimos
        //a la lista

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("id");
            int nombreIndex = cursor.getColumnIndex("nombre");
            int puntosIndex = cursor.getColumnIndex("puntos");
            int nivelIndex = cursor.getColumnIndex("nivel");

            if (idIndex != -1 && puntosIndex != -1) {
                int id = cursor.getInt(idIndex);
                int puntos = cursor.getInt(puntosIndex);
                String nivel = cursor.getString(nivelIndex);
                String nombre = cursor.getString(nombreIndex);
                listaUsuarios.add(nombre + " || " + nivel +"  " + puntos + " puntos");
            }
        }
        //Cerramos el cursor y la lista
        cursor.close();
        bd.close();
        //Devolvemos la lista
        return listaUsuarios;

    }
}

