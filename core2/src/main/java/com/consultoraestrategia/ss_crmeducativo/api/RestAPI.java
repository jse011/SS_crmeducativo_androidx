/* JSON API for android application [Web Service] */
package com.consultoraestrategia.ss_crmeducativo.api;

import android.text.TextUtils;
import android.util.Log;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class RestAPI {
    private static final String TAG = "RestAPI";
    public static final String REMOTE_URL = "http://crmeducativo.consultoraestrategia.com/CRMMovil/PortalAcadMovil.ashx";

    String url = REMOTE_URL;
    private static RestAPI instance = null;

    public static RestAPI getInstance(String url) {
        if (instance == null) {
            instance = new RestAPI(url);
        }
        return instance;
    }

    public RestAPI(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
        }
    }

    public RestAPI() {
        updateServerUrl();
    }
    public void updateServerUrl(){
        String serverUrl = GlobalSettings.getServerUrl();
        if (!TextUtils.isEmpty(serverUrl)) {
            this.url = serverUrl;
        }
    }

    public static JSONObject convertStreamToUTF8String(InputStream stream) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[4096];
            int readedChars = 0;
            while (readedChars != -1) {
                readedChars = reader.read(buffer);
                if (readedChars > 0)
                    sb.append(buffer, 0, readedChars);
            }
            jsonObject = new JSONObject(sb.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "UnsupportedEncodingException: "+ e);
        }
        return jsonObject;
    }



    public JSONObject load(String contents) throws IOException, JSONException {
//        Log.d(TAG, "contents : " + contents);
        Log.d(TAG, "url server : " + this.url);
        URL url = new URL(this.url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(60000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
        w.write(contents);
        w.flush();
        InputStream istream;
        if("gzip".equals(conn.getContentEncoding())){
            Log.d(TAG, "ContentEncoding : gzip");
            istream  = new GZIPInputStream(conn.getInputStream());
        }else {
            Log.d(TAG, "ContentEncoding : none");
            istream = conn.getInputStream();
        }

        return convertStreamToUTF8String(istream);
    }


    private Object mapObject(Object o) {
        Object finalValue = null;
        if (o.getClass().equals(String.class)) {
            finalValue = o;
        } else if (Number.class.isInstance(o)) {
            finalValue = String.valueOf(o);
        } else if (Date.class.isInstance(o)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", new Locale("en", "USA"));
            finalValue = sdf.format((Date) o);
        } else if (Collection.class.isInstance(o)) {
            Collection<?> col = (Collection<?>) o;
            JSONArray jarray = new JSONArray();
            for (Object item : col) {
                jarray.put(mapObject(item));
            }
            finalValue = jarray;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            Method[] methods = o.getClass().getMethods();
            for (Method method : methods) {
                if ((method.getDeclaringClass() == o.getClass()
                        || method.getDeclaringClass() == LocalEntity.class
                        || method.getDeclaringClass() == BaseRelEntity.class
                        || method.getDeclaringClass() == BaseEntity.class)
                        && method.getModifiers() == Modifier.PUBLIC
                        && method.getName().startsWith("get")) {
                    String key = method.getName().substring(3);
                    try {
                        Object obj = method.invoke(o, (Object[]) null);
                        Object value = mapObject(obj);
                        map.put(key, value);
                        finalValue = new JSONObject(map);
                    } catch (Exception e) {
                        Log.d(TAG, "Exception: "+ e);
                    }
                }
            }

        }
        return finalValue;
    }

    public JSONObject flst_MessengerListas(String usero, String passo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "flst_MessengerListas");
        p.put("usero", mapObject(usero));
        p.put("passo", mapObject(passo));
        o.put("parameters", p);
        String s = o.toString();
        return load(s);
    }

    public JSONObject fobj_ObtenerUsuario(String vstr_Usuario, String vstr_Clave) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fobj_ObtenerUsuario");
        p.put("vstr_Usuario", mapObject(vstr_Usuario));
        p.put("vstr_Clave", mapObject(vstr_Clave));
        o.put("parameters", p);
        String s = o.toString();
       return load(s);
    }

    public JSONObject flst_ObtenerDatos(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "flst_ObtenerDatos");
        p.put("vint_UsuarioId", mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarRubroEvaluacionProceso(ArrayList<Object> vobj_RubroEvaluacionProceso) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarRubroEvaluacionProceso");
        p.put("vobj_RubroEvaluacionProceso", mapObject(vobj_RubroEvaluacionProceso));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarEvaluacionProceso(ArrayList<Object> vobj_EvaluacionProceso) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fins_GuardarEvaluacionProceso");
        p.put("vobj_EvaluacionProceso", mapObject(vobj_EvaluacionProceso));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fUpd_ActualizarEvaluacionesResultados(ArrayList<Object> vlst_EvaluacionResultado) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fUpd_ActualizarEvaluacionesResultados");
        p.put("vlst_EvaluacionResultado", mapObject(vlst_EvaluacionResultado));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fUpd_ActualizarAsistenciaAlumnos(ArrayList<Object> vlst_AsistenciasAlumno) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fUpd_ActualizarAsistenciaAlumnos");
        p.put("vlst_AsistenciasAlumno", mapObject(vlst_AsistenciasAlumno));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fInsUpd_Mensajes(ArrayList<Object> vlst_Mensaje) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fInsUpd_Mensajes");
        p.put("vlst_Mensaje", mapObject(vlst_Mensaje));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }


    public JSONObject fInsUpd_Grupos(ArrayList<Object> vlst_GrupoEquipo) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fInsUpd_Grupos");
        p.put("vlst_GrupoEquipo", mapObject(vlst_GrupoEquipo));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fInsUpd_TareaEvento(ArrayList<Object> vlst_TareaEvento) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fInsUpd_TareaEvento_");
        p.put("vlst_TareaEvento", mapObject(vlst_TareaEvento));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fInsUpd_RecursoDidacticoEvento(ArrayList<Object> vlst_RecursoDidacticoEvento) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fInsUpd_RecursoDidacticoEvento");
        p.put("vlst_RecursoDidacticoEvento", mapObject(vlst_RecursoDidacticoEvento));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fInsUpd_TareaEventoRecurso(ArrayList<Object> vlst_TareaEventoRecurso) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "RestAPI");
        o.put("method", "fInsUpd_TareaEventoRecurso");
        p.put("vlst_TareaEventoRecurso", mapObject(vlst_TareaEventoRecurso));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerHorario(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerHorario");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarRubroEvalRNPFormula(ArrayList<Object> vobj_RubroEvalRNPFormula) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarRubroEvalRNPFormula");
        p.put("vobj_RubroEvalRNPFormula",mapObject(vobj_RubroEvalRNPFormula));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarCriterioRubroEvalProceso(ArrayList<Object> vobj_CriterioRubroEvalProceso) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarCriterioRubroEvalProceso");
        p.put("vobj_CriterioRubroEvalProceso",mapObject(vobj_CriterioRubroEvalProceso));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarColorCondicional(ArrayList<Object> vobj_ColoCondicional) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarColorCondicional");
        p.put("vobj_ColoCondicional",mapObject(vobj_ColoCondicional));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarRubroEvalProcesoCampoTematico(ArrayList<Object> vobj_RubroEvalProcesoCampoTematico) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarRubroEvalProcesoCampoTematico");
        p.put("vobj_RubroEvalProcesoCampoTematico",mapObject(vobj_RubroEvalProcesoCampoTematico));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarEvaluacionProceso_Sesion(ArrayList<Object> vobj_EvaluacionProceso) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarEvaluacionProceso_Sesion");
        p.put("vobj_EvaluacionProceso",mapObject(vobj_EvaluacionProceso));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerDatosLogin(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerDatosLogin");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerRubroEvaluacionProceso(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerRubroEvaluacionProceso");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerSilaboEvento(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerSilaboEvento");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerRubroEvaluacionResultado(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerRubroEvaluacionResultado");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerTipoNota(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerTipoNota");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerAsistencia(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerAsistencia");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }
