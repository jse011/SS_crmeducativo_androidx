package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class RubroEvalRNRFormula extends BaseRelEntity {

    @Unique
    @PrimaryKey
    private int rubroFormulaId;
    @Column
    private int rubroEvaluacionPrinId;
    @Column
    private int rubroEvaluacionSecId;
    @Column
    private double peso;
    @Column
    private String orientacion;


    public RubroEvalRNRFormula() {
    }

    public RubroEvalRNRFormula(int rubroFormulaId, int rubroEvaluacionPrinId, int rubroEvaluacionSecId) {
        this.rubroFormulaId = rubroFormulaId;
        this.rubroEvaluacionPrinId = rubroEvaluacionPrinId;
        this.rubroEvaluacionSecId = rubroEvaluacionSecId;
    }

    public int getRubroFormulaId() {
        return rubroFormulaId;
    }

    public void setRubroFormulaId(int rubroFormulaId) {
        this.rubroFormulaId = rubroFormulaId;
    }

    public int getRubroEvaluacionPrinId() {
        return rubroEvaluacionPrinId;
    }

    public void setRubroEvaluacionPrinId(int rubroEvaluacionPrinId) {
        this.rubroEvaluacionPrinId = rubroEvaluacionPrinId;
    }

    public int getRubroEvaluacionSecId() {
        return rubroEvaluacionSecId;
    }

    public void setRubroEvaluacionSecId(int rubroEvaluacionSecId) {
        this.rubroEvaluacionSecId = rubroEvaluacionSecId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }
}
