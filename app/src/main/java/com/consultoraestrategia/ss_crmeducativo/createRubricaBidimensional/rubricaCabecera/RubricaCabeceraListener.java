package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;

/**
 * Created by @stevecampos on 15/02/2018.
 */

public interface RubricaCabeceraListener extends BaseFragmentListener {
    void onBtnTipoEvaluacionClicked();

    void onBtnFormaEvaluacionClicked();

    void onBtnTipoNotaClicked();

    void onBtnEscalaClicked();

    void onBtnCompetenciaListClicked();

    void onBtnCampoAccionListClicked();

    void onBtnBuscarCompetenciaListClicked();
    void onBtnEstrategiaEvaluacionClicked();


    void onTextChangedEditarAlias(String texto);
}
