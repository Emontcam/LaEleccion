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

public class ActivityPregunta3 extends Activity {

    int cont = 0;
    int puntos =0;
    //creamos un color para correcto, otro para incorrecto y otro para volver a normal
    int bien = Color.rgb(26, 135, 23);
    int mal = Color.rgb(152, 31, 28);
    int normal = Color.rgb(0, 0, 0);

    String[] listaRespuestas = {"La relación entre la apertura y la velocidad de obturación", "La relación entre el ISO y la velocidad de obturación", "La relación entre la apertura y el ISO",
            "Objetivo de enfoque fijo", "Objetivo de zoom", "Objetivo de enfoque automático",
            "Tiempo que el obturador está abierto", "Cantidad de luz en la foto", "Distancia de enfoque nítido",
            "Cantidad de luz en la foto", "Efecto de estiramiento en los bordes de la imagen", "Tiempo que el obturador está abierto",
            "Un defecto que causa que los colores no se enfoquen en el mismo punto", "Un defecto que causa que una imagen sea más brillante en el centro que en los bordes", "Un defecto que causa que una imagen sea más oscura en el centro que en los bordes"};
    String[] listaPreguntas = {"¿Qué es la 'ley de reciprocidad' en la fotografía? ",
            "¿Qué es un 'histograma' en la fotografía?",
            "¿Qué es la velocidad de obturación en fotografía?",
            "¿Qué es la distorsión de la lente en fotografía?",
            "¿Qué es la 'aberración cromática' en la fotografía?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas3);
    }

    public void comprobarRespuestas3(View view) throws InterruptedException {
        Resources resources = getResources();
        Button r = (Button) view;
        String respuesta = r.getText().toString();
        int resourceId;

        switch (cont){
            case 0:
                resourceId = resources.getIdentifier("respuesta3.1.1C", "string", getPackageName());
                comprobar3(r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 1:
                resourceId = resources.getIdentifier("respuesta3.2.3C", "string", getPackageName());
                comprobar3(r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 2:
                resourceId = resources.getIdentifier("respuesta3.3.1C", "string", getPackageName());
                comprobar3(r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 3:
                resourceId = resources.getIdentifier("respuesta3.4.2C", "string", getPackageName());
                comprobar3(r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 4:
                resourceId = resources.getIdentifier("respuesta3.5.1C", "string", getPackageName());
                comprobar3(r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
        }


    }

    public void comprobar3(Button r, int resourceId, Resources resources, String respuesta, int bien, int mal) throws InterruptedException {
        //creamos una variable para el sonido
        MediaPlayer sonido;
        //ocultamos lo demas botones
        Button b1 = findViewById(R.id.respuesta7);
        String r1 = b1.getText().toString();
        Button b2 = findViewById(R.id.respuesta8);
        String r2 = b2.getText().toString();
        Button b3 = findViewById(R.id.respuesta9);
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

    public void cambiarPreg3(View view) throws InterruptedException {
        //cambiamos las preguntas
        Button uno = (Button) findViewById(R.id.respuesta7);
        uno.setTextColor(normal);
        Button dos = (Button) findViewById(R.id.respuesta8);
        dos.setTextColor(normal);
        Button tres = (Button) findViewById(R.id.respuesta9);
        tres.setTextColor(normal);
        Button siguiente = (Button) findViewById(R.id.siguiente3);

        //mostramos los botones
        uno.setVisibility(View.VISIBLE);
        uno.setEnabled(true);
        dos.setVisibility(View.VISIBLE);
        dos.setEnabled(true);
        tres.setVisibility(View.VISIBLE);
        tres.setEnabled(true);

        TextView pregunta = (TextView) findViewById(R.id.pregunta3);
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
                irAEnhorabuena3();

        }else{
            AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
            mensaje.setTitle("");
            mensaje.setMessage("Responde las preguntas");
            mensaje.show();
        }
    }
    public void irAEnhorabuena3() throws InterruptedException {
        Thread.sleep(100);
        Intent i = new Intent(this, ActivityResultados.class);

        Bundle parmetros = new Bundle();
        parmetros.putInt("p", puntos);
        parmetros.putString("n", "AVANZADO");

        i.putExtras(parmetros);
        startActivity(i);


    };

}
