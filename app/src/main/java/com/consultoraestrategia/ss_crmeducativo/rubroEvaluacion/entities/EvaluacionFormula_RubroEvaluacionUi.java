package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;

/**
 * Created by kike on 14/05/2018.
 */

public class EvaluacionFormula_RubroEvaluacionUi extends FormulaCelda {

    public String id;
    public String title;
    public String subtitle;
    public Double peso;
    public int tipoRubro;
    public NotaUi notaUi;//Evaluacion Global

    public EvaluacionFormula_RubroEvaluacionUi() {
    }

    public EvaluacionFormula_RubroEvaluacionUi(String id, String title, String subtitle, int tipoRubro) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.tipoRubro = tipoRubro;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }

    public void setNotaUi(NotaUi notaUi) {
        this.notaUi = notaUi;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getTipoRubro() {
        return tipoRubro;
    }

    public void setTipoRubro(int tipoRubro) {
        this.tipoRubro = tipoRubro;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        boolean equals = false;
        EvaluacionFormula_RubroEvaluacionUi rubro = (EvaluacionFormula_RubroEvaluacionUi) obj;
        if (rubro.getId().equals(id)) {
            equals = true;
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(id);
    }

    @Override
    public String toString() {
        return "RubroEvaluacionUi{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", peso=" + peso +
                ", tipoRubro=" + tipoRubro +
                ", notaUi=" + notaUi +
                '}';
    }
}
