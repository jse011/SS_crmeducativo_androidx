package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class UnidadEventoCompetenciaDesempenioIcdInstrumento  extends BaseModel {

    @PrimaryKey
    @Column
    private int unidadCompetenciaDesempenioIcdInstrumentoId;

    @Column
    private int instrumentoEvalId;

    @Column
    private int unidadCompetenciaDesempenioIcdId;

    @Column
    private int unidadCompetenciaDesempenioIcdInstrumentoBaseId;


    public int getUnidadCompetenciaDesempenioIcdInstrumentoId() {
        return unidadCompetenciaDesempenioIcdInstrumentoId;
    }

    public void setUnidadCompetenciaDesempenioIcdInstrumentoId(int unidadCompetenciaDesempenioIcdInstrumentoId) {
        this.unidadCompetenciaDesempenioIcdInstrumentoId = unidadCompetenciaDesempenioIcdInstrumentoId;
    }

    public int getInstrumentoEvalId() {
        return instrumentoEvalId;
    }

    public void setInstrumentoEvalId(int instrumentoEvalId) {
        this.instrumentoEvalId = instrumentoEvalId;
    }

    public int getUnidadCompetenciaDesempenioIcdId() {
        return unidadCompetenciaDesempenioIcdId;
    }

    public void setUnidadCompetenciaDesempenioIcdId(int unidadCompetenciaDesempenioIcdId) {
        this.unidadCompetenciaDesempenioIcdId = unidadCompetenciaDesempenioIcdId;
    }

    public int getUnidadCompetenciaDesempenioIcdInstrumentoBaseId() {
        return unidadCompetenciaDesempenioIcdInstrumentoBaseId;
    }

    public void setUnidadCompetenciaDesempenioIcdInstrumentoBaseId(int unidadCompetenciaDesempenioIcdInstrumentoBaseId) {
        this.unidadCompetenciaDesempenioIcdInstrumentoBaseId = unidadCompetenciaDesempenioIcdInstrumentoBaseId;
    }
}
