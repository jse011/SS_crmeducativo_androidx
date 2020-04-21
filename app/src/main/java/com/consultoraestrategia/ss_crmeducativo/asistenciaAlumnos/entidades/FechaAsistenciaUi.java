package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;

import java.io.Serializable;
import java.util.List;

public class FechaAsistenciaUi implements Serializable {
    private long fechaAsistencia;
    private int cantidadEvaluado;
    private int cantidadNoEvaluados;
    private int cantidadTotal;
    private int cantidadPuntual;
    private int cantidadTarde;
    private int cantidadAusente;
    private String diaMes;
    private String diaSemana;
    private String descripcion;
    private String subDescripcion;
    public enum Tipo{GENERADO, NO_GENERADO};
    private Tipo tipo=Tipo.NO_GENERADO;
    private ParametroDisenioUi parametroDisenioUi;


    public ParametroDisenioUi getParametroDisenioUi() {
        return parametroDisenioUi;
    }

    public void setParametroDisenioUi(ParametroDisenioUi parametroDisenioUi) {
        this.parametroDisenioUi = parametroDisenioUi;
    }

    public int getCantidadPuntual() {
        return cantidadPuntual;
    }

    public void setCantidadPuntual(int cantidadPuntual) {
        this.cantidadPuntual = cantidadPuntual;
    }


    public int getCantidadTarde() {
        return cantidadTarde;
    }

    public void setCantidadTarde(int cantidadTarde) {
        this.cantidadTarde = cantidadTarde;
    }

    public int getCantidadAusente() {
        return cantidadAusente;
    }

    public void setCantidadAusente(int cantidadAusente) {
        this.cantidadAusente = cantidadAusente;
    }

    public long getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(long fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getCantidadEvaluado() {
        return cantidadEvaluado;
    }

    public void setCantidadEvaluado(int cantidadEvaluado) {
        this.cantidadEvaluado = cantidadEvaluado;
    }

    public int getCantidadNoEvaluados() {
        return cantidadNoEvaluados;
    }

    public void setCantidadNoEvaluados(int cantidadNoEvaluados) {
        this.cantidadNoEvaluados = cantidadNoEvaluados;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(String diaMes) {
        this.diaMes = diaMes;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubDescripcion() {
        return subDescripcion;
    }

    public void setSubDescripcion(String subDescripcion) {
        this.subDescripcion = subDescripcion;
    }
}
