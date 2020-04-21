package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

import java.util.List;

public class CampoAccionUi {

    public enum Tipo{PADRE, HIJO}

    private int campoAccionId;
    private String campoAccionNombre;
    private List<CampoAccionUi> campoAccionUis;
    private Tipo tipo;

    public CampoAccionUi() {
    }

    public int getCampoAccionId() {
        return campoAccionId;
    }

    public void setCampoAccionId(int campoAccionId) {
        this.campoAccionId = campoAccionId;
    }

    public String getCampoAccionNombre() {
        return campoAccionNombre;
    }

    public void setCampoAccionNombre(String campoAccionNombre) {
        this.campoAccionNombre = campoAccionNombre;
    }

    public List<CampoAccionUi> getCampoAccionUis() {
        return campoAccionUis;
    }

    public void setCampoAccionUis(List<CampoAccionUi> campoAccionUis) {
        this.campoAccionUis = campoAccionUis;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "CampoAccionUi{" +
                "campoAccionId=" + campoAccionId +
                ", campoAccionNombre='" + campoAccionNombre + '\'' +
                ", campoAccionUis=" + campoAccionUis +
                ", tipo=" + tipo +
                '}';
    }
}
