package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.dialogAsistencia;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;


public interface AsistenciaDialogView extends BaseView<AsistenciaDialogPresenter> {

    void onClickAsistencia(AsistenciaUi asistenciaUi);

    void close();
}
