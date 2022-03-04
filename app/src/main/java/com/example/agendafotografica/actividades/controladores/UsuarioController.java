package com.example.agendafotografica.actividades.controladores;

import android.util.Log;

import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.modelos.BaseDB;
import com.example.agendafotografica.actividades.tareas.TareaInsertarUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class UsuarioController {
    
    public static boolean insertarUsuario(Usuario u) {
        FutureTask t = new FutureTask(new TareaInsertarUsuario(u));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        System.out.println("FutureTask: " + t);
        boolean insercionOK = false;
        System.out.println("insercion ok: " + t);
        try {
            insercionOK = (boolean) t.get();
            System.out.println("entra al try?: " + insercionOK);
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {


            return insercionOK;

        }
    }
    /*
    public static ArrayList<Evento> obtenerRol(String correo) {
        Connection conexion = BaseDB.conectarConBaseDeDatos(); //crea una conexion con la base de datos
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {
            //System.out.println("entra al try al obtener?");
            String ordenSQL = "SELECT rol FROM usuario WHERE correo like  ?"; //es el sql para obtenerlo
            //String ordenSQL = "SELECT * FROM evento WHERE fecha AND hora like  ?, ? "; //es el sql para obtenerlo
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

     */




}
