package com.example.agendafotografica.actividades.tareas;

import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.modelos.EventoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaSaberSiLaHoraEstaOcupada implements Callable<Boolean> { //hilo de ejecucion que devuelve <>

    private String hora = null;
    private String fecha = null;

    public TareaSaberSiLaHoraEstaOcupada(String fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    @Override
    public Boolean call() throws Exception {
        boolean horaOK = EventoDB.saberSiHoraEstaOcupada(fecha,hora);

        System.out.println("tareaSaberSiHoraEstaOcupada    "+  fecha+"    "+ hora);
        System.out.println("tareaSaberSiHoraEstaOcupada2 funciona?" + fecha +"    "+hora);
        return horaOK;
    }
}
