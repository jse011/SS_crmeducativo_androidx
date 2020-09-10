package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.entities.Rol;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetUsuarioCallback;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioUi;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetUsuarioUI extends UseCaseSincrono<Void, GetUsuarioUI.ResponseValue>{

    private MainRepository repository;

    public GetUsuarioUI(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Void request, Callback<GetUsuarioUI.ResponseValue> callback) {
        repository.getUsuarioUI(new GetUsuarioCallback() {
            @Override
            public void onError(String error) {
                callback.onResponse(false, null);
            }

            @Override
            public void onUsuarioLoaded(UsuarioUi usuarioUi) {
                callback.onResponse(true,new ResponseValue(usuarioUi));
            }

        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final UsuarioUi usuarioUi;

        public ResponseValue(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }
}
