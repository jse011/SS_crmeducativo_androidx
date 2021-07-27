package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui.ComportAlumnoCview;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public interface ComportAlumnoCPresenter extends BaseFragmentPresenter<ComportAlumnoCview> {
    void onSelectOpcionMenuComportamiento(ComportamientoUi comportamientoUi, int positionMenu);
    void deleteComportamiento();

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);

    void onClickComportamientoAlumno(ComportamientoUi comportamientoUi);

    void canceledDownload(long id);

    void finishedDownload(long id);
}
