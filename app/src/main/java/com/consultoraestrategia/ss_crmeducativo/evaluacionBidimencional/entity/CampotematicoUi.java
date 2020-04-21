package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 22/01/2018.
 */

public class CampotematicoUi
{
    private int id;
    private String descripcion;
    boolean checked;
    private boolean hijo;
    private int grupo;
    private boolean disable;
    private int numeracion;
    CampotematicoUi campotematicoUiPadre;
    List<CampotematicoUi> campotematicoUis = new ArrayList<>();

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
}
