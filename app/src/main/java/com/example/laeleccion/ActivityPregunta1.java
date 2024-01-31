package com.example.laeleccion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ActivityPregunta1 extends Activity {
int cont = 0;
int puntos =0;
    //creamos un color para correcto, otro para incorrecto y otro para volver a normal
    int bien = Color.rgb(26, 135, 23);
    int mal = Color.rgb(152, 31, 28);
    int normal = Color.rgb(0, 0, 0);

String[] listaRespuestas = {"Stop Motion", "Tiempo Congelado", "Dibujar con luz o grabar con luz",
        "Filtro Ultra violeta", "Filtro polarizador", "Filtro de densidad neutra",
        "60mm", "50mm", "85mm",
        "Ajustar un motivo en el visor mediante el zoom", "Fotografiar aquello que más te interesa en una escena", "Aislar un motivo de su entorno",
"Protege al sensor de la luz", "Da luminosidad al objetivo", "Le da perspectiva a tu fotografía"};
String[] listaPreguntas = {"La palabra fotografía proviene del griego que significa:",
        "¿Qué filtro se utiliza para eliminar los reflejos de superficies metálicas y no metálicas?",
        "La distancia focal clásica para tomar un retrato es de:",
        "Encuadrar es equivalente a:",
        "El obturador, además de regular el tiempo de exposición:"};
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas1);

    }

    public void comprobarRespuestas(View view) throws InterruptedException {
        Resources resources = getResources();
        Button r = (Button) view;

        String respuesta = r.getText().toString();
        int resourceId;

        switch (cont){
            case 0:
                resourceId = resources.getIdentifier("respuesta1.1.3C", "string", getPackageName());
                comprobar(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 1:
                resourceId = resources.getIdentifier("respuesta1.2.2C", "string", getPackageName());
                comprobar(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 2:
                 resourceId = resources.getIdentifier("respuesta1.3.2C", "string", getPackageName());
                comprobar(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 3:
                 resourceId = resources.getIdentifier("respuesta1.4.3C", "string", getPackageName());
                comprobar(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 4:
                resourceId = resources.getIdentifier("respuesta1.5.1C", "string", getPackageName());
                comprobar(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
        }


    }

    public void comprobar(int cont, Button r, int resourceId, Resources resources, String respuesta, int bien, int mal) throws InterruptedException {
    //creamos una variable para el sonido
        MediaPlayer sonido;
    //ocultamos lo demas botones
        Button b1 = findViewById(R.id.respuesta1);
        String r1 = b1.getText().toString();
        Button b2 = findViewById(R.id.resouesta2);
        String r2 = b2.getText().toString();
        Button b3 = findViewById(R.id.respuesta3);
        String r3 = b3.getText().toString();

        if (r1.equalsIgnoreCase(respuesta)){
            b1.setEnabled(false);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
        }
        if (r2.equalsIgnoreCase(respuesta)){
            b1.setVisibility(View.INVISIBLE);
            b2.setEnabled(false);
            b3.setVisibility(View.INVISIBLE);
        }
        if (r3.equalsIgnoreCase(respuesta)){
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setEnabled(false);
        }
    if (resourceId != 0) {
            //obtenemos el texto de la respuesta correcta
            //lo comparamos con el de el boton seleccionado
            String texto = resources.getString(resourceId);
            if (texto.equalsIgnoreCase(respuesta)){
                sonido = MediaPlayer.create(this, R.raw.acierto);
                sonido.start();
                r.setTextColor(bien);
                puntos++;

            }else{
                sonido = MediaPlayer.create(this, R.raw.error);
                sonido.start();
                r.setTextColor(mal);
            }
        }

    }

    public void cambiarPreg(View view) throws InterruptedException {

        //cambiamos las preguntas
        Button uno = (Button) findViewById(R.id.respuesta1);
        uno.setTextColor(normal);
        Button dos = (Button) findViewById(R.id.resouesta2);
        dos.setTextColor(normal);
        Button tres = (Button) findViewById(R.id.respuesta3);
        tres.setTextColor(normal);
        Button siguiente = (Button) findViewById(R.id.siguiente);

        //mostramos los botones
        uno.setVisibility(View.VISIBLE);
        uno.setEnabled(true);
        dos.setVisibility(View.VISIBLE);
        dos.setEnabled(true);
        tres.setVisibility(View.VISIBLE);
        tres.setEnabled(true);
        TextView pregunta = (TextView) findViewById(R.id.pregunta);
        if (cont==1){
            pregunta.setText(listaPreguntas[1]);
            uno.setText(listaRespuestas[3]);
            dos.setText(listaRespuestas[4]);
            tres.setText(listaRespuestas[5]);
        } else if (cont==2) {
            pregunta.setText(listaPreguntas[2]);
            uno.setText(listaRespuestas[6]);
            dos.setText(listaRespuestas[7]);
            tres.setText(listaRespuestas[8]);
        } else if (cont==3) {
            pregunta.setText(listaPreguntas[3]);
            uno.setText(listaRespuestas[9]);
            dos.setText(listaRespuestas[10]);
            tres.setText(listaRespuestas[11]);
        }
        else if (cont==4) {
            pregunta.setText(listaPreguntas[4]);
            uno.setText(listaRespuestas[12]);
            dos.setText(listaRespuestas[13]);
            tres.setText(listaRespuestas[14]);
            siguiente.setText("Finalizar");
        }else if(cont>=5){
              irAEnhorabuena();

  //--------------------------------------------------------------------------------
        }else{
            AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
            mensaje.setTitle("");
            mensaje.setMessage("Responde las preguntas");
            mensaje.show();
        }
    }
    public void irAEnhorabuena() throws InterruptedException {
        Thread.sleep(100);
        Intent i = new Intent(this, ActivityResultados.class);

        Bundle parmetros = new Bundle();
        parmetros.putInt("p", puntos);
        parmetros.putString("n", "BASICO");

        i.putExtras(parmetros);
        startActivity(i);
    };
}
