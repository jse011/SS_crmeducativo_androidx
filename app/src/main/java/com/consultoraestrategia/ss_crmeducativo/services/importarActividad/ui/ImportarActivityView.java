package com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ImportarActivityPresenter;

/**
 * Created by SCIEV on 26/07/2018.
 */

public interface ImportarActivityView extends BaseView<ImportarActivityPresenter> {
    void onStartProgress(long millisInFuture, long countDownInterval);
    void onEndProgress();
    void updateProgressText(String message);
    void showImagenSucces();
    void showImagenError();
    void hideActivity();
}
