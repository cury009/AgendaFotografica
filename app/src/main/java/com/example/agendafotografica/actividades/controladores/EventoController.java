package com.example.agendafotografica.actividades.controladores;

import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.tareas.TareaInsertarEvento;
import com.example.agendafotografica.actividades.tareas.TareaInsertarUsuario;
import com.example.agendafotografica.actividades.tareas.TareaObtenerEventos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EventoController {

    //---------------------------------------------INsertar Evento
    public static boolean insertarEvento(Evento e) {
        FutureTask t = new FutureTask(new TareaInsertarEvento(e));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        System.out.println("FutureTask: " + t); //Devuelve java.util.concurrent.FutureTask etc etc etc --> si lo devuelve
        boolean insercionOK = false;
        System.out.println("insercion ok: " + insercionOK); //false
        try {
            insercionOK = (boolean) t.get(); //posible linea que falle
            System.out.println("entra al try?: " + insercionOK); //deberia verse true --> entra como false
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException ex) {
                es.shutdownNow();
            }
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {

            System.out.println("devuelver insercionOK:   "+insercionOK); //muestra false pero deberia mostrar true
            return insercionOK;


        }


    }
    //------------------------------------------------------------------- obtener Evento
    public static ArrayList<Evento> obtenereventos() {
        ArrayList<Evento> eventos = null;
        FutureTask t = new FutureTask (new TareaObtenerEventos()); //crear tarea
        ExecutorService es = Executors.newSingleThreadExecutor(); //crear un hilo de ejecucion
        es.submit(t); //lo lanzas
        try {
            eventos= (ArrayList<Evento>)t.get(); //obtiene el resultado de la tarea (son los cursos)
            es.shutdown();//apagas el hilo que hhas lanzado anteriormente
            try { //si no se apaga bien
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow(); //apagar inmediatamente
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return eventos; //devuelve eventos
    }
}
