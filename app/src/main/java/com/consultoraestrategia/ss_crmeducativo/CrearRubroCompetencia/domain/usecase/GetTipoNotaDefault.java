package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class GetTipoNotaDefault {
    private CrearRubroRepository repository;

    public GetTipoNotaDefault(CrearRubroRepository repository) {
        this.repository = repository;
    }

    public TipoNotaUi executeUseCase(RequestValues requestValues) {
        return repository.getTipoNota(requestValues.getPromaEducativoId());
    }

    public static final class RequestValues {
        private int promaEducativoId;

        public RequestValues(int promaEducativoId) {
            this.promaEducativoId = promaEducativoId;
        }

        public int getPromaEducativoId() {
            return promaEducativoId;
        }
    }
    public static final class ResponseValue{
        private TipoNotaUi tipoNota;

        public ResponseValue(TipoNotaUi tipoNota) {
            this.tipoNota = tipoNota;
        }

        public TipoNotaUi getTipoNota() {
            return tipoNota;
        }
    }
}
