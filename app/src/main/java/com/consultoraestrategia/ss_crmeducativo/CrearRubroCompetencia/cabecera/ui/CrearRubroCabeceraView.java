package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

/**
 * Created by SCIEV on 28/02/2018.
 */

public interface CrearRubroCabeceraView extends BaseView<BasePresenterImpl<CrearRubroCabeceraView>> {
    void onSelectPager(int posicion);
    void onAceptar();
    void onCancelar();

    void onAcceptButtom();
}
