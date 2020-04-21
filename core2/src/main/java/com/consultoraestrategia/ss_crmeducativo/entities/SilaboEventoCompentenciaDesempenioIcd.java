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
public class SilaboEventoCompentenciaDesempenioIcd extends BaseModel {
    @Unique
    @PrimaryKey
    int silaboCompetenciaDesempenioIcdId ;
    @Column
    int silaboEventoCompetenciaId;
    @Column
    int desempenioIcdId ;

    public SilaboEventoCompentenciaDesempenioIcd() {
    }

    public SilaboEventoCompentenciaDesempenioIcd(int silaboCompetenciaDesempenioIcdId, int silaboEventoCompetenciaId, int desempenioIcdId) {
        this.silaboCompetenciaDesempenioIcdId = silaboCompetenciaDesempenioIcdId;
        this.silaboEventoCompetenciaId = silaboEventoCompetenciaId;
        this.desempenioIcdId = desempenioIcdId;
    }

    public int getSilaboCompetenciaDesempenioIcdId() {
        return silaboCompetenciaDesempenioIcdId;
    }

    public void setSilaboCompetenciaDesempenioIcdId(int silaboCompetenciaDesempenioIcdId) {
        this.silaboCompetenciaDesempenioIcdId = silaboCompetenciaDesempenioIcdId;
    }

    public int getSilaboEventoCompetenciaId() {
        return silaboEventoCompetenciaId;
    }

    public void setSilaboEventoCompetenciaId(int silaboEventoCompetenciaId) {
        this.silaboEventoCompetenciaId = silaboEventoCompetenciaId;
    }

    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }
}
