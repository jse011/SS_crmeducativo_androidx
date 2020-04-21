package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;


import java.util.List;

/**
 * Created by kike on 21/10/2017.
 */

public class GetPeriodoList extends UseCase<GetPeriodoList.RequestValues, GetPeriodoList.ResponseValue>{

    private RubroEvaluacionProcesoListaRepository repository;

    public GetPeriodoList(RubroEvaluacionProcesoListaRepository repository){
        this.repository= repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getPeriodoList(requestValues.getCargaCursoId(), requestValues.getCursoId(), new RubroEvaluacionProcesoListaDataSource.CallbackPeriodo(){
            @Override
            public void onListPeriodo(List<PeriodoUi> periodoUis, int status) {
                getUseCaseCallback().onSuccess(new ResponseValue(periodoUis,status));
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int cargaCursoId;
        private final int cursoId;

        public RequestValues(int cargaCursoId, int cursoId) {
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCursoId() {
            return cursoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<PeriodoUi> periodoUiList;

        private final int validate;

        public ResponseValue(List<PeriodoUi> periodoUiList, int validate) {
            this.periodoUiList = periodoUiList;
            this.validate = validate;
        }

        public List<PeriodoUi> getPeriodoUiList() {
            return periodoUiList;
        }

        public int getValidate() {
            return validate;
        }
    }
}
