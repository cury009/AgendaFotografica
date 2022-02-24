package com.example.agendafotografica.actividades.controladores;

import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.tareas.TareaInsertarUsuario;

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


    /*public static String obtenerPerfil(String micorreo) {

    }

     */

}
