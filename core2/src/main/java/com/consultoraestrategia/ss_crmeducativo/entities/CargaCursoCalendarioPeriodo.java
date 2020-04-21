package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 20/07/2018.
 */
@Table(database = AppDatabase.class)
public class CargaCursoCalendarioPeriodo extends BaseRelEntity{
    public static final  int PROCESO = 302, ENEVALUACION = 303, PENDIENTE = 304, CERRADO = 305, PROCESADO = 306, ENVIADO = 307;


    @PrimaryKey
    private int cargaCursoCalendarioPeriodoId;
    @Column
    private int cargaCursoId;
    @Column
    private int calendarioPeriodoId;
    @Column
    private int estadoId;
    @Column
    private long fechaInicio;
    @Column
    private long fechaFin;
    @Column
    private String horaInicio;
    @Column
    private String horaFin;
    @Column
    private int calendarioPeriodoDetId;
    @Column
    private int tipoId;
    @Column
    private int planCursoId;
    @Column
    private int anioAcademicoId;
    @Column
    private int cargaAcademicaid;
    @Column
    private String paths;

    public CargaCursoCalendarioPeriodo() {
    }

    public int getCargaCursoCalendarioPeriodoId() {
        return cargaCursoCalendarioPeriodoId;
    }

    public void setCargaCursoCalendarioPeriodoId(int cargaCursoCalendarioPeriodoId) {
        this.cargaCursoCalendarioPeriodoId = cargaCursoCalendarioPeriodoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public long getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(long fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getCalendarioPeriodoDetId() {
        return calendarioPeriodoDetId;
    }

    public void setCalendarioPeriodoDetId(int calendarioPeriodoDetId) {
        this.calendarioPeriodoDetId = calendarioPeriodoDetId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getPlanCursoId() {
        return planCursoId;
    }

    public void setPlanCursoId(int planCursoId) {
        this.planCursoId = planCursoId;
    }

    public int getAnioAcademicoId() {
        return anioAcademicoId;
    }

    public void setAnioAcademicoId(int anioAcademicoId) {
        this.anioAcademicoId = anioAcademicoId;
    }

    public int getCargaAcademicaid() {
        return cargaAcademicaid;
    }

    public void setCargaAcademicaid(int cargaAcademicaid) {
        this.cargaAcademicaid = cargaAcademicaid;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }
}
