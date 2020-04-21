package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class EvaluacionResultado extends BaseRelEntity {

    @PrimaryKey
    private int evaluacionResultadoId;
    @Column
    private int calendarioPeriodoId;
    @Column
    private int planCursoId;
    @Column
    private int competenciaId;
    @Column
    private int alumnoId;
    @Column
    private int docenteId;
    @Column
    private double nota;
    @Column
    private String escala;
    @Column
    private int rubroEvalResultadoId;
    @Column
    private int estadoExportado;
    @Column
    private String valorTipoNotaId;
    @Column
    private int icdId;


    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }

    public EvaluacionResultado(int evaluacionResultadoId) {
        this.androidId = androidId;
    }

    public EvaluacionResultado() {
    }

    public EvaluacionResultado(double nota) {
        this.nota = nota;
    }

    public int getEvaluacionResultadoId() {
        return evaluacionResultadoId;
    }

    public void setEvaluacionResultadoId(int evaluacionResultadoId) {
        this.evaluacionResultadoId = evaluacionResultadoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getPlanCursoId() {
        return planCursoId;
    }

    public void setPlanCursoId(int planCursoId) {
        this.planCursoId = planCursoId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
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

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
    }

    public int getIcdId() {
        return icdId;
    }

    public void setIcdId(int icdId) {
        this.icdId = icdId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    @Override
    public int hashCode() {
        return evaluacionResultadoId;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (this.getClass() != obj.getClass()) {
            return equals;
        }
        EvaluacionResultado resultado = (EvaluacionResultado) obj;
        if (resultado.getAndroidId() == androidId) {
            equals = true;
        }
        return equals;
    }

}
