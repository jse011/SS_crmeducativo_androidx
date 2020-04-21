package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */
public class RubBidUi {
    private String id;
    private String title;
    private String alias;
    private List<RubEvalProcUi> rubroEvalProcesoList;
    private TipoNotaUi tipoNota;
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private List<GrupoProcesoUi> grupoProcesoUis;
    private EvalProcUi evalProcUi = new EvalProcUi();
    private int calendarioPeriodoId;
    private boolean disabledEval;//si esta en estado true no se debe evaluar.


    public RubBidUi() {
    }

    public RubBidUi(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<RubEvalProcUi> getRubroEvalProcesoList() {
        return rubroEvalProcesoList;
    }

    public void setRubroEvalProcesoList(List<RubEvalProcUi> rubroEvalProcesoList) {
        this.rubroEvalProcesoList = rubroEvalProcesoList;
    }

    public TipoNotaUi getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNotaUi tipoNota) {
        this.tipoNota = tipoNota;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaUiList() {
        return valorTipoNotaUiList;
    }

    public void setValorTipoNotaUiList(List<ValorTipoNotaUi> valorTipoNotaUiList) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
    }

    public List<GrupoProcesoUi> getGrupoProcesoUis() {
        return grupoProcesoUis;
    }

    public void setGrupoProcesoUis(List<GrupoProcesoUi> grupoProcesoUis) {
        this.grupoProcesoUis = grupoProcesoUis;
    }

    public EvalProcUi getEvalProcUi() {
        return evalProcUi;
    }

    public void setEvalProcUi(EvalProcUi evalProcUi) {
        this.evalProcUi = evalProcUi;
    }

    public boolean isDisabledEval() {
        return disabledEval;
    }

    public void setDisabledEval(boolean disabledEval) {
        this.disabledEval = disabledEval;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }
}
