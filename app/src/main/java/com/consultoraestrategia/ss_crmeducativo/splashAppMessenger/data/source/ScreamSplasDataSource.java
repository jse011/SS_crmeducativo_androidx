package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source;

import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosContacto;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public interface ScreamSplasDataSource {

    interface ProgressCallBack{
        void onLoad(boolean success, BEDatosContacto beDatosContacto);
        void onNewPreLoad();
        void onRequestProgress(int progress);
        void onResponseProgress(int progress);
    }

    interface CallBack{
        void onLoad(boolean succes);
    }

    RetrofitCancel sincronizarInformacion(ProgressCallBack callBack);
    String getUrlServidorUrl();
}
