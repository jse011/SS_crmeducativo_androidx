package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell;

import java.io.Serializable;

/**
 * Created by SCIEV on 7/05/2018.
 */

public class CriterioEvaluacionUi extends Cell implements Serializable {
    private String key;
    private ValorTipoNotaUi valorTipoNotaUi;
    private IndicadorUi indicadorUi;
    private String titulo;
    private String rubroEvalProcesoId;

    public CriterioEvaluacionUi() {
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public void setValorTipoNotaUi(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUi = valorTipoNotaUi;
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriterioEvaluacionUi that = (CriterioEvaluacionUi) o;

        if (valorTipoNotaUi != null ? !valorTipoNotaUi.equals(that.valorTipoNotaUi) : that.valorTipoNotaUi != null)
            return false;
        return indicadorUi != null ? indicadorUi.equals(that.indicadorUi) : that.indicadorUi == null;
    }

    @Override
    public int hashCode() {
        int result = valorTipoNotaUi != null ? valorTipoNotaUi.hashCode() : 0;
        result = 31 * result + (indicadorUi != null ? indicadorUi.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CriterioEvaluacionUi{" +
                "key='" + key + '\'' +
                ", valorTipoNotaUi=" + valorTipoNotaUi +
                ", indicadorUi=" + indicadorUi +
                ", titulo='" + titulo + '\'' +
                ", rubroEvalProcesoId='" + rubroEvalProcesoId + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

}
