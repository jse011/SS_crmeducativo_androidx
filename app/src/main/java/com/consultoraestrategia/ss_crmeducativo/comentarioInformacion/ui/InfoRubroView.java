package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui;





import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;

import java.util.List;

/**
 * Created by Jse on 15/09/2018.
 */

public interface InfoRubroView extends BaseView<InfoRubroPresenter> {

    void showListComentarios(List<MensajeUi> mensajeUiList);

    void showListComentariosArchivos(List<ArchivoComentarioUi> execute);

    void cerrarDialog(String evaluacionProcesoId);

    void showBtnSendComentario();

    void hideBtnSendComentario();

    void clearTextInpuComentario();

}
