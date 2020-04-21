package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

/**
 * Created by kike on 11/05/2018.
 */

public class TotalFormula extends RubroProcesoUi {
    private double totalFormula;
    private ValorTipoNotaUi valorTipoNotaUi;

    public double getTotalFormula() {
        return totalFormula;
    }

    public void setTotalFormula(double totalFormula) {
        this.totalFormula = totalFormula;
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public void setValorTipoNotaUi(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUi = valorTipoNotaUi;
    }
}
