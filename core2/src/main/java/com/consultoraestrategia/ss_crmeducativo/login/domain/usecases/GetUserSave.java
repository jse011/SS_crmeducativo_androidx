package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;
import com.consultoraestrategia.ss_crmeducativo.login.entity.UsuarioUi;

import java.util.Date;

public class GetUserSave extends UseCaseSincrono<GetUserSave.RequestValues, GetUserSave.ResponseValue> {

    private static final String TAG = GetUserSave.class.getSimpleName();
    private LoginRepository repository;

    public GetUserSave(LoginRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(GetUserSave.RequestValues request, final Callback<GetUserSave.ResponseValue> callback) {

        Log.d(TAG, "executeUseCase time: " + new Date().getTime());
        Log.d(TAG, "getUserName :" + request.getUsuario());
        repository.loginUserSimple(request.getUsuario(), request.getPassword(), new LoginDataSource.Callback<Usuario>() {
            @Override
            public void onLoad(boolean success, Usuario item) {
                if (success) {
                    if (item == null) {
                        callback.onResponse(true, new GetUserSave.ResponseValue(null));
                    } else {
                        UsuarioUi usuarioUi = new UsuarioUi();
                        usuarioUi.setUsuarioId(item.getUsuarioId());
                        usuarioUi.setUserName(item.getUsuario());
                        usuarioUi.setPasswordEncrypted(item.getPassword());
                        //usuarioUi.setPersonaId(item.getPersonaId());
                        callback.onResponse(true, new GetUserSave.ResponseValue(usuarioUi));
                    }
                } else {
                    callback.onResponse(false, null);
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final String usuario;
        private final String password;

        public RequestValues(String usuario, String password) {
            this.usuario = usuario;
            this.password = password;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getPassword() {
            return password;
        }
    }


    public static final class ResponseValue implements UseCase.ResponseValue {
        private UsuarioUi usuarioUi;

        public ResponseValue(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }

}