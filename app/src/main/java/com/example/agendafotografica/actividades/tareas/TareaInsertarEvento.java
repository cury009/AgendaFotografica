package com.example.agendafotografica.actividades.tareas;

import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.modelos.EventoDB;

import java.util.concurrent.Callable;

public class TareaInsertarEvento implements Callable<Boolean> {

    private Evento e = null;
    public TareaInsertarEvento(Evento e) {

        this.e = e;
    }


    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = EventoDB.insertarEventoTabla(e);

        System.out.println("tareainsertarEvento1    "+  e);
        System.out.println("tareainsertarEvento2 funciona?" + insertadoOK);
        return insertadoOK;
    }
}
