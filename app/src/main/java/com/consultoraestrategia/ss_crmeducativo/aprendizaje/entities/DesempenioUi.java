package com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities;


import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 17/01/2018.
 */

public class DesempenioUi {

    private int id;
    private List<String> indicadoresUis = new ArrayList<>();
    private String Descripcion;
    private String icd;
    private boolean ocultaCabecera;
    private List<CampotematicoUi> campotematicoUiList;
    private List<IcdsUi> icdsUiList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public List<String> getIndicadoresUis() {
        return indicadoresUis;
    }

    public void setIndicadoresUis(List<String> indicadoresUis) {
        this.indicadoresUis = indicadoresUis;
    }

    public String getIcd() {
        return icd;
    }

    public void setIcd(String icd) {
        this.icd = icd;
    }

    public boolean isOcultaCabecera() {
        return ocultaCabecera;
    }

    public void setOcultaCabecera(boolean ocultaCabecera) {
        this.ocultaCabecera = ocultaCabecera;
    }

    public List<CampotematicoUi> getCampotematicoUiList() {
        return campotematicoUiList;
    }

    public void setCampotematicoUiList(List<CampotematicoUi> campotematicoUiList) {
        this.campotematicoUiList = campotematicoUiList;
    }

    public List<IcdsUi> getIcdsUiList() {
        return icdsUiList;
    }

    public void setIcdsUiList(List<IcdsUi> icdsUiList) {
        this.icdsUiList = icdsUiList;
    }
}
