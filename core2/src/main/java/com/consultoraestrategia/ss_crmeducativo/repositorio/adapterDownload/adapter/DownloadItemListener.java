package com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public interface DownloadItemListener {

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);
}
