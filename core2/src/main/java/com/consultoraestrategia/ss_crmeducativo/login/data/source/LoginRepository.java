package com.consultoraestrategia.ss_crmeducativo.login.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.ImportCallback;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.LoginCallback;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.local.LocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.remote.RemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;

import java.net.URI;
import java.util.Date;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class LoginRepository implements LoginDataSource {
    private static final String TAG = LoginRepository.class.getSimpleName();
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    private LoginRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    private static LoginRepository INSTANCE = null;

    public static LoginRepository getInstace(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }


    @Override
    public void loginUser(final String parameterUserName, final String parameterPassword, final LoginCallback callback) {
        Log.d(TAG, "loginUser");
        localDataSource.loginUser(parameterUserName, parameterPassword, new LoginCallback() {
            @Override
            public void onSuccess(SessionUser user) {
                Log.d(TAG, "usuario: " + user);
                if (user != null) {
                    user.setTimestampLogin(new Date().getTime());
                    user.setTimestampLastSeen(new Date().getTime());
                    user.setState(SessionUser.STATE_ACTIVE);
                    boolean success = localDataSource.saveSessionUser(user);
                    Log.d(TAG, "saveSessionUser: " + success);
                    if (!success) {
                        callback.onSuccess(null);
                    } else {
                        callback.onSuccess(user);
                    }
                } else {
                    remoteDataSource.loginUser(parameterUserName, parameterPassword, new LoginCallback() {
                        @Override
                        public void onSuccess(SessionUser user) {
                            if(user!=null){
                                Log.d(TAG, "remote loginUser onSucess: " + user.toString());
                                user.setUsername(parameterUserName);
                                user.setPasswordEncrypted(parameterPassword);//FIX ME!!!
                                user.setName("");//FIX ME!!!
                                user.setUrlPicture("");//FIX ME!!!
                                user.setTimestampLogin(new Date().getTime());
                                user.setTimestampLastSeen(new Date().getTime());
                                user.setState(SessionUser.STATE_ACTIVE);
                                boolean success = localDataSource.saveSessionUser(user);
                                Log.d(TAG, "saveSessionUser: " + success);
                                if (!success) {
                                    callback.onSuccess(null);
                                } else {
                                    callback.onSuccess(user);
                                }
                            }else callback.onSuccess(null);

                        }

                        @Override
                        public void onInvalidCredentials() {
                            Log.d(TAG, "onInvalidCredentials");
                            callback.onInvalidCredentials();
                        }

                        @Override
                        public void onRedError() {
                            Log.d(TAG, "onRedError");
                            callback.onRedError();
                        }
                    });
                }
            }

            @Override
            public void onInvalidCredentials() {
                Log.d(TAG, "onInvalidCredentials");
                callback.onInvalidCredentials();
            }

            @Override
            public void onRedError() {
                Log.d(TAG, "onRedError");
                callback.onRedError();
            }
        });
    }

    @Override
    public void importData(final int userId, final ImportCallback callback) {
        Log.d(TAG, "importData");
        SessionUser currentUser = SessionUser.getCurrentUser();
        /*if (currentUser != null && userId == currentUser.getUserId() && currentUser.isDataImported()) {
            callback.onSuccess();
            return;
        }*/

        remoteDataSource.importData(userId, new ImportCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "importData");
                SessionUser user = SessionUser.getCurrentUser();
                if (user != null) {
                    user.setDataImported(true);
                    user.save();
                }
                callback.onSuccess();
            }

            @Override
            public void onError() {
                callback.onError();
            }

            @Override
            public void onProgressInformationChanged(int informationType) {
                callback.onProgressInformationChanged(informationType);
            }
        });
    }

    @Override
    public void loginUserSimple(final String parameterUserName, final String parameterPassword, final LoginCallback callback) {
        localDataSource.loginUserSimple(parameterUserName, parameterPassword, new LoginCallback() {
            @Override
            public void onSuccess(SessionUser user) {

                if (user != null) {
                    callback.onSuccess(user);
                }else {
                    remoteDataSource.loginUserSimple(parameterUserName, parameterPassword, new LoginCallback() {
                        @Override
                        public void onSuccess(SessionUser user) {
                            if(user!=null){
                                Log.d(TAG, "remote loginUser onSucess: " + user.toString());
                                user.setUsername(parameterUserName);
                                user.setPasswordEncrypted(parameterPassword);//FIX ME!!!
                                user.setName("");//FIX ME!!!
                                user.setUrlPicture("");//FIX ME!!!
                                user.setTimestampLogin(new Date().getTime());
                                user.setTimestampLastSeen(new Date().getTime());
                                user.setState(SessionUser.STATE_ACTIVE);
                                boolean success = localDataSource.saveSessionUser(user);
                                Log.d(TAG, "saveSessionUser: " + success);
                                if (!success) {
                                    callback.onSuccess(null);
                                } else {
                                    callback.onSuccess(user);
                                }
                            }else callback.onSuccess(null);

                        }

                        @Override
                        public void onInvalidCredentials() {
                            Log.d(TAG, "onInvalidCredentials");
                            callback.onInvalidCredentials();
                        }

                        @Override
                        public void onRedError() {
                            Log.d(TAG, "onRedError");
                            callback.onRedError();
                        }
                    });
                }
            }

            @Override
            public void onRedError() {

            }

            @Override
            public void onInvalidCredentials() {

            }
        });
    }

    @Override
    public void obtenerPersonaUsuario(String parameterUserName, Callback<PersonaUi> callback) {
        remoteDataSource.obtenerPersonaUsuario(parameterUserName,callback);
    }

    @Override
    public void loginUserSimple(int usuarioExternoId, final Callback<Usuario> callback) {
        remoteDataSource.loginUserSimple(usuarioExternoId, new Callback<Usuario>() {
            @Override
            public void onLoad(boolean success, Usuario item) {
                if(item != null){
                   guardarUsuario(item, new Callback<Usuario>() {
                       @Override
                       public void onLoad(boolean success, Usuario item) {
                           if(success){
                               callback.onLoad(success, item);
                           }else {
                               callback.onLoad(false, null);
                           }
                       }
                   });
                }else {
                    callback.onLoad(success, null);
                }
            }
        });
    }

    @Override
    public void loginUserSimple(String usuario, String password, final Callback<Usuario> callback) {
        remoteDataSource.loginUserSimple(usuario, password, new Callback<Usuario>() {
            @Override
            public void onLoad(boolean success, Usuario item) {
                if(item != null){
                    guardarUsuario(item, new Callback<Usuario>() {
                        @Override
                        public void onLoad(boolean success, Usuario item) {
                            if(success){
                                callback.onLoad(success, item);
                            }else {
                                callback.onLoad(false, null);
                            }
                        }
                    });
                }else {
                    callback.onLoad(success, null);
                }
            }
        });
    }

    @Override
    public void guardarUsuario(Usuario usuario, Callback<Usuario> callback) {
        localDataSource.guardarUsuario(usuario, callback);
    }

    @Override
    public void recuperarPassword(int usuario,Callback<String> callback) {

        remoteDataSource.recuperarPassword(usuario, callback);
    }

    @Override
    public void obtenerAdminService(int opcion, String usuario, String passwordd, String correo, String numeroDocumento, String urlServidor, boolean states, Callback<AdminService> callback) {
        //if(correo)
        remoteDataSource.obtenerAdminService(opcion, usuario, passwordd, correo, numeroDocumento, urlServidor, states,callback);
    }

}
