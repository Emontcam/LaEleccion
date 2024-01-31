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

public class ActivityPregunta2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas2);
    }
    int cont = 0;
    int puntos =0;
    //creamos un color para correcto, otro para incorrecto y otro para volver a normal
    int bien = Color.rgb(26, 135, 23);
    int mal = Color.rgb(152, 31, 28);
    int normal = Color.rgb(0, 0, 0);

    String[] listaRespuestas = {"La nitidez y claridad de la imagen", "El desenfoque artístico del fondo", "El contraste entre luces y sombras",
            "Ayuda a calcular la exposición adecuada", "Divide la imagen en nueve secciones para crear equilibrio", "Indica la profundidad de campo de la fotografía",
            "Controlar la velocidad de obturación", "Cambiar el enfoque automático", "Ajustar la apertura para regular la cantidad de luz",
            "Una imagen sin procesar directamente desde el sensor", "Un formato de archivo comprimido para ahorrar espacio", "Un modo de disparo rápido para capturar movimiento",
            "Para intensificar los colores en un atardecer", "Para reducir la cantidad de luz en condiciones brillantes", "Para agregar efectos de viñeta a la imagen"};
    String[] listaPreguntas = {"¿Qué es el efecto bokeh en fotografía?",
            "¿Qué papel juega la regla de los tercios en composición fotográfica?",
            "¿Cuál es la función del diafragma en modo manual en una cámara?",
            "¿Qué significa el término 'RAW' en fotografía digital?",
            "¿En qué situación se utilizaría comúnmente un filtro de densidad neutra (ND) en fotografía?"};


    public void comprobarRespuestas2(View view) throws InterruptedException {
        Resources resources = getResources();
        Button r = (Button) view;
        String respuesta = r.getText().toString();
        int resourceId;


        switch (cont){
            case 0:
                resourceId = resources.getIdentifier("respuesta2.1.2C", "string", getPackageName());
                comprobar2(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 1:
                resourceId = resources.getIdentifier("respuesta2.2.2C", "string", getPackageName());
                comprobar2(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 2:
                resourceId = resources.getIdentifier("respuesta2.3.3C", "string", getPackageName());
                comprobar2(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 3:
                resourceId = resources.getIdentifier("respuesta2.4.1C", "string", getPackageName());
                comprobar2(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
            case 4:
                resourceId = resources.getIdentifier("respuesta2.5.2C", "string", getPackageName());
                comprobar2(cont, r, resourceId, resources, respuesta, bien, mal);
                cont++; break;
        }


    }

    public void comprobar2(int cont, Button r, int resourceId, Resources resources, String respuesta, int bien, int mal) throws InterruptedException {
        //creamos una variable para el sonido
        MediaPlayer sonido;
        //ocultamos lo demas botones
        Button b1 = findViewById(R.id.respuesta4);
        String r1 = b1.getText().toString();
        Button b2 = findViewById(R.id.respuesta5);
        String r2 = b2.getText().toString();
        Button b3 = findViewById(R.id.respuesta6);
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

    public void cambiarPreg2(View view) throws InterruptedException {

        //cambiamos las preguntas
        Button uno = (Button) findViewById(R.id.respuesta4);
        uno.setTextColor(normal);
        Button dos = (Button) findViewById(R.id.respuesta5);
        dos.setTextColor(normal);
        Button tres = (Button) findViewById(R.id.respuesta6);
        tres.setTextColor(normal);
        Button siguiente = (Button) findViewById(R.id.siguiente2);

        //mostramos los botones
        uno.setVisibility(View.VISIBLE);
        uno.setEnabled(true);
        dos.setVisibility(View.VISIBLE);
        dos.setEnabled(true);
        tres.setVisibility(View.VISIBLE);
        tres.setEnabled(true);
        TextView pregunta = (TextView) findViewById(R.id.pregunta2);
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
                irAEnhorabuena2();
        }else{

            AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
            mensaje.setTitle("");
            mensaje.setMessage("Responde las preguntas");
            mensaje.show();
        }
    }
    public void irAEnhorabuena2() throws InterruptedException {
        Thread.sleep(100);
        Intent i = new Intent(this, ActivityResultados.class);

        Bundle parmetros = new Bundle();
        parmetros.putInt("p", puntos);
        parmetros.putString("n", "MEDIO");

        i.putExtras(parmetros);
        startActivity(i);


    };




}
