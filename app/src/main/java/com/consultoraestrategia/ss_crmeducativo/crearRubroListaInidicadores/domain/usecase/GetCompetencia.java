package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresRepository;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

/**
 * Created by SCIEV on 16/04/2018.
 */

public class GetCompetencia extends UseCase<GetCompetencia.RequestValues, GetCompetencia.ResponseValue> {
    private ListaIndicadoresRepository repository;

    public GetCompetencia(ListaIndicadoresRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getCompetencia(requestValues.getNivel(), requestValues.getCompeteniciaId(), new ListaIndicadoresDataSource.Callback<CompetenciaUi>() {
            @Override
            public void onSucces(CompetenciaUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int nivel;
        private int competeniciaId;

        public RequestValues(int nivel, int competeniciaId) {
            this.nivel = nivel;
            this.competeniciaId = competeniciaId;
        }

        public int getNivel() {
            return nivel;
        }

        public int getCompeteniciaId() {
            return competeniciaId;
        }
    }

    public class ResponseValue implements UseCase.ResponseValue{
        private CompetenciaUi competenciaUi;

        public ResponseValue(CompetenciaUi competenciaUi) {
            this.competenciaUi = competenciaUi;
        }

        public CompetenciaUi getCompetenciaUi() {
            return competenciaUi;
        }
    }

}
