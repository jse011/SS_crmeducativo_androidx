package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

interface ScremSplashView extends BaseView<ScremSplashPresenter> {
    void updateListaEnviar(int progress);

    void startMainActivity();

    boolean isInternetAvailable();

    void startOfflineMainActivity();

    void starLoginActivity();

    void close();
}
