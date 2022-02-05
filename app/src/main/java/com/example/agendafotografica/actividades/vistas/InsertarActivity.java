package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;

public class InsertarActivity extends AppCompatActivity {

    TextView fechaIncorporada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        fechaIncorporada = (TextView) findViewById(R.id.fechaIncorporada);

        Intent intent = getIntent();
        String p = intent.getStringExtra(CalendarioActivity.enviarFecha);

        Evento e = (Evento) intent.getSerializableExtra(CalendarioActivity.enviarFecha);
        String fecha = e.getEvento();
        String fecha_recibida = fecha;
        fechaIncorporada.setText(fecha_recibida);


    }
}