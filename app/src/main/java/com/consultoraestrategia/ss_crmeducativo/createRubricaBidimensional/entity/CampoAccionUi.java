package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */
@Parcel
public class CampoAccionUi extends CheckedObject implements Serializable {


    public IndicadorCampoAccionUi indicadorCampoAccionUi;
    public enum Tipo{CHILDREN,PARENT,DEFAULD}
    public int id;
    public String titulo;
    public List<CampoAccionUi> campoAccionUiList;
    public Tipo tipo = Tipo.DEFAULD;
    public IndicadorUi indicadorUi;
    public CapacidadUi capacidadUi;
    public CompetenciaUi competenciaUi;
    public CampoAccionUi padre;
    public CampoAccionUi() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.title = titulo;
    }

    public List<CampoAccionUi> getCampoAccionUiList() {
        return campoAccionUiList;
    }

    public void setCampoAccionUiList(List<CampoAccionUi> campoAccionUiList) {
        this.campoAccionUiList = campoAccionUiList;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }

    public CapacidadUi getCapacidadUi() {
        return capacidadUi;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

    public CompetenciaUi getCompetenciaUi() {
        return competenciaUi;
    }

    public void setCompetenciaUi(CompetenciaUi competenciaUi) {
        this.competenciaUi = competenciaUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CampoAccionUi)) return false;

        CampoAccionUi that = (CampoAccionUi) o;

        if (getId() != that.getId()) return false;
        if (getIndicadorUi() != null ? !getIndicadorUi().equals(that.getIndicadorUi()) : that.getIndicadorUi() != null)
            return false;
        if (getCapacidadUi() != null ? !getCapacidadUi().equals(that.getCapacidadUi()) : that.getCapacidadUi() != null)
            return false;
        return getCompetenciaUi() != null ? getCompetenciaUi().equals(that.getCompetenciaUi()) : that.getCompetenciaUi() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getIndicadorUi() != null ? getIndicadorUi().hashCode() : 0);
        result = 31 * result + (getCapacidadUi() != null ? getCapacidadUi().hashCode() : 0);
        result = 31 * result + (getCompetenciaUi() != null ? getCompetenciaUi().hashCode() : 0);
        return result;
    }

    public CampoAccionUi simpleClone() {
        CampoAccionUi campoAccionUi = new CampoAccionUi();
        campoAccionUi.setId(id);
        campoAccionUi.setTitulo(titulo);
        campoAccionUi.setTipo(tipo);
        campoAccionUi.setChecked(checked);
        campoAccionUi.setTitle(title);
        campoAccionUi.setCapacidadUi(capacidadUi);
        campoAccionUi.setCompetenciaUi(competenciaUi);
        campoAccionUi.setIndicadorUi(indicadorUi);
        campoAccionUi.setIndicadorCampoAccionUi(indicadorCampoAccionUi);
        return campoAccionUi;
    }

    @Override
    public String toString() {
        return "CampoAccionUi{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", campoAccionUiList=" + campoAccionUiList +
                ", tipo=" + tipo +
                ", indicadorUi=" + indicadorUi +
                ", capacidadUi=" + capacidadUi +
                ", competenciaUi=" + competenciaUi +
                '}';
    }

    public void setPadre(CampoAccionUi padre) {
        this.padre = padre;
    }

    public CampoAccionUi getPadre() {
        return padre;
    }

    public IndicadorCampoAccionUi getIndicadorCampoAccionUi() {
        return indicadorCampoAccionUi;
    }

    public void setIndicadorCampoAccionUi(IndicadorCampoAccionUi indicadorCampoAccionUi) {
        this.indicadorCampoAccionUi = indicadorCampoAccionUi;
    }
}
