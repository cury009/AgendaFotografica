package com.example.agendafotografica.actividades.tareas;

import com.example.agendafotografica.actividades.modelos.EventoDB;


import java.util.concurrent.Callable;

public class TareaBorrarEvento implements Callable<Boolean>{//implements Callable<Boolean> {
    private Integer evento = null;
    private String correo = null;

    public TareaBorrarEvento(Integer evento, String correo) {
        this.evento = evento;
        this.correo = correo;
    }




    public Boolean call() throws  Exception {
        try
        {
            boolean borradoOK = EventoDB.borrarEvento(evento, correo);
            return borradoOK;
        }
        catch (Exception e)
        {

            return false;
        }
    }
}
