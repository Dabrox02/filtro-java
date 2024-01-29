package com.local.model;

import java.time.LocalDate;

public class NinjaMision {

    Ninja ninja;
    Mision mision;
    LocalDate fechaInicio;
    LocalDate fechaFin;


    public NinjaMision() {
    }

    public NinjaMision(Ninja ninja, Mision mision, LocalDate fechaInicio, LocalDate fechaFin) {
        this.ninja = ninja;
        this.mision = mision;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Ninja getNinja() {
        return this.ninja;
    }

    public void setNinja(Ninja ninja) {
        this.ninja = ninja;
    }

    public Mision getMision() {
        return this.mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "{" +
            " ninja='" + getNinja() + "'" +
            ", mision='" + getMision() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            "}";
    }
    


}
