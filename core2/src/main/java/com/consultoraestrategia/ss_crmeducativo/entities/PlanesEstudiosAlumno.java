package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 23/03/2017.
 */


public class PlanesEstudiosAlumno   {


    private long planEstAlumnoId;
    private boolean estado;
    private int planEstudiosId;
    private int personaId;
    private int periodoId;

    public PlanesEstudiosAlumno() {
    }

    public PlanesEstudiosAlumno(long planEstAlumnoId, boolean estado, int planEstudiosId, int personaId, int periodoId) {
        this.planEstAlumnoId = planEstAlumnoId;
        this.estado = estado;
        this.planEstudiosId = planEstudiosId;
        this.personaId = personaId;
        this.periodoId = periodoId;
    }

    public long getPlanEstAlumnoId() {
        return planEstAlumnoId;
    }

    public void setPlanEstAlumnoId(long planEstAlumnoId) {
        this.planEstAlumnoId = planEstAlumnoId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getPlanEstudiosId() {
        return planEstudiosId;
    }

    public void setPlanEstudiosId(int planEstudiosId) {
        this.planEstudiosId = planEstudiosId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }
}
