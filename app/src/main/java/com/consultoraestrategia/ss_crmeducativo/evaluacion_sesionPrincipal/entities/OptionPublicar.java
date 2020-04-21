package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

public class OptionPublicar extends NotaUi {
    private String evaluacionId;
    private boolean selected;
    private boolean vigente;

    public OptionPublicar() {
    }

    public String getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(String evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isVigente() {
        return vigente;
    }
}
