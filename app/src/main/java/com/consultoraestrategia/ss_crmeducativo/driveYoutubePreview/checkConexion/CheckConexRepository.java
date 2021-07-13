package com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.checkConexion;

public interface CheckConexRepository {
    void online(Callback callback);
    interface Callback{
        void onLoad(boolean success);
    }
}

