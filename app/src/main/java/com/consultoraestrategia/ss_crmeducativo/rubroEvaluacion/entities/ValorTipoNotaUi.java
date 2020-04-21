package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by kike on 11/05/2018.
 */
@Parcel
public class ValorTipoNotaUi extends FormulaCelda{
    public String key;
    public String alias;
    public String title;
    public double valorDefecto;
    public TipoNotaUi tipoNotaUi;
    public String icono;
    public List<PrecisionUi> precisionList;
    public boolean incluidoLInferior;
    public boolean incluidoLSuperior;
    public double limiteInferior;
    public double limiteSuperior;
    public String color;

    public ValorTipoNotaUi() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }


    public List<PrecisionUi> getPrecisionList() {
        return precisionList;
    }

    public void setPrecisionList(List<PrecisionUi> precisionList) {
        this.precisionList = precisionList;
    }

    public void setIncluidoLInferior(boolean incluidoLInferior) {
        this.incluidoLInferior = incluidoLInferior;
    }

    public boolean isIncluidoLInferior() {
        return incluidoLInferior;
    }

    public void setIncluidoLSuperior(boolean incluidoLSuperior) {
        this.incluidoLSuperior = incluidoLSuperior;
    }

    public boolean isIncluidoLSuperior() {
        return incluidoLSuperior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double isLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public double isLimiteSuperior() {
        return limiteSuperior;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
