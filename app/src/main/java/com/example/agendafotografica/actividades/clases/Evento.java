package com.example.agendafotografica.actividades.clases;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Evento implements Serializable {




    private String fecha_recibida;
    private String descripcionEvento;
    private String horaSeleccionada;
    private String correoUsuario;


    public Evento(String fecha_recibida, String descripcionEvento, String horaSeleccionada, String correoUsuario) {
        this.fecha_recibida = fecha_recibida;
        this.descripcionEvento = descripcionEvento;
        this.horaSeleccionada = horaSeleccionada;
        this.correoUsuario = correoUsuario;
    }





    public String getFecha_recibida() {
        return fecha_recibida;
    }

    public void setFecha_recibida(String fecha_recibida) {
        this.fecha_recibida = fecha_recibida;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getHoraSeleccionada() {
        return horaSeleccionada;
    }

    public void setHoraSeleccionada(String horaSeleccionada) {
        this.horaSeleccionada = horaSeleccionada;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }


    @Override
    public String toString() {
        return "Evento{" +
                "fecha_recibida='" + fecha_recibida + '\'' +
                ", descripcionEvento='" + descripcionEvento + '\'' +
                ", horaSeleccionada='" + horaSeleccionada + '\'' +
                ", correoUsuario='" + correoUsuario + '\'' +
                '}';
    }
}
