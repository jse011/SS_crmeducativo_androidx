package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;

import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */

public interface SeleccionarCompetenciaView extends BaseView<SeleccionarCompetenciaPresenter> {

    void showCompetencias(List<Object> competenciasBase, List<Object> competenciasTransversales, List<Object> competenciasEnfoque);
    void devolverResultado(List<CampoAccionUi> competenciaList);
    void flitroCompetencia(String filtro);

}
