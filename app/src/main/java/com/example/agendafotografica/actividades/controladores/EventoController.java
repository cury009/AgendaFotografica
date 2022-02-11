package com.example.agendafotografica.actividades.controladores;

import com.example.agendafotografica.actividades.clases.Evento;
import com.example.agendafotografica.actividades.tareas.TareaInsertarEvento;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EventoController {

    public static boolean insertarEvento(Evento e) {
        FutureTask t = new FutureTask(new TareaInsertarEvento(e));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);


        boolean insercionOK = false;

        System.out.println("insercionOk: " + insercionOK);
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException exception) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException exeption) {
            exeption.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        finally {
            System.out.println("EventoController funciona?" + insercionOK); //tiene que devolver true, devuelve false
            return insercionOK;
        }
    }
}
