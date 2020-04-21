package com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 22/01/2018.
 */

public class CampotematicoUi
{
    public enum Tipo{CHILDREN,PARENT,DEFAULD}
    private Tipo tipo = Tipo.DEFAULD;
    private int id;
    private String descripcion;
    boolean checked;
    private boolean hijo;
    private int grupo;
    private boolean disable;
    private int numeracion;
    private int tipoIds;
    private String tipoDescricion;
    CampotematicoUi campotematicoUiPadre;
    List<CampotematicoUi> campotematicoUis = new ArrayList<>();
    List<CampotematicoUi> campotematicoUiListHijo = new ArrayList<>();

    public List<CampotematicoUi> getCampotematicoUiListHijo() {
        return campotematicoUiListHijo;
    }

    public void setCampotematicoUiListHijo(List<CampotematicoUi> campotematicoUiListHijo) {
        this.campotematicoUiListHijo = campotematicoUiListHijo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isHijo() {
        return hijo;
    }

    public void setHijo(boolean hijo) {
        this.hijo = hijo;
    }

    public List<CampotematicoUi> getCampotematicoUis() {
        return campotematicoUis;
    }

    public void setCampotematicoUis(List<CampotematicoUi> campotematicoUis) {
        this.campotematicoUis = campotematicoUis;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CampotematicoUi getCampotematicoUiPadre() {
        return campotematicoUiPadre;
    }

    public void setCampotematicoUiPadre(CampotematicoUi campotematicoUiPadre) {
        this.campotematicoUiPadre = campotematicoUiPadre;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getTipoIds() {
        return tipoIds;
    }

    public void setTipoIds(int tipoIds) {
        this.tipoIds = tipoIds;
    }

    public String getTipoDescricion() {
        return tipoDescricion;
    }

    public void setTipoDescricion(String tipoDescricion) {
        this.tipoDescricion = tipoDescricion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CampotematicoUi that = (CampotematicoUi) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void addCampotematicos(CampotematicoUi campotematicoUi){
        campotematicoUis.add(campotematicoUi);
    }

    @Override
    public String toString() {
        return "CampotematicoUi{" +
                "tipo=" + tipo +
                ", id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", checked=" + checked +
                ", hijo=" + hijo +
                ", grupo=" + grupo +
                ", disable=" + disable +
                ", numeracion=" + numeracion +
                ", tipoIds=" + tipoIds +
                ", tipoDescricion='" + tipoDescricion + '\'' +
                ", campotematicoUiPadre=" + campotematicoUiPadre +
                ", campotematicoUis=" + campotematicoUis +
                ", campotematicoUiListHijo=" + campotematicoUiListHijo +
                '}';
    }
}
