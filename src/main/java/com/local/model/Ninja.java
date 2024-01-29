package com.local.model;

import java.util.List;

public class Ninja {

    private int ninjaId;
    private String nombreNinja;
    private RangoEnum rangoNinja;
    private String aldeaNinja;
    private List<Habilidad> habilidades;

    public Ninja() {
    }

    public Ninja(int ninjaId, String nombreNinja, RangoEnum rangoNinja, String aldeaNinja,
            List<Habilidad> habilidades) {
        this.ninjaId = ninjaId;
        this.nombreNinja = nombreNinja;
        this.rangoNinja = rangoNinja;
        this.aldeaNinja = aldeaNinja;
        this.habilidades = habilidades;
    }

    public int getNinjaId() {
        return this.ninjaId;
    }

    public void setNinjaId(int ninjaId) {
        this.ninjaId = ninjaId;
    }

    public String getNombreNinja() {
        return this.nombreNinja;
    }

    public void setNombreNinja(String nombreNinja) {
        this.nombreNinja = nombreNinja;
    }

    public RangoEnum getRangoNinja() {
        return this.rangoNinja;
    }

    public void setRangoNinja(RangoEnum rangoNinja) {
        this.rangoNinja = rangoNinja;
    }

    public String getAldeaNinja() {
        return this.aldeaNinja;
    }

    public void setAldeaNinja(String aldeaNinja) {
        this.aldeaNinja = aldeaNinja;
    }

    public List<Habilidad> getHabilidades() {
        return this.habilidades;
    }

    public void addHabilidad(Habilidad habilidad) {
        this.habilidades.add(habilidad);
    }

    @Override
    public String toString() {
        return "{" +
                " ninjaId='" + getNinjaId() + "'" +
                ", nombreNinja='" + getNombreNinja() + "'" +
                ", rangoNinja='" + getRangoNinja() + "'" +
                ", aldeaNinja='" + getAldeaNinja() + "'" +
                ", habilidades='" + getHabilidades() + "'" +
                "}";
    }

}
