package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;


@QueryModel(database = AppDatabase.class, allFields = true)
public class CursoCustom {
    
    @Column
    public int cursoId;
    @Column
    public String nombre;
    @Column
    public int estadoId;
    @Column
    public String descripcion;
    @Column
    public String alias;
    @Column
    public int cargaCursoId;
    @Column
    public int cargaAcademicaId;
    @Column
    public int seccionId;
    @Column
    public int periodoId;
    @Column
    public int aulaId;
    @Column
    public int idPlanEstudio;
    @Column
    public int idPlanEstudioVersion;
    @Column
    public int idAnioAcademico;
    @Column
    public String seccion;
    @Column
    public String periodo;
    @Column
    public String nivelAcademico;
    @Column
    public int complejo;
    @Column
    public int empleadoId;

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }

    public int getAulaId() {
        return aulaId;
    }

    public void setAulaId(int aulaId) {
        this.aulaId = aulaId;
    }

    public int getIdPlanEstudio() {
        return idPlanEstudio;
    }

    public void setIdPlanEstudio(int idPlanEstudio) {
        this.idPlanEstudio = idPlanEstudio;
    }

    public int getIdPlanEstudioVersion() {
        return idPlanEstudioVersion;
    }

    public void setIdPlanEstudioVersion(int idPlanEstudioVersion) {
        this.idPlanEstudioVersion = idPlanEstudioVersion;
    }

    public int getIdAnioAcademico() {
        return idAnioAcademico;
    }

    public void setIdAnioAcademico(int idAnioAcademico) {
        this.idAnioAcademico = idAnioAcademico;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public int getComplejo() {
        return complejo;
    }

    public void setComplejo(int complejo) {
        this.complejo = complejo;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public String toString() {
        return "CursoCustom{" +
                "cursoId=" + cursoId +
                ", nombre='" + nombre + '\'' +
                ", estadoId=" + estadoId +
                ", descripcion='" + descripcion + '\'' +
                ", alias='" + alias + '\'' +
                ", cargaCursoId=" + cargaCursoId +
                ", cargaAcademicaId=" + cargaAcademicaId +
                ", seccionId=" + seccionId +
                ", periodoId=" + periodoId +
                ", aulaId=" + aulaId +
                ", idPlanEstudio=" + idPlanEstudio +
                ", idPlanEstudioVersion=" + idPlanEstudioVersion +
                ", idAnioAcademico=" + idAnioAcademico +
                ", seccion='" + seccion + '\'' +
                ", periodo='" + periodo + '\'' +
                '}';
    }
}
