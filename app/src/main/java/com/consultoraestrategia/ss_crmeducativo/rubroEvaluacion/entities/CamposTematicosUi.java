package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

/**
 * Created by kike on 25/04/2018.
 */

public class CamposTematicosUi {
    private int campoTematicoId;
    private String titulo;

    public CamposTematicosUi(int campoTematicoId, String titulo) {
        this.campoTematicoId = campoTematicoId;
        this.titulo = titulo;
    }

    public int getCampoTematicoId() {
        return campoTematicoId;
    }

    public void setCampoTematicoId(int campoTematicoId) {
        this.campoTematicoId = campoTematicoId;
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
