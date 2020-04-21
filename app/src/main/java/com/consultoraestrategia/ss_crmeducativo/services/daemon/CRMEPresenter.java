package com.consultoraestrategia.ss_crmeducativo.services.daemon;


import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.services.daemon.ui.CRMEEvent;

/**
 * Created by SCIEV on 24/05/2018.
 */

public interface CRMEPresenter {
    void attachView(CRMEEvent event);
    void onCreate();
    void onDestroy();
    void onHandleIntent();
    void setExtra(Bundle extra);
}
