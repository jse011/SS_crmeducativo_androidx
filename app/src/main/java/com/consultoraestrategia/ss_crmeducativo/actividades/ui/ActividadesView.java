package com.consultoraestrategia.ss_crmeducativo.actividades.ui;

import com.consultoraestrategia.ss_crmeducativo.actividades.ActividadesPresenter;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

/**
 * Created by kike on 07/02/2018.
 */

public interface ActividadesView extends BaseView<ActividadesPresenter> {

    void showProgress();

    void hideProgress();

    void showListObject(List<Object> objectList);

    void addActividades(Object item);

    void clearActividades();

    void mostrarMensaje(String mensaje);

    void showMessage();

    void hideMessage();


    void showVinculo(String url);

    void showYoutube(String url);

    void leerArchivo(String path);

    void setUpdate(RepositorioFileUi repositorioFileUi);

    void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count);
}
