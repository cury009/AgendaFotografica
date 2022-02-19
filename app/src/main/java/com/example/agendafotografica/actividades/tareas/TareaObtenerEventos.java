package com.example.agendafotografica.actividades.tareas;

import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.modelos.EventoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerEventos implements Callable<ArrayList<Evento>> { //hilo de ejecucion que devuelve <>

    @Override
    public ArrayList<Evento> call() throws Exception {  //metodo que hay que sobrescribir obligatoriamente
        ArrayList<Evento> eventos= EventoDB.obtenerEventos(); //obtiene todos los eventos de EventoDb
        return eventos; //devuelve cursos
    }
}
