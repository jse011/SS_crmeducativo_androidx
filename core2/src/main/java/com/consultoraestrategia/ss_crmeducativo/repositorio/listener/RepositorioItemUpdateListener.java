package com.consultoraestrategia.ss_crmeducativo.repositorio.listener;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

public interface RepositorioItemUpdateListener {

    void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi);

    void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi);

    void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi);

    void onClickArchivo(UpdateRepositorioFileUi updateRepositorioFileUi);
}
