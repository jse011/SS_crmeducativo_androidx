package com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request;

import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.Calendar;
import java.util.Date;

public class GenerarAsistenciaPorCursoRequest {
    int empleadoId;
    int georefenciaId;
    int cargaCursoId;
    int calendarioPeridoId;
    long fechaAsistencia;
    private int programaEducativoId;
    private int estadoAsistenciaValorTipoNota;
    String hora;


    public GenerarAsistenciaPorCursoRequest() {
    }

    public int getEstadoAsistenciaValorTipoNota() {
        return estadoAsistenciaValorTipoNota;
    }

    public void setEstadoAsistenciaValorTipoNota(int estadoAsistenciaValorTipoNota) {
        this.estadoAsistenciaValorTipoNota = estadoAsistenciaValorTipoNota;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getGeorefenciaId() {
        return georefenciaId;
    }

    public void setGeorefenciaId(int georefenciaId) {
        this.georefenciaId = georefenciaId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCalendarioPeridoId() {
        return calendarioPeridoId;
    }

    public void setCalendarioPeridoId(int calendarioPeridoId) {
        this.calendarioPeridoId = calendarioPeridoId;
    }

    public long getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistenciaSinHora(long fechaAsistencia) {
        this.fechaAsistencia = Utils.transformarFecha_a_FechaAsistenciaSinHora(fechaAsistencia);
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
