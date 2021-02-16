package com.consultoraestrategia.ss_crmeducativo.utils.AndroidOnline;

public interface Online {
    void online(Callback callback);
    interface Callback{
        void onLoad(boolean success);
    }
}
