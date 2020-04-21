package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 16/08/2018.
 */
@Table(database = AppDatabase.class)
public class InstrumentoEvaluacion extends BaseModel {
    @PrimaryKey
    int instrumentoevalid;
    @Column
    String nombre;
    @Column
    String descripcion;
    @Column
    int tipoinstrumentoid;
    @Column
    int entidadid;
    @Column
    int georeferenciaid;
    @Column
    int areaInstrumentoid;



    public int getInstrumentoevalid() {
        return instrumentoevalid;
    }

    public void setInstrumentoevalid(int instrumentoevalid) {
        this.instrumentoevalid = instrumentoevalid;
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

    public int getTipoinstrumentoid() {
        return tipoinstrumentoid;
    }

    public void setTipoinstrumentoid(int tipoinstrumentoid) {
        this.tipoinstrumentoid = tipoinstrumentoid;
    }

    public int getEntidadid() {
        return entidadid;
    }

    public void setEntidadid(int entidadid) {
        this.entidadid = entidadid;
    }

    public int getGeoreferenciaid() {
        return georeferenciaid;
    }

    public void setGeoreferenciaid(int georeferenciaid) {
        this.georeferenciaid = georeferenciaid;
    }

    public int getAreaInstrumentoid() {
        return areaInstrumentoid;
    }

    public void setAreaInstrumentoid(int areaInstrumentoid) {
        this.areaInstrumentoid = areaInstrumentoid;
    }
}
