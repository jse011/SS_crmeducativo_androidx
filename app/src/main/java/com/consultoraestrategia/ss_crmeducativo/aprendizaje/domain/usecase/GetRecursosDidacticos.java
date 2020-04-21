package com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetRecursoDidacticoCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.RecursosDidacticoUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class GetRecursosDidacticos extends UseCaseSincrono<GetRecursosDidacticos.RequestValues, GetRecursosDidacticos.ResponseValue> {
    private AprendizajeRepository repository;


    public GetRecursosDidacticos(AprendizajeRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        repository.getRecursoDidactico(requestValues.getSesionAprendizajeId(), new GetRecursoDidacticoCallback() {
            @Override
            public void onRecursoLoad(List<RecursosDidacticoUi> recursosDidacticoUis) {
                callback.onResponse(true, new ResponseValue(recursosDidacticoUis));
            }

            @Override
            public void onError(String errot) {
                callback.onResponse(false, null);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int sesionAprendizajeId;

        public RequestValues(int sesionAprendizajeId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<RecursosDidacticoUi> recursosDidacticoUis;

        public ResponseValue(List<RecursosDidacticoUi> recursosDidacticoUis) {
            this.recursosDidacticoUis = recursosDidacticoUis;
        }

        public List<RecursosDidacticoUi> getRecursosDidacticoUis() {
            return recursosDidacticoUis;
        }
    }
}
