package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.seleccionarCompetencias;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */

public interface SeleccionarCompetenciaView extends BaseView<SeleccionarCompetenciaPresenter> {

    void showCompetencias(List<CompetenciaUi> competenciasBase, List<CompetenciaUi> competenciasTransversales, List<CompetenciaUi> competenciasEnfoque);

    void devolverResultado(List<CompetenciaUi> competenciaList);

    /*void showCompetenciasBase(List<CompetenciaUi> items);
    void showCompetenciasTransversales(List<CompetenciaUi> items);
    void showCompetenciasEnfoque(List<CompetenciaUi> items);*/
}
