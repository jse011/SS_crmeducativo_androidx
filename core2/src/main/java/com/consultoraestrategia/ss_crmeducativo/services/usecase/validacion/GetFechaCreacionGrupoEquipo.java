package com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;

/**
 * Created by SCIEV on 28/05/2018.
 */

public class GetFechaCreacionGrupoEquipo extends UseCase<GetFechaCreacionGrupoEquipo.RequestValues, GetFechaCreacionGrupoEquipo.ResponseValue> {

    private BEDatosEnvioGrupoRepository repository;

    public GetFechaCreacionGrupoEquipo(BEDatosEnvioGrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.comprobarCambiosGrupos(requestValues.getGrupoEquipoId(), new ServiceDataSource.DosObjectCallBack<Long, Long>() {
            @Override
            public void onResponse(boolean success, Long f_Servidor, Long f_Local) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(f_Servidor, f_Local));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
       String grupoEquipoId;

        public RequestValues(String grupoEquipoId) {
            this.grupoEquipoId = grupoEquipoId;
        }

        public String getGrupoEquipoId() {
            return grupoEquipoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        long f_Servidor;
        long f_Local;

        public ResponseValue(long f_Servidor, long f_Local) {
            this.f_Servidor = f_Servidor;
            this.f_Local = f_Local;
        }

        public long getF_Servidor() {
            return f_Servidor;
        }

        public long getF_Local() {
            return f_Local;
        }
    }
}
