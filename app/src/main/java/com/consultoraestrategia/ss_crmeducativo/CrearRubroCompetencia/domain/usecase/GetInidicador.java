package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;



import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetIndicadorCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.ArrayList;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class GetInidicador extends UseCase<GetInidicador.RequestValues, GetInidicador.ResponseValue> {
    private CrearRubroRepository repository;

    public GetInidicador(CrearRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.GetIndicaor(requestValues.getIndicadorId(), requestValues.getCamposAccionId(), new GetIndicadorCallback() {
            @Override
            public void onIndicadorLoad(IndicadorUi indicadorUi) {
                getUseCaseCallback().onSuccess(new ResponseValue(indicadorUi));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private int indicadorId;
        private ArrayList<Integer> camposAccionId;

        public RequestValues(int indicadorId, ArrayList<Integer> camposAccionId) {
            this.indicadorId = indicadorId;
            this.camposAccionId = camposAccionId;
        }

        public int getIndicadorId() {
            return indicadorId;
        }

        public ArrayList<Integer> getCamposAccionId() {
            return camposAccionId;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private IndicadorUi indicadorUi;

        public ResponseValue(IndicadorUi indicadorUi) {
            this.indicadorUi = indicadorUi;
        }

        public IndicadorUi getIndicadorUi() {
            return indicadorUi;
        }
    }
}
