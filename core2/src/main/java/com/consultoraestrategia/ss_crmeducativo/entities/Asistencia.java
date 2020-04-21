package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 22/05/2017.
 */

public class Asistencia  {


    private long asistenciaId;

    private int alumnoId;

    private int docenteId;

    private int sesionAprendizajeId;

    private int georeferenciaId;

    private String fechaAsistencia;

    private String horaIngreso;

    private String horaSalida;

    private int tipoAsistenciaId;

    private int controlId;

    public Asistencia(long asistenciaId, int alumnoId, int docenteId, int sesionAprendizajeId, int georeferenciaId, String fechaAsistencia, String horaIngreso, String horaSalida, int tipoAsistenciaId, int controlId) {
        this.asistenciaId = asistenciaId;
        this.alumnoId = alumnoId;
        this.docenteId = docenteId;
        this.sesionAprendizajeId = sesionAprendizajeId;
        this.georeferenciaId = georeferenciaId;
        this.fechaAsistencia = fechaAsistencia;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.tipoAsistenciaId = tipoAsistenciaId;
        this.controlId = controlId;
    }

    public long getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(long asistenciaId) {
        this.asistenciaId = asistenciaId;
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

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public String getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(String fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getTipoAsistenciaId() {
        return tipoAsistenciaId;
    }

    public void setTipoAsistenciaId(int tipoAsistenciaId) {
        this.tipoAsistenciaId = tipoAsistenciaId;
    }

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }
}
