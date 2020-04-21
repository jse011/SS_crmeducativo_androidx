package com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetSesionCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardSesionUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class GetSesion extends UseCaseSincrono<GetSesion.RequestValues, GetSesion.ResponseValues> {

    private AprendizajeRepository repository;

    public GetSesion(AprendizajeRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValues> callback) {
        repository.getSesion(requestValues.getSesionAprendizajeId(), new GetSesionCallback() {
            @Override
            public void onRecursoLoad(CardSesionUi sesionUi) {
                callback.onResponse(true, new ResponseValues(sesionUi));
            }

            @Override
            public void onError(String error) {
               callback.onResponse(false, null);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int SesionAprendizajeId;

        public RequestValues(int sesionAprendizajeId) {
            SesionAprendizajeId = sesionAprendizajeId;
        }

        public int getSesionAprendizajeId() {
            return SesionAprendizajeId;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValue {
        private CardSesionUi sesionUi;

        public ResponseValues(CardSesionUi sesionUi) {
            this.sesionUi = sesionUi;
        }

        public CardSesionUi getSesionUi() {
            return sesionUi;
        }
    }

}
