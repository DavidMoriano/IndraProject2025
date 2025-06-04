package com.entities;

public class Ubicacion {
    private int id_ubicacion;
    private String tipo;
    private String direccion;

    public Ubicacion() {
        super();
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-50s", this.id_ubicacion, this.tipo, this.direccion);
    }

    public static String getHeader() {
        return String.format("%-10s %-25s %-50s", "Id", "Tipo", "Dirección");
    }

}
