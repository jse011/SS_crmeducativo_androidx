package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class Caso extends BaseEntity {
    public static final int ESTADO_CREADO =  355, ESTADO_ELIMINADO = 357, ESTADO_ACTUALIUZADO =356;
    @Column
    private String casoId;
    @Column
    private String descripcion;
    @Column
    private int alumnoId;
    @Column
    private int tipoCasoId;
    @Column
    private int comportamientoId;
    @Column
    private long fechaCaso;
    //private int usuarioOrigenId { get; set; }
    @Column
    private int estadoId;
    @Column
    private int programaEducativoId;
    @Column
    private int cargaAcademicaId;
    @Column
    private int cargaCursoId;
    @Column
    private int docenteId;
    @Column
    private int calendarioPeriodoId;

    public String getCasoId() {
        return casoId;
    }

    public void setCasoId(String casoId) {
        this.casoId = casoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getTipoCasoId() {
        return tipoCasoId;
    }

    public void setTipoCasoId(int tipoCasoId) {
        this.tipoCasoId = tipoCasoId;
    }

    public long getFechaCaso() {
        return fechaCaso;
    }

    public void setFechaCaso(long fechaCaso) {
        this.fechaCaso = fechaCaso;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getComportamientoId() {
        return comportamientoId;
    }

    public void setComportamientoId(int comportamientoId) {
        this.comportamientoId = comportamientoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caso)) return false;

        Caso caso = (Caso) o;

        return getKey() != null ? getKey().equals(caso.getKey()) : caso.getKey() == null;
    }

    @Override
    public int hashCode() {
        return getKey() != null ? getKey().hashCode() : 0;
    }
}
