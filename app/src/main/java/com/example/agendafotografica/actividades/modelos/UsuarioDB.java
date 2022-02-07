package com.example.agendafotografica.actividades.modelos;

import com.example.agendafotografica.actividades.clases.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDB {

    public static boolean insertarUsuarioTabla(Usuario u)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO usuario (correo, password, nombre, apellidos, telefono) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, u.getCorreo());
            pst.setString(2, u.getPassword());
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
