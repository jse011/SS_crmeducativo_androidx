package com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities;

import java.util.List;

public class TipoUi {

    private int idTipo;
    private String nombre;
    private List<CampotematicoUi> campotematicoUiList;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CampotematicoUi> getCampotematicoUiList() {
        return campotematicoUiList;
    }

    public void setCampotematicoUiList(List<CampotematicoUi> campotematicoUiList) {
        this.campotematicoUiList = campotematicoUiList;
    }
}
