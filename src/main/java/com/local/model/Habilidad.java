package com.local.model;

public class Habilidad {
    String nombreHabilidad;
    String descripcionHabilidad;

    public Habilidad(String nombreHabilidad, String descripcionHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
        this.descripcionHabilidad = descripcionHabilidad;
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

}
