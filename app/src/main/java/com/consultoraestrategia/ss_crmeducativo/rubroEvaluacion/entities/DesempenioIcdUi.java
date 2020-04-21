package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

/**
 * Created by kike on 25/04/2018.
 */

public class DesempenioIcdUi {
    private int desempenioIcdId;
    private String titulo;

    public DesempenioIcdUi(int desempenioIcdId, String titulo) {
        this.desempenioIcdId = desempenioIcdId;
        this.titulo = titulo;
    }

    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "" + titulo + "";
    }
}
