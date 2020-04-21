package com.consultoraestrategia.ss_crmeducativo.services.importarActividad;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivityView;

/**
 * Created by SCIEV on 26/07/2018.
 */

public interface ImportarActivityPresenter extends BasePresenter<ImportarActivityView> {

    void onImportarCountDownTimerFinish();

    void onImportarCountDownTimerCount(int count);

    void onCreateView();

    void postImagenSucces();

    void postImagenError();
}
