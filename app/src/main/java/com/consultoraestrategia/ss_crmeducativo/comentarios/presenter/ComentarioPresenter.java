package com.consultoraestrategia.ss_crmeducativo.comentarios.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;

public interface ComentarioPresenter extends BaseFragmentPresenter<ComentarioView> {

    void setExtras(int sesionAprendizajeId);

    void onResumeFragment();

    void onClickSaveComentario(String contenido);

}
