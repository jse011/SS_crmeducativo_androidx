package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion;


import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoRubroView;

import java.util.Map;

/**
 * Created by Jse on 15/09/2018.
 */

public interface InfoRubroPresenter extends BaseFragmentPresenter<InfoRubroView> {

    void closeDialog();

    void onTextChangedEditComentario(String toString);

    void saveComentario(String valueOf);

    void onClickComentarioNormal(MensajeUi mensajeUi);

    void onClickCamera();

    void onClickGalery();

    void removerComentarioArchivo(ArchivoUi archivoComentarioUi);

    void onUpdload(Map<Uri, String> photoPaths);
}
