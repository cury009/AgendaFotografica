package com.example.agendafotografica.actividades.modelos;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class BaseDB {

    //----------------------------------------------------------  conectar con base de datos
    public static Connection conectarConBaseDeDatos() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conexion = DriverManager.getConnection(ConfiguracionDB.URLMYSQL, ConfiguracionDB.USUARIODB, ConfiguracionDB.CLAVEDB);
            Log.i("conexion","conexion establecida");
            return conexion;
        } catch (SQLException e) {
            Log.i("conexion","no se pudo conectar con la base de datos");
            Log.i("conexion", "la cadena de conexion url es " + ConfiguracionDB.URLMYSQL);
            // System.out.println("no se pudo establecer la conexion con la base de datos");

            return null;
        }
    }


}
