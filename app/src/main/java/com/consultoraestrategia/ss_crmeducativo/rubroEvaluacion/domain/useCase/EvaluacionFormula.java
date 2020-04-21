package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;

import java.util.List;

/**
 * Created by kike on 14/05/2018.
 */

public class EvaluacionFormula extends UseCase<EvaluacionFormula.RequestValues, EvaluacionFormula.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public EvaluacionFormula(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onEvaluacionFormulaList(requestValues.getCargaCursoId(), requestValues.getCursoId(), requestValues.getRubroProcesoUi(), new RubroEvaluacionProcesoListaDataSource.CallBackList<List<AlumnosUi>>() {
            @Override
            public void onLoadList(List<AlumnosUi> tList,List<List<FormulaCelda>>formulaCeldasList) {
                getUseCaseCallback().onSuccess(new ResponseValue(tList,formulaCeldasList));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private RubroProcesoUi rubroProcesoUi;
        private int cargaCursoId;
        private int cursoId;

        public RequestValues(RubroProcesoUi rubroProcesoUi, int cargaCursoId, int cursoId) {
            this.rubroProcesoUi = rubroProcesoUi;
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCursoId() {
            return cursoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<AlumnosUi> alumnosUis;
        private List<List<FormulaCelda>>formulaCeldasList;

        public ResponseValue(List<AlumnosUi> alumnosUis, List<List<FormulaCelda>> formulaCeldasList) {
            this.alumnosUis = alumnosUis;
            this.formulaCeldasList = formulaCeldasList;
        }

        public List<AlumnosUi> getAlumnosUis() {
            return alumnosUis;
        }

        public List<List<FormulaCelda>> getFormulaCeldasList() {
            return formulaCeldasList;
        }
    }
}
