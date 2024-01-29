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

    public static RangoEnum getRangoEnum(String rangoEnum) {
        if (rangoEnum.equalsIgnoreCase("genin")) {
            return RangoEnum.GENIN;
        }
        if (rangoEnum.equalsIgnoreCase("chunin")) {
            return RangoEnum.CHUNIN;
        }
        if (rangoEnum.equalsIgnoreCase("jonin")) {
            return RangoEnum.JONIN;
        }
        if (rangoEnum.equalsIgnoreCase("shinobi")) {
            return RangoEnum.SHINOBI;
        }
        if (rangoEnum.equalsIgnoreCase("kage")) {
            return RangoEnum.KAGE;
        }
        return null;
    }
}
