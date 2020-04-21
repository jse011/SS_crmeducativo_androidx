package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.view.MostrarIndicadoresView;

/**
 * Created by kike on 10/05/2018.
 */

public interface MostrarIndicadoresPresenter extends BaseFragmentPresenter<MostrarIndicadoresView> {
    void onExtras(Bundle bundle);
}
