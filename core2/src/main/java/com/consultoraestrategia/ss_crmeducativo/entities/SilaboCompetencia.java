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
public class SilaboCompetencia extends BaseModel {
    @PrimaryKey
    private int silaboEventoCompetenciaId ;
    @Column
    private int silaboEventoId;
    @Column
    private int competenciaId;


    public SilaboCompetencia() {
    }

    public SilaboCompetencia(int silaboEventoCompetenciaId, int silaboEventoId, int competenciaId) {
        this.silaboEventoCompetenciaId = silaboEventoCompetenciaId;
        this.silaboEventoId = silaboEventoId;
        this.competenciaId = competenciaId;
    }

    public int getSilaboEventoCompetenciaId() {
        return silaboEventoCompetenciaId;
    }

    public void setSilaboEventoCompetenciaId(int silaboEventoCompetenciaId) {
        this.silaboEventoCompetenciaId = silaboEventoCompetenciaId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }
}
