package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import java.io.Serializable;

public class PrecisionUi implements Serializable {
    private String contenido;
    private String contornoColor;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContornoColor() {
        return contornoColor;
    }

    public void setContornoColor(String contornoColor) {
        this.contornoColor = contornoColor;
    }
}
