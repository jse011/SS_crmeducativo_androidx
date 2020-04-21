package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;


/**
 * Created by @stevecampos on 23/02/2018.
 */

public class GetRubBid extends UseCase<GetRubBid.RequestValues, GetRubBid.ResponseValue> {

    private static final String TAG = GetRubBid.class.getSimpleName();
    private EvalRubBidRepository repository;

    public GetRubBid(EvalRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        try {
            repository.getRubBid(requestValues, new EvalRubBidDataSource.Callback<RubBidUi>() {
                @Override
                public void onLoaded(RubBidUi object) {
                    Log.d(TAG, "onLoaded");
                    getUseCaseCallback().onSuccess(new ResponseValue(object));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            getUseCaseCallback().onError();
        }
    }


    public static class RequestValues implements UseCase.RequestValues {
        private final String rubBidId;
        private final int cursoId;
        private final int cargaCursoId;

        public RequestValues(String rubBidId, int cursoId, int cargaCursoId) {
            this.rubBidId = rubBidId;
            this.cursoId = cursoId;
            this.cargaCursoId = cargaCursoId;
        }

        public String getRubBidId() {
            return rubBidId;
        }

        public int getCursoId() {
            return cursoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final RubBidUi rubBidUi;

        public ResponseValue(RubBidUi rubBidUi) {
            this.rubBidUi = rubBidUi;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }
    }
}
