package com.consultoraestrategia.ss_crmeducativo.core2.listener;

/**
 * Created by Jse on 27/12/2018.
 */

public interface Core2Listener {

    void onStart(Class<?> aClass);
    void onLoad(int count);
    void onFinish();
    void onFinishSimple();
}
