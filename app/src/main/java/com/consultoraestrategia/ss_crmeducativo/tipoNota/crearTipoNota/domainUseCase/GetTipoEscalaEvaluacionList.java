package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.domainUseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;


import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class GetTipoEscalaEvaluacionList extends UseCase<GetTipoEscalaEvaluacionList.RequestValues, GetTipoEscalaEvaluacionList.ResponseValue> {

    private CrearTipoNotaRepository repository;

    public GetTipoEscalaEvaluacionList(CrearTipoNotaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoEscalaEvaluacion(requestValues.getUsuarioCreadorId(),new CrearTipoNotaDataSource.Callback<List<TipoEscalaEvaluacionUi>>() {
            @Override
            public void onLoaded(List<TipoEscalaEvaluacionUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }

        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int usuarioCreadorId;

        public RequestValues(int usuarioCreadorId) {
            this.usuarioCreadorId = usuarioCreadorId;
        }

        public int getUsuarioCreadorId() {
            return usuarioCreadorId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<TipoEscalaEvaluacionUi> tipoEscalaEvaluacionUiList;

        public ResponseValue(List<TipoEscalaEvaluacionUi> tipoEscalaEvaluacionUiList) {
            this.tipoEscalaEvaluacionUiList = tipoEscalaEvaluacionUiList;
        }

        public List<TipoEscalaEvaluacionUi> getTipoEscalaEvaluacionUiList() {
            return tipoEscalaEvaluacionUiList;
        }
    }
}
