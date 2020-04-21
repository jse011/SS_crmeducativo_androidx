package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista;


import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public interface CamposAccionChooserCallback {
    void onCheckedCamposAccion(CampoAccionUi campoAccionUi);

    void onCheckedIndicador(IndicadorCampoAccionUi indicadorCompetenciaUi);
}
