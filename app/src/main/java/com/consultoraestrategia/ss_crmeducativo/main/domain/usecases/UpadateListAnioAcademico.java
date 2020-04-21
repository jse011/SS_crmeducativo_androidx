package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosInicioSesion;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class UpadateListAnioAcademico implements UseCaseLoginSincrono<UpadateListAnioAcademico.Response, UpadateListAnioAcademico.Request> {

    private MainRepository mainRepository;

    public UpadateListAnioAcademico(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public RetrofitCancel execute(Response request, final Callback<Request> callback) {
        return mainRepository.updateListAnioAcademico(request.getUsuarioId(), new MainDataSource.SucessCallback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                callback.onResponse(success, new Request());
            }
        });
    }

    public static class Response {
        private int usuarioId;

        public Response(int usuarioId) {
            this.usuarioId = usuarioId;
        }

        public int getUsuarioId() {
            return usuarioId;
        }

    }

    public class Request {
    }
}
