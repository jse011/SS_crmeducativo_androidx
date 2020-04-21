package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewPresenter;

import java.util.ArrayList;

public interface SendView {
    void setImgaPreview(PersonaUi personaUi, ArrayList<SentimientoUi> sentimientoList);
    void setPresentar(PreviewPresenter presenter);
    void progress(String nombre, int position);
    void onErrorSendStorege();
    void onSuccesSendStorege();

    void hideContenDownload();

    void showContenDownload();

    void showBtnSend();

    void hideBtnSend();
}
