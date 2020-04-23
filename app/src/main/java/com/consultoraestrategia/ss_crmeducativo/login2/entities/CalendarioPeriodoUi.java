package com.consultoraestrategia.ss_crmeducativo.login2.entities;

public class CalendarioPeriodoUi {
    private int calendarioId;
    private String nombre;
    private long fechaFin;
    private long fechaInicio;

    public int getCalendarioId() {
        return calendarioId;
    }

    public void setCalendarioId(int calendarioId) {
        this.calendarioId = calendarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    public void setFechaInicio(long fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public long getFechaInicio() {
        return fechaInicio;
    }
}
