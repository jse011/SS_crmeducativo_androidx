package com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;

/**
 * Created by SCIEV on 28/05/2018.
 */

public class GetFechaCreacionSesionAprendizaje extends UseCase<GetFechaCreacionSesionAprendizaje.RequestValues, GetFechaCreacionSesionAprendizaje.ResponseValue> {

    private BEDatosSesionAprendizajeRepository repository;

    public GetFechaCreacionSesionAprendizaje(BEDatosSesionAprendizajeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.comprobarCambiosSesionAprendizaje(requestValues.getSesionAprendizajeId(), new ServiceDataSource.DosObjectCallBack<Long, Long>() {
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
       int sesionAprendizajeId;

        public RequestValues(int sesionAprendizajeId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
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
