package com.example.agendafotografica.actividades.modelos;

public class ConfiguracionDB {

    //public static final String HOSTDB = "10.0.2.2"; //direccion del servidor localhost
    //public static final String HOSTDB = "localhost";
    public static final String HOSTDB = "infsalinas.sytes.net";

    public static final String NOMBREDB = "raul_db"; //nombre de la base datos

    public static final String USUARIODB = "raul"; //nombre usuario

    public static final String CLAVEDB = "salinas_tfg"; //contraseña usuario

    // no funcionan las dos ultimas opciones
    // private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //public static final String PUERTOMYSQL = "3306"; //puerto local
    public static final String PUERTOMYSQL = "10499"; //puerto
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;

    //----------------------------------------------------------....
}
