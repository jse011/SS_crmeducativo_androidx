package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;

import java.util.List;

public class GetUsuarioUiDestinos extends UseCase<GetUsuarioUiDestinos.RequestValues, GetUsuarioUiDestinos.ResponseValue> {

     ComportamientoRepository comportamientoRepository;

    public GetUsuarioUiDestinos(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        comportamientoRepository.getUsuariosDestino(requestValues.getGeoReferenciaId(), new ComportamientoDataSource.SucessCallback<List<UsuarioUi>>() {
            @Override
            public void onLoad(boolean success, List<UsuarioUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int geoReferenciaId;

        public int getGeoReferenciaId() {
            return geoReferenciaId;
        }

        public RequestValues(int geoReferenciaId) {
            this.geoReferenciaId = geoReferenciaId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<UsuarioUi> usuarioUiList;

        public ResponseValue(List<UsuarioUi> usuarioUiList) {
            this.usuarioUiList = usuarioUiList;
        }

        public List<UsuarioUi> getUsuarioUiList() {
            return usuarioUiList;
        }
    }
}
