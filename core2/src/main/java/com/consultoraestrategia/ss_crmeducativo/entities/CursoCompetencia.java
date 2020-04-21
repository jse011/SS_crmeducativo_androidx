package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 13/04/2017.
 */

@Table(database = AppDatabase.class)
public class CursoCompetencia extends BaseModel {
    @PrimaryKey
    @Column
    private int competenciaId;
    @Column
    private int cursoId;


    public CursoCompetencia() {
    }

    public CursoCompetencia(int competenciaId, int cursoId) {
        this.competenciaId = competenciaId;
        this.cursoId = cursoId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }
}
