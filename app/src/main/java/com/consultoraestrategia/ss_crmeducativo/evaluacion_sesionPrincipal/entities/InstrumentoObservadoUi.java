package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 15/08/2018.
 */
@Parcel
public class InstrumentoObservadoUi {
    public String key;
    public int personaId;
    public String fechaEvaluacion;

    public InstrumentoObservadoUi() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
}
