package com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by SCIEV on 28/05/2018.
 */

public class SaveGEDatosSesionAprendizajeImport extends UseCase<SaveGEDatosSesionAprendizajeImport.RequestValues, SaveGEDatosSesionAprendizajeImport.ResponseValue> {

    private BEDatosSesionAprendizajeRepository repository;

    public SaveGEDatosSesionAprendizajeImport(BEDatosSesionAprendizajeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        BEVariables beVariables = requestValues.getBeVariables();
        try {
            SessionUser sessionUser = SessionUser.getCurrentUser();
            Empleado empleado = SQLite.select()
                    .from(Empleado.class)
                    .where(Empleado_Table.personaId.eq(sessionUser.getPersonaId()))
                    .querySingle();
            beVariables.setDocenteId(empleado.getEmpleadoId());
            beVariables.setUsuarioId(sessionUser.getUserId());

        }catch (Exception e){
            e.printStackTrace();
        }


        repository.getDatosImportar(beVariables, new ServiceDataSource.ObjectCallBack<BEDatosSesionAprendizaje>() {
            @Override
            public void onResponse(boolean success, BEDatosSesionAprendizaje item) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        BEVariables beVariables;

        public RequestValues(BEVariables beVariables) {
            this.beVariables = beVariables;
        }

        public BEVariables getBeVariables() {
            return beVariables;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        BEDatosSesionAprendizaje beDatosSesionAprendizaje;

        public ResponseValue(BEDatosSesionAprendizaje beDatosSesionAprendizaje) {
            this.beDatosSesionAprendizaje = beDatosSesionAprendizaje;
        }

        public BEDatosSesionAprendizaje getBeDatosSesionAprendizaje() {
            return beDatosSesionAprendizaje;
        }
    }
}
