package com.example.tl01e117.Configuracion;

public class Transacciones {
    // Nombre de la bd
    public static final String NameDatabase = "Agenda";

    // Creacion de tabla y objetos
    public static final String tablacontactos = "Contactos";

    /* Campos de la tabla personas */
    public static String id = "id";
    public static String nombre = "nombre";
    public static String notas = "notas";
    public static String numero = "numero";
    public static String codigo = "codigo";

    // Consultas SQL DDL
    public static String CreateTBContactos = "CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEX, numero INTEGER, notas TEXT, codigo TEXT )";

    public static String DropTBContactos = "DROP TABLE IF EXISTS personas";
}