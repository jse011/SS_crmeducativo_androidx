package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public class GetCompetencias extends UseCase<GetCompetencias.RequestValues, GetCompetencias.ResponseValue> {

    private static final String TAG = GetCompetencias.class.getSimpleName();
    CreateRubBidRepository repository;

    public static final int BY_SESSION = 100;
    public static final int BY_CARGA_CURSO_PERIODO = 101;

    public GetCompetencias(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        int tipo = requestValues.getTipo();
        int sesionAprendizajeId = requestValues.getSesionAprendizajeId();
        int cursoId = requestValues.getCursoId();
        int cargaCursoId = requestValues.getCargaCursoId();
        int calendarioPeriodoId = requestValues.getCalendarioPeriodoId();
        Log.d(TAG, "tipo:" + tipo);
        switch (tipo) {
            default:
                repository.getCompetencias(sesionAprendizajeId, new CreateRubBidDataSource.Callback<CompetenciaUi>() {
                    @Override
                    public void onLoaded(List<CompetenciaUi> listLoaded) {
                        Log.d(TAG, "listLoaded:" + listLoaded.size());
                        getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
                    }
                });
                break;
            case BY_CARGA_CURSO_PERIODO:
                repository.getCompetencias(cursoId, cargaCursoId, calendarioPeriodoId, new CreateRubBidDataSource.Callback<CompetenciaUi>() {
                    @Override
                    public void onLoaded(List<CompetenciaUi> listLoaded) {
                        getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
                    }
                });
                break;
        }
    }

    public static class RequestValues implements UseCase.RequestValues {
        private final int tipo;
        private final int sesionAprendizajeId;
        private final int cursoId;
        private final int cargaCursoId;
        private final int calendarioPeriodoId;

        public RequestValues(int tipo, int sesionAprendizajeId, int cursoId, int cargaCursoId, int calendarioPeriodoId) {
            this.tipo = tipo;
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.cursoId = cursoId;
            this.cargaCursoId = cargaCursoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public int getTipo() {
            return tipo;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public int getCursoId() {
            return cursoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<CompetenciaUi> competenciaList;

        public ResponseValue(List<CompetenciaUi> competenciaList) {
            this.competenciaList = competenciaList;
        }

        public List<CompetenciaUi> getCompetenciaList() {
            return competenciaList;
        }
    }
}
