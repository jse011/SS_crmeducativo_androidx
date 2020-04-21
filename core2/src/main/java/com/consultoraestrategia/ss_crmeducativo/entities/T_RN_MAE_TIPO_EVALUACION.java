package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 13/02/2018.
 */
@Table(database = AppDatabase.class)
public class T_RN_MAE_TIPO_EVALUACION extends BaseModel {

    @PrimaryKey
    int tipoEvaluacionId;
    @Column
    String nombre;
    @Column
    boolean estado;

    public T_RN_MAE_TIPO_EVALUACION() {
    }

    public int getTipoEvaluacionId() {
        return tipoEvaluacionId;
    }

    public void setTipoEvaluacionId(int tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
