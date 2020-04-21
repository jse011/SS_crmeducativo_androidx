package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class GetFormaEvaluacion extends UseCase<GetFormaEvaluacion.RequestValues, GetFormaEvaluacion.ResponseValue> {
    private CrearRubroRepository repository;

    public GetFormaEvaluacion(CrearRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.GetFormaEvaluacion(new CrearRubroDataSource.ListCallck<FormaEvaluacionUi>() {
            @Override
            public void onSucess(List<FormaEvaluacionUi> items) {
                getUseCaseCallback().onSuccess(new ResponseValue(items));
            }
        });
    }
    public static final class RequestValues implements UseCase.RequestValues{

    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private List<FormaEvaluacionUi> formaEvaluacionUiList;

        public ResponseValue(List<FormaEvaluacionUi> formaEvaluacionUiList) {
            this.formaEvaluacionUiList = formaEvaluacionUiList;
        }

        public List<FormaEvaluacionUi> getFormaEvaluacionUiList() {
            return formaEvaluacionUiList;
        }
    }
}
