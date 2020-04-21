package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase;



import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresRepository;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 30/10/2017.
 */

public class GetIndicadorSilavoList extends UseCase<GetIndicadorSilavoList.RequestValues, GetIndicadorSilavoList.ResponseValue> {


    private ListaIndicadoresRepository repository;

    public GetIndicadorSilavoList(ListaIndicadoresRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getIndicadoresListSilaboCompetencia(requestValues.getCompetenciaId(),requestValues.getSilavoEventoId(),requestValues.getNivel(), requestValues.getCalendarioPeridoId(), new GetIndicadorListCallback() {
            @Override
            public void onRecursoLoad(List<CompetenciaUi> competenciaUiList) {
                getUseCaseCallback().onSuccess(new ResponseValue(competenciaUiList));
            }

            @Override
            public void onError(String errot) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int competenciaId;
        private int silavoEventoId;
        private int nivel;
        private int calendarioPeridoId;

        public RequestValues(int competenciaId, int silavoEventoId, int nivel, int calendarioPeridoId) {
            this.competenciaId = competenciaId;
            this.silavoEventoId = silavoEventoId;
            this.nivel = nivel;
            this.calendarioPeridoId = calendarioPeridoId;
        }

        public int getCompetenciaId() {
            return competenciaId;
        }

        public int getSilavoEventoId() {
            return silavoEventoId;
        }

        public int getNivel() {
            return nivel;
        }

        public int getCalendarioPeridoId() {
            return calendarioPeridoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        List<CompetenciaUi> alumnosEvaluacionUis;

        public ResponseValue(List<CompetenciaUi> alumnosEvaluacionUis) {
            this.alumnosEvaluacionUis = alumnosEvaluacionUis;
        }

        public List<CompetenciaUi> getAlumnosEvaluacionUis() {
            return alumnosEvaluacionUis;
        }
    }
}
