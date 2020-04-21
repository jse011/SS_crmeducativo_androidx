package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities;


import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

@SuppressWarnings("serial")
public class ValorTipoNotaUi implements Serializable {
    private String id;
    private TipoNotaUi tipoNotaUi;
    private String icono;
    private String titulo;
    private String alias;
    private CriterioEvaluacionUi criterioEvaluacionUi;
    private List<PrecisionUi> precisionList;
    private double limiteSuperior;
    private double limiteInferior;
    private String color;
    private boolean incluidoLInferior;
    private boolean incluidoLSuperior;
    private double valorNumerico;

    public ValorTipoNotaUi() {
    }

    public CriterioEvaluacionUi getCriterioEvaluacionUi() {
        return criterioEvaluacionUi;
    }

    public void setCriterioEvaluacionUi(CriterioEvaluacionUi criterioEvaluacionUi) {
        this.criterioEvaluacionUi = criterioEvaluacionUi;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "ValorTipoNotaUi{" +
                "id='" + id + '\'' +
                ", tipoNotaUi=" + tipoNotaUi +
                ", icono='" + icono + '\'' +
                ", titulo='" + titulo + '\'' +
                ", alias='" + alias + '\'' +
                ", criterioEvaluacionUi=" + criterioEvaluacionUi +
                '}';
    }

    public List<PrecisionUi> getPrecisionList() {
        return precisionList;
    }

    public void setPrecisionList(List<PrecisionUi> precisionList) {
        this.precisionList = precisionList;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }
}
