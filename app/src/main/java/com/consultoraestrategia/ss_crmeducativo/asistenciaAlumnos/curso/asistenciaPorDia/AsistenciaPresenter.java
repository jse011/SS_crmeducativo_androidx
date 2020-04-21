package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.ui.AsistenciaView;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

public interface AsistenciaPresenter extends BasePresenter<AsistenciaView> {
   void valorSelected(AsistenciaUi asistenciaUi, ValorTipoNotaUi valorTipoNotaUi);
   void saveAsistencias();

    void valorUnSelected(AsistenciaUi asistenciaUi);
    void saveJustificacion(AsistenciaJustificaBundel asistenciaJustificaBundel);

}
