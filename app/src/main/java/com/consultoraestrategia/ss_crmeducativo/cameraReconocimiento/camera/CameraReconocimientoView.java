package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.camera;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

public interface CameraReconocimientoView extends BaseView<CameraReconocimientoPresenter> {

    void setCamaraPersona(String nombre);

    void showBarProgress(int progress);

    void successProgress();

    void errorProgress();

}
