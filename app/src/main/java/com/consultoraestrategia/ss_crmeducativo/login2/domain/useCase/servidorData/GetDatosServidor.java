package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.logging.Handler;

public class GetDatosServidor implements UseCaseLoginSincrono<ActualizarUi, GetDatosServidor.Response> {

    private LoginDataRepository repositorio;

    public GetDatosServidor(LoginDataRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public RetrofitCancel execute(final ActualizarUi actualizarUi, final Callback<GetDatosServidor.Response> callback) {
        LoginDataRepository.CallBackSucces<ActualizarUi> callBackSucces = new LoginDataRepository.CallBackSucces<ActualizarUi>() {
            @Override
            public void onLoad(boolean success, ActualizarUi item) {
                if(success){
                    repositorio.updateTimeSesionData(item);
                    callback.onResponse(true, new Response(actualizarUi));
                }else {
                    callback.onResponse(false, new Response(actualizarUi));
                }
            }

            @Override
            public void onRequestProgress(int progress) {
                actualizarUi.setUploadProgress(progress);
                callback.onResponse(true, new ResponseUploadProgress(actualizarUi));
            }

            @Override
            public void onResponseProgress(int progress) {
                actualizarUi.setDowloadProgress(progress);
                callback.onResponse(true, new ResponseDownloadProgress(actualizarUi));
            }
        };

        switch (actualizarUi.getTipo()){
            case Unidades:
                return repositorio.getDatosUnidades(actualizarUi, callBackSucces);
            case TipoNota:
                return repositorio.getDatosTipoNota(actualizarUi, callBackSucces );
            case Estudiantes:
                return repositorio.getDatosEstudiantes(actualizarUi, callBackSucces);
            case Resultado:
                return repositorio.getDatosResultado(actualizarUi, callBackSucces);
            case Rubros:
                return repositorio.getDatosRubro(actualizarUi, callBackSucces);
            case Grupos:
                return repositorio.getDatosGrupo(actualizarUi, callBackSucces);
            case Tareas:
                return repositorio.getDatosTarea(actualizarUi, callBackSucces);
            case Casos:
                return repositorio.getDatosCasos(actualizarUi, callBackSucces);
            case Asistencias:
                callback.onResponse(false,  new Response(actualizarUi));
                return null;
            case Docente:
                callback.onResponse(false,  new Response(actualizarUi));
                return null;
            case Dimencion_Desarrollo:
                return repositorio.getDimendinoDesarrollo(actualizarUi, callBackSucces);
             default:
                 callback.onResponse(false,  new Response(actualizarUi));
                 return null;
        }
    }

    public class Response {
        private  ActualizarUi actualizarUi;

        public Response(ActualizarUi actualizarUi) {
            this.actualizarUi = actualizarUi;
        }

        public ActualizarUi getActualizarUi() {
            return actualizarUi;
        }
    }

    public class ResponseUploadProgress extends Response {

        public ResponseUploadProgress(ActualizarUi actualizarUi) {
            super(actualizarUi);
        }
    }

    public class ResponseDownloadProgress extends Response {

        public ResponseDownloadProgress(ActualizarUi actualizarUi) {
            super(actualizarUi);
        }
    }


}
