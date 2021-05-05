package com.consultoraestrategia.ss_crmeducativo.repositorio;


import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface RepositorioPresenter extends BaseFragmentPresenter<RepositorioView> {

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);

    void onClickAddFile();

    void onSalirSelectPiket(ArrayList<String> photoPaths);

    void onSalirSelectPiket(Map<Uri,String> fileName);

    void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi);

    void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi);

    void onClickClose(UpdateRepositorioFileUi repositorioFileUi);

    void changeList(List<RepositorioFileUi> repositorioFileUiList);

    void onClickCheck(RepositorioFileUi repositorioFileUi);

    List<RepositorioFileUi> getListFiles();

    void onClickAddMultimedia();

    void onClickAddVinculo();

    void onClickAceptarDialogVinculo(RepositorioFileUi repositorioFileUi);

    void onProgressDownload(double progress, RepositorioFileUi repositorioFileUi);

    void finishedDownload(long downloadId);

    void canceledDownload(long downloadId);
}
