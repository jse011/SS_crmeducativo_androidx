package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class GetDatosServidorTwo implements UseCaseLoginSincrono<GetDatosServidorTwo.Request, GetDatosServidorTwo.Response> {

    private LoginDataRepository repositorio;

    public GetDatosServidorTwo(LoginDataRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public RetrofitCancel execute(final GetDatosServidorTwo.Request request,final Callback<GetDatosServidorTwo.Response> callback) {

        LoginDataRepository.CallBackSucces<ProgramaEducativoUi> callBackSucces = new LoginDataRepository.CallBackSucces<ProgramaEducativoUi>() {
            @Override
            public void onLoad(boolean success, ProgramaEducativoUi item) {
                if(success){
                    for(ActualizarUi actualizarUi : item.getActualizarUiList()){
                        if(actualizarUi.getSuccess()==1){
                            repositorio.updateTimeSesionData(actualizarUi);
                        }

                    }

                    callback.onResponse(true, new Response(item));
                }else {
                    callback.onResponse(false, new Response(item));
                }
            }

            @Override
            public void onRequestProgress(int progress) {
                request.getProgramaEducativoUi().setUploadProgress(progress);
                callback.onResponse(true, new ResponseUploadProgress(request.getProgramaEducativoUi()));
            }

            @Override
            public void onResponseProgress(int progress) {
                request.getProgramaEducativoUi().setDowloadProgress(progress);
                callback.onResponse(true, new ResponseDownloadProgress(request.getProgramaEducativoUi()));
            }
        };

        switch (request.getTipo()){
            case Unidades:
                return repositorio.getDatosUnidades(request.getProgramaEducativoUi(), callBackSucces);
            case TipoNota:
                return repositorio.getDatosTipoNota(request.getProgramaEducativoUi(), callBackSucces );
            case Estudiantes:
                return repositorio.getDatosEstudiantes(request.getProgramaEducativoUi(), callBackSucces);
            case Resultado:
                return repositorio.getDatosResultado(request.getProgramaEducativoUi(), callBackSucces);
            case Rubros:
                return repositorio.getDatosRubro(request.getProgramaEducativoUi(), callBackSucces);
            case Grupos:
                return repositorio.getDatosGrupo(request.getProgramaEducativoUi(), callBackSucces);
            case Tareas:
                return repositorio.getDatosTarea(request.getProgramaEducativoUi(), callBackSucces);
            case Casos:
                return repositorio.getDatosCasos(request.getProgramaEducativoUi(), callBackSucces);
            case Asistencias:
                callback.onResponse(false,  new Response(request.getProgramaEducativoUi()));
                return null;
            case Docente:
                callback.onResponse(false,  new Response(request.getProgramaEducativoUi()));
                return null;
            case Dimencion_Desarrollo:
                return repositorio.getDimendinoDesarrollo(request.getProgramaEducativoUi(), callBackSucces);
             default:
                 callback.onResponse(false,  new Response(request.getProgramaEducativoUi()));
                 return null;
        }
    }

    public static class Request {
        private ProgramaEducativoUi programaEducativoUi;
        private ActualizarTipoUi tipo;

        public Request(ProgramaEducativoUi programaEducativoUi, ActualizarTipoUi tipo) {
            this.programaEducativoUi = programaEducativoUi;
            this.tipo = tipo;
        }

        public ProgramaEducativoUi getProgramaEducativoUi() {
            return programaEducativoUi;
        }

        public ActualizarTipoUi getTipo() {
            return tipo;
        }
    }

    public class Response {
        private ProgramaEducativoUi programaEducativoUi;

        public Response(ProgramaEducativoUi programaEducativoUi) {
            this.programaEducativoUi = programaEducativoUi;
        }

        public ProgramaEducativoUi getProgramaEducativoUi() {
            return programaEducativoUi;
        }
    }

    public class ResponseUploadProgress extends Response {

        public ResponseUploadProgress(ProgramaEducativoUi cursosUi) {
            super(cursosUi);
        }
    }

    public class ResponseDownloadProgress extends Response {

        public ResponseDownloadProgress(ProgramaEducativoUi cursosUi) {
            super(cursosUi);
        }
    }


}
