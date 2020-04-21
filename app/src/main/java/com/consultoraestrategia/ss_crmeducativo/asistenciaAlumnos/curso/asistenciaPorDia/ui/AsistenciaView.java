package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.ui;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.AsistenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

import java.util.List;

public interface AsistenciaView extends BaseView<AsistenciaPresenter>  {
   void showTableAsistenciaAlumno(List<List<ValorTipoNotaUi>> cellsAsistenciaList, List<ValorTipoNotaUi> ColumnsValorList, List<AlumnosUi> rowsAlumnosList, String color);
    void showEmptyView();
    void showEmptyNota();

    void sincronizar(int programaEducativoId);

    void showDialogJustificacion(AsistenciaJustificaBundel asistenciaJustificaBundel);
}
