package com.example.agendafotografica.actividades.modelos;

import com.example.agendafotografica.actividades.clases.Evento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventoDB {

    public static boolean insertarEventoTabla(Evento e) {

        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO evento (fecha,descripcion,hora,correo) VALUES (?, ?, ?, ?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1,  e.getFecha_recibida());
            pst.setString(2, e.getDescripcionEvento());
            pst.setString(3, e.getHoraSeleccionada());
            pst.setString(4, e.getCorreoUsuario());

            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();


            System.out.println("Evento e: " + e.getFecha_recibida() + ","+e.getDescripcionEvento()+","+  e.getHoraSeleccionada()+ ","+ e.getCorreoUsuario());
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException exception) {
            return false;
        }
    }

}
