package com.example.agendafotografica.actividades.modelos;

import android.util.Log;

import com.example.agendafotografica.actividades.clases.Evento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static ArrayList<Evento> obtenerEventos() {
        Connection conexion = BaseDB.conectarConBaseDeDatos(); //crea una conexion con la base de datos
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {
            System.out.println("entra al try al obtener?");
            Statement sentencia = conexion.createStatement(); //crea una sentencia preparada de evento
            String ordenSQL = "SELECT * FROM evento"; //es el sql para obtenerlo
            System.out.println("que devuelve ordenSQL?" + ordenSQL);
            ResultSet resultado = sentencia.executeQuery(ordenSQL); //ejecuta la orden SQL
            System.out.println("que devuelve resultado" + resultado);
            while(resultado.next()) //vas leyendo linea a linea del resultado
            {
                System.out.println("entra al while?");
                String correo = resultado.getString("correo"); //obtiene las columnas de la tabla
                String descripcion = resultado.getString("descripcion");
                String hora = resultado.getString("hora");
                String fecha = resultado.getString("fecha");
                Evento elevento = new Evento(fecha,descripcion,hora,correo);
                eventos.add(elevento); //creas un objeto de tipo evento y la añades al arraylist
            }
            resultado.close(); //cerrar siempre
            sentencia.close();
            conexion.close();
            return eventos;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return eventos; //cursos vacio
        }
    }

}
