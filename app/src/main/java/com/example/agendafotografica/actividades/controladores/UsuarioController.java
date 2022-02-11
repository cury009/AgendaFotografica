package com.example.agendafotografica.actividades.controladores;

import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.modelos.BaseDB;
import com.example.agendafotografica.actividades.tareas.TareaInsertarUsuario;
import com.example.agendafotografica.actividades.tareas.TareaObtenerUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarioDevueltas = null;
        FutureTask t = new FutureTask ((Callable) new TareaObtenerUsuario());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            usuarioDevueltas= (ArrayList<Usuario>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return usuarioDevueltas;
    }
}
