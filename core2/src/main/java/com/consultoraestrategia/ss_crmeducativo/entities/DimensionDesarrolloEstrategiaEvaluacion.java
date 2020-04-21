package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = AppDatabase.class)
public class DimensionDesarrolloEstrategiaEvaluacion extends BaseModel {

    @PrimaryKey
    @Column
    private int estrategiaEvaluacionId;
    @Column
    private int dimensionDesarrolloId;

    public int getEstrategiaEvaluacionId() {
        return estrategiaEvaluacionId;
    }

    public void setEstrategiaEvaluacionId(int estrategiaEvaluacionId) {
        this.estrategiaEvaluacionId = estrategiaEvaluacionId;
    }

    public int getDimensionDesarrolloId() {
        return dimensionDesarrolloId;
    }

    public void setDimensionDesarrolloId(int dimensionDesarrolloId) {
        this.dimensionDesarrolloId = dimensionDesarrolloId;
    }
}
