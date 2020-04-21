package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;

import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public class GetCamposAccion extends UseCase<GetCamposAccion.RequestValues, GetCamposAccion.ResponseValue> {

    private static final String TAG = GetCamposAccion.class.getSimpleName();
    CreateRubBidRepository repository;

    public static final int BY_SESSION = 100;
    public static final int BY_CARGA_CURSO_PERIODO = 101;

    public GetCamposAccion(CreateRubBidRepository repository) {
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

        switch (tipo) {
            default:
                repository.getCamposAccion(sesionAprendizajeId, new CreateRubBidDataSource.Callback<CampoAccionUi>() {
                    @Override
                    public void onLoaded(List<CampoAccionUi> listLoaded) {
                        getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
                    }
                });
                break;
            case BY_CARGA_CURSO_PERIODO:
                repository.getCamposAccion(cursoId, cargaCursoId, calendarioPeriodoId, new CreateRubBidDataSource.Callback<CampoAccionUi>() {
                    @Override
                    public void onLoaded(List<CampoAccionUi> listLoaded) {
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
        private final List<CampoAccionUi> campoAccionUiList;

        public ResponseValue(List<CampoAccionUi> campoAccionUiList) {
            this.campoAccionUiList = campoAccionUiList;
        }

        public List<CampoAccionUi> getCampoAccionUiList() {
            return campoAccionUiList;
        }
    }
}
