package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 16/10/2017.
 */

@Parcel
public class RubroEvaluacionUi {
    public String id;
    public String title;
    public String subtitle;
    public Double peso;
    public NotaUi notaUi;//Evaluacion Global
    public boolean tipo;

    public RubroEvaluacionUi() {
    }

    public RubroEvaluacionUi(String id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
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

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        boolean equals = false;
        RubroEvaluacionUi rubro = (RubroEvaluacionUi) obj;
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
        return "id: " + id + "\n" +
                "title: " + title + "\n" +
                "subtitle: " + subtitle;
    }
}
