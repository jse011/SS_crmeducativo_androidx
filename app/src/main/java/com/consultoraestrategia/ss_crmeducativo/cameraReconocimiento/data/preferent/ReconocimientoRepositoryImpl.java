package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.preferent;

import android.content.Context;
import android.content.SharedPreferences;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.Sentimiento1;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ReconocimientoRepositoryImpl implements ReconocimientoPreferentRepository {

    private Context context;
    private final String PREFERENCIA = "ReconocimientoPreferentRepository.Reconsimento";
    private final String KEY = "fotos";
    public ReconocimientoRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<SentimientoUi> getFotos(int personaId) {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        String json = miPreferencia.getString(String.valueOf(personaId), "[]");
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<SentimientoUi>>(){}.getType());
    }

    @Override
    public void guardarFotos(List<SentimientoUi> fotos, int personaId, Callback<Boolean> usuarioCallback) {
        try {

            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            Gson gson = new Gson();
            String json = gson.toJson(fotos);
            editor.putString(String.valueOf(personaId), json);
            editor.apply();

            usuarioCallback.onLoad(true, true);
        }catch (Exception e){
            e.printStackTrace();
            usuarioCallback.onLoad(false, false);
        }
    }

    @Override
    public void eliminarFotos(int personaId, Callback<Boolean> usuarioCallback) {
        try {
            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            editor.putString(String.valueOf(personaId), "[]");
            editor.apply();
            usuarioCallback.onLoad(true, true);
        }catch (Exception e){
            e.printStackTrace();
            usuarioCallback.onLoad(false, false);
        }
    }
}
