package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */
@Table(database = AppDatabase.class)
public class PlanCursosAlumno extends BaseModel {

    @PrimaryKey
    private int planCursosAlumId;
    @Column
    private int planEstAlumnoId;
    @Column
    private String escala;
    @Column
    private double nota;
    @Column
    private int cursoId;
    @Column
    private int idContratoDetAcad;
    @Column
    private int periodoId;

    public PlanCursosAlumno(int planCursosAlumId, int planEstAlumnoId, String escala, double nota, int cursoId, int idContratoDetAcad, int periodoId) {
        this.planCursosAlumId = planCursosAlumId;
        this.planEstAlumnoId = planEstAlumnoId;
        this.escala = escala;
        this.nota = nota;
        this.cursoId = cursoId;
        this.idContratoDetAcad = idContratoDetAcad;
        this.periodoId = periodoId;
    }

    public PlanCursosAlumno() {
    }

    public int getPlanCursosAlumId() {
        return planCursosAlumId;
    }

    public void setPlanCursosAlumId(int planCursosAlumId) {
        this.planCursosAlumId = planCursosAlumId;
    }

    public int getPlanEstAlumnoId() {
        return planEstAlumnoId;
    }

    public void setPlanEstAlumnoId(int planEstAlumnoId) {
        this.planEstAlumnoId = planEstAlumnoId;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getIdContratoDetAcad() {
        return idContratoDetAcad;
    }

    public void setIdContratoDetAcad(int idContratoDetAcad) {
        this.idContratoDetAcad = idContratoDetAcad;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }
}
