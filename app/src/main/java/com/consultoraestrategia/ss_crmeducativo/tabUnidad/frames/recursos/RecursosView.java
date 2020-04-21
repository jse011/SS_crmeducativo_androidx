package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.recursos;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RecursosDidacticoUi;

import java.util.List;

public interface RecursosView extends BaseView<RecursosPresenter> {
    void showRecursoDidactico(List<RecursosDidacticoUi> recursosDidacticoUis);

    void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count);

    void setUpdate(RepositorioFileUi repositorioFileUi);

    void showVinculo(String url);

    void showYoutube(String url);

    void leerArchivo(String path);

    void showProgresBar();

    void hideProgressBar();
}
