package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.presenter.ComportAlumnoCPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public interface ComportAlumnoCview extends BaseView<ComportAlumnoCPresenter> {
    void showListComportamiento(List<ComportamientoUi> comportamientoUiList);
    void dialogDeleteComportamiento(CharSequence message);
    void lauchEditComportamiento(ComportamientoUi comportamientoUi);
    void updateComportamiento(ComportamientoUi comportamientoUi);
    void deleteComportamiento(ComportamientoUi comportamientoUi, int programaEducativoId);
    void showEmptyText();
    void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count);

    void setUpdate(RepositorioFileUi repositorioFileUi);

    void leerArchivo(String path);

    void getFileNameDowload(RepositorioFileUi repositorioFileUi);
}
