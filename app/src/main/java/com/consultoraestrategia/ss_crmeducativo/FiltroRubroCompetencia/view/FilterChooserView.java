package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.view;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.presenter.FilterChooserPresenter;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;

import java.util.ArrayList;
import java.util.List;

public interface FilterChooserView  extends BaseView<FilterChooserPresenter> {

    void mostrarMensaje(String mensaje);

    void onSuccess(ArrayList<CompetenciaCheck> tipoCompetencia);
    void setList(List<CompetenciaCheck>  competenciaCheckList);
}
