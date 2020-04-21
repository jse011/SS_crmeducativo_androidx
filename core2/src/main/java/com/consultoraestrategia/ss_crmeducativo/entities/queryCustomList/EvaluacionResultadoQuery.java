package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

@QueryModel(database = AppDatabase.class, allFields = true)
public class EvaluacionResultadoQuery {

    @Column
    int evaluacionResultadoId;
    @Column
    int calendarioPeriodoId;
    @Column
    int planCursoId;
    @Column
    int competenciaId;
    @Column
    int alumnoId;
    @Column
    int docenteId;
    @Column
    double nota;
    @Column
    int rubroEvalResultadoId;
    @Column
    String valorTipoNotaId;
    @Column
    String tipoNotaId;
    @Column
    int tipoFormulaId;
    @Column
    int silaboEventoId;
    @Column
    int tipoRedondeoId;
    @Column
    int valorRedondeoId;
    @Column
    double peso;
    @Column
    int estadoId;
    @Column
     String rubroEvalProcesoId;
    @Column
     int evaluable;
    @Column
     int escalaEvaluacionId;
    @Column
     int rubroFormal;
    @Column
     int tipoCalendarioPeriodoId;
    @Column
     int rubroEvalResultadoBaseId;
    @Column
     double promedio;
    @Column
     double desviacionEstandar;

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

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public int getTipoFormulaId() {
        return tipoFormulaId;
    }

    public void setTipoFormulaId(int tipoFormulaId) {
        this.tipoFormulaId = tipoFormulaId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getTipoRedondeoId() {
        return tipoRedondeoId;
    }

    public void setTipoRedondeoId(int tipoRedondeoId) {
        this.tipoRedondeoId = tipoRedondeoId;
    }

    public int getValorRedondeoId() {
        return valorRedondeoId;
    }

    public void setValorRedondeoId(int valorRedondeoId) {
        this.valorRedondeoId = valorRedondeoId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public int getEvaluable() {
        return evaluable;
    }

    public void setEvaluable(int evaluable) {
        this.evaluable = evaluable;
    }

    public int getEscalaEvaluacionId() {
        return escalaEvaluacionId;
    }

    public void setEscalaEvaluacionId(int escalaEvaluacionId) {
        this.escalaEvaluacionId = escalaEvaluacionId;
    }

    public int getRubroFormal() {
        return rubroFormal;
    }

    public void setRubroFormal(int rubroFormal) {
        this.rubroFormal = rubroFormal;
    }

    public int getTipoCalendarioPeriodoId() {
        return tipoCalendarioPeriodoId;
    }

    public void setTipoCalendarioPeriodoId(int tipoCalendarioPeriodoId) {
        this.tipoCalendarioPeriodoId = tipoCalendarioPeriodoId;
    }

    public int getRubroEvalResultadoBaseId() {
        return rubroEvalResultadoBaseId;
    }

    public void setRubroEvalResultadoBaseId(int rubroEvalResultadoBaseId) {
        this.rubroEvalResultadoBaseId = rubroEvalResultadoBaseId;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }
}
