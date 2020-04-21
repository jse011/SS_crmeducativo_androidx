package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion;


import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.view.MostrarCamposAccionView;

/**
 * Created by kike on 08/05/2018.
 */

public interface MostrarCamposAccionPresenter extends BaseFragmentPresenter<MostrarCamposAccionView> {
    void onExtras(Bundle bundle);
}
