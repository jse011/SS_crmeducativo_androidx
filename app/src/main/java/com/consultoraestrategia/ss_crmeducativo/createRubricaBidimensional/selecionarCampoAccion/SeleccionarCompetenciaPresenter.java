package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;

/**
 * Created by @stevecampos on 6/04/2018.
 */

public interface SeleccionarCompetenciaPresenter extends BasePresenter<SeleccionarCompetenciaView> {
    void onAceptarButtonClicked();

    void onCheckedCamposAccion(CampoAccionUi campoAccionUi);

    void onQueryTextChange(String newText);

    void onCheckedIndicador(IndicadorCampoAccionUi indicadorCompetenciaUi);
}
