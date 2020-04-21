package com.consultoraestrategia.ss_crmeducativo.login.data.source;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.ImportCallback;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.LoginCallback;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface LoginDataSource {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    void loginUser(String parameterUserName, String parameterPassword, LoginCallback callback);
    void importData(int idUsuario, ImportCallback callback);
    void loginUserSimple(String parameterUserName, String parameterPassword, LoginCallback callback);
    void obtenerPersonaUsuario(String parameterUserName, Callback<PersonaUi> callback);
    void loginUserSimple(int usuarioExternoId, Callback<Usuario> callback);
    void loginUserSimple(String usuario, String password, Callback<Usuario> callback);
    void guardarUsuario(Usuario usuario, Callback<Usuario> callback);
    void recuperarPassword(int usuario, Callback<String> callback);
    void obtenerAdminService(int opcion, String usuario, String passwordd, String correo, String numeroDocumento, String urlServidor, boolean states,Callback<AdminService> callback);
}
