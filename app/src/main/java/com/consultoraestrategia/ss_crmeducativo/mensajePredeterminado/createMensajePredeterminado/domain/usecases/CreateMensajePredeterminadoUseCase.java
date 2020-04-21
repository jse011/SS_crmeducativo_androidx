package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.CreateMensPreRepository;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.callbacks.CreateMensajePredeterminadoCallback;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class CreateMensajePredeterminadoUseCase extends UseCase<CreateMensajePredeterminadoUseCase.RequestValues, CreateMensajePredeterminadoUseCase.ResponseValue> {

    private CreateMensPreRepository repository;

    public CreateMensajePredeterminadoUseCase(CreateMensPreRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getMesnajesPredternidadosUIList(requestValues, new CreateMensajePredeterminadoCallback() {
            @Override
            public void onCreateMensPredLoaded(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {
        private final String asunto;
        private final String presentacion;
        private final String cabecera;
        private final String cuerpo;
        private final String despedida;
        private final int idAlcanceSelected;
        private final int idObjetivoSelected;
        private final String keyMensajePred;

        public RequestValues(String asunto, String presentacion, String cabecera, String cuerpo, String despedida, int idAlcanceSelected, int idObjetivoSelected, String keyMensajePred) {
            this.asunto = asunto;
            this.presentacion = presentacion;
            this.cabecera = cabecera;
            this.cuerpo = cuerpo;
            this.despedida = despedida;
            this.idAlcanceSelected = idAlcanceSelected;
            this.idObjetivoSelected = idObjetivoSelected;
            this.keyMensajePred = keyMensajePred;
        }

        public String getKeyMensajePred() {
            return keyMensajePred;
        }

        public String getAsunto() {
            return asunto;
        }

        public String getPresentacion() {
            return presentacion;
        }

        public String getCabecera() {
            return cabecera;
        }

        public String getCuerpo() {
            return cuerpo;
        }

        public String getDespedida() {
            return despedida;
        }

        public int getIdAlcanceSelected() {
            return idAlcanceSelected;
        }

        public int getIdObjetivoSelected() {
            return idObjetivoSelected;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final boolean success;


        public ResponseValue(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
