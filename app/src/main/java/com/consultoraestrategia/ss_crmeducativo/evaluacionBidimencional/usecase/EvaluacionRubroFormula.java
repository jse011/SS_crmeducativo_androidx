package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;

public class EvaluacionRubroFormula extends UseCaseSincrono<EvaluacionRubroFormula.Request, Boolean> {

    private RubroEvaluacionProcesoListaRepository evaluacionFormulaRepository;

    public EvaluacionRubroFormula(RubroEvaluacionProcesoListaRepository evaluacionFormulaRepository) {
        this.evaluacionFormulaRepository = evaluacionFormulaRepository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        try {
            evaluacionFormulaRepository.onUpdateEvaluacionFormula( new RubroEvaluacionProcesoListaDataSource.SimpleSuccessCallBack() {
                @Override
                public void onSuccess(boolean success) {
                    callback.onResponse(success, success);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            callback.onResponse(false, false);
        }
    }

    public static class Request{

    }
}
