package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;

public class UpdateEvaluacionFormulas extends UseCaseSincrono<UpdateEvaluacionFormulas.Request,UpdateEvaluacionFormulas.Response > {

    private RubroRepository rubroRepository;

    public UpdateEvaluacionFormulas(RubroRepository rubroRepository) {
        this.rubroRepository = rubroRepository;
    }

    @Override
    public void execute(Request request, final Callback<Response> callback) {
        rubroRepository.onUpdateEvaluacionFormula(new RubroDataSource.UpdateEvaluacionFormulaCallback() {
            @Override
            public void onPreLoad() {
                callback.onResponse(false, new Response(true));
            }

            @Override
            public void onLoad(boolean success) {
                callback.onResponse(success, new Response(false));
            }
        });
    }

    public static class Request implements UseCase.RequestValues{

    }

    public class Response implements UseCase.ResponseValue{
        private boolean preload;

        public Response(boolean preload) {
            this.preload = preload;
        }

        public boolean isPreload() {
            return preload;
        }
    }
}
