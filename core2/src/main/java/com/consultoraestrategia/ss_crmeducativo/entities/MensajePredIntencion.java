package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 09/08/2018.
 */
@Table(database = AppDatabase.class)
public class MensajePredIntencion extends BaseRelEntity {
    @PrimaryKey
    private String mensajePredeterminadoId;
    @PrimaryKey
    private int intencionId;

    public MensajePredIntencion() {
    }

    public String getMensajePredeterminadoId() {
        return mensajePredeterminadoId;
    }

    public void setMensajePredeterminadoId(String mensajePredeterminadoId) {
        this.mensajePredeterminadoId = mensajePredeterminadoId;
    }

    public int getIntencionId() {
        return intencionId;
    }

    public void setIntencionId(int intencionId) {
        this.intencionId = intencionId;
    }
}
