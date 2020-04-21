package com.consultoraestrategia.ss_crmeducativo.asyntasks;

import android.os.AsyncTask;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioAcceso;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Georeferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.PersonaGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Rol;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import org.json.JSONObject;

import java.util.List;


/**
 * Created by irvinmarin on 28/04/2017.
 */

public class LoginAsyntask extends AsyncTask<String, String, LoginAsyntask.LoginObject> {

    public static final int LOGIN_SUCESS = 1;
    public static final int LOGIN_ERROR_INVALID_USER_PASSWORD = -1;
    public static final int LOGIN_ERROR_RED = -2;
    private static final String TAG = "LoginAsyntask";

    /*private JSONObject jsonObject = null;
    private LoginInterface loginInterface;
    private Context context;
    private Usuario usuario;
    private ObjectMapper mapper;
    int estado = 0;*/
    private RestAPI restAPI;
    private DatabaseDefinition database;
    private Callback<LoginObject> callback;




    public LoginAsyntask(Callback<LoginObject> callback) {
        Log.d(TAG, "LoginAsyntask");
        database = FlowManager.getDatabase(AppDatabase.class);
        this.callback = callback;
    }

    @Override
    protected LoginObject doInBackground(String... params) {
        Log.d(TAG, "doInBackground");
        int estado;
        Usuario usuario;
        int usuarioId = 0;
        int personaId = 0;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String username = params[0];
            String password = params[1];
            RestAPI restAPI = new RestAPI();
            JSONObject jsonObject = restAPI.fobj_ObtenerUsuario(username, password);
            Log.d(TAG, jsonObject.toString());
            if (Utils.isSuccess(jsonObject)) {
                usuario = mapper.readValue(jsonObject.optString("Value"), Usuario.class);
                usuarioId = usuario.getUsuarioId();
                personaId = usuario.getPersonaId();
                if (usuarioId >= 1) {
                    manipulateInTransaction(usuario);
                    estado = LOGIN_SUCESS;
                } else {
                    estado = LOGIN_ERROR_INVALID_USER_PASSWORD;
                }
            } else {
                estado = LOGIN_ERROR_INVALID_USER_PASSWORD;
            }
        } catch (Exception e) {
            e.printStackTrace();
            estado = LOGIN_ERROR_RED;
        }
        return new LoginObject(usuarioId, personaId, estado);
    }
    private <T extends BaseModel> void fastStoreList(Class<T> clazz, List<T> list) {
        if (list != null && !list.isEmpty()) {
            FastStoreModelTransaction fastStoreModelTransaction = FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(clazz))
                    .addAll(list)
                    .build();
            database.beginTransactionAsync(fastStoreModelTransaction).build().execute();
        }
    }


    private void manipulateInTransaction(Usuario usuario) {
        fastStoreList(Entidad.class, usuario.getEntidades());
        fastStoreList(Georeferencia.class, usuario.getGeoreferencias());
        fastStoreList(Rol.class, usuario.getRoles());
        fastStoreList(UsuarioRolGeoreferencia.class, usuario.getUsuarioRolGeoreferencias());
        fastStoreList(PersonaGeoreferencia.class, usuario.getPersonaGeoreferencias());
        fastStoreList(UsuarioAcceso.class, usuario.getAccesos());
    }

    @Override
    protected void onPostExecute(LoginObject loginObject) {
        //super.onPostExecute(loginObject);
        if (callback != null) {
            callback.onFinish(loginObject);
        }
    }

    public interface Callback<T> {
        void onFinish(T object);
    }

    public class LoginObject {
        private int idUsuario;
        private int idPersona;
        private int state;

        public LoginObject(int idUsuario, int idPersona, int state) {
            this.idUsuario = idUsuario;
            this.idPersona = idPersona;
            this.state = state;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public int getIdPersona() {
            return idPersona;
        }

        public void setIdPersona(int idPersona) {
            this.idPersona = idPersona;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

}
