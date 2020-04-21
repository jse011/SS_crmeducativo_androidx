package com.consultoraestrategia.ss_crmeducativo.entities;


import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 24/07/2017.
 */
@Table(database = AppDatabase.class)
public class AsistenciaSesionAlumnoC extends BaseEntity {
    @Column
    private String nombreSesion;
    @Column
    private String asistenciaSesionId;
    @Column
    private long fechaAsistencia;
    @Column
    private String hora;
    @Column
    private int georeferenciaId;
    @Column
    private int alumnoId;
    @Column
    private int sesionAprendizajeId;
    @Column
    private int calendarioPeriodoId;
    @Column
    private int cargaCursoId;
    @Column
    private String valorTipoNotaId;

    @Column
    private int periodoId;//periodo_academico
    @Column
    private int docenteId;
    @Column
    private int grupoId;//seccionId



    public AsistenciaSesionAlumnoC() {
    }



    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getAsistenciaSesionId() {
        return asistenciaSesionId;
    }

    public void setAsistenciaSesionId(String asistenciaSesionId) {
        this.asistenciaSesionId = asistenciaSesionId;
    }

    public String getNombreSesion() {
        return nombreSesion;
    }

    public void setNombreSesion(String nombreSesion) {
        this.nombreSesion = nombreSesion;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }




    public long getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(long fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }


    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    @Override
    public String toString() {
        return "AsistenciaSesionAlumnoC{" +
                "nombreSesion='" + nombreSesion + '\'' +
                ", asistenciaSesionId='" + asistenciaSesionId + '\'' +
                ", fechaAsistencia='" + fechaAsistencia + '\'' +
                ", hora='" + hora + '\'' +
                ", georeferenciaId=" + georeferenciaId +
                ", alumnoId=" + alumnoId +
                ", sesionAprendizajeId=" + sesionAprendizajeId +
                ", calendarioPeriodoId=" + calendarioPeriodoId +
                ", cargaCursoId=" + cargaCursoId +
                ", valorTipoNotaId='" + valorTipoNotaId + '\'' +
                ", periodoId=" + periodoId +
                ", docenteId=" + docenteId +
                ", key='" + key + '\'' +
                ", grupoId=" + grupoId +
                '}';
    }
}
