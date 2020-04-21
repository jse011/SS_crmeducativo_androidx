package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.RubricaSesionDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.RubricaSesionRepository;

import java.util.List;

/**
 * Created by CCIE on 08/03/2018.
 */

public class GetRubricaSesionLista extends UseCase<GetRubricaSesionLista.RequestValues,GetRubricaSesionLista.ResponseValue>{

    private RubricaSesionRepository repository;

    public GetRubricaSesionLista(RubricaSesionRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getRubricasSesion(requestValues.getSesionAprendizajeId(), requestValues.getCargaCursoId(), requestValues.getCursoId(), requestValues.getCalendarioPeriodoId(), new RubricaSesionDataSource.CallBackListRub() {
            @Override
            public void onListaRubBidList(List<RubBidUi> rubBidUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubBidUis));
            }
        });

    }
    public static class RequestValues implements UseCase.RequestValues{
        private  int sesionAprendizajeId;
        private  int cargaCursoId;
        private  int cursoId;
        private int calendarioPeriodoId;

        public RequestValues(int sesionAprendizajeId, int cargaCursoId, int cursoId, int calendarioPeriodoId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCursoId() {
            return cursoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private  List<RubBidUi> list;

        public ResponseValue(List<RubBidUi> list) {
            this.list = list;
        }

        public List<RubBidUi> getList() {
            return list;
        }
    }


}
