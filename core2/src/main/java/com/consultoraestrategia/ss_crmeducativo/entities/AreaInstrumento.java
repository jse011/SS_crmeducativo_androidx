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
public class AreaInstrumento extends BaseModel {
    @PrimaryKey
    public int areaInstrumentoId;
    @Column
    public int parentId;
    @Column
    public String nombre;
    @Column
    public String descripcion;


    public int getAreaInstrumentoId() {
        return areaInstrumentoId;
    }

    public void setAreaInstrumentoId(int areaInstrumentoId) {
        this.areaInstrumentoId = areaInstrumentoId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
