package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 12/02/2018.
 */
@Table(database = AppDatabase.class)
public class T_GC_REL_UNIDAD_APREN_EVENTO_TIPO extends BaseRelEntity {

    @PrimaryKey
    private int unidadaprendizajeId;
    @PrimaryKey
    private int tipoid;

    public T_GC_REL_UNIDAD_APREN_EVENTO_TIPO() {
    }

    public int getUnidadaprendizajeId() {
        return unidadaprendizajeId;
    }

    public void setUnidadaprendizajeId(int unidadaprendizajeId) {
        this.unidadaprendizajeId = unidadaprendizajeId;
    }

    public int getTipoid() {
        return tipoid;
    }

    public void setTipoid(int tipoid) {
        this.tipoid = tipoid;
    }
}
