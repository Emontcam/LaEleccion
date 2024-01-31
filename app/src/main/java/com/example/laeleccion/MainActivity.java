package com.example.laeleccion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Utilizamos un Handler para postDelayed y cambiar a la próxima actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Este método se ejecutará después del tiempo de espera
                Intent i = new Intent(MainActivity.this, ActivityInicio.class);
                startActivity(i);
                //para que no pueda volver atrás
                finish();
            }
        }, 1000);
    }


}