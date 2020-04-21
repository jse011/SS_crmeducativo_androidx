package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;
;

/**
 * Created by SCIEV on 15/02/2018.
 */

public class SelectorNumericoUi extends NotaUi {

    private double valorMaximo;
    private double valorMinino;
    private double longitudPaso;
    private EscalaEvaluacionUI escalaEvaluacionUi;

    public double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public double getValorMinino() {
        return valorMinino;
    }

    public void setValorMinino(double valorMinino) {
        this.valorMinino = valorMinino;
    }

    public EscalaEvaluacionUI getEscalaEvaluacionUi() {
        return escalaEvaluacionUi;
    }

    public void setEscalaEvaluacionUi(EscalaEvaluacionUI escalaEvaluacionUi) {
        this.escalaEvaluacionUi = escalaEvaluacionUi;
    }

    public double getLongitudPaso() {
        return longitudPaso;
    }

    public void setLongitudPaso(double longitudPaso) {
        this.longitudPaso = longitudPaso;
    }
}
