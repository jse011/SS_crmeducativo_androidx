package com.consultoraestrategia.ss_crmeducativo.entities;


import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by CCIE on 24/01/2018.
 */

@Table(database = AppDatabase.class)
public class SilaboEventoDesempenioIcdCampotematico extends BaseModel {
    @Unique
    @PrimaryKey
    private int silaboeventodesempenioicdcampotematico;
    @Column
    private int silabocompetenciadesempenioicdId;
    @Column
    private int campotematicoId;

    public SilaboEventoDesempenioIcdCampotematico() {
    }

    public SilaboEventoDesempenioIcdCampotematico(int silaboeventodesempenioicdcampotematico, int silabocompetenciadesempenioicdId, int campotematicoId) {
        this.silaboeventodesempenioicdcampotematico = silaboeventodesempenioicdcampotematico;
        this.silabocompetenciadesempenioicdId = silabocompetenciadesempenioicdId;
        this.campotematicoId = campotematicoId;
    }

    public int getSilaboeventodesempenioicdcampotematico() {
        return silaboeventodesempenioicdcampotematico;
    }

    public void setSilaboeventodesempenioicdcampotematico(int silaboeventodesempenioicdcampotematico) {
        this.silaboeventodesempenioicdcampotematico = silaboeventodesempenioicdcampotematico;
    }

    public int getSilabocompetenciadesempenioicdId() {
        return silabocompetenciadesempenioicdId;
    }

    public void setSilabocompetenciadesempenioicdId(int silabocompetenciadesempenioicdId) {
        this.silabocompetenciadesempenioicdId = silabocompetenciadesempenioicdId;
    }

    public int getCampotematicoId() {
        return campotematicoId;
    }

    public void setCampotematicoId(int campotematicoId) {
        this.campotematicoId = campotematicoId;
    }
}
