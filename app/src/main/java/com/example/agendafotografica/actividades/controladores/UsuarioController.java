package com.example.agendafotografica.actividades.controladores;

import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.modelos.BaseDB;
import com.example.agendafotografica.actividades.tareas.TareaInsertarUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class UsuarioController {

    /*
    public static boolean insertarUsuario(Usuario u)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "INSERT INTO `raul_db`.`usuario` (`correo`, `password`,`nombre`,`apellidos`,`telefono`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setString(1,u.getCorreo());
            sentencia.setString(2,u.getPassword());
            sentencia.setString(3,u.getNombre());
            sentencia.setString(4,u.getApellidos());
            sentencia.setInt(5,u.getTelefono());
            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas != 0)
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
*/

    public static boolean insertarUsuario(Usuario u) {
        FutureTask t = new FutureTask(new TareaInsertarUsuario(u));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
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
}
