package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;


/**
 * Created by SCIEV on 16/10/2017.
 */

public class SaveAlumnoEvaluacion extends UseCaseSincrono<SaveAlumnoEvaluacion.RequestValues, SaveAlumnoEvaluacion.ResponseValue> {

    private RubroRepository repository;

    public SaveAlumnoEvaluacion(RubroRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        repository.SaveRubro( request.getSesionAprendizajeId()
                ,request.getRubroEvaluacionUi()
                ,request.getAlumnosEvaluacionUi(), new SaveEvaluacionCallBack() {
                    @Override
                    public void localSuccess(AlumnosEvaluacionUi alumnosEvaluacionUi, boolean success) {
                    if(success)
                        callback.onResponse(success, new ResponseValue(alumnosEvaluacionUi, success));
                    else callback.onResponse(false,null);
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues{

        private int sesionAprendizajeId;
        private RubroEvaluacionUi rubroEvaluacionUi;
        private AlumnosEvaluacionUi alumnosEvaluacionUi;

        public RequestValues(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            this.alumnosEvaluacionUi = alumnosEvaluacionUi;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }

        public AlumnosEvaluacionUi getAlumnosEvaluacionUi() {
            return alumnosEvaluacionUi;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
       private AlumnosEvaluacionUi alumnosEvaluacionUi;
       private Boolean succes;

        public ResponseValue(AlumnosEvaluacionUi alumnosEvaluacionUi, Boolean succes) {
            this.alumnosEvaluacionUi = alumnosEvaluacionUi;
            this.succes = succes;
        }

        public AlumnosEvaluacionUi getAlumnosEvaluacionUi() {
            return alumnosEvaluacionUi;
        }

        public Boolean getSucces() {
            return succes;
        }
    }
}
