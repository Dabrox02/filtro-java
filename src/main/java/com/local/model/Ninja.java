package com.local.model;

import java.util.ArrayList;
import java.util.List;

public class Ninja {

    private int ninjaId;
    private String nombreNinja;
    private RangoEnum rangoNinja;
    private String aldeaNinja;
    private List<Habilidad> habilidades;

    public Ninja() {
    }

    public Ninja(int ninjaId, String nombreNinja, String rangoNinja, String aldeaNinja) {
        this.ninjaId = ninjaId;
        this.nombreNinja = nombreNinja;
        this.rangoNinja = RangoEnum.getRangoEnum(rangoNinja);
        this.aldeaNinja = aldeaNinja;
        this.habilidades = new ArrayList<>();
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

    public void setRangoNinja(String rangoNinja) {
        this.rangoNinja = RangoEnum.getRangoEnum(rangoNinja);
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

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public void addHabilidad(Habilidad habilidad) {
        this.habilidades.add(habilidad);
    }

    @Override
    public String toString() {
        return "============= \n Datos Ninja: {" +
                "\n ninjaId='" + getNinjaId() + "'" +
                "\n nombreNinja='" + getNombreNinja() + "'" +
                "\n rangoNinja='" + getRangoNinja() + "'" +
                "\n aldeaNinja='" + getAldeaNinja() + "'" +
                "\n habilidades='" + getHabilidades() + "'" +
                "\n } \n ==================";
    }

}
