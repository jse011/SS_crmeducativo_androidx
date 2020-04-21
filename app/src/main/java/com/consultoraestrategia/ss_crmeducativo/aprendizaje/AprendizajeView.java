package com.consultoraestrategia.ss_crmeducativo.aprendizaje;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public interface AprendizajeView extends BaseView<AprendizajePresenter> {
    void addAprendizaje(Object item);

    void setAprendizaje(List<Object> aprendizaje);

    void clearAprendizaje();

    void showProgress();

    void hideProgress();

    void showVinculo(String url);

    void showYoutube(String url);

    void leerArchivo(String path);

    void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count);

    void setUpdate(RepositorioFileUi repositorioFileUi);
}
