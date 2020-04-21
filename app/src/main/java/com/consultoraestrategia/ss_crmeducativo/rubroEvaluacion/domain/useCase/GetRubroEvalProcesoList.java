package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;

import java.util.List;

/**
 * Created by kike on 21/10/2017.
 */

public class GetRubroEvalProcesoList extends UseCase<GetRubroEvalProcesoList.RequestValues, GetRubroEvalProcesoList.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public GetRubroEvalProcesoList(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        getUseCaseCallback().onSuccess(new ResponseValue(null));
        repository.getRubroProcesoSilavoList(requestValues.getIdCalendarioPeriodo(), requestValues.getCargaCursoId(), requestValues.getIdcompetencia(), requestValues.getParametrodisenioid(),new RubroEvaluacionProcesoListaDataSource.ListCallback<Object>() {
            @Override
            public void onLoaded(List<Object> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });
    }



    public static final class RequestValues implements UseCase.RequestValues {
        private int idCalendarioPeriodo;
        private int cargaCursoId;
        private int idcompetencia;
        private int parametrodisenioid;

        public RequestValues(int idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid) {
            this.idCalendarioPeriodo = idCalendarioPeriodo;
            this.cargaCursoId = cargaCursoId;
            this.idcompetencia=idcompetencia;
            this.parametrodisenioid=parametrodisenioid;
        }

        public int getParametrodisenioid() {
            return parametrodisenioid;
        }

        public void setParametrodisenioid(int parametrodisenioid) {
            this.parametrodisenioid = parametrodisenioid;
        }

        public void setIdCalendarioPeriodo(int idCalendarioPeriodo) {
            this.idCalendarioPeriodo = idCalendarioPeriodo;
        }

        public int getIdCalendarioPeriodo() {
            return idCalendarioPeriodo;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getIdcompetencia() {
            return idcompetencia;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<Object> items;

        public ResponseValue(List<Object> items) {
            this.items = items;
        }


        public List<Object> getItems() {
            return items;
        }
    }
}
