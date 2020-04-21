package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase;



import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresRepository;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 30/10/2017.
 */

public class GetIndicadorSesionList extends UseCase<GetIndicadorSesionList.RequestValues, GetIndicadorSesionList.ResponseValue> {


    private ListaIndicadoresRepository repository;

    public GetIndicadorSesionList(ListaIndicadoresRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getIndicadoresList(requestValues.getSesionAprendizajeId(),requestValues.getNivel(), requestValues.getCompetenciaId(), new GetIndicadorListCallback() {
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
        private int sesionAprendizajeId;
        private int nivel;
        private int competenciaId;

        public RequestValues(int sesionAprendizajeId, int nivel, int competenciaId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.nivel = nivel;
            this.competenciaId = competenciaId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public int getNivel() {
            return nivel;
        }

        public int getCompetenciaId() {
            return competenciaId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        List<CompetenciaUi> competenciaUiList;

        public ResponseValue(List<CompetenciaUi> competenciaUiList) {
            this.competenciaUiList = competenciaUiList;
        }

        public List<CompetenciaUi> getCompetenciaUiList() {
            return competenciaUiList;
        }
    }
}
