package com.local.model;

public class Mision {

    private int misionId;
    private String descripcionMision;
    private RangoEnum rangoMision;
    private double recompensaMision;

    public Mision() {
    }

    public Mision(int misionId, String descripcionMision, RangoEnum rangoMision, double recompensaMision) {
        this.misionId = misionId;
        this.descripcionMision = descripcionMision;
        this.rangoMision = rangoMision;
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

    public void setRangoMision(RangoEnum rangoMision) {
        this.rangoMision = rangoMision;
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
