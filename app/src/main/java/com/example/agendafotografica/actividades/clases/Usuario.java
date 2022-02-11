package com.example.agendafotografica.actividades.clases;

import android.widget.Spinner;

import java.util.Objects;

public class Usuario {

    private int idUsuario;
    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private Integer telefono;
    private String rol;


    public Usuario(String correo) {

        this.correo = correo;

    }

    public Usuario(String correo, String password, String nombre, String apellidos, Integer telefono, String rol) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.rol = rol;
    }

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int idUsuario, String correo) {
        this.idUsuario = idUsuario;
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

    public Usuario(String correo, String password, String nombre, String apellidos, int phone, Spinner spinner) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", rol='" + rol + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