//
//    public JSONObject flst_ObtenerHorario(int vint_UsuarioId) throws Exception {
//        JSONObject result = null;
//        JSONObject o = new JSONObject();
//        JSONObject p = new JSONObject();
//        o.put("interface","RestAPI");
//        o.put("method", "flst_ObtenerHorario");
//        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
//        o.put("parameters", p);
//        String s = o.toString();
//        
//       
//       return load(s);
//    }

    public JSONObject flst_ObtenerGrupo(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerGrupo");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerCargaAcademica(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerCargaAcademica");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject flst_ObtenerMensajeria(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerMensajeria");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarRubroEvaluacionProcesoAndroid(Object vobj_InsertarVariasEntidades) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarRubroEvaluacionProcesoAndroid");
        p.put("vobj_InsertarVariasEntidades",mapObject(vobj_InsertarVariasEntidades));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarTareRecursosAndroid(Object vobj_InsertarVariasEntidades) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarTareRecursosAndroid");
        p.put("vobj_InsertarVariasEntidades",mapObject(vobj_InsertarVariasEntidades));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG, s);
        
       
       return load(s);
    }

    public JSONObject fins_GuardarSesion_Android(Object vobj_InsertarVariasEntidades) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarSesion_Android");
        p.put("vobj_InsertarVariasEntidades",mapObject(vobj_InsertarVariasEntidades));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarAsistencia_Android(Object vobj_InsertarVariasEntidades) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarAsistencia_Android");
        p.put("vobj_InsertarVariasEntidades",mapObject(vobj_InsertarVariasEntidades));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG, s);
        
       
       return load(s);
    }

    //Importacion
    public JSONObject fins_ListarRubroEvaluacionProceso(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarRubroEvaluacionProceso");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG,"parametros: "+s);

       return load(s);
    }

    public JSONObject fins_ListarEnvioAsistencia(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarEnvioAsistencia");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_ListarTareRecursos(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarTareRecursos");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG,s);

       return load(s);
    }

    public JSONObject fins_ListarSesiones(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarSesiones");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();

        Log.d(TAG,"parametros: "+s);
       return load(s);
    }

    public JSONObject fins_ListarRubroEvaluacionResultado(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarRubroEvaluacionResultado");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();
        
       
       return load(s);
    }

    public JSONObject fins_GuardarGrupoEquipo_Android(Object vobj_InsertarVariasEntidades) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarGrupoEquipo_Android");
        p.put("vobj_InsertarVariasEntidades",mapObject(vobj_InsertarVariasEntidades));
        o.put("parameters", p);
        String s = o.toString();
        return load(s);
    }

    public JSONObject fins_ListarGrupoEquipo_Equipo_EquiIntegrante(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarGrupoEquipo_Equipo_EquiIntegrante");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();
        return load(s);
    }

    public JSONObject fins_ListarTipoNotas(Object variablesImport) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_ListarTipoNotas");
        p.put("variablesImport",mapObject(variablesImport));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG,"fins_ListarTipoNotas: " +s);
        return load(s);

    }

    public JSONObject flst_Obtenerlistado_Personas(int vint_UsuarioId) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_Obtenerlistado_Personas");
        p.put("vint_UsuarioId",mapObject(vint_UsuarioId));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG , "flst_Obtenerlistado_Personas: "+ s);
        return load(s);
    }

    public JSONObject fins_GuardarMensajesProcesoAndroid(Object vobj_InsertarVariasEntidades) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "fins_GuardarMensajesProcesoAndroid");
        p.put("vobj_InsertarVariasEntidades",mapObject(vobj_InsertarVariasEntidades));
        o.put("parameters", p);
        String s = o.toString();
        Log.d(TAG , "fins_GuardarMensajesProcesoAndroid: "+ s);
        return load(s);
    }
}


