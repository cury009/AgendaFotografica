package com.example.agendafotografica.actividades.tareas;

import com.example.agendafotografica.actividades.clases.Usuario;
import com.example.agendafotografica.actividades.modelos.UsuarioDB;

import java.util.ArrayList;

public class TareaObtenerUsuario {


    public ArrayList<Usuario> call() throws Exception {
        ArrayList<Usuario> categorias = UsuarioDB.obtenerUsuario();
        return categorias;
    }
}
