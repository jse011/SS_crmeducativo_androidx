package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview;

import android.graphics.Bitmap;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.InicialView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.SendView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.SentimientoView;

public interface PreviewPresenter extends BasePresenter<PreviewView> {

    void onResumedFragment(SentimientoView resumedFragment, int position);

    void onBackPrincipalClicked();

    void toggleFacing();

    void onViewFlash();

    void capturePicture();

    void flashOn();

    void flashOff();

    void flashAuto();

    void onResumedSendView(SendView resumedFragment);

    void onClickBtnSave();

    void onClickCancelar();

    void onResumedInicialView(InicialView inicialView);

    void setAtachInicialView(InicialView inicialView);

    void btnSiguiente();

    void btnAtras();

    void btnIniciar();

    void onPictureTaken(String path_img, int position);
}
