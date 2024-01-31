package com.example.laeleccion;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityResultados extends Activity {
  TextView texto;
  TextView titulo;
  TextView dificultad;
  ImageView foto1;
  ImageView foto2;
  Button siguiente;
    TextView n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enhorabuena);
         n = findViewById(R.id.nombre);
        n.setEnabled(false);
        texto =  findViewById(R.id.punto);
        titulo =  findViewById(R.id.titulo2);
        dificultad =  findViewById(R.id.nivel);
        foto1 =  findViewById(R.id.foto1);
        foto2 =  findViewById(R.id.foto2);
        //ponemos el texto de nivel invisible
        dificultad.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();


        if (bundle!=null){
            int p = bundle.getInt("p");
            String n = bundle.getString("n");
            if (p>=4){
                MediaPlayer sonido = MediaPlayer.create(this, R.raw.aplausos);
                sonido.start();
            }else{
                MediaPlayer sonido = MediaPlayer.create(this, R.raw.abucheos);
                sonido.start();
                titulo.setText("Que pena");
                foto1.setImageResource(R.drawable.camara_rota);
                foto2.setImageResource(R.drawable.camara_rota);
            }

            dificultad.setText(n);
            texto.setText(p +"");
        }
    }

    public void inicio(String nombre){

        int p = Integer.parseInt(texto.getText().toString());
        String nn = dificultad.getText().toString();

        //mandamos los datos a la pagina de ranking
        Intent i = new Intent(this, ActivityRanking.class);
        Bundle parmetros = new Bundle();
        parmetros.putString("nombre", nombre);
        parmetros.putInt("puntuacion", p);
        parmetros.putString("nivel", nn);
        i.putExtras(parmetros);
        startActivity(i);

    }
    public void pedirNombre(View view){

        titulo.setText("¿Nos dices tu nombre?");
        texto.setVisibility(View.GONE);
        TextView t = findViewById(R.id.p);
        t.setVisibility(View.GONE);

        n.setVisibility(View.VISIBLE);
        n.setEnabled(true);

        String nombre = n.getText().toString();

        siguiente = findViewById(R.id.seguir);
        siguiente.setText("Ver Ranking");
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                if ((n.getText().toString()).equalsIgnoreCase("")){
                    name = "JugadorX";
                }else{
                    name = n.getText().toString();
                }
                inicio(name);
            }
        });
    }
}
