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
public class DimensionObservada extends BaseModel {
    @PrimaryKey
    public String dimensionObservadaId;
    @Column
    public String instrumentoObservadaId;
    @Column
    public int dimensionId;
    @Column
    public double valor;

    public String getDimensionObservadaId() {
        return dimensionObservadaId;
    }

    public void setDimensionObservadaId(String dimensionObservadaId) {
        this.dimensionObservadaId = dimensionObservadaId;
    }

    public String getInstrumentoObservadaId() {
        return instrumentoObservadaId;
    }

    public void setInstrumentoObservadaId(String instrumentoObservadaId) {
        this.instrumentoObservadaId = instrumentoObservadaId;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
