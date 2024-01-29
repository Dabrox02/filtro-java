package com.local.model;

public enum RangoEnum {
    GENIN("genin"), CHUNIN("chunin"), JONIN("jonin"), SHINOBI("shinobi"), KAGE("kage");

    private String nombreRango;

    private RangoEnum(String nombreRango) {
        this.nombreRango = nombreRango;
    }

    public String getNombreRango() {
        return nombreRango;
    }

}
