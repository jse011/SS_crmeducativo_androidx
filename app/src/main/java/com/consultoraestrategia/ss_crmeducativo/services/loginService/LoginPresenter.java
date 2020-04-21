package com.consultoraestrategia.ss_crmeducativo.services.loginService;


import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.services.loginService.ui.LoginEvent;

/**
 * Created by SCIEV on 24/05/2018.
 */

public interface LoginPresenter {
    void attachView(LoginEvent event);
    void onCreate();
    void onDestroy();
    void setExtra(Bundle extras);
    void onHandleIntent();
}
