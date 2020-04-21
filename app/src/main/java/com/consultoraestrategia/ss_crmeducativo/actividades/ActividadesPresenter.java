package com.consultoraestrategia.ss_crmeducativo.actividades;

import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ActividadesUi;
import com.consultoraestrategia.ss_crmeducativo.actividades.ui.ActividadesView;
import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

/**
 * Created by kike on 08/02/2018.
 */

public interface ActividadesPresenter extends BaseFragmentPresenter<ActividadesView> {

    void setExtras(int cargaCursoId, int sesionAprendizajeId, int backgroundColor, int personaId);

    void onResumeFragment();

    void onSelectActividad(ActividadesUi actividadesUi);

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);
}
