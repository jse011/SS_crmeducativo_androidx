package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;

import org.parceler.Parcel;

import java.util.List;
import java.util.Objects;

/**
 * Created by @stevecampos on 23/02/2018.
 */
public class RubEvalProcUi extends Cell {
    private String id;
    private String titulo;
    private double peso;
    private String tipoNotaId;
    private TipoNotaUi tipoNotaUi;
    private List<CritRubEvalProcUi> critRubEvalProcList;
    private EvalProcUi evalProcUi = new EvalProcUi();
    private int icdsId;
    private TipoCompenciaEnum tipoCompenciaEnum = TipoCompenciaEnum.COMPETENCIA_BASE;
    private TipoIndicadorUi tipoIndicadorUi =TipoIndicadorUi.DEFAULT;
    private String url;

    public RubEvalProcUi() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public List<CritRubEvalProcUi> getCritRubEvalProcList() {
        return critRubEvalProcList;
    }

    public void setCritRubEvalProcList(List<CritRubEvalProcUi> critRubEvalProcList) {
        this.critRubEvalProcList = critRubEvalProcList;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public EvalProcUi getEvalProcUi() {
        return evalProcUi;
    }

    public void setEvalProcUi(EvalProcUi evalProcUi) {
        this.evalProcUi = evalProcUi;
    }

    public int getIcdsId() {
        return icdsId;
    }

    public void setIcdsId(int icdsId) {
        this.icdsId = icdsId;
    }

    public TipoCompenciaEnum getTipoCompenciaEnum() {
        return tipoCompenciaEnum;
    }

    public void setTipoCompenciaEnum(TipoCompenciaEnum tipoCompenciaEnum) {
        this.tipoCompenciaEnum = tipoCompenciaEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubEvalProcUi)) return false;

        RubEvalProcUi that = (RubEvalProcUi) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public TipoIndicadorUi getTipoIndicadorUi() {
        return tipoIndicadorUi;
    }

    public void setTipoIndicadorUi(TipoIndicadorUi tipoIndicadorUi) {
        this.tipoIndicadorUi = tipoIndicadorUi;
    }
}
