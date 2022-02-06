package com.example.agendafotografica.actividades.modelos;

public class ConfiguracionDB {

    //public static final String HOSTDB = "10.0.2.2";
    public static final String HOSTDB = "https://infsalinas.sytes.net:10487/";

    public static final String NOMBREDB = "raul_db";

    public static final String USUARIODB = "raul";
    //  public static final String USUARIODB = "damserver1";

    //public static final String CLAVEDB = "1234";
    public static final String CLAVEDB = "salinas_tfg";

    // no funcionan las dos ultimas opciones
    // private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String PUERTOMYSQL = "3306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;

    //----------------------------------------------------------....
}
