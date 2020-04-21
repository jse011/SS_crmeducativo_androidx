package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;

public class UpdateEvaluacionFormula extends UseCaseSincrono<UpdateEvaluacionFormula.RequestValues,UpdateEvaluacionFormula.ResponseValue> {

    private EvalRubBidRepository evalRubBidRepository;

    public UpdateEvaluacionFormula(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }

    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        evalRubBidRepository.onUpdateEvaluacionFormula(new EvalRubBidDataSource.UpdateEvaluacionFormulaCallback() {
            @Override
            public void onPreLoad() {
                callback.onResponse(false, new ResponseValue(true));
            }

            @Override
            public void onLoaded(boolean success) {
                callback.onResponse(success, new ResponseValue(false));
            }

        });


    }


    public static class RequestValues implements UseCase.RequestValues{

    }

    public class ResponseValue implements UseCase.ResponseValue{
        private boolean preload;

        public ResponseValue(boolean preload) {
            this.preload = preload;
        }

        public boolean isPreload() {
            return preload;
        }
    }

}
