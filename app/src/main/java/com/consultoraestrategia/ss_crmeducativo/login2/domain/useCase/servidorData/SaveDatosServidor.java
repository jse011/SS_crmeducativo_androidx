package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class SaveDatosServidor implements UseCaseLoginSincrono<ServiceEnvioUi, SaveDatosServidor.Response> {

    private LoginDataRepository repositorio;

    public SaveDatosServidor(LoginDataRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public RetrofitCancel execute(final ServiceEnvioUi serviceEnvioUi, final Callback<SaveDatosServidor.Response> callback) {
        LoginDataRepository.CallBackSuccessRelacion<ServiceEnvioUi> callBackSucces = new LoginDataRepository.CallBackSuccessRelacion<ServiceEnvioUi>() {
            @Override
            public void onRequestRubroProgress(int progress) {
                serviceEnvioUi.setUploadRubroProgress(progress);
                callback.onResponse(true, new ResponseUploadProgress(serviceEnvioUi));
            }

            @Override
            public void onLoad(boolean success, ServiceEnvioUi item) {
                if(success){
                    callback.onResponse(true, new Response(serviceEnvioUi));
                }else {
                    callback.onResponse(false, new Response(serviceEnvioUi));
                }
            }

            @Override
            public void onRequestProgress(int progress) {
                serviceEnvioUi.setUploadProgress(progress);
                callback.onResponse(true, new ResponseUploadProgress(serviceEnvioUi));
            }

            @Override
            public void onResponseProgress(int progress) {
                serviceEnvioUi.setDowloadProgress(progress);
                callback.onResponse(true, new ResponseDownloadProgress(serviceEnvioUi));
            }
        };

        switch (serviceEnvioUi.getTipo()){
            case Unidades:
                return repositorio.saveDatosUnidades(serviceEnvioUi, callBackSucces);
            case TipoNota:
                callback.onResponse(false,  new Response(serviceEnvioUi));
                return null;
            case Estudiantes:
                callback.onResponse(false,  new Response(serviceEnvioUi));
                return null;
            case Resultado:
                return repositorio.saveDatosResultado(serviceEnvioUi, callBackSucces);
            case Rubro:
                return repositorio.saveDatosRubro(serviceEnvioUi, callBackSucces);
            case Rubrica:
                return repositorio.saveDatosRubrica(serviceEnvioUi, callBackSucces);
            case Formula:
                return repositorio.saveDatosFormula(serviceEnvioUi, callBackSucces);
            case Grupos:
                return repositorio.saveDatosGrupo(serviceEnvioUi, callBackSucces);
            case Tareas:
                return repositorio.saveDatosTarea(serviceEnvioUi, callBackSucces);
            case Casos:
                return repositorio.saveDatosCasos(serviceEnvioUi, callBackSucces);
            case Asistencias:
                callback.onResponse(false,  new Response(serviceEnvioUi));
                return null;
            case CerrarCurso:
                return repositorio.saveDatosCerrarCurso(serviceEnvioUi, callBackSucces);
            default:
                callback.onResponse(false,  new Response(serviceEnvioUi));
                return null;
        }
    }


    public class Response {
        private  ServiceEnvioUi serviceEnvioUi;

        public Response(ServiceEnvioUi serviceEnvioUi) {
            this.serviceEnvioUi = serviceEnvioUi;
        }

        public ServiceEnvioUi getServiceEnvioUi() {
            return serviceEnvioUi;
        }
    }

    public class ResponseUploadProgress extends Response {

        public ResponseUploadProgress(ServiceEnvioUi serviceEnvioUi) {
            super(serviceEnvioUi);
        }
    }
    public class ResponseDownloadProgress extends Response {

        public ResponseDownloadProgress(ServiceEnvioUi serviceEnvioUi) {
            super(serviceEnvioUi);
        }
    }
}


