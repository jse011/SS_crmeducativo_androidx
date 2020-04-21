package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.recursos;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public interface RecursosPresenter extends BaseFragmentPresenter<RecursosView> {
    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);
}
