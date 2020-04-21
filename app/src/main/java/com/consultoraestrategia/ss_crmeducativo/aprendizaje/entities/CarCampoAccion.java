package com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities;

import java.util.List;

/**
 * Created by SCIEV on 31/07/2018.
 */

public class CarCampoAccion {
    private List<CampotematicoUi> competenciaUiListCompetenciaTransversales;

    private List<CampotematicoUi> competenciaUiListEnfoqueTransversal;

    private List<CampotematicoUi> competenciaUiListCompetenciaBase;

    public List<CampotematicoUi> getCompetenciaUiListCompetenciaTransversales() {
        return competenciaUiListCompetenciaTransversales;
    }

    public void setCompetenciaUiListCompetenciaTransversales(List<CampotematicoUi> competenciaUiListCompetenciaTransversales) {
        this.competenciaUiListCompetenciaTransversales = competenciaUiListCompetenciaTransversales;
    }

    public List<CampotematicoUi> getCompetenciaUiListEnfoqueTransversal() {
        return competenciaUiListEnfoqueTransversal;
    }

    public void setCompetenciaUiListEnfoqueTransversal(List<CampotematicoUi> competenciaUiListEnfoqueTransversal) {
        this.competenciaUiListEnfoqueTransversal = competenciaUiListEnfoqueTransversal;
    }

    public List<CampotematicoUi> getCompetenciaUiListCompetenciaBase() {
        return competenciaUiListCompetenciaBase;
    }

    public void setCompetenciaUiListCompetenciaBase(List<CampotematicoUi> competenciaUiListCompetenciaBase) {
        this.competenciaUiListCompetenciaBase = competenciaUiListCompetenciaBase;
    }
}
