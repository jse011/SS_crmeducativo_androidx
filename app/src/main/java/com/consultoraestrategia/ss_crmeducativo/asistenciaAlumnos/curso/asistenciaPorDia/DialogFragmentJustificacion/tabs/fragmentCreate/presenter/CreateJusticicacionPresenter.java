package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.presenter;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.ui.FragmentCreateView;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;

public interface CreateJusticicacionPresenter  extends  BaseFragmentPresenter<FragmentCreateView> {
    void showTiposJustificacion();
    AsistenciaJustificaBundel saveJustificacion(String razon);
}
