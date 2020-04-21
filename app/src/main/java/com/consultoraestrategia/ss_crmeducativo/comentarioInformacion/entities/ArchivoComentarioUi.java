package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public class ArchivoComentarioUi extends RepositorioFileUi {

    private String evaluacionId;

    public String getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(String evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

}
