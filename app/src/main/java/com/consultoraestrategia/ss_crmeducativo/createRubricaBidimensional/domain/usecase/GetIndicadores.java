package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public class GetIndicadores  extends UseCase<GetIndicadores.RequestValues, GetIndicadores.ResponseValue> {

    private static final String TAG = GetIndicadores.class.getSimpleName();
    private CreateRubBidRepository repository;

    public GetIndicadores(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        repository.getIndicadores(requestValues.getIdCompetencia(), new CreateRubBidDataSource.Callback<IndicadorUi>() {
            @Override
            public void onLoaded(List<IndicadorUi> listLoaded) {
                getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private final int idCompetencia;

        public RequestValues(int idCompetencia) {
            this.idCompetencia = idCompetencia;
        }

        public int getIdCompetencia() {
            return idCompetencia;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<IndicadorUi> indicadores;

        public ResponseValue(List<IndicadorUi> competenciaUiList) {
            this.indicadores = competenciaUiList;
        }

        public List<IndicadorUi> getIndicadores() {
            return indicadores;
        }
    }
}
