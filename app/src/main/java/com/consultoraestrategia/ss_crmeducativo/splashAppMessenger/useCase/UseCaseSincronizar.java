package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.useCase;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosContacto;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.ScreamSplasDataSource;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.ScreamSplashRepository;

public class UseCaseSincronizar implements UseCaseLoginSincrono<UseCaseSincronizar.Request, UseCaseSincronizar.Response> {

    private ScreamSplashRepository repository;

    public UseCaseSincronizar(ScreamSplashRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(Request request, final Callback<Response> callback) {
        return repository.sincronizarInformacion(new ScreamSplasDataSource.ProgressCallBack() {
            @Override
            public void onLoad(boolean success, BEDatosContacto beDatosContacto) {
                callback.onResponse(success, new Response());
            }

            @Override
            public void onNewPreLoad() {

            }

            @Override
            public void onRequestProgress(int progress) {
                callback.onResponse(true, new ResponseUploadProgress(progress));
            }

            @Override
            public void onResponseProgress(int progress) {
                callback.onResponse(false, new ResponseDownloadProgress(progress));
            }
        });
    }


    public static class Request {

    }

    public class Response {

    }

    public class ResponseUploadProgress extends Response {
        private int progress;

        public ResponseUploadProgress(int progress) {
            this.progress = progress;
        }

        public int getProgress() {
            return progress;
        }
    }

    public class ResponseDownloadProgress extends Response {
        private int progress;

        public ResponseDownloadProgress(int progress) {
            this.progress = progress;
        }

        public int getProgress() {
            return progress;
        }
    }
}
