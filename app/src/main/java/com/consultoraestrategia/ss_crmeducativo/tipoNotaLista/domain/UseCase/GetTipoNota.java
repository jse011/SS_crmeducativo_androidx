package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class GetTipoNota extends UseCase<GetTipoNota.RequestValues, GetTipoNota.ResponseValue> {
    private TipoNotaListRepository repository;

    public GetTipoNota(TipoNotaListRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoNota(requestValues.getTipoNotaId(), new TipoNotaListDataSource.Callback<TipoNotaUi>() {
            @Override
            public void onLoad(boolean success, TipoNotaUi item) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String tipoNotaId;

        public RequestValues(String tipoNotaId) {
            this.tipoNotaId = tipoNotaId;
        }

        public String getTipoNotaId() {
            return tipoNotaId;
        }
    }

    public final class ResponseValue implements UseCase.ResponseValue {
        private TipoNotaUi tipoNotaUi;

        public ResponseValue(TipoNotaUi tipoNotaUi) {
            this.tipoNotaUi = tipoNotaUi;
        }

        public TipoNotaUi getTipoNotaUi() {
            return tipoNotaUi;
        }
    }
}
