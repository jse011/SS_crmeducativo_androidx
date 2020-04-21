package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 15/12/2017.
 */
//Tabla para borrar en la limpiza de codigo
@Table(database = AppDatabase.class)
public class SesionAprendizajeRelacionEquipo extends BaseModel {
    @PrimaryKey
    @Column
    private int sesionAprendizajeId;
    @Column
    private int equipoId;
    @Column
    private int equipoIdAndroid;

    public SesionAprendizajeRelacionEquipo() {
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }
    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public int getEquipoIdAndroid() {
        return equipoIdAndroid;
    }

    public void setEquipoIdAndroid(int equipoIdAndroid) {
        this.equipoIdAndroid = equipoIdAndroid;
    }
}
