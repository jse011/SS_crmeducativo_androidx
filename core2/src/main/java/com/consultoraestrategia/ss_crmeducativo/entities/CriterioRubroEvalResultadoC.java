package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by CCIE on 04/05/2018.
 */
@Table(database = AppDatabase.class)
public class CriterioRubroEvalResultadoC extends BaseModel {
    @PrimaryKey
    public int criteriosEvaluacionId;
    @Column
    public int rubroEvalResultadoId;
    @Column
    public String valorTipoNotaId;

    @Column
    public String descripcion;

    public int getCriteriosEvaluacionId() {
        return criteriosEvaluacionId;
    }

    public void setCriteriosEvaluacionId(int criteriosEvaluacionId) {
        this.criteriosEvaluacionId = criteriosEvaluacionId;
    }

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
