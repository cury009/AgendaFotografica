package com.example.agendafotografica.actividades.clases;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendafotografica.R;
import com.example.agendafotografica.actividades.vistas.MostrarActivity;
import com.example.agendafotografica.actividades.vistas.MostrarDetallesEventoActivity;

import java.util.ArrayList;

public class EventoViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener { //heredar de RecyclerViewHolder
    //ONclickListener. para hacer el onclick de la ventana detalle
    public static final String EXTRA_OBJETO_EVENTO =  "com.example.agendafotografica.actividades";
    public TextView txt_rv_evento_ideventoe = null;
    public TextView txt_rv_evento_nombree = null;
    public TextView txt_rv_evento_descripcion = null;
    public TextView txt_rv_evento_hora = null;
    public TextView getTxt_rv_evento_fecha= null;
    ListaEventosAdapter lcAdapter;

    public EventoViewHolder(@NonNull View itemView, ListaEventosAdapter lcAdapter) {
        //itemView la vista que genero el inflate
        //lo enlazas con listaCursoAdapter y lcAdapter
        super(itemView);

        txt_rv_evento_nombree = (TextView)  itemView.findViewById(R.id.txt_rv_evento_nombree); //enlazar
        txt_rv_evento_descripcion = (TextView)  itemView.findViewById(R.id.txt_rv_evento_descripcion); //enlazar
        txt_rv_evento_hora = (TextView)  itemView.findViewById(R.id.txt_rv_evento_hora); //enlazar
        getTxt_rv_evento_fecha = (TextView)  itemView.findViewById(R.id.txt_rv_evento_fecha); //enlazar
        this.lcAdapter = lcAdapter;
        itemView.setOnClickListener(this); //para activar el onckick cuando pulsas la casillas del recyvlerView
    }


    @Override
    public void onClick(View view) {

        int mPosition = getAdapterPosition(); //cogeer la posicion del curso actual
        // int mPosition = getLayoutPosition();
        ArrayList<Evento> eventos = this.lcAdapter.getListaEventos(); //cojo ese evento.
        Evento eventoactual = eventos.get(mPosition); //coge todos los eventos actualmente
        // lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetallesEventoActivity.class);  //lo vas enviar a detalle
        intent.putExtra(EXTRA_OBJETO_EVENTO, eventoactual);
        lcAdapter.getC().startActivity(intent); //coges contexto que es la pantalla que tiene el recyclerview y lo envias a detalles
    }
}
