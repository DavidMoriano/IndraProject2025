package com.entities;

public class Organizador {
    private int id_organizadores;
    private String nombre; //Posible nombre de usuario
    private String informacion_contacto; //Posible email o usuario

    public Organizador() {
        super();
    }

    public int getId_organizadores() {
        return id_organizadores;
    }

    public void setId_organizadores(int id_organizadores) {
        this.id_organizadores = id_organizadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion_contacto() {
        return informacion_contacto;
    }

    public void setInformacion_contacto(String informacion_contacto) {
        this.informacion_contacto = informacion_contacto;
    }

    @Override
    public String toString() {
        return "Organizadores [id_organizadores=" + id_organizadores + ", nombre=" + nombre + ", informacion_contacto="
                + informacion_contacto + "]";
    }

}