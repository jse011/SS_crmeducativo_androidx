package com.consultoraestrategia.ss_crmeducativo.entities;


import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica extends BaseModel {

    @PrimaryKey
    @Column
    private int unidadCompetenciaDesempenioIcdInstrumentoId;

    @Column
    private int tecnicaEvaluacionId;

    public int getUnidadCompetenciaDesempenioIcdInstrumentoId() {
        return unidadCompetenciaDesempenioIcdInstrumentoId;
    }

    public void setUnidadCompetenciaDesempenioIcdInstrumentoId(int unidadCompetenciaDesempenioIcdInstrumentoId) {
        this.unidadCompetenciaDesempenioIcdInstrumentoId = unidadCompetenciaDesempenioIcdInstrumentoId;
    }

    public int getTecnicaEvaluacionId() {
        return tecnicaEvaluacionId;
    }

    public void setTecnicaEvaluacionId(int tecnicaEvaluacionId) {
        this.tecnicaEvaluacionId = tecnicaEvaluacionId;
    }
}
