package com.example.agendafotografica.actividades.tareas;

import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.modelos.UsuarioDB;

import java.util.concurrent.Callable;

public class TareaInsertarUsuario implements Callable<Boolean> {
    private Usuario u = null;

    public TareaInsertarUsuario(Usuario u) {
        this.u = u;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = UsuarioDB.insertarUsuarioTabla(u);
        return insertadoOK;
    }
}
