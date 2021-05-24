package com.example.roomieapp;
public class Departamento {
    private String Costo;
    private String Descripcion;
    private String Lugar;
    private String Municipio;
    private String Nombre;

    public Departamento() {
    }

    public Departamento(String costo, String descripcion, String lugar, String municipio, String nombre) {
        Costo = costo;
        Descripcion = descripcion;
        Lugar = lugar;
        Municipio = municipio;
        Nombre = nombre;
    }

    public String getCosto() {
        return Costo;
    }

    public void setCosto(String costo) {
        Costo = costo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
