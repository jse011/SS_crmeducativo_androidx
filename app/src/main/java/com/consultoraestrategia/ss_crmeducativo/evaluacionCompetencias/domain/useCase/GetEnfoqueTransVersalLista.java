package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;

import java.util.List;

/**
 * Created by kike on 12/04/2018.
 */

public class GetEnfoqueTransVersalLista extends UseCase<GetEnfoqueTransVersalLista.ResquestValues,GetEnfoqueTransVersalLista.ResponseValue> {
    private EvaluacionCompetenciaRepository repository;

    public GetEnfoqueTransVersalLista(EvaluacionCompetenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(ResquestValues requestValues) {
        repository.getEnfoqueTransVersal(requestValues.getPeriodoId(), requestValues.getCargaCursoId(), requestValues.getCursoId(), new EvaluacionCompetenciaDataSource.CallBack() {
            @Override
            public void onLista(List<Object> objectList) {
                getUseCaseCallback().onSuccess(new ResponseValue(objectList));
            }
        });
    }

    public static final class ResquestValues implements UseCase.RequestValues {
        private int periodoId;
        private int cargaCursoId;
        private int cursoId;

        public ResquestValues(int periodoId, int cargaCursoId, int cursoId) {
            this.periodoId = periodoId;
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
        }

        public int getPeriodoId() {
            return periodoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCursoId() {
            return cursoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<Object> objectList;

        public ResponseValue(List<Object> objectList) {
            this.objectList = objectList;
        }

        public List<Object> getObjectList() {
            return objectList;
        }
    }
}
