package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 21/12/2017.
 */
@Table(database = AppDatabase.class)
public class CompetenciaDesempenioIcd extends BaseModel {

    @PrimaryKey
    @Column
    private int competenciaId;
    @PrimaryKey
    @Column
    private int desempenioIcdId;
    @Column
    private int usuarioAccionId;


    public CompetenciaDesempenioIcd() {
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

}
