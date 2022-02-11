package com.example.agendafotografica.actividades.clases;

import android.widget.Spinner;

import java.util.Objects;

public class Usuario {

    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private Integer telefono;
    private String rol;




    public Usuario(String correo, String password, String nombre, String apellidos, Integer telefono, String rol) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.rol = rol;
    }

    public Usuario(String correo) {
        this.correo = correo;

    }



    public Usuario(String correo, String password, Integer telefono) {
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
    }

    public Usuario(String correo, String password, String nombre, Integer telefono) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
    }





    @Override
    public String toString() {
        return "Usuario{" +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", rol='" + rol + '\'' +
                '}';
    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
