package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.entities.Rol;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;
import com.consultoraestrategia.ss_crmeducativo.login.entity.UsuarioUi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetUsuarioSimple extends UseCaseSincrono<GetUsuarioSimple.RequestValues, GetUsuarioSimple.ResponseValue> {

    private static final String TAG = GetUsuarioSimple.class.getSimpleName();
    private LoginRepository repository;

    public GetUsuarioSimple(LoginRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {

        Log.d(TAG, "executeUseCase time: " + new Date().getTime());
        Log.d(TAG, "getUserName :" + request.getUsuarioExternoId());
        repository.loginUserSimple(request.getUsuarioExternoId(), new LoginDataSource.Callback<Usuario>() {
            @Override
            public void onLoad(boolean success, Usuario item) {
                if(success){
                    if(item == null){
                        callback.onResponse(false, null);
                    }else {
                        UsuarioUi usuarioUi = new UsuarioUi();
                        usuarioUi.setUsuarioId(item.getUsuarioId());
                        usuarioUi.setUserName(item.getUsuario());
                        usuarioUi.setPasswordEncrypted(item.getPassword());
                        usuarioUi.setPersonaId(item.getPersonaId());
                        usuarioUi.setPersonaImagenUrl(item.getFotoPersona());
                        usuarioUi.setInstitucionUrl(item.getFotoEntidad());
                        List<Rol> rolList = item.getRoles();
                        List<Integer> rolIdList = new ArrayList<>();

                        if(rolList!=null){
                            for (Rol rol : rolList)rolIdList.add(rol.getRolId());
                        }

                        usuarioUi.setRolIdList(rolIdList);
                        callback.onResponse(true, new ResponseValue(usuarioUi));
                    }
                }else {
                    callback.onResponse(false, null);
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int usuarioExternoId;

        public RequestValues(int usuarioExternoId) {
            this.usuarioExternoId = usuarioExternoId;
        }

        public int getUsuarioExternoId() {
            return usuarioExternoId;
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
