package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 26/01/2018.
 */
@Table(database = AppDatabase.class)
public class SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica extends BaseModel {
    @PrimaryKey
    @Column
    private int sesionDempenioIcdInstrumentoId;
    @Column
    private int tecnicaEvaluacionId;

    public SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica() {
    }

    public int getSesionDempenioIcdInstrumentoId() {
        return sesionDempenioIcdInstrumentoId;
    }

    public void setSesionDempenioIcdInstrumentoId(int sesionDempenioIcdInstrumentoId) {
        this.sesionDempenioIcdInstrumentoId = sesionDempenioIcdInstrumentoId;
    }

    public int getTecnicaEvaluacionId() {
        return tecnicaEvaluacionId;
    }

    public void setTecnicaEvaluacionId(int tecnicaEvaluacionId) {
        this.tecnicaEvaluacionId = tecnicaEvaluacionId;
    }
}
