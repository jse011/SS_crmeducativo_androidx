package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.EvaluacionResultadoDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.EvaluacionResultadoRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;

import java.util.List;

/**
 * Created by @stevecampos on 18/10/2017.
 */

public class GetRubroEvaluacionList extends UseCase<GetRubroEvaluacionList.RequestValues, GetRubroEvaluacionList.ResponseValue> {

    private EvaluacionResultadoRepository repository;

    public GetRubroEvaluacionList(EvaluacionResultadoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTableEvaluacionResultado(requestValues.getRubroEvalId(), requestValues.getTipo(), new EvaluacionResultadoDataSource.SucessCallback<List<AlumnoUi>>() {
            @Override
            public void onLoad(boolean success, List<AlumnoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int rubroEvalId;
        private final int  tipo;

        public int getRubroEvalId() {
            return rubroEvalId;
        }

        public RequestValues(int rubroEvalId, int tipo) {
            this.rubroEvalId = rubroEvalId;
            this.tipo = tipo;
        }

        public int getTipo() {
            return tipo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private  final List<AlumnoUi> AlumnosList;

        public List<AlumnoUi> getAlumnosList() {
            return AlumnosList;
        }

        public ResponseValue(List<AlumnoUi> alumnosList) {
            AlumnosList = alumnosList;
        }
    }
}
