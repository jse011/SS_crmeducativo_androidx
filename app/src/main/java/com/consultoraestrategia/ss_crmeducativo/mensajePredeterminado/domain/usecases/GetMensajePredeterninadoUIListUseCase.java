package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.MensajePredeterminadoRepository;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks.GetMensajesPredeterminadosCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class GetMensajePredeterninadoUIListUseCase extends UseCase<GetMensajePredeterninadoUIListUseCase.RequestValues, GetMensajePredeterninadoUIListUseCase.ResponseValue> {

    private MensajePredeterminadoRepository repository;

    public GetMensajePredeterninadoUIListUseCase(MensajePredeterminadoRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getMesnajesPredternidadosUIList(requestValues, new GetMensajesPredeterminadosCallback() {
            @Override
            public void onGetMensajePredeterniandoLoaded(List<MensajePredeterminadoUI> mensajePredeterminadoUIList) {
                getUseCaseCallback().onSuccess(new ResponseValue(mensajePredeterminadoUIList));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {

        private final int idObjetiveMessage;

        public RequestValues(int idObjetiveMessage) {
            this.idObjetiveMessage = idObjetiveMessage;
        }

        public int getIdObjetiveMessage() {
            return idObjetiveMessage;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<MensajePredeterminadoUI> mensajePredeterminadoUIList;

        public List<MensajePredeterminadoUI> getMensajePredeterminadoUIList() {
            return mensajePredeterminadoUIList;
        }

        public ResponseValue(List<MensajePredeterminadoUI> mensajePredeterminadoUIList) {
            this.mensajePredeterminadoUIList = mensajePredeterminadoUIList;
        }
    }
}
