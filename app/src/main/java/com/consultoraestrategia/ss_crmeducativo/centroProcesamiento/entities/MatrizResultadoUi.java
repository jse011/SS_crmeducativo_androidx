package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

import java.util.ArrayList;
import java.util.List;

public class MatrizResultadoUi {
    private List<CabeceraUi> cabeceraList = new ArrayList<>();
    private List<CabeceraUi> competenciaList = new ArrayList<>();
    private List<ColumnTableRegEvalUi> capacidadList = new ArrayList<>();
    private List<RowTableRegEvalUi> alumnoEvalList = new ArrayList<>();
    private List<List<CellTableRegEvalUi>> evaluacionListList = new ArrayList<>();
    private List<CellTableRegEvalUi> evaluacionList = new ArrayList<>();
    private int habilitado;
    private int rangoFecha;
    private int estadoCalendarioPeriodoId;
    private int estadoCargaCurCalPerId;

    public List<CabeceraUi> getCabeceraList() {
        return cabeceraList;
    }

    public void setCabeceraList(List<CabeceraUi> cabeceraList) {
        this.cabeceraList = cabeceraList;
    }

    public List<CabeceraUi> getCompetenciaList() {
        return competenciaList;
    }

    public void setCompetenciaList(List<CabeceraUi> competenciaList) {
        this.competenciaList = competenciaList;
    }

    public List<ColumnTableRegEvalUi> getCapacidadList() {
        return capacidadList;
    }

    public void setCapacidadList(List<ColumnTableRegEvalUi> capacidadList) {
        this.capacidadList = capacidadList;
    }

    public List<RowTableRegEvalUi> getAlumnoEvalList() {
        return alumnoEvalList;
    }

    public void setAlumnoEvalList(List<RowTableRegEvalUi> alumnoEvalList) {
        this.alumnoEvalList = alumnoEvalList;
    }

    public List<List<CellTableRegEvalUi>> getEvaluacionListList() {
        return evaluacionListList;
    }

    public void setEvaluacionListList(List<List<CellTableRegEvalUi>> evaluacionListList) {
        this.evaluacionListList = evaluacionListList;
    }

    public List<CellTableRegEvalUi> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<CellTableRegEvalUi> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setRangoFecha(int rangoFecha) {
        this.rangoFecha = rangoFecha;
    }

    public int getRangoFecha() {
        return rangoFecha;
    }

    public void setEstadoCalendarioPeriodoId(int estadoCalendarioPeriodoId) {
        this.estadoCalendarioPeriodoId = estadoCalendarioPeriodoId;
    }

    public int getEstadoCalendarioPeriodoId() {
        return estadoCalendarioPeriodoId;
    }

    public void setEstadoCargaCurCalPerId(int estadoCargaCurCalPerId) {
        this.estadoCargaCurCalPerId = estadoCargaCurCalPerId;
    }

    public int getEstadoCargaCurCalPerId() {
        return estadoCargaCurCalPerId;
    }
}
