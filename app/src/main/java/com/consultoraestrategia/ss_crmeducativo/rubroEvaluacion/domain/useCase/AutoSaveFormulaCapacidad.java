package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;

import java.util.ArrayList;
import java.util.List;

public class AutoSaveFormulaCapacidad extends UseCaseSincrono<AutoSaveFormulaCapacidad.Response, AutoSaveFormulaCapacidad.Request> {

    private RubroEvaluacionProcesoListaRepository repository;

    public AutoSaveFormulaCapacidad(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Response request, final Callback<Request> callback) {
        repository.saveRubroFormulaCapacidad(request.getCargaCursoId(), request.getCalendarioPeriodoId(), request.getCompetenciaList(), new RubroEvaluacionProcesoListaDataSource.SimpleSuccessCallBack() {
            @Override
            public void onSuccess(boolean success) {
                callback.onResponse(true, new Request(success));
            }
        });
    }

    public static class Response {
        private int cargaCursoId;
        private int calendarioPeriodoId;
        private List<CompetenciaUi> competenciaList = new ArrayList<>();

        public Response(int cargaCursoId, int calendarioPeriodoId,List<CompetenciaUi> competenciaList) {
            this.cargaCursoId = cargaCursoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.competenciaList.addAll(competenciaList);
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public List<CompetenciaUi> getCompetenciaList() {
            return competenciaList;
        }
    }

    public static class Request{
        private boolean success;

        public Request(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
