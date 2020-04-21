package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 15/08/2018.
 */
@Table(database = AppDatabase.class)
public class InstrumentoObservado extends BaseModel {
    @PrimaryKey
    public String instrumentoObservadoId;
    @Column
    public int personaId;
    @Column
    public long fechaEvaluacion;
    @Column
    public int usuarioId;
    @Column
    public int instrumentoEvalId;
    @Column
    public String unidadAnalisis;
    @Column
    public String hecho;
    @Column
    public String poblacion;
    @Column
    public int objetoId;

    public String getInstrumentoObservadoId() {
        return instrumentoObservadoId;
    }

    public void setInstrumentoObservadoId(String instrumentoObservadoId) {
        this.instrumentoObservadoId = instrumentoObservadoId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public long getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(long fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getInstrumentoEvalId() {
        return instrumentoEvalId;
    }

    public void setInstrumentoEvalId(int instrumentoEvalId) {
        this.instrumentoEvalId = instrumentoEvalId;
    }

    public String getUnidadAnalisis() {
        return unidadAnalisis;
    }

    public void setUnidadAnalisis(String unidadAnalisis) {
        this.unidadAnalisis = unidadAnalisis;
    }

    public String getHecho() {
        return hecho;
    }

    public void setHecho(String hecho) {
        this.hecho = hecho;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public int getObjetoId() {
        return objetoId;
    }

    public void setObjetoId(int objetoId) {
        this.objetoId = objetoId;
    }
}
