package com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents;

import android.content.Context;
import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

import java.util.List;

public class RepositorioPreferentsDataSource implements RepositorioDataSource {
    private Context context;
    private final String PREFERENCIA = "Usuario";
    private final String KEY = "usuarios";

    @Override
    public void dowloadImage(String url, String nombre, String carpeta, CallbackProgress<String> repositorioFileUiCallback) {

    }

    @Override
    public void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void uploadFileJustificacion(String archivoId, String urlServidor, String path, CallbackProgress<String> callback) {

    }

    @Override
    public void getUrlRepositorioArchivo(Callback<String> stringCallback) {

    }

    @Override
    public void uploadFileArchivo(String archivoId, String urlServidor, String path, CallbackProgress<String> callbackProgress) {

    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {

    }

    @Override
    public void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void uploadFileCaso(UpdateRepositorioFileUi updateRepositorioFileUi, String urlServidor, Uri path, CallbackProgress<String> callbackProgress) {

    }

    @Override
    public void updateSucessDowloadRubro(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void uploadFileCasoRubro(String archivoId, String urlServidor, Uri path, CallbackProgress<String> callbackProgress) {

    }
/*
    @Override
    public void getTodos(Callback<List<RepositorioFileUi>> callback) {
        List<PersonaUi> usuarioUiList;
        try {
            usuarioUiList = getTodosUsuarios();
            usuarioCallback.onLoad(true, usuarioUiList);
        }catch (Exception e){
            e.printStackTrace();
            usuarioCallback.onLoad(false , new ArrayList<PersonaUi>());
        }

    }

    private List<RepositorioFileUi> getTodosUsuarios() throws Exception {
        SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        String json = miPreferencia.getString(KEY, "[]");
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<PersonaUi>>(){}.getType());
    }

    @Override
    public void guardar(RepositorioFileUi repositorioFileUi, Callback<Boolean> callback) {

        try {
            List<RepositorioFileUi> repositorioFileUiList = getTodosUsuarios();

            for (RepositorioFileUi repositorioFileUi : )
            int pocision = repositorioFileUiList.indexOf(repositorioFileUi);


            if(pocision==-1){
                SharedPreferences miPreferencia = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miPreferencia.edit();
                Gson gson = new Gson();
                repositorioFileUiList.add(repositorioFileUi);
                String json = gson.toJson(repositorioFileUi);
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
    public void eliminar(RepositorioFileUi repositorioFileUi, Callback<Boolean> callback) {
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
    public void elimarTodos(Callback<Boolean> callback) {
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
    }*/

}
