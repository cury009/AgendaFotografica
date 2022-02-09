package com.example.agendafotografica.actividades.vistas;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.modelos.BaseDB;
import com.example.agendafotografica.actividades.modelos.ConfiguracionDB;

import java.sql.Connection;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class); //indica a que actividad redigira
                //Intent intent = new Intent(SplashActivity.this, LoginActivity.class); //indica a que actividad redigira
                startActivity(intent);
                finish();


            }
        },3000); //tiempo para redirigir
    }
}