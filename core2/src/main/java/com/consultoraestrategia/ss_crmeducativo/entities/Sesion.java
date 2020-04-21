package com.consultoraestrategia.ss_crmeducativo.entities;

import android.widget.TextView;

import java.util.List;

/**
 * Created by irvinmarin on 10/07/2017.
 */

public class Sesion {
    private int columna;
    private int tipoAsistenciaId;
    private int idSesion;
    private int nroSesion;
    private int idAlumno;
    private int cargaAcademicaId;
    private int calendarioPeiodoId;
    private int planCursoId;
    private String estado;
    private String fecha;
    private String fechaBd;
    private String hora;
    private List<TextView> textViews;

    public Sesion() {
    }

    public Sesion(int idSesion,int tipoAsistenciaId) {
        this.tipoAsistenciaId = tipoAsistenciaId;
        this.idSesion = idSesion;
    }

    public Sesion(int idSesion, int nroSesion, int idAlumno, int calendarioPeiodoId, String fechaBd, String fecha, String hora) {
        this.idSesion = idSesion;
        this.nroSesion = nroSesion;
        this.idAlumno = idAlumno;
        this.calendarioPeiodoId = calendarioPeiodoId;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaBd = fechaBd;

    }

    public String getFechaBd() {
        return fechaBd;
    }

    public void setFechaBd(String fechaBd) {
        this.fechaBd = fechaBd;
    }

    public List<TextView> getTextViews() {
        return textViews;
    }

    public void setTextViews(List<TextView> textViews) {
        this.textViews = textViews;
    }


    public int getTipoAsistenciaId() {
        return tipoAsistenciaId;
    }

    public void setTipoAsistenciaId(int tipoAsistenciaId) {
        this.tipoAsistenciaId = tipoAsistenciaId;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getCalendarioPeiodoId() {
        return calendarioPeiodoId;
    }

    public void setCalendarioPeiodoId(int calendarioPeiodoId) {
        this.calendarioPeiodoId = calendarioPeiodoId;
    }

    public int getPlanCursoId() {
        return planCursoId;
    }

    public void setPlanCursoId(int planCursoId) {
        this.planCursoId = planCursoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getNroSesion() {
        return nroSesion;
    }

    public void setNroSesion(int nroSesion) {
        this.nroSesion = nroSesion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "columna=" + columna +
                ", tipoAsistenciaId=" + tipoAsistenciaId +
                ", idSesion=" + idSesion +
                ", nroSesion=" + nroSesion +
                ", idAlumno=" + idAlumno +
                ", cargaAcademicaId=" + cargaAcademicaId +
                ", calendarioPeiodoId=" + calendarioPeiodoId +
                ", planCursoId=" + planCursoId +
                ", estado='" + estado + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", textViews=" + textViews +
                '}';
    }
}
