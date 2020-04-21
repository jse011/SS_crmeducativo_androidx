package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetHijosListCallback;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetHijosUIList extends UseCase<GetHijosUIList.RequestValues, GetHijosUIList.ResponseValue> {

    private static final String TAG = GetHijosUIList.class.getSimpleName();
    private MainRepository repository;

    public GetHijosUIList(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getHijosUIList(requestValues.getIdUsuario(), requestValues.getIdHijo(), new GetHijosListCallback() {
            @Override
            public void onHijosListLoaded(List<Persona> hijoUIList) {
                getUseCaseCallback().onSuccess(new ResponseValue(hijoUIList));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
                Log.d(TAG, error);
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

        public int getIdUsuario() {
            return idUsuario;
        }

        public int getIdHijo() {
            return idHijo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<Persona> hijoUIList;

        public ResponseValue(List<Persona> hijoUIList) {
            this.hijoUIList = hijoUIList;
        }

        public List<Persona> getHijoUIList() {
            return hijoUIList;
        }
    }
}
