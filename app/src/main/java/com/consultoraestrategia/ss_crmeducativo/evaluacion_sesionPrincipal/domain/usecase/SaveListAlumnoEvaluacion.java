package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveListEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

public class SaveListAlumnoEvaluacion extends UseCaseSincrono<SaveListAlumnoEvaluacion.RequestValues, SaveListAlumnoEvaluacion.ResponseValue> {

    private RubroRepository repository;

    public SaveListAlumnoEvaluacion(RubroRepository repository) {
        this.repository = repository;
    }



    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        repository.SaveEvaluacionList( requestValues.getSesionAprendizajeId()
                ,requestValues.getRubroEvaluacionUi()
                ,requestValues.getAlumnosEvaluacionUiList(), new SaveListEvaluacionCallBack() {
                    @Override
                    public void localSuccess(boolean success) {
                        callback.onResponse(true, new SaveListAlumnoEvaluacion.ResponseValue(success) );
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues{

        private int sesionAprendizajeId;
        private RubroEvaluacionUi rubroEvaluacionUi;
        private List<AlumnosEvaluacionUi> alumnosEvaluacionUiList;

        public RequestValues(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUiList) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            this.alumnosEvaluacionUiList = alumnosEvaluacionUiList;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }

        public List<AlumnosEvaluacionUi> getAlumnosEvaluacionUiList() {
            return alumnosEvaluacionUiList;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{

        private Boolean succes;

        public ResponseValue(Boolean succes) {
            this.succes = succes;
        }

        public Boolean getSucces() {
            return succes;
        }
    }
}
