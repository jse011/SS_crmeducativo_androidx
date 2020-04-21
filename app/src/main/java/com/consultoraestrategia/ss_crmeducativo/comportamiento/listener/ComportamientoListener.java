package com.consultoraestrategia.ss_crmeducativo.comportamiento.listener;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;

public interface ComportamientoListener extends BaseFragmentListener {

    void onclick(AlumnoUi alumnoUiselected);
}
