package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.PresenterAsistenciaCurso;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;

import java.util.List;

public interface ViewAsistenciaCurso extends BaseView<PresenterAsistenciaCurso> {
    void onResumenFragment(String idCalendarioPeriodo);

    void showListAsistenciaCursos(List<Object> asistenciaUiList, int calendarioPeriodoId);

    void showAsistenciaActivity(AsistenciaUi asistenciaUi);

}
