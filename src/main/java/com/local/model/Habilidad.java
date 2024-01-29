package com.local.model;

public class Habilidad {

    private int ninjaId;
    private String nombreHabilidad;
    private String descripcionHabilidad;

    public Habilidad(String nombreHabilidad, String descripcionHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
        this.descripcionHabilidad = descripcionHabilidad;
    }

    public int getNinjaId() {
        return this.ninjaId;
    }

    public void setNinjaId(int ninjaId) {
        this.ninjaId = ninjaId;
    }

    public String getNombreHabilidad() {
        return this.nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    public String getDescripcionHabilidad() {
        return this.descripcionHabilidad;
    }

    public void setDescripcionHabilidad(String descripcionHabilidad) {
        this.descripcionHabilidad = descripcionHabilidad;
    }

    @Override
    public String toString() {
        return "{" +
                " nombreHabilidad='" + getNombreHabilidad() + "'" +
                ", descripcionHabilidad='" + getDescripcionHabilidad() + "'" +
                "}";
    }

}
