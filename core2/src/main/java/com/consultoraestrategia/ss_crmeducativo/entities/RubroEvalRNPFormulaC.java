package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class RubroEvalRNPFormulaC extends BaseEntity {
    public static final int RUBRO_HIJO_FORMULA = 10;
    @Column
    private String rubroFormulaId;
    @Column
    private String rubroEvaluacionPrimId;
    @Column
    private String rubroEvaluacionSecId;
    @Column
    private double peso;
    /*Opcional-No se agregan ala BD*/
    @Column
    private int posicion;
    /*Opcional-No se agregan ala BD*/
    @Column
    private int estado;


    public RubroEvalRNPFormulaC() {
    }

    public String getRubroFormulaId() {
        return rubroFormulaId;
    }

    public void setRubroFormulaId(String rubroFormulaId) {
        this.rubroFormulaId = rubroFormulaId;
    }

    public String getRubroEvaluacionPrimId() {
        return rubroEvaluacionPrimId;
    }

    public void setRubroEvaluacionPrimId(String rubroEvaluacionPrimId) {
        this.rubroEvaluacionPrimId = rubroEvaluacionPrimId;
    }

    public String getRubroEvaluacionSecId() {
        return rubroEvaluacionSecId;
    }

    public void setRubroEvaluacionSecId(String rubroEvaluacionSecId) {
        this.rubroEvaluacionSecId = rubroEvaluacionSecId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubroEvalRNPFormulaC)) return false;

        RubroEvalRNPFormulaC that = (RubroEvalRNPFormulaC) o;

        if (posicion != that.posicion) return false;
        return rubroEvaluacionPrimId != null ? rubroEvaluacionPrimId.equals(that.rubroEvaluacionPrimId) : that.rubroEvaluacionPrimId == null;
    }

    @Override
    public int hashCode() {
        int result = rubroEvaluacionPrimId != null ? rubroEvaluacionPrimId.hashCode() : 0;
        result = 31 * result + posicion;
        return result;
    }
}
