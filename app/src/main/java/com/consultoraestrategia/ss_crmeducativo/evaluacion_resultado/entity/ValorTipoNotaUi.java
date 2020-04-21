package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity;

import androidx.annotation.ColorRes;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 23/10/2017.
 */
@Parcel
public class ValorTipoNotaUi {
    String id;
    String titulo;
    String alias;
    double limiteInferior;
    double limiteSuperior;
    String icono;
    CriterioUi criterio;
    boolean selected;
    double valorNumerico;
    @ColorRes int Bgcolor;
    @ColorRes int textColor;

    public ValorTipoNotaUi() {
    }

    public ValorTipoNotaUi(String id) {
        this.id = id;
    }

    public ValorTipoNotaUi(String id, String titulo, String alias, double limiteInferior, double limiteSuperior, String icono, CriterioUi criterio) {
        this.id = id;
        this.titulo = titulo;
        this.alias = alias;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.icono = icono;
        this.criterio = criterio;
    }

    public ValorTipoNotaUi(String id, String titulo, String alias, double limiteInferior, double limiteSuperior, String icono) {
        this.id = id;
        this.titulo = titulo;
        this.alias = alias;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.icono = icono;
    }

    public ValorTipoNotaUi(String id, String titulo, String alias, double limiteInferior, double limiteSuperior, String icono, double valorNumerico) {
        this.id = id;
        this.titulo = titulo;
        this.alias = alias;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.icono = icono;
        this.valorNumerico = valorNumerico;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAlias() {
        return alias;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public String getIcono() {
        return icono;
    }

    public CriterioUi getCriterio() {
        return criterio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public void setCriterio(CriterioUi criterio) {
        this.criterio = criterio;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public int getBgcolor() {
        return Bgcolor;
    }

    public void setBgcolor(int bgcolor) {
        Bgcolor = bgcolor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (this.getClass() != obj.getClass()) {
            return equals;
        }
        ValorTipoNotaUi valorTipoNota = (ValorTipoNotaUi) obj;
        if (valorTipoNota.getId().equals(id)) {
            equals = true;
        }
        return equals;
    }
}
