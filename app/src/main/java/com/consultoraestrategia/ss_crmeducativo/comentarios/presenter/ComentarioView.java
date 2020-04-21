package com.consultoraestrategia.ss_crmeducativo.comentarios.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;

import java.util.List;

public interface ComentarioView extends BaseView<ComentarioPresenter> {

    void clickCreateComentario(Object item);

    void showListComentarios(List<Object> objectList);

    void clearComentarios();

    void hideComentario();

    void onSaveSucceFull();
}
