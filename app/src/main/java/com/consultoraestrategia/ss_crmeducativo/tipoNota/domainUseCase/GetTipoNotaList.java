package com.consultoraestrategia.ss_crmeducativo.tipoNota.domainUseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.TipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.TipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public class GetTipoNotaList extends UseCase<GetTipoNotaList.RequestValues,GetTipoNotaList.ResponseValues> {

    private TipoNotaRepository repository;

    public GetTipoNotaList(TipoNotaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoNota(requestValues.getUsuarioCreacionId(), new TipoNotaDataSource.CallabackTipoNota() {
            @Override
            public void onListTipoNota(List<TipoNotaUi> tipoNotaUiList) {
                getUseCaseCallback().onSuccess(new ResponseValues(tipoNotaUiList));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private int usuarioCreacionId;

        public RequestValues(int usuarioCreacionId) {
            this.usuarioCreacionId = usuarioCreacionId;
        }

        public int getUsuarioCreacionId() {
            return usuarioCreacionId;
        }
    }
    public static final class ResponseValues implements UseCase.ResponseValue{
        List<TipoNotaUi> tipoNotaUiList ;

        public ResponseValues(List<TipoNotaUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoNotaUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }
    }
}
