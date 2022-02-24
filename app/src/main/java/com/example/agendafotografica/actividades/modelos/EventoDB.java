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

    public static ArrayList<Evento> obtenerEventos(String correo) {
        Connection conexion = BaseDB.conectarConBaseDeDatos(); //crea una conexion con la base de datos
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {
            //System.out.println("entra al try al obtener?");
            String ordenSQL = "SELECT * FROM evento WHERE correo like  ?"; //es el sql para obtenerlo
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL); //crea una sentencia preparada de evento

             sentencia.setString(1, correo);

            //System.out.println("que devuelve ordenSQL?" + ordenSQL);
            ResultSet resultado = sentencia.executeQuery(); //ejecuta la orden SQL
            //System.out.println("que devuelve resultado" + resultado);
            while(resultado.next()) //vas leyendo linea a linea del resultado
            {
                //System.out.println("entra al while?");
                Integer idevento = resultado.getInt("idevento");
                //System.out.println("idevento tiene?" + idevento);
                String correo1 = resultado.getString("correo"); //obtiene las columnas de la tabla
                String descripcion = resultado.getString("descripcion");
                String hora = resultado.getString("hora");
                String fecha = resultado.getString("fecha");
                Evento elevento = new Evento(idevento,fecha,descripcion,hora,correo1);
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


    public static boolean borrarEvento(Integer elevento, String correo) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            //System.out.println("se mete al try¿?");
            String ordensql = "DELETE FROM evento WHERE (idevento = ? AND correo like ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            //sentencia.setString(1, Integer.valueOf(e.getIdEvento()));
            sentencia.setString(1, String.valueOf(elevento));
            sentencia.setString(2, String.valueOf(correo));

            int filasafectadas = sentencia.executeUpdate();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }
}
