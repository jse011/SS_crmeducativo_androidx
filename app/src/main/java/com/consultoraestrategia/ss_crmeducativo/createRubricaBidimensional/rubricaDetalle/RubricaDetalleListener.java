package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;

/**
 * Created by @stevecampos on 16/02/2018.
 */

public interface RubricaDetalleListener extends BaseFragmentListener {
    void onBtnTipoNivelClicked();

    void onTableTitleClicked();

    void onBtnDetalleCampoAccionListClicked();

    void onBtnDetalleCompetenciaListClicked();

    void onBtnDetalleBuscarCampoAccionListClicked();
}
