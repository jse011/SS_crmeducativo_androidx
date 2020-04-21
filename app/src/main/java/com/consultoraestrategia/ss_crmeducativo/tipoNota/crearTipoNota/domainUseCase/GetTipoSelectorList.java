package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.domainUseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;

import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public class GetTipoSelectorList extends UseCase<GetTipoSelectorList.RequestValues,GetTipoSelectorList.ResponseValue> {

    private CrearTipoNotaRepository repository;

    public GetTipoSelectorList(CrearTipoNotaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoSelector(requestValues.getIdUsuario(),new CrearTipoNotaDataSource.Callback<List<TipoSelectorUi>>() {
            @Override
            public void onLoaded(List<TipoSelectorUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int idUsuario;

        public RequestValues(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<TipoSelectorUi> tipoSelectorUiList;

        public ResponseValue(List<TipoSelectorUi> tipoSelectorUiList) {
            this.tipoSelectorUiList = tipoSelectorUiList;
        }

        public List<TipoSelectorUi> getTipoSelectorUiList() {
            return tipoSelectorUiList;
        }
    }
}
