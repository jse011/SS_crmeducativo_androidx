package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

public class OptionComentario extends NotaUi {
    private String evaluacionId;
    private boolean vigente;

    public OptionComentario() {
    }

    public String getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(String evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isVigente() {
        return vigente;
    }
}
