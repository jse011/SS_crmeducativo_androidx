package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetAccesosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetAccesosUIList extends UseCase<GetAccesosUIList.RequestValues, GetAccesosUIList.ResponseValue> {

    private MainRepository repository;

    public GetAccesosUIList(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getAccesosUIList(requestValues.getIdUsuario(), requestValues.getIdHijo(), new GetAccesosListCallback() {
            @Override
            public void onAccesosListLoaded(List<UsuarioAccesoUI> usuarioAccesoUIList) {
                getUseCaseCallback().onSuccess(new ResponseValue(usuarioAccesoUIList));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });


    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int idUsuario;
        private final int idHijo;


        public RequestValues(int idUsuario, int idHijo) {
            this.idUsuario = idUsuario;
            this.idHijo = idHijo;
        }

        public int getIdHijo() {
            return idHijo;
        }

        public int getIdUsuario() {
            return idUsuario;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<UsuarioAccesoUI> usuarioAccesoUIList;

        public ResponseValue(List<UsuarioAccesoUI> usuarioAccesoUIList) {
            this.usuarioAccesoUIList = usuarioAccesoUIList;
        }

        public List<UsuarioAccesoUI> getUsuarioAccesoUIList() {
            return usuarioAccesoUIList;
        }
    }
}
