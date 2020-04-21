package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;

import java.io.Serializable;

public class AsistenciaBundle implements Serializable {

    private int idProgramaEducativo;//
    private int idCargaCurso;//
    private int idGeoreferencia;//
    private int idCalendarioPeriodo;//
    private int idDocente;//
    private long fecha;//
    private int idCargaAcademica;
    private String color;

    public AsistenciaBundle() {
    }

    public AsistenciaBundle(AsistenciaUi asistenciaUi) {
        setFecha(asistenciaUi.getFecha());
        setIdCalendarioPeriodo(asistenciaUi.getIdCalendarioPeriodo());
        setIdCargaCurso(asistenciaUi.getIdCargaCurso());
        setIdDocente(asistenciaUi.getIdDocente());
        setIdGeoreferencia(asistenciaUi.getIdGeoreferencia());
        setIdProgramaEducativo(asistenciaUi.getIdProgramaEducativo());
        setIdCargaAcademica(asistenciaUi.getIdCargaAcademica());
        setColor(asistenciaUi.getColor());
    }

    public AsistenciaUi getAsistenciaUi(){
        AsistenciaUi asistenciaUi = new AsistenciaUi();
        asistenciaUi.setFecha(getFecha());
        asistenciaUi.setIdCalendarioPeriodo(getIdCalendarioPeriodo());
        asistenciaUi.setIdCargaCurso(getIdCargaCurso());
        asistenciaUi.setIdDocente(getIdDocente());
        asistenciaUi.setIdGeoreferencia(getIdGeoreferencia());
        asistenciaUi.setIdProgramaEducativo(getIdProgramaEducativo());
        asistenciaUi.setIdCargaAcademica(getIdCargaAcademica());
        return asistenciaUi;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdCargaAcademica() {
        return idCargaAcademica;
    }

    public void setIdCargaAcademica(int idCargaAcademica) {
        this.idCargaAcademica = idCargaAcademica;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public int getIdCargaCurso() {
        return idCargaCurso;
    }

    public void setIdCargaCurso(int idCargaCurso) {
        this.idCargaCurso = idCargaCurso;
    }

    public int getIdGeoreferencia() {
        return idGeoreferencia;
    }

    public void setIdGeoreferencia(int idGeoreferencia) {
        this.idGeoreferencia = idGeoreferencia;
    }

    public int getIdCalendarioPeriodo() {
        return idCalendarioPeriodo;
    }

    public void setIdCalendarioPeriodo(int idCalendarioPeriodo) {
        this.idCalendarioPeriodo = idCalendarioPeriodo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    private transient static final String ASISTENCIABUNDLE =  "AsistenciaBundle";
    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ASISTENCIABUNDLE, this);
        return bundle;
    }

    public static AsistenciaBundle clone(Bundle bundle) {
        return (AsistenciaBundle)bundle.getSerializable(ASISTENCIABUNDLE);
    }
}
