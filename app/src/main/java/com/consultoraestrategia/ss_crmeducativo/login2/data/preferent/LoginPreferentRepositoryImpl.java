package com.consultoraestrategia.ss_crmeducativo.login2.data.preferent;

import android.content.Context;
import android.content.SharedPreferences;

import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LoginPreferentRepositoryImpl implements LoginPreferentRepository {

    private Context context;
    private final String PREFERENCIA = "LoginPreferentRepository.Usuario";
    private final String KEY = "usuarios";
    private final String KEYWEBCONFIG = "WebConfig";
    private final String KEYCAMBIOSFB = "CambiosFirebase";
    private final String KEYFECHACAMBIOSFB = "FechaCambiosFirebase";

    public LoginPreferentRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<PersonaUi> getTodosUsuarios() {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        String json = miPreferencia.getString(KEY, "[]");
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<PersonaUi>>(){}.getType());

    }

    @Override
    public void guardarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback) {

        try {
            List<PersonaUi> usuarioUiList = getTodosUsuarios();
            int pocision = usuarioUiList.indexOf(usuarioUi);
            if(pocision==-1){
                SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miPreferencia.edit();
                Gson gson = new Gson();
                usuarioUiList.add(usuarioUi);
                String json = gson.toJson(usuarioUiList);
                editor.putString(KEY, json);
                editor.apply();
            }
            usuarioCallback.onLoad(true, true);
        }catch (Exception e){
            e.printStackTrace();
            usuarioCallback.onLoad(false, false);
        }
    }

    @Override
    public void eliminarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback) {
        try {
            List<PersonaUi> usuarioUiList = getTodosUsuarios();
            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            Gson gson = new Gson();
            usuarioUiList.remove(usuarioUi);
            String json = gson.toJson(usuarioUiList);
            editor.putString(KEY, json);
            editor.apply();
            usuarioCallback.onLoad(true, true);
        }catch (Exception e){
            e.printStackTrace();
            usuarioCallback.onLoad(false, false);
        }
    }

    @Override
    public void elimarTodosUsuario(Callback<Boolean> usuarioCallback) {
        try {
            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            editor.putString(KEY, "[]");
            editor.apply();
            usuarioCallback.onLoad(true, true);
        }catch (Exception e){
            e.printStackTrace();
            usuarioCallback.onLoad(false, false);
        }
    }

    @Override
    public void guardarWebConfig(List<WebConfig> webConfigs) {
        try {
            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            Gson gson = new Gson();
            String json = gson.toJson(webConfigs);
            editor.putString(KEYWEBCONFIG, json);
            editor.apply();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void elimarWebConfig() {
        try {
            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            editor.putString(KEYWEBCONFIG, "[]");
            editor.apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public WebConfig getWebConfig(String key) {
        WebConfig webConfig = null;
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        String json = miPreferencia.getString(KEYWEBCONFIG, "[]");
        Gson gson = new Gson();
        List<WebConfig> webConfigList = gson.fromJson(json, new TypeToken<List<WebConfig>>(){}.getType());
        int position = webConfigList.indexOf(new WebConfig(key));
        if(position!=-1){
            webConfig = webConfigList.get(position);
        }
        return webConfig;
    }

    @Override
    public List<ServiceEnvioFbUi> getListaCambios() {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        String json = miPreferencia.getString(KEYCAMBIOSFB, "[]");
        long fechaCambio = getFechaCambiosFirebase();

        Gson gson = new Gson();
        List<ServiceEnvioFbUi> serviceEnvioFbUiList = new ArrayList<>();
        for (ServiceEnvioFbUi serviceEnvioFbUi : gson.<List<ServiceEnvioFbUi>>fromJson(json, new TypeToken<List<ServiceEnvioFbUi>>(){}.getType())){
            if(fechaCambio < serviceEnvioFbUi.getFechaModificacion()){
                serviceEnvioFbUiList.add(serviceEnvioFbUi);
            }
        }

        return serviceEnvioFbUiList;
    }

    private List<ServiceEnvioFbUi> getListaCambiosSinFiltro() {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        String json = miPreferencia.getString(KEYCAMBIOSFB, "[]");
        Gson gson = new Gson();
        return gson.<List<ServiceEnvioFbUi>>fromJson(json, new TypeToken<List<ServiceEnvioFbUi>>(){}.getType());
    }

    @Override
    public void eliminarCambios() {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferencia.edit();
        editor.putString(KEYCAMBIOSFB, "[]");
        editor.apply();
    }

    @Override
    public void saveCambiosFirebase(List<ServiceEnvioFbUi> serviceEnvioUis) {

        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferencia.edit();
        Gson gson = new Gson();
        List<ServiceEnvioFbUi> serviceEnvioUiList = getListaCambiosSinFiltro();

        for (ServiceEnvioFbUi item : serviceEnvioUis){
            int position = serviceEnvioUiList.indexOf(item);
            if(position!=-1){
                serviceEnvioUiList.set(position, item);
            }else {
                serviceEnvioUiList.add(item);
            }
        }

        Collections.sort(serviceEnvioUiList, new Comparator<ServiceEnvioFbUi>() {
            @Override
            public int compare(ServiceEnvioFbUi o1, ServiceEnvioFbUi o2) {
                return Long.compare(o2.getFechaModificacion(), o1.getFechaModificacion());
            }
        });

        String json = gson.toJson(serviceEnvioUiList);
        editor.putString(KEYCAMBIOSFB, json);
        editor.apply();
    }

    @Override
    public long getFechaCambiosFirebase() {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        return miPreferencia.getLong(KEYFECHACAMBIOSFB, 0L);
    }

    @Override
    public void clearCambiosFirebase() {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferencia.edit();
        editor.putString(KEYCAMBIOSFB, "[]");
        editor.apply();
        saveFechaCambiosFirebase(0L);
    }

    @Override
    public void saveFechaCambiosFirebase(long fecha) {
        try {
            SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferencia.edit();
            editor.putLong(KEYFECHACAMBIOSFB, fecha);
            editor.apply();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
