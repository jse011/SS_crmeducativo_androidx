package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui;


import android.content.Context;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

public interface ListenerComportAlumnoC extends BaseFragmentListener {
    void onclickOpciones(ComportamientoUi comportamientoUi, View view, Context context);
}
