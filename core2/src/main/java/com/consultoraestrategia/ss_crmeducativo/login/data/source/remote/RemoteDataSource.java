package com.consultoraestrategia.ss_crmeducativo.login.data.source.remote;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.asyntasks.GetDatosInterface;
import com.consultoraestrategia.ss_crmeducativo.asyntasks.LoginAsyntask;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.login.GetDatosAsyntask;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.ImportCallback;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.LoginCallback;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.consultoraestrategia.ss_crmeducativo.asyntasks.LoginAsyntask.LOGIN_ERROR_INVALID_USER_PASSWORD;
import static com.consultoraestrategia.ss_crmeducativo.asyntasks.LoginAsyntask.LOGIN_ERROR_RED;
import static com.consultoraestrategia.ss_crmeducativo.asyntasks.LoginAsyntask.LOGIN_SUCESS;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class RemoteDataSource implements LoginDataSource {
    private static final String TAG = "LoginRemoteDataSource";
    private Context context;
    PersonaDao personaDao;

    public RemoteDataSource(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    public RemoteDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void loginUser(final String parameterUserName, final String parameterPassword, final LoginCallback callback) {
        Log.d(TAG, "loginUser");
        Log.d(TAG, "user: " + parameterUserName);
        Log.d(TAG, "password: " + parameterPassword);

        new LoginAsyntask(new LoginAsyntask.Callback<LoginAsyntask.LoginObject>() {
            @Override
            public void onFinish(LoginAsyntask.LoginObject object) {
                int personaId = object.getIdPersona();
                int userId = object.getIdUsuario();
                int state = object.getState();
                Log.d(TAG, "personaId: " + personaId);
                Log.d(TAG, "userId: " + userId);
                Log.d(TAG, "state: " + state);
                if (state == LOGIN_SUCESS) {
                    SessionUser sessionUser = new SessionUser();
                    sessionUser.setUserId(userId);
                    sessionUser.setPersonaId(personaId);
                    callback.onSuccess(sessionUser);
                } else if (state == LOGIN_ERROR_INVALID_USER_PASSWORD) {
                    callback.onInvalidCredentials();
                } else if (state == LOGIN_ERROR_RED) {
                    callback.onRedError();
                }

            }
        }).execute(parameterUserName, parameterPassword);
    }

    @Override
    public void importData(int idUsuario, final ImportCallback callback) {
        Log.d(TAG, "importData");
        new GetDatosAsyntask(new GetDatosInterface() {
            @Override
            public void GetDatosCorrecto(String mensaje) {
                Log.d(TAG, "getDatosCorrectos: " + mensaje);
                callback.onSuccess();
            }

            @Override
            public void GetDatosError(String mensaje) {
                Log.d(TAG, "GetDatosError: " + mensaje);
                callback.onError();
            }

            @Override
            public void GetDatosErrorProcedimiento(String mensaje) {
                Log.d(TAG, "GetDatosErrorProcedimiento: " + mensaje);
                callback.onError();
            }

            @Override
            public void onProgressInformationChanged(int informationType) {
                callback.onProgressInformationChanged(informationType);

            }
        }, context).execute(idUsuario);
    }

    @Override
    public void loginUserSimple(String parameterUserName, String parameterPassword, final LoginCallback callback) {
        Log.d(TAG, "loginUser");
        Log.d(TAG, "user: " + parameterUserName);
        Log.d(TAG, "password: " + parameterPassword);

        new LoginAsyntask(new LoginAsyntask.Callback<LoginAsyntask.LoginObject>() {
            @Override
            public void onFinish(LoginAsyntask.LoginObject object) {
                int personaId = object.getIdPersona();
                int userId = object.getIdUsuario();
                int state = object.getState();
                Log.d(TAG, "personaId: " + personaId);
                Log.d(TAG, "userId: " + userId);
                Log.d(TAG, "state: " + state);
                if (state == LOGIN_SUCESS) {
                    SessionUser sessionUser = new SessionUser();
                    sessionUser.setUserId(userId);
                    sessionUser.setPersonaId(personaId);
                    callback.onSuccess(sessionUser);
                } else if (state == LOGIN_ERROR_INVALID_USER_PASSWORD) {
                    callback.onInvalidCredentials();
                } else if (state == LOGIN_ERROR_RED) {
                    callback.onRedError();
                }

            }
        }).execute(parameterUserName, parameterPassword);
    }

    @Override
    public void obtenerPersonaUsuario(String parameterUserName, final Callback<PersonaUi> callback) {
        try{
            ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
            apiRetrofit.changeSetTime(30,15,15,TimeUnit.SECONDS);
            Call<RestApiResponse<Persona>> responseCall = apiRetrofit.flst_ObtenerPersona(parameterUserName);
            responseCall.enqueue(new retrofit2.Callback<RestApiResponse<Persona>>() {
                @Override
                public void onResponse(@NonNull Call<RestApiResponse<Persona>> call, @NonNull Response<RestApiResponse<Persona>> response) {
                    RestApiResponse<Persona> respuesta = response.body();
                    if(respuesta == null){
                        callback.onLoad(false, null);
                    }else if(respuesta.isSuccessful()){
                        Persona persona = respuesta.getValue();
                        if(persona==null){
                            callback.onLoad(true, null);
                            return;
                        }
                        PersonaUi personaUi = new PersonaUi();
                        personaUi.setId(persona.getPersonaId());
                        personaUi.setNombres(persona.getNombres());
                        personaUi.setApellidos(persona.getApellidos());
                        personaUi.setImagenUrl(persona.getUrlPicture());
                        callback.onLoad(true, personaUi);
                        Log.d(TAG,"isSuccessful true");
                    }else {
                        callback.onLoad(false, null);
                        Log.d(TAG,"isSuccessful false");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RestApiResponse<Persona>> call, @NonNull Throwable t) {
                    callback.onLoad(false,null);
                    Log.d(TAG,"onFailure");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, null);
        }


    }

    @Override
    public void loginUserSimple(int usuarioExternoId, final Callback<Usuario> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(30,15,15,TimeUnit.SECONDS);
        Call<RestApiResponse<Usuario>> responseCall = apiRetrofit.fobj_ObtenerUsuario(usuarioExternoId);
        responseCall.enqueue(new retrofit2.Callback<RestApiResponse<Usuario>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<Usuario>> call, @NonNull Response<RestApiResponse<Usuario>> response) {
                RestApiResponse<Usuario> respuesta = response.body();
                if(respuesta == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response null");
                }else if(respuesta.isSuccessful()){
                    Usuario usuario = respuesta.getValue();
                    if(usuario==null){
                        callback.onLoad(true, null);
                        Log.d(TAG,"isSuccessful true  null");
                        return;
                    }else if(usuario.getUsuarioId() < 1){
                        callback.onLoad(true, null);
                        Log.d(TAG,"isSuccessful true usuarioId < 1");
                        return;
                    }

                    callback.onLoad(true, usuario);
                    Log.d(TAG,"isSuccessful true");
                }else {
                    callback.onLoad(false, null);
                    Log.d(TAG,"isSuccessful false");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestApiResponse<Usuario>> call, @NonNull Throwable t) {
                callback.onLoad(false,null);
                Log.d(TAG,"onFailure");
            }
        });
    }

    @Override
    public void loginUserSimple(String usuario, String password,final Callback<Usuario> callback) {
        Log.d(TAG,"isSuccessful true  null" + usuario + " " + password);
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(30,15,15,TimeUnit.SECONDS);
        Call<RestApiResponse<Usuario>> responseCall = apiRetrofit.fobj_ObtenerUsuario(usuario,password);
        responseCall.enqueue(new retrofit2.Callback<RestApiResponse<Usuario>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<Usuario>> call, @NonNull Response<RestApiResponse<Usuario>> response) {
                RestApiResponse<Usuario> respuesta = response.body();
                if(respuesta == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response null");
                }else if(respuesta.isSuccessful()){
                    Usuario usuario = respuesta.getValue();
                    if(usuario==null){
                        callback.onLoad(true, null);
                        Log.d(TAG,"isSuccessful true  null");
                        return;
                    }else if(usuario.getUsuarioId() < 1){
                        callback.onLoad(true, null);
                        Log.d(TAG,"isSuccessful true usuarioId < 1*");
                        return;
                    }

                    callback.onLoad(true, usuario);
                    Log.d(TAG,"isSuccessful true");
                }else {
                    callback.onLoad(false, null);
                    Log.d(TAG,"isSuccessful false");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestApiResponse<Usuario>> call, @NonNull Throwable t) {
                callback.onLoad(false,null);
                Log.d(TAG,"onFailure");
            }
        });

    }

    @Override
    public void guardarUsuario(Usuario usuario, Callback<Usuario> callback) {

    }

    @Override
    public void recuperarPassword(int usuario, final Callback<String> callback) {

       Log.d(TAG,"getpersonaid"+ usuario);

        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        Call<RestApiResponse<List<Persona>>> responseCall = apiRetrofit.flst_ObtenerPersonaByCorreoGmail(usuario);
        responseCall.enqueue(new retrofit2.Callback<RestApiResponse<List<Persona>>>() {
            @Override
            public void onResponse(Call<RestApiResponse<List<Persona>>> call, Response<RestApiResponse<List<Persona>>> response) {
                RestApiResponse<List<Persona>> respuesta = response.body();

                if(respuesta.isSuccessful()){
                   List<Persona> p = respuesta.getValue();
                    if(p!=null){
                        for(Persona per:p){
                            callback.onLoad(true, per.getCorreo());
                        }
                    }else{
                        callback.onLoad(true, null);
                        return;
                    }

                }

            }

            @Override
            public void onFailure(Call<RestApiResponse<List<Persona>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void obtenerAdminService(final int opcion, final String usuario, final String passwordd, final String correo, final String numeroDocumento, final String urlServidor, final boolean states, final Callback<AdminService> callback) {
        Log.d(TAG,"obtenerAdminService" + " " + opcion + " " + usuario + " " + passwordd + " " + correo + " " + numeroDocumento);
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15,TimeUnit.SECONDS);
        Call<RestApiResponse<AdminService>> responseCall = apiRetrofit.f_BuscarUsuarioCent(opcion, usuario, passwordd, correo, numeroDocumento, urlServidor);
        responseCall.enqueue(new retrofit2.Callback<RestApiResponse<AdminService>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<AdminService>> call, @NonNull Response<RestApiResponse<AdminService>> response) {
                RestApiResponse<AdminService> respuesta = response.body();
                if(respuesta == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response null");
                }else if(respuesta.isSuccessful()){
                    AdminService adminService= respuesta.getValue();
                    if(adminService==null){
                        callback.onLoad(true, null);
                        Log.d(TAG,"isSuccessful true  null");
                        return;
                    }

                    if (states){
                        if (opcion==1){
                            if(adminService.getUsuarioId()==-1)
                                adminService.setTipo(AdminService.Tipo.USUARIO_NO_VALIDO);
                            else {
                                if (adminService.getUsuarioExternoId()>0)
                                    adminService.setTipo(AdminService.Tipo.USUARIO_UNICO);
                                else adminService.setTipo(AdminService.Tipo.USUARIO);
                            }
                        }
                        else if (opcion==2){
                            if (adminService.getUsuarioId()==-1){
                                adminService.setTipo(AdminService.Tipo.CORREO_NO_VALIDO);
                            }else {
                                if (adminService.getUsuarioExternoId()>0)
                                    adminService.setTipo(AdminService.Tipo.CORREO_UNICO);
                                else adminService.setTipo(AdminService.Tipo.CORREO);
                            }

                        }
                        else if (opcion==3){
                            if (adminService.getUsuarioId()==-1){
                                adminService.setTipo(AdminService.Tipo.DNI_NO_VALIDO);
                            }else adminService.setTipo(AdminService.Tipo.DNI);
                        }

                    }else {
                        if(adminService.getUsuarioId()==-1)
                            adminService.setTipo(AdminService.Tipo.USUARIO_NO_VALIDO);
                        else adminService.setTipo(AdminService.Tipo.USUARIO_UNICO);

                    }


                    callback.onLoad(true, adminService);
                    Log.d(TAG,"obtenerAdminService" + " " + adminService.toString());
                    Log.d(TAG,"obtenerAdminService" + " " + adminService.getTipo());
                    Log.d(TAG,"isSuccessful true");
                }else {
                    callback.onLoad(false, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestApiResponse<AdminService>> call, @NonNull Throwable t) {
                callback.onLoad(false,null);
                t.printStackTrace();
                Log.d(TAG,"onFailure");
            }
        });

    }

}
