package com.consultoraestrategia.ss_crmeducativo.repositorio.listener;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public interface RepositorioItemListener {

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickCheck(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);
}
