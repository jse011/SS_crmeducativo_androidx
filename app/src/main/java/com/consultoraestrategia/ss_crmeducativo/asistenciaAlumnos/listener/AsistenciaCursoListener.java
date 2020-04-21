package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.listener;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;

public interface AsistenciaCursoListener {

    void showAsistenciaCursoItemDialog(FechaAsistenciaUi fechaAsistenciaUi);

    void onClickAsistencia(FechaAsistenciaUi fechaAsistenciaUi);

    void onResume();
}
