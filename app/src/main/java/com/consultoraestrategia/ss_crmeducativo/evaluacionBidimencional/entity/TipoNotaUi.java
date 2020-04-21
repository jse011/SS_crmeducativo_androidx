package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;


import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.CellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.CellHolderUi;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by @stevecampos on 20/10/2017.
 */
public class TipoNotaUi extends CellHolderUi {
    public enum Tipo{SELECTOR_ICONOS, SELECTOR_VALORES}
    RubEvalProcUi rubEvalProcUi;
    String id;
    String nombre;
    String valorDefecto;
    int valorMinimo;
    int valorMaximo;
    double longitudPaso;
    int tipoId;
    List<ValorTipoNotaUi> valorTipoNotaList;
    EscalaEvaluacionUI escalaEvaluacionUi;
    Tipo tipo;
    ValorTipoNotaUi selectValorTipoNotaUi;
    boolean intervalo;
    public TipoNotaUi() {
        tipo = Tipo.SELECTOR_VALORES;
    }

    public int getTipoId() {
        return tipoId;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaList() {
        return valorTipoNotaList;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public double getLongitudPaso() {
        return longitudPaso;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setLongitudPaso(double longitudPaso) {
        this.longitudPaso = longitudPaso;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "nombre: " + nombre + "\n" +
                "valorDefecto: " + valorDefecto + "\n" +
                "valorMinimo: " + valorMinimo + "\n" +
                "valorMaximo: " + valorMaximo + "\n" +
                "longitudPaso: " + longitudPaso + "\n" +
                "tipoId: " + tipoId;
    }

    public EscalaEvaluacionUI getEscalaEvaluacionUi() {
        return escalaEvaluacionUi;
    }

    public void setEscalaEvaluacionUi(EscalaEvaluacionUI escalaEvaluacionUi) {
        this.escalaEvaluacionUi = escalaEvaluacionUi;
    }

    public void setValorTipoNotaList(List<ValorTipoNotaUi> valorTipoNotaList) {
        this.valorTipoNotaList = valorTipoNotaList;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ValorTipoNotaUi getSelectValorTipoNotaUi() {
        return selectValorTipoNotaUi;
    }

    public void setSelectValorTipoNotaUi(ValorTipoNotaUi selectValorTipoNotaUi) {
        if(selectValorTipoNotaUi == null){
            setSelectCell(null);
        }
        this.selectValorTipoNotaUi = selectValorTipoNotaUi;
    }
    public RubEvalProcUi getRubEvalProcUi() {
        return rubEvalProcUi;
    }

    public void setRubEvalProcUi(RubEvalProcUi rubEvalProcUi) {
        this.rubEvalProcUi = rubEvalProcUi;
    }

    public boolean isIntervalo() {
        return intervalo;
    }

    public void setIntervalo(boolean intervalo) {
        this.intervalo = intervalo;
    }
}
