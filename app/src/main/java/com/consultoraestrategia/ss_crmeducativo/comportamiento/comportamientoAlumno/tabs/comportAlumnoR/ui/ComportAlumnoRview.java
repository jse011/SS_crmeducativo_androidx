package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.presenter.ComportAlumnoRPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

import java.util.List;

public interface ComportAlumnoRview extends BaseView<ComportAlumnoRPresenter> {
    void showList(List<ComportamientoUi> comportamientoUiList);
    void showEmptyText();
}
