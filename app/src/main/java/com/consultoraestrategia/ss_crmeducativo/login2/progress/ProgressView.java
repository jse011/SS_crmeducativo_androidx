package com.consultoraestrategia.ss_crmeducativo.login2.progress;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

public interface ProgressView extends BaseView<ProgressPrenter> {
    void finshedDialog();

    void showDialogError();

    void updatePorcebtaje(int porcentaje);
}
