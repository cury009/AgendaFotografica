package com.example.agendafotografica.actividades.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.ListaEventosAdapter;
import com.example.agendafotografica.actividades.controladores.EventoController;

import java.util.ArrayList;
import java.util.List;

public class MostrarActivity extends AppCompatActivity {

    //atributos
    private RecyclerView rv_eventos;
    private ListaEventosAdapter mAdapter;
    private List<Evento> evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        rv_eventos = findViewById(R.id.rv_evento); //enlazar con recycler view

        mAdapter = new ListaEventosAdapter(this); //objeto
        ArrayList<Evento> eventos = EventoController.obtenereventos(); //obtienes todos los eventos de la base de datos
        if(eventos != null) { //si contiene cursos entra
            mAdapter.setListaEventos(eventos); //se lo asigna al adapttador
        }

        //------------------------------------------------------------
        rv_eventos.setAdapter(mAdapter); //añades al recycler view el adaptador
        rv_eventos.setLayoutManager(new LinearLayoutManager(this)); //hacerlo  con un  Linear layout y le paso this
        // + SE MUESTRA EN PANTLLA EN FORMA LINEAL

    }

    //método volver
    public void volverbtn(View view) {
        Intent volver = new Intent(this, CalendarioActivity.class);
        startActivity(volver);
    }
    private void mostrarToast(String texto) { //MOSTRAR MENSAJES EN PANTALLA
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }
}