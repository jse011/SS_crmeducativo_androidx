package com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request;

import com.consultoraestrategia.ss_crmeducativo.util.Utils;

public class GenerarAsistenciaPorCargaAcademicaRequest {
    int empleadoId;
    int georefenciaId;
    int cargaAcademicaId;
    int calendarioPeridoId;
    long fechaAsistencia;
    private int programaEducativoId;
    private int estadoAsistenciaValorTipoNota;
    String hora;

    public GenerarAsistenciaPorCargaAcademicaRequest() {
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

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
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
