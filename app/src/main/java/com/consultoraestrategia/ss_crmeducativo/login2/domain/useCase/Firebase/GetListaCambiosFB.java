package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.Firebase;

import android.content.Context;

import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GetListaCambiosFB  {

    private final Context context;
    private LoginDataRepository loginDataRepository;
    private LoginPreferentRepository loginPreferentRepository;
    private static ValueEventListener mListener;

    public GetListaCambiosFB(LoginDataRepository loginDataRepository, LoginPreferentRepository loginPreferentRepository, Context context) {
        this.loginDataRepository = loginDataRepository;
        this.loginPreferentRepository = loginPreferentRepository;
        this.context = context;
    }

    public RetrofitCancel execute(int usuarioId, boolean online, Callback callback){
        if(!online){
            callback.onLoad(false, loginPreferentRepository.getListaCambios());
            return null;
        }
        return loginDataRepository.getCambiosFirebase(usuarioId, loginPreferentRepository.getFechaCambiosFirebase(), false, new LoginDataRepository.Callback<List<ServiceEnvioFbUi>>() {
            @Override
            public void onResponse(boolean success, List<ServiceEnvioFbUi> value) {
                if(success){
                    loginPreferentRepository.saveCambiosFirebase(value);
                    callback.onLoad(true, loginPreferentRepository.getListaCambios());
                }else {
                    callback.onLoad(false, loginPreferentRepository.getListaCambios());
                }
            }
        });
    }

    public interface Callback{
        //0 preload, 1 Success, -1 error
        void onLoad(boolean success, List<ServiceEnvioFbUi> serviceEnvioUiList);
    }
}
