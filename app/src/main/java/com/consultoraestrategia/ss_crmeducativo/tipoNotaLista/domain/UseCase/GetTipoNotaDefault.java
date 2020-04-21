package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class GetTipoNotaDefault extends UseCase<GetTipoNotaDefault.RequestValues, GetTipoNotaDefault.ResponseValue> {
    private TipoNotaListRepository repository;

    public GetTipoNotaDefault(TipoNotaListRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoNotaDefault(new TipoNotaListDataSource.Callback<TipoNotaUi>() {
            @Override
            public void onLoad(boolean success, TipoNotaUi item) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        }, requestValues);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int programaEducativo;
        private TipoUi.Tipo tipos[];

        public RequestValues(int programaEducativo, TipoUi.Tipo... tipos) {
            this.programaEducativo = programaEducativo;
            this.tipos = tipos;
        }

        public int getProgramaEducativo() {
            return programaEducativo;
        }

        public TipoUi.Tipo[] getTipos() {
            return tipos;
        }
    }

    public final class ResponseValue implements UseCase.ResponseValue {
        TipoNotaUi tipoNotaUi;

        public ResponseValue(TipoNotaUi tipoNotaUi) {
            this.tipoNotaUi = tipoNotaUi;
        }

        public TipoNotaUi getTipoNotaUi() {
            return tipoNotaUi;
        }
    }
}
