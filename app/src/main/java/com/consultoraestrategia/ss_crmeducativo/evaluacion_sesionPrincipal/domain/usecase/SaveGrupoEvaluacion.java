package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionGrupoCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;


/**
 * Created by SCIEV on 16/10/2017.
 */

public class SaveGrupoEvaluacion extends UseCase<SaveGrupoEvaluacion.RequestValues, SaveGrupoEvaluacion.ResponseValue> {
    private RubroRepository repository;

    public SaveGrupoEvaluacion(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.saveEvaluacionGrupo( requestValues.getSesionAprendizajeId()
                ,requestValues.getRubroEvaluacionUi()
                ,requestValues.getGrupoEvaluacionUi()
                ,requestValues.getAlumnosEvaluacionUi(), new SaveEvaluacionGrupoCallBack() {
                    @Override
                    public void localSuccess(GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, boolean success) {
                        getUseCaseCallback().onSuccess(new ResponseValue(grupoEvaluacionUi,alumnosEvaluacionUi,success));
                    }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{

        private int sesionAprendizajeId;
        private RubroEvaluacionUi rubroEvaluacionUi;
        private GrupoEvaluacionUi grupoEvaluacionUi;
        private AlumnosEvaluacionUi alumnosEvaluacionUi;

        public RequestValues(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            this.grupoEvaluacionUi = grupoEvaluacionUi;
            this.alumnosEvaluacionUi = alumnosEvaluacionUi;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }

        public GrupoEvaluacionUi getGrupoEvaluacionUi() {
            return grupoEvaluacionUi;
        }

        public AlumnosEvaluacionUi getAlumnosEvaluacionUi() {
            return alumnosEvaluacionUi;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
       private GrupoEvaluacionUi grupoEvaluacionUi;
       private AlumnosEvaluacionUi alumnosEvaluacionUi;
       private Boolean succes;

        public ResponseValue(GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, Boolean succes) {
            this.grupoEvaluacionUi = grupoEvaluacionUi;
            this.alumnosEvaluacionUi = alumnosEvaluacionUi;
            this.succes = succes;
        }

        public GrupoEvaluacionUi getGrupoEvaluacionUi() {
            return grupoEvaluacionUi;
        }

        public AlumnosEvaluacionUi getAlumnosEvaluacionUi() {
            return alumnosEvaluacionUi;
        }

        public Boolean getSucces() {
            return succes;
        }
    }
}
