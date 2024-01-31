package com.example.laeleccion;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class ActivityRanking extends Activity {

    BDHelper bd;
    ListView listaGrafica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        //iniciamos la musica
        MediaPlayer sonido = MediaPlayer.create(this, R.raw.aplausos);
        sonido.start();
        listaGrafica = findViewById(R.id.lista);
        // Llamamos al constructor para crear el BDHelper
        bd = new BDHelper(this);

       //recogemos los datos
       Bundle bundle = getIntent().getExtras();
       if (bundle!=null){
           String nombre = bundle.getString("nombre");
           int p = bundle.getInt("puntuacion");
           String n = bundle.getString("nivel");
          //añadimos los puntos a la base de datos
           try {
               bd.insertarUsuario(p, n, nombre);

           } catch (Exception e) {
               e.printStackTrace();
           }

           mostrar();
       }

    }


    public void mostrar() {

            //Obtener la lista de usuarios que la devuelve el método
            ArrayList<String> lista = bd.mostrarListaUsuarios();

            //Crear un adaptador para la lista de usuarios
            //(adapta la estrucutura de datos para que pueda ser mostrada en el ListView)

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    lista
            );

            //Al componente lista le ponemos la lista adaptada

            listaGrafica.setAdapter(adapter);
        }
        public void inicio(View view){
            Intent i = new Intent(this, ActivityInicio.class);
            startActivity(i);
        }
    }


