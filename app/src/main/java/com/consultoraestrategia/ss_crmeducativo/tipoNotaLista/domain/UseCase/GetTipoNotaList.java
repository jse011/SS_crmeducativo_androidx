package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;

import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class GetTipoNotaList extends UseCase<GetTipoNotaList.RequestValues, GetTipoNotaList.ResponseValue> {
    private TipoNotaListRepository repository;

    public GetTipoNotaList(TipoNotaListRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoNotaList(new TipoNotaListDataSource.CallbackList<TipoNotaUi>() {
            @Override
            public void onLoadList(List<TipoNotaUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        }, requestValues);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int programaEducativo;
        private TipoUi.Tipo tipos[];

        public RequestValues(int programaEducativo, TipoUi.Tipo[] tipos) {
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
        List<TipoNotaUi> tipoNotaUiList;

        public ResponseValue(List<TipoNotaUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoNotaUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }
    }
}
