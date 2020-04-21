package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

/**
 * Created by @stevecampos on 6/02/2018.
 */

public interface IndicadorChooserPresenter  extends BasePresenter<IndicadorChooserView> {
    void setExtra(Bundle arguments);

    void onDestroyView();

    void onClickCampoAccion(CampoAccionUi campoAccionUi);

    void onClickIndicador(IndicadorUi indicadorUi);
}
