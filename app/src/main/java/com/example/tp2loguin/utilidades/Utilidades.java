package com.example.tp2loguin.utilidades;

public class Utilidades {

    //constantes campos de la tabla de usuarios
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_DNI="dni";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_APELLIDO="apellido";
    public static final String CAMPO_ROL="rol";
    public static final String CAMPO_CONTRASENIA="contrasenia";
    public static final String CAMPO_EMAIL="eMail";
    public static final String CAMPO_FOTO="foto";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_DNI+" INTEGER, "+CAMPO_NOMBRE+" STRING, "+CAMPO_APELLIDO+" STRING, "+CAMPO_ROL+" STRING, "+CAMPO_CONTRASENIA+" STRING, "+CAMPO_EMAIL+" STRING, "+CAMPO_FOTO+" IMAGEVIEW)";





}
