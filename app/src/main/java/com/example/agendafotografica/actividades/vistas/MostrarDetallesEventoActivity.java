package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.EventoViewHolder;

public class MostrarDetallesEventoActivity extends AppCompatActivity {

    TextView txt_detalle_correoe = null;
    TextView txt_detalle_descripcione = null;
    TextView txt_detalle_horae = null;
    TextView txt_detalle_fechae = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles_evento);
        txt_detalle_correoe = findViewById(R.id.txt_detalle_nombree);
        txt_detalle_descripcione = findViewById(R.id.txt_detalle_descripcione);
        txt_detalle_horae = findViewById(R.id.txt_detalle_horae);
        txt_detalle_fechae = findViewById(R.id.txt_detalle_fechae);
        Intent intent = getIntent();
        if(intent != null)
        {
            Evento e = (Evento) intent.getSerializableExtra(EventoViewHolder.EXTRA_OBJETO_EVENTO);
            txt_detalle_correoe.setText(e.getCorreoUsuario());
            txt_detalle_correoe.setText("correo: " + String.valueOf(e.getCorreoUsuario()));
            txt_detalle_descripcione.setText("descripcion: " + String.valueOf(e.getDescripcionEvento()));
            txt_detalle_horae.setText("hora: " + String.valueOf(e.getHoraSeleccionada()));
            txt_detalle_fechae.setText("fecha: " + String.valueOf(e.getFecha_recibida()));
        }
    }

    //método botón borrar evento
    public void borrar(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar Pantlla Borrar", Toast.LENGTH_SHORT).show();
        Intent borrar = new Intent(this, BorrarActivity.class);
        startActivity(borrar);

    }
    //método botón volver
    public void volverbtn(View view) {
        //.makeText(getApplicationContext(), "Iniciar Pantalla Mostrar", Toast.LENGTH_SHORT).show();
        Intent volver = new Intent(this, CalendarioActivity.class);
        startActivity(volver);
    }
}