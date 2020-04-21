package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.aprendizaje;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;

import java.util.List;

public interface AprendizajeView extends BaseView<AprendizajePresenter> {
    void showCompetencias(List<CompetenciaUi> competenciaUis);

    void showCampoAccion(List<CampoAccionUi> campoAccionUis);

    void hideVacioCampos();

    void showVacioCampos();

    void showVacioCompetencias();

    void hideVacioCompetencias();

    void showProgressCamposTematico();

    void hideProgressCamposTematicos();

}
