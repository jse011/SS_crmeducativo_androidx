package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;


import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 08/05/2018.
 */
@Parcel
public class CamposAccionUi {
    public enum Tipo {CHILDREN, PARENT, DEFAULT}
    public String key;
    public String binieta;
    public String nombre;
    public boolean hijo;
    public List<CamposAccionUi> campoAccionUiList;
    public Tipo tipo = Tipo.DEFAULT;

    public CamposAccionUi() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CamposAccionUi> getCampoAccionUiList() {
        return campoAccionUiList;
    }

    public void setCampoAccionUiList(List<CamposAccionUi> campoAccionUiList) {
        this.campoAccionUiList = campoAccionUiList;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void addCampoAccion(CamposAccionUi campotematicoUi) {
        if (campoAccionUiList == null) {
            campoAccionUiList = new ArrayList<>();
        }
        campoAccionUiList.add(campotematicoUi);
    }

    public boolean isHijo() {
        return hijo;
    }

    public void setHijo(boolean hijo) {
        this.hijo = hijo;
    }

    public String getBinieta() {
        return binieta;
    }

    public void setBinieta(String binieta) {
        this.binieta = binieta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CamposAccionUi)) return false;

        CamposAccionUi that = (CamposAccionUi) o;

        return getKey() != null ? getKey().equals(that.getKey()) : that.getKey() == null;
    }

    @Override
    public int hashCode() {
        return getKey() != null ? getKey().hashCode() : 0;
    }
}


