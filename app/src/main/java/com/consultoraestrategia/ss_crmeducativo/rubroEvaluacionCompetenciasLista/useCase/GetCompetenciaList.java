package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.RubroEvaluacionProcesoListaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class GetCompetenciaList extends UseCase<GetCompetenciaList.RequestValues, GetCompetenciaList.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public GetCompetenciaList(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getCompetenciaList(requestValues.getCompetenciaIdList(),requestValues.getSesionAprendizajeId(), requestValues.getNivel(), new RubroEvaluacionProcesoListaDataSource.ListCallback<CompetenciaUi>() {
            @Override
            public void onLoaded(List<CompetenciaUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private ArrayList<Integer> competenciaIdList;
        private int sesionAprendizajeId;
        private int nivel;

        public RequestValues(ArrayList<Integer> competenciaIdList, int sesionAprendizajeId, int nivel) {
            this.competenciaIdList = competenciaIdList;
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.nivel = nivel;
        }

        public ArrayList<Integer> getCompetenciaIdList() {
            return competenciaIdList;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public int getNivel() {
            return nivel;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private List<CompetenciaUi> competenciaUiList;

        public ResponseValue(List<CompetenciaUi> competenciaUiList) {
            this.competenciaUiList = competenciaUiList;
        }

        public List<CompetenciaUi> getCompetenciaUiList() {
            return competenciaUiList;
        }
    }
}
