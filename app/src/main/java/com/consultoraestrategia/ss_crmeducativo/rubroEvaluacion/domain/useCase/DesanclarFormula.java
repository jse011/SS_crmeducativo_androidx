package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

public class DesanclarFormula extends UseCaseSincrono<DesanclarFormula.Request, DesanclarFormula.Response> {

    private RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository;

    public DesanclarFormula(RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository) {
        this.rubroEvaluacionProcesoListaRepository = rubroEvaluacionProcesoListaRepository;
    }

    @Override
    public void execute(Request request, final Callback<Response> callback) {
        rubroEvaluacionProcesoListaRepository.desanclarCaseAnclar(request.getCapacidadUi(), request.getRubroformula(), new RubroEvaluacionProcesoListaDataSource.ObjetoCallback<CapacidadUi, RubroProcesoUi>() {
            @Override
            public void onObject(CapacidadUi capacidad, RubroProcesoUi rubroProceso) {
               callback.onResponse(true, new Response(rubroProceso, capacidad));
            }

            @Override
            public void onError(String mensaje) {

            }
        });
    }
    public static class Request{
       private RubroProcesoUi rubroformula;
       private CapacidadUi capacidadUi;

       public Request(RubroProcesoUi rubroformula, CapacidadUi capacidadUi) {
           this.rubroformula = rubroformula;
           this.capacidadUi = capacidadUi;
       }

       public RubroProcesoUi getRubroformula() {
           return rubroformula;
       }

       public CapacidadUi getCapacidadUi() {
           return capacidadUi;
       }
   }

    public static class Response{
        private RubroProcesoUi rubroformula;
        private CapacidadUi capacidadUi;

        public Response(RubroProcesoUi rubroformula, CapacidadUi capacidadUi) {
            this.rubroformula = rubroformula;
            this.capacidadUi = capacidadUi;
        }

        public RubroProcesoUi getRubroformula() {
            return rubroformula;
        }

        public CapacidadUi getCapacidadUi() {
            return capacidadUi;
        }
    }
}
