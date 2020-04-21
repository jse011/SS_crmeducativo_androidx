package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TareasUI;

public class GetTarea extends UseCase<GetTarea.RequestValues, GetTarea.ResponseValue> {
    private static final String TAG = GetIndicadores.class.getSimpleName();
    private CreateRubBidRepository repository;

    public GetTarea(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
            repository.getTareaUi(requestValues.getIdTarea(), new CreateRubBidDataSource.GetTareaUICallback() {
                @Override
                public void onTareaLoaded(TareasUI tareasUI) {
                    getUseCaseCallback().onSuccess(new ResponseValue(tareasUI));
                }

                @Override
                public void onError() {

                }
            });
    }


    public static class RequestValues implements UseCase.RequestValues {
        private final String idTarea;

        public RequestValues(String idTarea) {
            this.idTarea = idTarea;
        }

        public String getIdTarea() {
            return idTarea;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final TareasUI tareasUI;

        public ResponseValue(TareasUI tareasUI) {
            this.tareasUI = tareasUI;
        }

        public TareasUI getTareasUI() {
            return tareasUI;
        }
    }
}
