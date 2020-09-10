package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;

public class GetUploadImagen extends UseCaseSincrono<PersonaUi, PersonaUi> {
    private MainRepository repository;
    private static final String TAG = GetUploadImagen.class.getSimpleName();

    public GetUploadImagen(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final PersonaUi request, final Callback<PersonaUi> callback) {

        repository.uploadFile(request, new MainDataSource.SucessCallback<String>() {
            @Override
            public void onLoad(boolean success, String item) {
                Log.d(TAG, "success " + success);
                if(success){
                    Log.d(TAG, "urls " + item);
                    if(!TextUtils.isEmpty(item)){
                        request.setFoto(item.substring(0,item.length()-1));
                        callback.onResponse(true, request);
                    }else {
                        callback.onResponse(false, request);
                    }
                }else {
                    callback.onResponse(false, request);
                }
            }
        });
    }


}
