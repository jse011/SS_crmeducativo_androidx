package com.consultoraestrategia.ss_crmeducativo.aprendizaje;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.Extras;
import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

/**
 * Created by SCIEV on 15/01/2018.
 */

public interface AprendizajePresenter extends BaseFragmentPresenter<AprendizajeView> {
    void setExtras(Extras extra);

    void onResumeFragment();

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);
}
