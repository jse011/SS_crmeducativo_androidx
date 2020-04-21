package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui.ViewAsistenciaCurso;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;

import java.util.List;

public interface PresenterAsistenciaCurso extends BaseFragmentPresenter<ViewAsistenciaCurso>  {
    void onResumenFragment(String idCalendarioPeriodo);

    void onClickAsistencia(FechaAsistenciaUi fechaAsistenciaUi);
    CRMBundle  getCrmBundle();
}
