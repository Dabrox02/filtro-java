package com.local.model;

import java.io.Serializable;

public class Mision implements Serializable {

    private int misionId;
    private String descripcionMision;
    private RangoEnum rangoMision;
    private double recompensaMision;

    public Mision() {
    }

    public Mision(int misionId, String descripcionMision, String rangoMision, double recompensaMision) {
        this.misionId = misionId;
        this.descripcionMision = descripcionMision;
        this.rangoMision = RangoEnum.getRangoEnum(rangoMision);
        this.recompensaMision = recompensaMision;
    }

    public int getMisionId() {
        return this.misionId;
    }

    public void setMisionId(int misionId) {
        this.misionId = misionId;
    }

    public String getDescripcionMision() {
        return this.descripcionMision;
    }

    public void setDescripcionMision(String descripcionMision) {
        this.descripcionMision = descripcionMision;
    }

    public RangoEnum getRangoMision() {
        return this.rangoMision;
    }

    public void setRangoMision(String rangoMision) {
        this.rangoMision = RangoEnum.getRangoEnum(rangoMision);
    }

    public double getRecompensaMision() {
        return this.recompensaMision;
    }

    public void setRecompensaMision(double recompensaMision) {
        this.recompensaMision = recompensaMision;
    }

    @Override
    public String toString() {
        return "{" +
                " misionId='" + getMisionId() + "'" +
                ", descripcionMision='" + getDescripcionMision() + "'" +
                ", rangoMision='" + getRangoMision() + "'" +
                ", recompensaMision='" + getRecompensaMision() + "'" +
                "}";
    }

}
