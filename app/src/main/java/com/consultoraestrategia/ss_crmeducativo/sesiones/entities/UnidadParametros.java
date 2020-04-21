package com.consultoraestrategia.ss_crmeducativo.sesiones.entities;

import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;

public class UnidadParametros implements Serializable {

    private int unidadAprendizajeId;
    private int programaEducativoId;
    private int calendarioPeriodoId;
    private int cargaCursoId;
    private int entidadId;
    private String unidadTitulo;
    private int cargaAcademicaId;

    public UnidadParametros() {
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(UnidadParametros.class.getSimpleName(), this);
        return bundle;
    }

    public static UnidadParametros clone(Bundle bundle) {
        if(bundle==null)return null;
        UnidadParametros serializable = (UnidadParametros) bundle.getSerializable(UnidadParametros.class.getSimpleName());
        Log.d(UnidadParametros.class.getSimpleName(), bundle.toString());
        return serializable;
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

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public String getUnidadTitulo() {
        return unidadTitulo;
    }

    public void setUnidadTitulo(String unidadTitulo) {
        this.unidadTitulo = unidadTitulo;
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
}
