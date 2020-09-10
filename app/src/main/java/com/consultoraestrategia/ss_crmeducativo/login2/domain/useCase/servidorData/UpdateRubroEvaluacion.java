package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData;

import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.ArrayList;
import java.util.List;

public class UpdateRubroEvaluacion implements UseCaseLoginSincrono<List<ServiceEnvioFbUi>, Void> {
    private LoginDataRepository loginDataRepository;
    private LoginPreferentRepository loginPreferentRepository;

    public UpdateRubroEvaluacion(LoginDataRepository loginDataRepository, LoginPreferentRepository loginPreferentRepository) {
        this.loginDataRepository = loginDataRepository;
        this.loginPreferentRepository = loginPreferentRepository;
    }

    @Override
    public RetrofitCancel execute(List<ServiceEnvioFbUi> serviceEnvioFbUis, UseCaseLoginSincrono.Callback<Void> callback) {
        List<String> rubroEvalaucionIds = new ArrayList<>();
        for (ServiceEnvioFbUi serviceEnvioFbUi : serviceEnvioFbUis){
            List<ServiceEnvioFbUi.Detalle> detalles = serviceEnvioFbUi.getDetalles();
            if(serviceEnvioFbUi.getTipo()== ServiceEnvioUi.Tipo.SessionAlumno){
                serviceEnvioFbUi.setNombreSesionDocenteId(loginDataRepository.changeEstadoSesionEjecutado(serviceEnvioFbUi.getSesionAprendizajeDocenteId()));
            }
            if(detalles!=null){
                for (ServiceEnvioFbUi.Detalle detalle: detalles) {
                    if(!TextUtils.isEmpty(detalle.getRubroEvalProcesoId()))
                        rubroEvalaucionIds.add(detalle.getRubroEvalProcesoId());
                }
            }
            String rubroEvalId = serviceEnvioFbUi.getRubroEvaluacionId();
            if(!TextUtils.isEmpty(rubroEvalId))rubroEvalaucionIds.add(rubroEvalId);
        }

        return loginDataRepository.updateRubroEvalaucionServidor(rubroEvalaucionIds, new LoginDataRepository.Callback<Throwable>() {
            @Override
            public void onResponse(boolean success, Throwable value) {
                if(success){
                    if(!serviceEnvioFbUis.isEmpty()){
                        loginPreferentRepository.saveFechaCambiosFirebase(serviceEnvioFbUis.get(0).getFechaModificacion());
                    }
                    callback.onResponse(true, null);
                }else {
                    callback.onResponse(false, null);
                }
            }
        });
    }

}
