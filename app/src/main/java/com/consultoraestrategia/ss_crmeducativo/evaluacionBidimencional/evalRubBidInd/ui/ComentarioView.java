package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;

import java.util.List;

public interface ComentarioView  {

    void showListComentarios(List<MensajeUi>mensajeUiList);

    void showBtnSendComentario();

    void hideBtnSendComentario();

    void showListComentariosArchivos(List<ArchivoUi> objects);

    void clearTextInpuComentario();
}
