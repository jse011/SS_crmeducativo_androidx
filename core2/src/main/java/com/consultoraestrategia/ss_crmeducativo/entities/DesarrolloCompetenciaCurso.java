package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */

@Table(database = AppDatabase.class)
public class DesarrolloCompetenciaCurso extends BaseModel {

    @Unique
    @PrimaryKey
    private int desCompetenciaId;
    @Column
    private int planCursoId;
    @Column
    private double nota;
    @Column
    private String escala;
    @Column
    private int calendarioPeriodoId;
    @Column
    private int personaId;
    @Column
    private int empleadoId;
    @Column
    private String fechaAccion;
    @Column
    private int usuarioAccion;


    public DesarrolloCompetenciaCurso() {
    }

    public DesarrolloCompetenciaCurso(int desCompetenciaId, int planCursoId, double nota, String escala, int calendarioPeriodoId, int personaId, int empleadoId, String fechaAccion, int usuarioAccion) {
        this.desCompetenciaId = desCompetenciaId;
        this.planCursoId = planCursoId;
        this.nota = nota;
        this.escala = escala;
        this.calendarioPeriodoId = calendarioPeriodoId;
        this.personaId = personaId;
        this.empleadoId = empleadoId;
        this.fechaAccion = fechaAccion;
        this.usuarioAccion = usuarioAccion;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public int getDesCompetenciaId() {
        return desCompetenciaId;
    }

    public void setDesCompetenciaId(int desCompetenciaId) {
        this.desCompetenciaId = desCompetenciaId;
    }

    public int getPlanCursoId() {
        return planCursoId;
    }

    public void setPlanCursoId(int planCursoId) {
        this.planCursoId = planCursoId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    @Override
    public String toString() {
        return "DesarrolloCompetenciaCurso{" +
                "desCompetenciaId=" + desCompetenciaId +
                ", planCursoId=" + planCursoId +
                ", nota=" + nota +
                ", escala='" + escala + '\'' +
                ", calendarioPeriodoId=" + calendarioPeriodoId +
                ", personaId=" + personaId +
                ", empleadoId=" + empleadoId +
                ", fechaAccion='" + fechaAccion + '\'' +
                ", usuarioAccion=" + usuarioAccion +
                '}';
    }
}
