package com.consultoraestrategia.ss_crmeducativo.repositorio;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

import java.util.List;

public interface RepositorioView extends BaseView<RepositorioPresenter> {

    void showListArchivos(List<RepositorioFileUi> repositorioFileUiList);

    void updateList(RepositorioFileUi repositorioFileUi);

    void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count);

    void setUpdate(RepositorioFileUi repositorioFileUi);

    void leerArchivo(String path);

    void showPickPhoto(boolean enableVideo, int maxCount, List<UpdateRepositorioFileUi> photoPaths);

    void onShowPickDoc(int maxCount, List<UpdateRepositorioFileUi> docPaths);

    void callbackChange(List<RepositorioFileUi> repositorioFileUiList);

    void showFloadButtonAddMultimedia();

    void showFloadButtonAddFile();

    void hideFloadButtonAddFile();

    void showFloadButtonAddVinculo();

    void hideFloadButtonAddVinculo();

    void showDialogaddVinculo(RepositorioFileUi repositorioFileUi);

    boolean isInternetAvailable();

    void showMessageNotConnection();

    void changeVersionTwoList();

    void changeColorButtom(String colorButtom);

    void showListArchivosSelecte(List<RepositorioFileUi> repositorioFileUiList);
}