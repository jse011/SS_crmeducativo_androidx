package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source;

import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.repositorio.ScreamRemoteDataSource;

public class ScreamSplashRepository implements ScreamSplasDataSource{
    private ScreamRemoteDataSource remoteDataSource;

    public ScreamSplashRepository(ScreamRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public RetrofitCancel sincronizarInformacion(ProgressCallBack callBack) {
        return remoteDataSource.sincronizarInformacion(callBack);
    }

    @Override
    public String getUrlServidorUrl() {
        return remoteDataSource.getUrlServidorUrl();
    }
}
