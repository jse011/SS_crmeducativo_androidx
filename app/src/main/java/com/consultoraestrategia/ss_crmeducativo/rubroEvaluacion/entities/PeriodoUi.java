package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

/**
 * Created by kike on 21/10/2017.
 */

public class PeriodoUi  {
    private String idCalendarioPeriodo;
    private String tipoName;
    private String idPeriodo;
    private boolean status;
    private boolean vigente;
    private String color;

    public PeriodoUi() {

    }

    public PeriodoUi(String idCalendarioPeriodo, String tipoName, String idPeriodo, boolean status) {
        this.idCalendarioPeriodo = idCalendarioPeriodo;
        this.tipoName = tipoName;
        this.status = status;
        this.idPeriodo = idPeriodo;
    }

    public String getIdCalendarioPeriodo() {
        return idCalendarioPeriodo;
    }

    public void setIdCalendarioPeriodo(String idCalendarioPeriodo) {
        this.idCalendarioPeriodo = idCalendarioPeriodo;
    }

    public String getTipoName() {
        return tipoName;
    }

    public void setTipoName(String tipoName) {
        this.tipoName = tipoName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isVigente() {
        return vigente;
    }
}
