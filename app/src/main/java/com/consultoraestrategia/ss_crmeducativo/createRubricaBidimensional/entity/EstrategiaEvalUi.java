package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

public class EstrategiaEvalUi {

    private int estrategiaId;
    private String estrategia;
    private String descripcionRubro;
    private String tituloRubro;


    public int getEstrategiaId() {
        return estrategiaId;
    }

    public void setEstrategiaId(int estrategiaId) {
        this.estrategiaId = estrategiaId;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public String getDescripcionRubro() {
        return descripcionRubro;
    }

    public void setDescripcionRubro(String descripcionRubro) {
        this.descripcionRubro = descripcionRubro;
    }

    @Override
    public String toString() {
        return estrategia+ " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstrategiaEvalUi)) return false;

        EstrategiaEvalUi that = (EstrategiaEvalUi) o;

        return estrategiaId == that.estrategiaId;
    }

    @Override
    public int hashCode() {
        return estrategiaId;
    }

    public void setTituloRubro(String tituloRubro) {
        this.tituloRubro = tituloRubro;
    }

    public String getTituloRubro() {
        return tituloRubro;
    }
}
