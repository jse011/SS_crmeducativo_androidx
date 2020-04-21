package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.io.Serializable;
import java.util.List;


public class AsistenciaJustificaBundel implements Serializable {

    private int alumnoId;
    private String nombre;
    private String lastName;
    private String urlProfile;
    private String asistenciaId;
    private long fechaAsistencia;
    private int tipoJustificacionId;
    private String  justificacionId;
    private String justificacionRazon;
    private List<RepositorioFileUi> repositorioFileUiList;

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public long getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(long fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public int getTipoJustificacionId() {
        return tipoJustificacionId;
    }

    public void setTipoJustificacionId(int tipoJustificacionId) {
        this.tipoJustificacionId = tipoJustificacionId;
    }

    public String getJustificacionId() {
        return justificacionId;
    }

    public void setJustificacionId(String justificacionId) {
        this.justificacionId = justificacionId;
    }

    public String getJustificacionRazon() {
        return justificacionRazon;
    }

    public void setJustificacionRazon(String justificacionRazon) {
        this.justificacionRazon = justificacionRazon;
    }

    public String getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(String asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public List<RepositorioFileUi> getRepositorioFileUiList() {
        return repositorioFileUiList;
    }

    public void setRepositorioFileUiList(List<RepositorioFileUi> repositorioFileUiList) {
        this.repositorioFileUiList = repositorioFileUiList;
    }

    private transient static final String JUSTIFICACIONBUNDLE =  "JustificacionFragmetDialog.AsistenciaUi";
    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(JUSTIFICACIONBUNDLE, this);
        return bundle;
    }

    public static AsistenciaJustificaBundel clone(Bundle bundle) {
        try {
            return (AsistenciaJustificaBundel)bundle.getSerializable(JUSTIFICACIONBUNDLE);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
