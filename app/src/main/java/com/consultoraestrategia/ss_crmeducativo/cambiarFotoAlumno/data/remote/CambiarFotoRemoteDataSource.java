package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.remote;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CamabiarFotoDataSourse;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CambiarFotoRemoteDataSource implements CamabiarFotoDataSourse {
    private final static String TAG =CambiarFotoRemoteDataSource.class.getSimpleName();
    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {
        return null;
    }

    @Override
    public void savePathPersona(PersonaUi personaUi) {

    }

    @Override
    public void uploadFileCasoRubro(PersonaUi personaUi, final Callback<String> stringCallback) {

        Rutas rutas = SQLite.select()
                .from(Rutas.class)
                .where(Rutas_Table.rutaId.eq(Rutas.ALUMNO))
                .querySingle();

        if(rutas!=null){
            try {
                ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
                Call<String> call = apiRetrofit1.uploadMultiFileAlumno(rutas.getRuta_ArchivoAsistencia(),personaUi.getPersonaId(), personaUi.getPath());
                call.enqueue(new retrofit2.Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String serverResponse = response.body();

                        if (serverResponse != null) {
                            if(!TextUtils.isEmpty(serverResponse)
                                    &&!serverResponse.equals("null")){
                                Log.d(TAG, "serverResponse: " + serverResponse);
                                stringCallback.onLoad(true, serverResponse);
                            }else {
                                Log.d(TAG, "vacio");
                                stringCallback.onLoad(false, null);
                            }

                        } else {
                            Log.d(TAG, "vacio");
                            stringCallback.onLoad(false, null);
                        }
                        // callback.onLoad(true, "pruebas");
                        //downloadCancelUi.setCancel(true);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        stringCallback.onLoad(false, null);
                        t.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                stringCallback.onLoad(false, null);
            }
        }else {
            stringCallback.onLoad(false, null);
        }

    }
}
