package com.example.tp2loguin.entidades;

import android.widget.ImageView;

import java.sql.Blob;

public class Usuario {

    private Integer dni;
    private String nombre;
    private String apellido;
    private String rol;
    private String contrasenia;
    private String eMail;
    private byte[] foto;

    public Usuario(Integer dni, String nombre, String apellido, String rol, String contrasenia, String eMail, byte[] foto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.eMail = eMail;
        this.foto =  foto;
    }

    public Usuario( Integer usuario, String nombre, String apellido) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = usuario;
    }
    public Usuario( Integer usuario, String nombre, String apellido, byte[] foto) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = usuario;
        this.foto = foto;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
