package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.agendafotografica.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    //método botón volver
    public void volverbtn(View view) {
        //.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
}