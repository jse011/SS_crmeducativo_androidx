package com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades;

import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public class TipoNotaUi {

    private int contador;
    private String nombre;
    private String  tipoNombre;
    private int tipoSelectorId;
    private int tipoEscalaEvaluacionId;
    private int tipoValorPorDefectoId;
    private double valorPorDefecto;
    private boolean aBoolean;
    private List<ValoresUi> valoresUiList;

    public TipoNotaUi() {
    }

    public TipoNotaUi(int contador, String nombre, String tipoNombre, int tipoSelectorId, int tipoEscalaEvaluacionId, int tipoValorPorDefectoId, double valorPorDefecto, List<ValoresUi> valoresUiList) {
        this.contador = contador;
        this.nombre = nombre;
        this.tipoNombre = tipoNombre;
        this.tipoSelectorId = tipoSelectorId;
        this.tipoEscalaEvaluacionId = tipoEscalaEvaluacionId;
        this.tipoValorPorDefectoId = tipoValorPorDefectoId;
        this.valorPorDefecto = valorPorDefecto;
        this.valoresUiList = valoresUiList;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public int getTipoSelectorId() {
        return tipoSelectorId;
    }

    public void setTipoSelectorId(int tipoSelectorId) {
        this.tipoSelectorId = tipoSelectorId;
    }

    public int getTipoEscalaEvaluacionId() {
        return tipoEscalaEvaluacionId;
    }

    public void setTipoEscalaEvaluacionId(int tipoEscalaEvaluacionId) {
        this.tipoEscalaEvaluacionId = tipoEscalaEvaluacionId;
    }

    public int getTipoValorPorDefectoId() {
        return tipoValorPorDefectoId;
    }

    public void setTipoValorPorDefectoId(int tipoValorPorDefectoId) {
        this.tipoValorPorDefectoId = tipoValorPorDefectoId;
    }

    public double getValorPorDefecto() {
        return valorPorDefecto;
    }

    public void setValorPorDefecto(double valorPorDefecto) {
        this.valorPorDefecto = valorPorDefecto;
    }

    public List<ValoresUi> getValoresUiList() {
        return valoresUiList;
    }

    public void setValoresUiList(List<ValoresUi> valoresUiList) {
        this.valoresUiList = valoresUiList;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
