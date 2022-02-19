package com.example.agendafotografica.actividades.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendafotografica.R;

import java.util.ArrayList;

public class ListaEventosAdapter extends RecyclerView.Adapter<EventoViewHolder> { //hereda de recyclerview.adapter

    private Context c; //recibo el contexto
    private ArrayList<Evento> listaEventos;
    private LayoutInflater mInflater; //va crear un view

    public void setC(Context c) {
        this.c = c;
        this.listaEventos = new ArrayList<Evento>();
    }
    public ListaEventosAdapter(Context c, ArrayList<Evento> listaEventos) { //constructor
        this.c = c;
        this.listaEventos = listaEventos;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() {
        return c;
    }

    public ArrayList<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(ArrayList<Evento> listaEventos) { //pasar la lista de even
        this.listaEventos = listaEventos;

        notifyDataSetChanged(); //actualice la pantalla del recycler view
    }
    public ListaEventosAdapter(Context c) {
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }
    //SOBRESCRIBIR METODOS--------------------------------------
    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_evento, parent, false);
        //crear una casilla del recyclerview
        //se crea con el método inflate poniendo parent(padre), false(falso)
        return new EventoViewHolder(mItemView, this); //devuelve una viewHolder

        //crear un xml desde una vista nueva  (item_recyclerview_evento)
        //esa vista se la pasa eventoViewHolder
    }
    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) { //holder esta enlazado con la casiilla actual del recyclerview
        //coger del array de eventos, coger de la casilla actual que viene la variable position
        //(el dedo pulsado en la pantalla)
        if(listaEventos!=null) {
            //si lista Eventos es distinto a null --> entra
            Evento evento_actual = listaEventos.get(position); //evento actual y la cassilla del arraylist que esta cogiendo
            //la posicion de arraylist
            holder.txt_rv_evento_nombree.setText("correo: " + evento_actual.getCorreoUsuario());
            //escribe txt_rv_evento_nombree el correo del evento.
            holder.txt_rv_evento_descripcion.setText(String.valueOf("descripcion: " + evento_actual.getDescripcionEvento()));
            //escribe txt_rv_evento_descripcion la descripcion del evento
            holder.getTxt_rv_evento_fecha.setText(String.valueOf("fecha: " + evento_actual.getFecha_recibida()));
            //escribe txt_rv_evento_fecha la fecha del evento
            holder.txt_rv_evento_hora.setText(String.valueOf("hora: " + evento_actual.getHoraSeleccionada()));
            //escribe txt_rv_evento_hora la hora del evento
        }
    }
    @Override
    public int getItemCount() {
        if (listaEventos != null) //si la listaCursos es distinta a null, ha obtenido cursos
            return listaEventos.size(); //devuelve el tamaño del arrayList del curso
        else return 0; // y si no devuelve 0 que tiene 0 casillas
    }




}
