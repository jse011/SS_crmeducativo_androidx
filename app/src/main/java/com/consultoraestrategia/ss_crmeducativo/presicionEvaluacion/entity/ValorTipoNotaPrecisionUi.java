package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity;

import java.util.List;

public class ValorTipoNotaPrecisionUi {

    public enum TIPO {SELECTOR_VALORES, SELECTOR_ICONOS;}

    private String descripcion;
    private TIPO tipo = TIPO.SELECTOR_ICONOS;
    private String icono;
    private double limiteSuperior;
    private double limiteInferior;
    private boolean incluidoLInferior;
    private boolean incluidoLSuperior;
    private String alias;
    private String rubroNombre;
    private List<NotaCircularUi> notaCircularUiList;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getIcono() {
        return icono;
    }


    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }


    public List<NotaCircularUi> getNotaCircularUiList() {
        return notaCircularUiList;
    }

    public void setNotaCircularUiList(List<NotaCircularUi> notaCircularUiList) {
        this.notaCircularUiList = notaCircularUiList;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public boolean isIncluidoLInferior() {
        return incluidoLInferior;
    }

    public void setIncluidoLInferior(boolean incluidoLInferior) {
        this.incluidoLInferior = incluidoLInferior;
    }

    public boolean isIncluidoLSuperior() {
        return incluidoLSuperior;
    }

    public void setIncluidoLSuperior(boolean incluidoLSuperior) {
        this.incluidoLSuperior = incluidoLSuperior;
    }

    public String getRubroNombre() {
        return rubroNombre;
    }

    public void setRubroNombre(String rubroNombre) {
        this.rubroNombre = rubroNombre;
    }
}
