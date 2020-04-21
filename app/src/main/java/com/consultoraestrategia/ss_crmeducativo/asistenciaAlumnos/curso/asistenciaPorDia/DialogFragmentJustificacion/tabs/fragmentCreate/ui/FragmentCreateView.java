package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.ui;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.presenter.CreateJusticicacionPresenter;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;

import java.util.List;

public interface FragmentCreateView extends BaseView<CreateJusticicacionPresenter> {

    void setDatos(AlumnosUi alumnosUi, long fechaA, JustificacionUi justificacionUi);
    void setTipoJustificacion(TipoUi tipoJustificacion);
    void showListArchivos(List<RepositorioFileUi> value);
}
