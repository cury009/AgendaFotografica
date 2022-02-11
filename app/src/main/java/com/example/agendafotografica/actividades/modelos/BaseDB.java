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

    /*
    public static final String HOSTDB = "127.0.0.1"; //direccion del servidor
    public static final String NOMBREDB = "raul_db"; //nombre de la base datos
    public static final String USUARIODB = "raul"; //nombre usuario
    public static final String CLAVEDB = "salinas_tfg"; //contraseña usuario
    public static final String PUERTOMYSQL = "3306"; //puerto
    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;
    */

    //----------------------------------------------------------  conectar con base de datos

    /*
    protected static Connection conectarConBaseDeDatos(String url, String usuariodb, String clavedb) {
        try {
            Connection conexion = DriverManager.getConnection(url, usuariodb, clavedb);
            return conexion;
        } catch (SQLException e) {
            System.out.println("no se pudo establecer la conexion con la base de datos");
            return null;
        }
    }
    */


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
