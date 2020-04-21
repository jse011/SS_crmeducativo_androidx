package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewPresenter;

public interface InicialView {

    void addList(SentimientoUi sentimientoUi);

    void setPresenter(PreviewPresenter presenter);

    void showProgress();

    void hideProgress();
}
