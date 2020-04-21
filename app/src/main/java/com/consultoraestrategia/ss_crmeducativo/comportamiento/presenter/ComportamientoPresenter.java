package com.consultoraestrategia.ss_crmeducativo.comportamiento.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.ui.ComportamientoView;

public interface ComportamientoPresenter extends BaseFragmentPresenter<ComportamientoView> {
    void onResumeFragment(String calendarioPeriodoId);

    void setselectedAlumno(AlumnoUi alumnoUiselected);

    void onFabClicked();
}
