package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.SendMessageRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.ui.entities.DataImportantMessageUI;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class GetDataImportantMessageUseCase extends UseCase<GetDataImportantMessageUseCase.RequestValues, GetDataImportantMessageUseCase.ResponseValue> {

    private SendMessageRubroRepository repository;

    public GetDataImportantMessageUseCase(SendMessageRubroRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {


    }


    public static final class RequestValues implements UseCase.RequestValues {
        private final int cargaCursoId;
        private final String rubroId;

        public RequestValues(int cargaCursoId, String rubroId) {
            this.cargaCursoId = cargaCursoId;
            this.rubroId = rubroId;
        }

        public String getRubroId() {
            return rubroId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final DataImportantMessageUI dataImportantMessageUI;

        public ResponseValue(DataImportantMessageUI dataImportantMessageUI) {
            this.dataImportantMessageUI = dataImportantMessageUI;
        }

        public DataImportantMessageUI getDataImportantMessageUI() {
            return dataImportantMessageUI;
        }
    }
}
