package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public class ArchivoComentarioUi extends RepositorioFileUi {

    private int alumnoId;
    private String rubroEvaluacionId;

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }
}
