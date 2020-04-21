package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.RowHeader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public class ValorTipoNotaUi extends RowHeader implements Serializable {
    private String id;
    private String alias;
    private String title;
    private double valorDefecto;
    private TipoNotaUi tipoNotaUi;
    private CriterioEvaluacionUi criterioEvaluacionUi;
    private String icono;
    private List<PrecisionUi> precisionList = new ArrayList<>();
    private String color;
    private double limiteSuperior;
    private double limiteInferior;
    private boolean incluidoLSuperior;
    private boolean incluidoLInferior;
    private double valorNumerico;


    public ValorTipoNotaUi() {
    }


    public CriterioEvaluacionUi getCriterioEvaluacionUi() {
        return criterioEvaluacionUi;
    }

    public void setCriterioEvaluacionUi(CriterioEvaluacionUi criterioEvaluacionUi) {
        this.criterioEvaluacionUi = criterioEvaluacionUi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(double valorDefecto) {
        this.valorDefecto = valorDefecto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValorTipoNotaUi that = (ValorTipoNotaUi) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public List<PrecisionUi> getPrecisionList() {
        return precisionList;
    }

    public void setPrecisionList(List<PrecisionUi> precisionList) {
        this.precisionList = precisionList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public boolean isIncluidoLSuperior() {
        return incluidoLSuperior;
    }

    public void setIncluidoLSuperior(boolean incluidoLSuperior) {
        this.incluidoLSuperior = incluidoLSuperior;
    }

    public boolean isIncluidoLInferior() {
        return incluidoLInferior;
    }

    public void setIncluidoLInferior(boolean incluidoLInferior) {
        this.incluidoLInferior = incluidoLInferior;
    }

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }
}
