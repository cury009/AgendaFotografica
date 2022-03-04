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
import com.example.agendafotografica.actividades.controladores.UsuarioController;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MostrarActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    //atributos
    private RecyclerView rv_eventos;
    public static ListaEventosAdapter mAdapter;
    private List<Evento> evento;
    private String miperfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        mAuth = FirebaseAuth.getInstance();
        rv_eventos = findViewById(R.id.rv_evento); //enlazar con recycler view

        mAdapter = new ListaEventosAdapter(this); //objeto
        String micorreo = mAuth.getCurrentUser().getEmail();

        System.out.println("que tiene micorreo:   " + micorreo);
        ArrayList<Evento> eventos;

        //miperfil = UsuarioController.obtenerPerfil(micorreo);
        //if(miperfil.equalsIgnoreCase("administrador")) {
            //obtener evento de administrador, no filtro por correo
            //eventos = EventoController.obtenereventosAdministrador();
        //}
        //else {
            eventos = EventoController.obtenereventos(micorreo); //obtienes todos los eventos de la base de datos del usuario
        //}

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