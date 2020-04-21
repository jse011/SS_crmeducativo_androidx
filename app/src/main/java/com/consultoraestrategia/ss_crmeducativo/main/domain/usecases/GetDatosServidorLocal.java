package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class GetDatosServidorLocal implements UseCaseLoginSincrono<GetDatosServidorLocal.Request, Boolean> {
    private MainRepository mainRepository;

    public GetDatosServidorLocal(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public RetrofitCancel execute(Request request, final Callback<Boolean> callback) {
        return mainRepository.getDatosInicioSesion(request.getEmpleadoId(), request.getAnioAcademicoId(), new MainDataSource.SucessCallback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                if(success){
                    callback.onResponse(true, true);
                }else {
                    callback.onResponse(false, false);
                }
            }
        });
    }

    public static class Request{
        private int empleadoId;
        private int anioAcademicoId;

        public Request(int empleadoId, int anioAcademicoId) {
            this.empleadoId = empleadoId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getEmpleadoId() {
            return empleadoId;
        }

        public void setEmpleadoId(int empleadoId) {
            this.empleadoId = empleadoId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }
    }
}
