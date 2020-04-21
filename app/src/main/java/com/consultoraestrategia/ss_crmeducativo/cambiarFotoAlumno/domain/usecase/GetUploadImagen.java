package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CamabiarFotoDataSourse;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CambiarFotoRepository;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

public class GetUploadImagen extends UseCaseSincrono<PersonaUi, PersonaUi> {
    private CambiarFotoRepository repository;
    private static final String TAG = GetUploadImagen.class.getSimpleName();

    public GetUploadImagen(CambiarFotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final PersonaUi request, final Callback<PersonaUi> callback) {

        repository.uploadFileCasoRubro(request, new CamabiarFotoDataSourse.Callback<String>() {
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
