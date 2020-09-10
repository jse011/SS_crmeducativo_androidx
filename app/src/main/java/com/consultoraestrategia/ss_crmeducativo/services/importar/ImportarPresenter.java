package com.consultoraestrategia.ss_crmeducativo.services.importar;


import android.os.Bundle;

import androidx.work.Data;

import com.consultoraestrategia.ss_crmeducativo.services.importar.ui.ImportarEvent;

/**
 * Created by SCIEV on 24/05/2018.
 */

public interface ImportarPresenter {
    void attachView(ImportarEvent event);
    void onCreate();
    void onDestroy();
    void onHandleIntent();
    void setExtra(Data extra);
}
