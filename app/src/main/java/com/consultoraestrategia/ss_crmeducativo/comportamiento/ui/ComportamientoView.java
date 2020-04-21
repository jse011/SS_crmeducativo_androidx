package com.consultoraestrategia.ss_crmeducativo.comportamiento.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.presenter.ComportamientoPresenter;

import java.util.List;

public interface ComportamientoView extends BaseView<ComportamientoPresenter> {
    void setComportamientos(List<AlumnoUi> alumnoUiList);

    void onResumenFragment(String idcaledarioPeriodo);

    void lauchActivity(CRMBundle crmBundle, int alumnoId);

    void lauchDialogCreate(CRMBundle crmbundle);

    void showEmptyText();

    void hideEmptyText();
}
