package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.presenter;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.view.FilterChooserView;
import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;

import java.util.ArrayList;

public interface FilterChooserPresenter extends BasePresenter<FilterChooserView> {
    void filterChooserCheckItem(CompetenciaCheck tipoCompetencia);

    void onAceptar();

    void onViewCreate();

    void setExtras(ArrayList<CompetenciaCheck> competenciaCheckArrayList);


}
