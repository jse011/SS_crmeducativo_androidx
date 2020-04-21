package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewPresenter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

public interface SentimientoView {
    void onResumeViewPager();
    void onPauseViewPager();
    void setContente(SentimientoUi sentimientoUi);

    void setPresentar(PreviewPresenter presenter);
    void showPreview(SentimientoUi sentimientoUi, PersonaUi personaUi);
    void hideCamera();

    void hidePreview();

    void showCamera();

    void flash(int flash);
}
