package com.consultoraestrategia.ss_crmeducativo.asyntasks;

import android.content.Context;
import android.os.AsyncTask;


import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by irvinmarin on 19/12/2016.
 */

public class GuardarAsistenciasAsyntask extends AsyncTask<String, String, String> {
    private static final String TAG = "AsistenciasAsyntask";
    private saveAsistenciaInterface saveAsistenciaInterface;
    private RestAPI restAPI;
    private JSONObject jsonObject, jsonObject2;
    private JSONArray jsonArray;
    ObjectMapper mapper;
    private int estado = 0;
    int idpedido;

    public GuardarAsistenciasAsyntask(saveAsistenciaInterface saveAsistenciaInterface, Context context) {
        this.saveAsistenciaInterface = saveAsistenciaInterface;


        restAPI = new RestAPI();

    }

    public interface saveAsistenciaInterface {
        void saveAsistenciaCorrecto(String mensaje);

        void saveAsistenciaError(String mensaje);
    }


    @Override
    protected String doInBackground(String... params) {

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        switch (estado) {
            case 1:
                saveAsistenciaInterface.saveAsistenciaCorrecto("Asistencias Exportadas ");
//                Log.d(TAG, "asistenciaSesionAlumnoList : " + asistenciaSesionAlumnoList);
                break;
            case -1:
                saveAsistenciaInterface.saveAsistenciaError("Error -1: No se exportaron datos ");
                break;
            case -2:
                saveAsistenciaInterface.saveAsistenciaError("Error -2: No se exportaron datos ");
        }

        super.onPostExecute(s);
    }
}
