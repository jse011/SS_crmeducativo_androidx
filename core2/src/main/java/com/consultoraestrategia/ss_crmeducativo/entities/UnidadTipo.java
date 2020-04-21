package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 27/07/2017.
 */

@Table(database = AppDatabase.class)
public class UnidadTipo extends BaseRelEntity {

    @PrimaryKey
    private int unidadAprendizajeId;
    @PrimaryKey
    private int tipoId;

    public UnidadTipo() {
    }

    public UnidadTipo(int unidadAprendizajeId, int tipoId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
        this.tipoId = tipoId;
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
}
