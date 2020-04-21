package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;

import java.util.List;

/**
 * Created by @stevecampos on 25/02/2018.
 */

public class DeleteAlumnosProcesoBid extends UseCase<DeleteAlumnosProcesoBid.RequestValues, DeleteAlumnosProcesoBid.ResponseValue> {

    public static final String TAG = DeleteAlumnosProcesoBid.class.getSimpleName();
    private EvalRubBidRepository repository;

    public DeleteAlumnosProcesoBid(EvalRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        repository.deleteEvalAlumnosConProceso(requestValues, new EvalRubBidDataSource.Callback<Boolean>() {
            @Override
            public void onLoaded(Boolean object) {
                getUseCaseCallback().onSuccess(new ResponseValue(object));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private EvalProcUi evalProcUi;
        private final List<EvalProcUi> evalProcUiList;

        public RequestValues(EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList) {
            this.evalProcUi = evalProcUi;
            this.evalProcUiList = evalProcUiList;
        }

        public EvalProcUi getEvalProcUi() {
            return evalProcUi;
        }

        public List<EvalProcUi> getEvalProcUiList() {
            return evalProcUiList;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final Boolean sucess;

        public ResponseValue(Boolean sucess) {
            this.sucess = sucess;
        }

        public Boolean getSucess() {
            return sucess;
        }
    }
}
