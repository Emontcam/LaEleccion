package com.example.laeleccion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityInicio  extends Activity {
    Button boton;
    TextView titulo;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void nivelBasico(View view){
        boton = (Button) view;
        cambio(boton);
        Intent i = new Intent(this, ActivityPregunta1.class);
        startActivity(i);

    }

    public void nivelMedio(View view){
        boton = (Button) view;
        cambio(boton);
        Intent i = new Intent(this, ActivityPregunta2.class);
        startActivity(i);
    }

    public void nivelAvanzado(View view){
        boton = (Button) view;
        cambio(boton);
        Intent i = new Intent(this, ActivityPregunta3.class);
        startActivity(i);
    }
    public void cambio(Button b){
        b.setScaleX(0.9f);
        b.setScaleY(0.9f);
        b.setAlpha(0.5f);
    }
    public void defecto(Button b){
        b.setScaleX(1f);
        b.setScaleY(1f);
        b.setAlpha(1f);
    }
}
