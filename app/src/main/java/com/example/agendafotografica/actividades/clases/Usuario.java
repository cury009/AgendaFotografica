package com.example.agendafotografica.actividades.clases;

public class Usuario {

    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private Integer telefono;

    public Usuario(String correo, String password, String nombre, String apellidos, Integer telefono) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
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
}
