package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class CampoTematicoUi {
    private int id;
    private String titulo;
    private TipoCamTemEn tipoCampoTematicoE;
    private String binieta;
    private List<CampoTematicoUi> campoTematicoUiList;

    public CampoTematicoUi() {
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
    }

    public TipoCamTemEn getTipoCampoTematicoE() {
        return tipoCampoTematicoE;
    }

    public void setTipoCampoTematicoE(TipoCamTemEn tipoCampoTematicoE) {
        this.tipoCampoTematicoE = tipoCampoTematicoE;
    }

    public String getBinieta() {
        return binieta;
    }

    public void setBinieta(String binieta) {
        this.binieta = binieta;
    }

    public void addCampoAccion(CampoTematicoUi campotematicoUi) {
        if(this.campoTematicoUiList==null)this.campoTematicoUiList = new ArrayList<>();
        this.campoTematicoUiList.add(campotematicoUi);
    }

    public List<CampoTematicoUi> getCampoTematicoUiList() {
        return campoTematicoUiList;
    }

    public void setCampoTematicoUiList(List<CampoTematicoUi> campoTematicoUiList) {
        this.campoTematicoUiList = campoTematicoUiList;
    }
}
