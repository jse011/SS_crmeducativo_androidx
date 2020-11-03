package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class GetCompetenciasLista extends UseCase<GetCompetenciasLista.ResquestValues, GetCompetenciasLista.ResponseValue> {
    private static final String TAG = GetCompetenciasLista.class.getSimpleName();
    private EvaluacionCompetenciaRepository repository;

    public GetCompetenciasLista(EvaluacionCompetenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(final ResquestValues requestValues) {
        getCompenteciaLista(requestValues,new EvaluacionCompetenciaDataSource.CallBack() {
            @Override
            public void onLista(List<Object> objectList) {
                Log.d(TAG, "Cantidad :" +objectList.size());
                getUseCaseCallback().onSuccess(new ResponseValue(objectList));
            }
        });

    }



    public void getCompenteciaLista(ResquestValues requestValues, final EvaluacionCompetenciaDataSource.CallBack callBack){
        repository.getCompenteciaLista(requestValues.getPeriodoId(), requestValues.getCargaCursoId(), requestValues.getCursoId(),requestValues.getFiltradoUiList(), callBack);
    }


    public static final class ResquestValues implements UseCase.RequestValues {
        private int periodoId;
        private int cargaCursoId;
        private int cursoId;
        List<FiltradoUi> filtradoUiList;

        public ResquestValues(int periodoId, int cargaCursoId, int cursoId, ArrayList<FiltradoUi> filtradoUiList) {
            this.periodoId = periodoId;
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
            this.filtradoUiList = filtradoUiList;
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

        public List<FiltradoUi> getFiltradoUiList() {
            return filtradoUiList;
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
