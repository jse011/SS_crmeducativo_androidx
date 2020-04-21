package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.RubricaBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.RubricaBidRepository;

import java.util.List;

/**
 * Created by CCIE on 08/03/2018.
 */

public class GetRubricaBidLista  extends UseCase<GetRubricaBidLista.RequestValues,GetRubricaBidLista.ResponseValue>{

    private RubricaBidRepository  repository;

    public GetRubricaBidLista(RubricaBidRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        getUseCaseCallback().onSuccess(new ResponseValue(null));
        repository.getRubricasBid(requestValues.getPeriodoId(), requestValues.getCargaCursoId(), requestValues.getCursoId(), new RubricaBidDataSource.CallBackListRub() {
            @Override
            public void onListaRubBidList(List<RubBidUi> rubBidUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubBidUis));
            }
        });

    }
    public static class RequestValues implements UseCase.RequestValues{
        private  int periodoId;
        private  int cargaCursoId;
        private  int cursoId;

        public RequestValues(int periodoId, int cargaCursoId, int cursoId) {
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
