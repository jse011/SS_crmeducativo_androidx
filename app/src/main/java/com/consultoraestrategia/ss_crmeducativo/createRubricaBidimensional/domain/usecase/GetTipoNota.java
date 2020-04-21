package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public class GetTipoNota extends UseCase<GetTipoNota.RequestValues, GetTipoNota.ResponseValue> {

    private static final String TAG = GetTipoNota.class.getSimpleName();
    private CreateRubBidRepository repository;

    public GetTipoNota(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        repository.getTipoNota(requestValues.getTiposId(),new CreateRubBidDataSource.CallbackSingle<TipoNotaUi>() {
            @Override
            public void onLoaded(TipoNotaUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private String tiposId;

        public RequestValues(String tiposId) {
            this.tiposId = tiposId;
        }

        public String getTiposId() {
            return tiposId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final TipoNotaUi tipoNotaUi;

        public ResponseValue(TipoNotaUi tipoNotaUi) {
            this.tipoNotaUi = tipoNotaUi;
        }

        public TipoNotaUi getTipoNotaUi() {
            return tipoNotaUi;
        }
    }
}
