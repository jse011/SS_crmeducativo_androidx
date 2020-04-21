package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.CriterioUi;

/**
 * Created by @stevecampos on 23/10/2017.
 */

public class ValorTipoNotaUi extends Cell {

    private boolean incluidoLInferior;
    private boolean incluidoLSuperior;

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

    public enum Estilo{
        GREY("#90A4AE"),AZUL("#1976d2"),ANARANJADO("#FF6D00"),ROJO("#D32F2F"),VERDE("#388e3c");
        private String color;
        Estilo(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }
    String id;
    String titulo;
    String alias;
    double limiteInferior;
    double limiteSuperior;
    String icono;
    CriterioUi criterio;
    boolean selected;
    double valorNumerico;
    double valorAsignado;
    TipoNotaUi tipoNotaUi;
    Estilo estilo = Estilo.GREY;
    private EscalaEvaluacionUI escalaEvaluacionUI;
    private String intervalo;
    boolean validar;

    public ValorTipoNotaUi() {

    }

    public ValorTipoNotaUi(String id) {
        this.id = id;
    }


    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
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

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public EscalaEvaluacionUI getEscalaEvaluacionUI() {
        return escalaEvaluacionUI;
    }

    public void setEscalaEvaluacionUI(EscalaEvaluacionUI escalaEvaluacionUI) {
        this.escalaEvaluacionUI = escalaEvaluacionUI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValorTipoNotaUi that = (ValorTipoNotaUi) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ValorTipoNotaUi{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", alias='" + alias + '\'' +
                ", limiteInferior=" + limiteInferior +
                ", limiteSuperior=" + limiteSuperior +
                ", icono='" + icono + '\'' +
                ", criterio=" + criterio +
                ", selected=" + selected +
                ", valorNumerico=" + valorNumerico +
                ", tipoNotaUi=" + tipoNotaUi +
                ", estilo=" + estilo +
                ", escalaEvaluacionUI=" + escalaEvaluacionUI +
                '}';
    }

    public double getValorAsignado() {
        return valorAsignado;
    }

    public void setValorAsignado(double valorAsignado) {
        this.valorAsignado = valorAsignado;
    }


    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }
}
