package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class GetTipoNota extends UseCase<GetTipoNota.RequestValues, GetTipoNota.ResponseValue> {
    private CrearRubroRepository repository;

    public GetTipoNota(CrearRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoNota(requestValues.getTipoNotaId(), new CrearRubroDataSource.Callback<TipoNotaUi>() {
            @Override
            public void onSucess(TipoNotaUi tipoNotaUi) {
                getUseCaseCallback().onSuccess(new ResponseValue(tipoNotaUi));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String tipoNotaId;

        public RequestValues(String tipoNotaId) {
            this.tipoNotaId = tipoNotaId;
        }

        public String getTipoNotaId() {
            return tipoNotaId;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private TipoNotaUi tipoNota;

        public ResponseValue(TipoNotaUi tipoNota) {
            this.tipoNota = tipoNota;
        }

        public TipoNotaUi getTipoNota() {
            return tipoNota;
        }
    }
}
