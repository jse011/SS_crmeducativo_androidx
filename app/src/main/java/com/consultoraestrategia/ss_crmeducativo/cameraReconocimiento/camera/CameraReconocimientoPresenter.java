package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.camera;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

public interface CameraReconocimientoPresenter extends BasePresenter<CameraReconocimientoView> {

    void onPictureTaken(byte[] jpeg, int mOrientation);

    void onClickReintentarEnvio();
}
