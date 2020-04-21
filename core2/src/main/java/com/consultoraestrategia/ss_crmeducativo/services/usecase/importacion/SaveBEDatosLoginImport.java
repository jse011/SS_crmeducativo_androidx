package com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.BEObtenerDatosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by SCIEV on 28/05/2018.
 */

public class SaveBEDatosLoginImport extends UseCase<SaveBEDatosLoginImport.RequestValues, SaveBEDatosLoginImport.ResponseValue> {

    private BEObtenerDatosLoginRepository repository;

    public SaveBEDatosLoginImport(BEObtenerDatosLoginRepository repository) {
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
            if(empleado!=null){beVariables.setDocenteId(empleado.getEmpleadoId());
            beVariables.setUsuarioId(sessionUser.getUserId());}

        }catch (Exception e){
            e.printStackTrace();
        }


        repository.getDatosImportar(beVariables, new ServiceDataSource.ObjectCallBack<BEObtenerDatosLogin>() {
            @Override
            public void onResponse(boolean success, BEObtenerDatosLogin item) {
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
        BEObtenerDatosLogin beObtenerDatosLogin;

        public ResponseValue(BEObtenerDatosLogin beObtenerDatosLogin) {
            this.beObtenerDatosLogin = beObtenerDatosLogin;
        }

        public BEObtenerDatosLogin getBeObtenerDatosLogin() {
            return beObtenerDatosLogin;
        }
    }
}
