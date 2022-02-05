package com.example.agendafotografica.actividades.clases;

import java.io.Serializable;

public class Evento implements Serializable {

    private String evento;

    public Evento(String evento) {
        this.evento = evento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
}
