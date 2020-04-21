package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoRubroView;

/**
 * Created by Jse on 15/09/2018.
 */

public interface InfoRubroPresenter extends BaseFragmentPresenter<InfoRubroView> {

    void closeDialog();

    void onTextChangedEditComentario(String toString);

    void saveComentario(String valueOf);

    void onClickComentarioNormal(MensajeUi mensajeUi);

    void saveComentarioArchivo(ArchivoComentarioUi archivoComentarioUi);

    void removerComentarioArchivo(ArchivoComentarioUi archivoComentarioUi);
}
