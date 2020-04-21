package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class DowloadImageUseCase extends UseCase<DowloadImageUseCase.RequestValues, UseCase.ResponseValue> {

    private RepositorioRepository repositorioRepository;

    public DowloadImageUseCase(RepositorioRepository repositorioRepository) {
        this.repositorioRepository = repositorioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        final RepositorioFileUi repositorioFileUi = requestValues.getRepositorioFileUi();
        Log.d(DowloadImageUseCase.class.getSimpleName(),"DowloadImageUseCase: " + repositorioFileUi.getUrl());
        repositorioFileUi.setCancel(false);
        String url = repositorioFileUi.getUrl();
        if(TextUtils.isEmpty(url)){
            repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
            getUseCaseCallback().onSuccess(new ResponseErrorValue(repositorioFileUi));
            return;
        }
        int p = Math.max(url.lastIndexOf('/'), url.lastIndexOf('\\'));
        String nombre = url.substring(p + 1);

        String carpeta = getCarpetaArchivo(url);
        repositorioRepository.dowloadImage(url,nombre, carpeta,new RepositorioDataSource.CallbackProgress<String>() {
            private DownloadCancelUi downloadCancelUi;
            @Override
            public void onProgress(int count) {
                repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.ENPROCESO_DESCARGA);
                if(repositorioFileUi.isCancel())downloadCancelUi.setCancel(true);
                getUseCaseCallback().onSuccess(new ResponseProgressValue(count, repositorioFileUi));
            }

            @Override
            public void onLoad(boolean success, String item) {
                if(success){
                    repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                    repositorioFileUi.setPath(item);
                    getUseCaseCallback().onSuccess(new ResponseSuccessValue(repositorioFileUi));
                }else {
                    repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
                    getUseCaseCallback().onSuccess(new ResponseErrorValue(repositorioFileUi));
                }
            }

            @Override
            public void onPreLoad(DownloadCancelUi isCancel) {
                downloadCancelUi = isCancel;
            }
        });
    }


    private String getCarpetaArchivo(String fileName){
        String extencion = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        String file = fileName.substring(p + 1);
        if (i > p) {
            extencion = fileName.substring(i+1);
        }

        if(!TextUtils.isEmpty(file)) {
            extencion = "." + extencion.toLowerCase();
            if (extencion.contains(".doc") || extencion.contains(".docx")) {
                // Word document
                return "/DOCUMENTO";
            } else if (extencion.contains(".pdf")) {
                // PDF file
                return "/PDF";
            } else if (extencion.contains(".ppt") || extencion.contains(".pptx")) {
                // Powerpoint file
                return "/DIAPOSITIVA";
            } else if (extencion.contains(".xls") || extencion.contains(".xlsx") || extencion.contains(".csv")) {
                // Excel file
                return "/HOJA_CALCULO";
            } else if (extencion.contains(".zip") || extencion.contains(".rar")) {
                // WAV audio file
                return "/COMPRESS";
            } else if (extencion.contains(".rtf")) {
                // RTF file
                return "/DOCUMENTO";
            } else if (extencion.contains(".wav") || extencion.contains(".mp3")) {
                // WAV audio file
                return "/AUDIO";
            } else if (extencion.contains(".gif")) {
                // GIF file
                return "/IMAGEN";
            } else if (extencion.contains(".jpg") || extencion.contains(".jpeg") || extencion.contains(".png")) {
                // JPG file
                return "/IMAGEN";
            } else if (extencion.contains(".txt")) {
                // Text file
                return "/DOCUMENTO";
            } else if (extencion.contains(".3gp") || extencion.contains(".mpg") || extencion.contains(".mpeg") || extencion.contains(".mpe") || extencion.contains(".mp4") || extencion.contains(".avi")) {
                // Video files
                return "/VIDEO";
            } else {
                return "/MATERIALES";
                // Other files
                //intent.setDataAndType(uri, "*/*");
            }
        }
        return "";
    }

    public static class RequestValues implements UseCase.RequestValues{
        private RepositorioFileUi repositorioFileUi;

        public RequestValues(RepositorioFileUi repositorioFileUi) {
            this.repositorioFileUi = repositorioFileUi;
        }

        public RepositorioFileUi getRepositorioFileUi() {
            return repositorioFileUi;
        }
    }

    public class ResponseSuccessValue implements UseCase.ResponseValue{
        private RepositorioFileUi repositorioFileUi;

        public ResponseSuccessValue(RepositorioFileUi repositorioFileUi) {
            this.repositorioFileUi = repositorioFileUi;
        }

        public RepositorioFileUi getRepositorioFileUi() {
            return repositorioFileUi;
        }
    }

    public class ResponseProgressValue implements UseCase.ResponseValue{
        private int count;
        private RepositorioFileUi repositorioFileUi;

        public ResponseProgressValue(int count, RepositorioFileUi repositorioFileUi) {
            this.count = count;
            this.repositorioFileUi = repositorioFileUi;
        }

        public int getCount() {
            return count;
        }

        public RepositorioFileUi getRepositorioFileUi() {
            return repositorioFileUi;
        }
    }

    public class ResponseErrorValue implements UseCase.ResponseValue{
        private RepositorioFileUi repositorioFileUi;

        public ResponseErrorValue(RepositorioFileUi repositorioFileUi) {
            this.repositorioFileUi = repositorioFileUi;
        }

        public RepositorioFileUi getRepositorioFileUi() {
            return repositorioFileUi;
        }
    }
}
