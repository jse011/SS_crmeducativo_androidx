package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;

import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class GetRubroProcesoSesionList extends UseCase<GetRubroProcesoSesionList.RequestValues,GetRubroProcesoSesionList.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public GetRubroProcesoSesionList(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getRubroProcesoSesionList(requestValues.idrubroformal,requestValues.getSesionAprendizajeId(), requestValues.getNivel(), requestValues.getCalendarioPeriodoId(), requestValues.getSilaboEventoId(),requestValues.getCargaCursoId(), new RubroEvaluacionProcesoListaDataSource.ListCallback<Object>() {
            @Override
            public void onLoaded(List<Object> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private int idrubroformal;
        private int sesionAprendizajeId;
        private int nivel;
        private int calendarioPeriodoId;
        private int silaboEventoId;
        private int cargaCursoId;


        public RequestValues(int idrubroformal, int sesionAprendizajeId, int nivel, int calendarioPeriodoId, int silaboEventoId, int cargaCursoId) {
            this.idrubroformal = idrubroformal;
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.nivel = nivel;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.silaboEventoId = silaboEventoId;
            this.cargaCursoId = cargaCursoId;

        }

        public int getIdrubroformal() {
            return idrubroformal;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public int getNivel() {
            return nivel;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getSilaboEventoId() {
            return silaboEventoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private List<Object> items;

        public ResponseValue(List<Object> items) {
            this.items = items;
        }

        public List<Object> getItems() {
            return items;
        }
    }
}
