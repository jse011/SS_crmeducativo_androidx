package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.ImportCallback;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.LoginCallback;

import java.util.Date;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetUsuarioUI extends UseCase<GetUsuarioUI.RequestValues, GetUsuarioUI.ResponseValue> {

    private static final String TAG = GetUsuarioUI.class.getSimpleName();
    private LoginRepository repository;

    public GetUsuarioUI(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase time: " + new Date().getTime());
        Log.d(TAG, "getUserName :" + requestValues.getUserName());
        Log.d(TAG, "getUserPassword :" + requestValues.getUserPassword());

        final String username = requestValues.getUserName();
        final String password = requestValues.getUserPassword();

        repository.loginUser(username, password, new LoginCallback() {
            @Override
            public void onSuccess(final SessionUser user) {
                Log.d(TAG, "loginUser onSucess, user: " + user.toString());
                if (user != null) {
                    repository.importData(user.getUserId(), new ImportCallback() {
                        @Override
                        public void onSuccess() {
                            if (user != null) {
                                getUseCaseCallback().onSuccess(new ResponseValue(user, GETUSER_IMPORT_SUCCESS));
                            } else {
                                getUseCaseCallback().onSuccess(new ResponseValue(user, GETUSER_ERROR_SAVING_USERSESSION));
                            }
                        }

                        @Override
                        public void onError() {
                            getUseCaseCallback().onSuccess(new ResponseValue(user, GETUSER_IMPORT_FAILED));
                        }

                        @Override
                        public void onProgressInformationChanged(int informationTye) {
                            if (user !=null){
                                getUseCaseCallback().onSuccess(new ResponseValue(user, informationTye));
                            }

                        }
                    });
                } else {
                    getUseCaseCallback().onSuccess(new ResponseValue(user, GETUSER_ERROR_SAVING_USERSESSION));
                }
            }

            @Override
            public void onInvalidCredentials() {
                getUseCaseCallback().onSuccess(new ResponseValue(null, GETUSER_INVALID_CREDENTIALS));
            }

            @Override
            public void onRedError() {
                getUseCaseCallback().onSuccess(new ResponseValue(null, GETUSER_RED_ERROR));
            }
        });


    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final String userName;
        private final String userPassword;

        public RequestValues(String userName, String userPassword) {
            this.userName = userName;
            this.userPassword = userPassword;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserPassword() {
            return userPassword;
        }
    }

    public static final int GETUSER_IMPORT_SUCCESS = 1;
    public static final int GETUSER_IMPORT_FAILED = -4;
    public static final int GETUSER_ERROR_SAVING_USERSESSION = -1;
    public static final int GETUSER_INVALID_CREDENTIALS = -2;
    public static final int GETUSER_RED_ERROR = -3;

    public static final int INF_LOADING_CONSUMING_API = 100;
    public static final int INF_LOADING_PARSING_RESPONSE = 101;
    public static final int INFO_LOADING_PARGING_CURSOS = 102;
    public static final int INFO_LOADING_PARGING_PERSONAS = 103;
    public static final int INFO_LOADING_PARGING_PERIODOS = 104;
    public static final int INFO_LOADING_PARGING_USUARIOS = 105;
    public static final int INFO_LOADING_PARGING_RESULTADO = 106;
    public static final int INFO_LOADING_PARGING_CAMPO_TEMATICO = 107;
    public static final int INFO_LOADING_PARGING_SILABO_COMPETENCIA = 108;
    public static final int INFO_LOADING_PARGING_SILABO_EVENTO = 109;
    public static final int INFO_LOADING_PARGING_ASISTENCIA_ALUMNOS = 110;
    public static final int INFO_LOADING_PARGING_INDICADORES = 111;
    public static final int INFO_LOADING_LOADING_FILES = 112;


    public static final class ResponseValue implements UseCase.ResponseValue {
        private final SessionUser user;
        private final int informationType;

        public ResponseValue(SessionUser user, int informationType) {
            this.user = user;
            this.informationType = informationType;
        }

        public SessionUser getUser() {
            return user;
        }

        public int getInformationType() {
            return informationType;
        }
    }
}
