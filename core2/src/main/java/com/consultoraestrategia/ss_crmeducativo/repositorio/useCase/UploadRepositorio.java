package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUploadEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

import java.io.File;

import static com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio.*;

public class UploadRepositorio extends UseCase<Request, UploadRepositorio.Response> {

    protected RepositorioRepository repositorioRepository;
    protected  String TAG = UploadRepositorio.class.getSimpleName();
    protected Context context;

    public UploadRepositorio(RepositorioRepository repositorioRepository) {
        this.repositorioRepository = repositorioRepository;
    }

    @Override
    protected void executeUseCase(Request request) {

        final UpdateRepositorioFileUi updateRepositorioFileUi = request.getUpdateRepositorioFileUi();
        Log.d(TAG,"getUrlServidor: "+ request.getUrlServidor() +"getPath: " + updateRepositorioFileUi.getUri());
        if(TextUtils.isEmpty(request.getUrlServidor())){
            updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ERROR_SUBIDA);
            getUseCaseCallback().onSuccess(new Response(updateRepositorioFileUi, false));
            return;
        }
        if(updateRepositorioFileUi.getUri()==null){
            updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ERROR_SUBIDA);
            getUseCaseCallback().onSuccess(new Response(updateRepositorioFileUi, false));
            return;
        }

        RepositorioDataSource.CallbackProgress<String> callbackProgress = new RepositorioDataSource.CallbackProgress<String>() {
            @Override
            public void onProgress(int count) {
                updateRepositorioFileUi.setCount(count);
                getUseCaseCallback().onSuccess(new ResponseProgressValue(count , updateRepositorioFileUi));
            }

            @Override
            public void onLoad(boolean success, String item) {
                Log.d(TAG, "success : " + success + "item: " + item);
                if (success) {
                    updateRepositorioFileUi.setUrl(item);
                    updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.SUDIDA_COMPLETA);
                    getUseCaseCallback().onSuccess(new Response(updateRepositorioFileUi, true));
                } else {
                    updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ERROR_SUBIDA);
                    getUseCaseCallback().onSuccess(new Response(updateRepositorioFileUi, false));
                }
            }

            @Override
            public void onPreLoad(DownloadCancelUi isCancel) {
                updateRepositorioFileUi.setCount(0);
                updateRepositorioFileUi.setDownloadCancelUi(isCancel);
                updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ENPROCESO_SUBIDA);
                getUseCaseCallback().onSuccess(new Response(updateRepositorioFileUi, false));
            }
        };


        Log.d(TAG, request.getRepositorio().toString());
        switch (request.getRepositorio()){
            case ARCHIVO:
                repositorioRepository.uploadFileArchivo(updateRepositorioFileUi.getExtencionArchivoId(), request.getUrlServidor(), updateRepositorioFileUi.getPath(),callbackProgress);
                break;
            case ARCHIVO_ASISTENICA:
                repositorioRepository.uploadFileJustificacion(updateRepositorioFileUi.getExtencionArchivoId(),request.getUrlServidor(), updateRepositorioFileUi.getPath(),callbackProgress);
                break;
            case ARCHIVO_COMPORTAMIENTO:
                repositorioRepository.uploadFileCaso(updateRepositorioFileUi, request.getUrlServidor(), updateRepositorioFileUi.getUri(),callbackProgress);
                break;
            case ARCHIVO_RUBRO:
                repositorioRepository.uploadFileCasoRubro(updateRepositorioFileUi.getExtencionArchivoId(), request.getUrlServidor(), updateRepositorioFileUi.getUri(),callbackProgress);
                break;
        }

    }

    public static class Request implements UseCase.RequestValues {
        private String urlServidor;
        private UpdateRepositorioFileUi updateRepositorioFileUi;
        private RepositorioUi repositorio;

        public Request(String urlServidor, RepositorioUi repositorio, UpdateRepositorioFileUi updateRepositorioFileUi) {
            this.urlServidor = urlServidor;
            this.updateRepositorioFileUi = updateRepositorioFileUi;
            this.repositorio = repositorio;
        }

        public String getUrlServidor() {
            return urlServidor;
        }

        public UpdateRepositorioFileUi getUpdateRepositorioFileUi() {
            return updateRepositorioFileUi;
        }

        public RepositorioUi getRepositorio() {
            return repositorio;
        }
    }

    public static class Response implements UseCase.ResponseValue{
        UpdateRepositorioFileUi updateRepositorioFileUi;
        boolean success;

        public Response(UpdateRepositorioFileUi updateRepositorioFileUi, boolean success) {
            this.updateRepositorioFileUi = updateRepositorioFileUi;
            this.success = success;
        }

        public UpdateRepositorioFileUi getUpdateRepositorioFileUi() {
            return updateRepositorioFileUi;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    public static class ResponseProgressValue extends Response{
        private int count;
        private UpdateRepositorioFileUi updateRepositorioFileUi;

        public ResponseProgressValue(int count, UpdateRepositorioFileUi updateRepositorioFileUi) {
            super(null, false);
            this.count = count;
            this.updateRepositorioFileUi = updateRepositorioFileUi;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public UpdateRepositorioFileUi getUpdateRepositorioFileUi() {
            return updateRepositorioFileUi;
        }

        public void setUpdateRepositorioFileUi(UpdateRepositorioFileUi updateRepositorioFileUi) {
            this.updateRepositorioFileUi = updateRepositorioFileUi;
        }
    }
}
