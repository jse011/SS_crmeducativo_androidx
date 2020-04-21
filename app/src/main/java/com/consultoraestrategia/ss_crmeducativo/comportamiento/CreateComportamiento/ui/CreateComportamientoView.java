package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.presenter.CreateComportamientoPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import java.util.List;


public interface CreateComportamientoView extends BaseView<CreateComportamientoPresenter> {

    void onResumenSave();
    void onResumenEdit();
    void setParametroDisenio(ParametroDisenioUi parametroDisenioUi);

    void sincronizar(int programaEducativoId);
}
