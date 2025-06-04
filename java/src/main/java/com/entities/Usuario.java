package com.entities;

public class Usuario {
    private int id_usuario;
    private String nombre;
    private String email;
    private String password;

    public Usuario() {
        super();
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id_usuario=" + id_usuario + ", nombre=" + nombre + ", email=" + email + ", password=" + password
                + "]";
    }

}
