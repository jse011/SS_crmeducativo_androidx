package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

public interface PreviewView extends BaseView<PreviewPresenter> {
    void toggleFacing();

    void onViewFlash();

    void capturePicture();

    void changePage(int page);

    void exitActivity();
}
