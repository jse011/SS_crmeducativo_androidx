package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = AppDatabase.class)
public class DimensionDesarrolloDetalle extends BaseModel {

    @PrimaryKey
    @Column
    private int dimensionDesarrolloDetId;

    @Column
    private int dimensionDesarrolloId;

    @Column
    private int cursoId;


    public int getDimensionDesarrolloDetId() {
        return dimensionDesarrolloDetId;
    }

    public void setDimensionDesarrolloDetId(int dimensionDesarrolloDetId) {
        this.dimensionDesarrolloDetId = dimensionDesarrolloDetId;
    }

    public int getDimensionDesarrolloId() {
        return dimensionDesarrolloId;
    }

    public void setDimensionDesarrolloId(int dimensionDesarrolloId) {
        this.dimensionDesarrolloId = dimensionDesarrolloId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }
}
