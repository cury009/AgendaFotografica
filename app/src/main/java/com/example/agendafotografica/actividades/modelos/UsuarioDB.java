package com.example.agendafotografica.actividades.modelos;

import android.util.Log;

import com.example.agendafotografica.actividades.clases.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDB {

    //metodo insertarUsuarioTabla
    public static boolean insertarUsuarioTabla(Usuario u)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO usuario (correo, rol, nombre, apellidos, telefono) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, u.getCorreo());
            pst.setString(2, u.getRol());
            pst.setString(3, u.getNombre());
            pst.setString(4, u.getApellidos());
            pst.setInt(5, u.getTelefono());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
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
