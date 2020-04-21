package com.consultoraestrategia.ss_crmeducativo.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetAdminService;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPasswordServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPersonaServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUserSave;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioSimple;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login.entity.ServerUi;
import com.consultoraestrategia.ss_crmeducativo.login.entity.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.login.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.BorrarUsuarioLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosCargaAcademicaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioAsistenciaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioGrupoLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioHorarioLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioMensajeriaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioTipoNotaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEvaluacionResultadoLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.SaveDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvi on 20/02/2017.
 */

public abstract class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    public static final String TAG = LoginPresenterImpl.class.getSimpleName();
    private GetUsuarioUI getUsuarioUI;
    private Context context;
    private GetUsuarioSimple getUsuarioSimple;
    private SaveDatosCompletosLogin saveDatosCompletosLogin;
    private SaveDatosCompletosLogin.RequestValues requestValues;
    private BorrarUsuarioLogin borrarUsuarioLogin;
    private GetAdminService getAdminService;
    private GetPersonaServidor getPersonaServidor;
    private GetPasswordServidor   getPasswordServidor;
    private GetUserSave getUserSave;
    public final List<Object> tablasList = new ArrayList<>();
    protected CountDownTimer countTimer;
    private String usuario;
    protected UsuarioUi usuarioUi;
    private LoginPreferentRepository loginPreferentRepository;
    private boolean quitarUsuario;
    private String correo;
    private String DNI;
    private int opcion;
    private String msgError;


    public LoginPresenterImpl(UseCaseHandler handler, Resources res, GetUsuarioUI getUsuarioUI, Context context,GetUsuarioSimple getUsuarioSimple, SaveDatosCompletosLogin saveDatosCompletosLogin, BorrarUsuarioLogin borrarUsuarioLogin,GetPasswordServidor getPasswordServidor
            ,GetPersonaServidor getPersonaServidor, GetAdminService getAdminService, GetUserSave getUserSave) {
        super(handler, res);
        this.getUsuarioUI = getUsuarioUI;
        this.context = context;
        this.getUsuarioSimple = getUsuarioSimple;
        this.saveDatosCompletosLogin = saveDatosCompletosLogin;
        this.requestValues = new SaveDatosCompletosLogin.RequestValues();
        this.borrarUsuarioLogin = borrarUsuarioLogin;
        this.getPasswordServidor= getPasswordServidor;
        this.getPersonaServidor = getPersonaServidor;
        this.getAdminService = getAdminService;
        this.getUserSave = getUserSave;

    }

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
        this.loginPreferentRepository = view;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getListUsuarioPreferents(false);
        boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet)if(view!=null)view.showMessageNotConnection();

    }

    private void getListUsuarioPreferents(final boolean elimiarUsuario) {
        if(loginPreferentRepository !=null)loginPreferentRepository.getTodosUsuarios(new LoginPreferentRepository.Callback<List<PersonaUi>>() {
            @Override
            public void onLoad(boolean success, List<PersonaUi> usuarioUiList) {
                quitarUsuario = elimiarUsuario;
                if(usuarioUiList.size()>0){
                    showLoginPageListUser();
                    hideLoginPageUser();
                    hideLoginPagePassword();
                    PersonaUi personaUi= new PersonaUi();
                    personaUi.setNombres("Usar Otra Cuenta");
                    personaUi.setApellidos("");
                    personaUi.setUsarOtraCuenta(true);
                    usuarioUiList.add(personaUi);
                    if(view!=null)view.showAtnAtrasLstUsu();
                    if(view!=null)view.showListUsuario(usuarioUiList, elimiarUsuario);
                }else {
                    if(view!=null)view.hidebtnAtrasLstUsu();
                    hideLoginPageListUser();
                    showLoginPageUser();
                    if (view!=null)view.hideKeyBoard();
                }
            }
        });
    }

    private void setGetAdminService(final int opcion, final String usuario, final String password, final String correo, final String numeroDocumento, final String urlServidor, final boolean states){
        Log.d(TAG, " " + opcion + " " + usuario + " " + password + " " + correo + " " + numeroDocumento + " " + states);
        boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }

        if(view!=null)view.showProgress();
        if(view!=null)view.disabledOnClick();
        handler.execute(getAdminService,
                new GetAdminService.RequestValues(opcion, usuario, password, correo, numeroDocumento, urlServidor, states),
                new UseCase.UseCaseCallback<GetAdminService.ResponseValue>() {
                    @Override
                    public void onSuccess(GetAdminService.ResponseValue response) {
                        hideProgress();
                        if(view!=null)view.enableOnClick();
                        Log.d("recogiendodato", response.getAdminService().toString());
                        switch (response.getAdminService().getTipo()){
                            case USUARIO:
                                hideLoginPageUser();
                                hideLoginPageListUser();
                                hideLoginPagePassword();
                                // if(view!=null)view.hideLoginPasswordUser()
                                if(view!=null)view.showLoginCorreo();
                                break;
                            case CORREO:
                                if(view!=null)view.showLoginDNI();
                                hideLoginPageListUser();
                                hideLoginPagePassword();
                                hideLoginPageUser();
                                hideLoginCorreo();
                                break;
                            case USUARIO_UNICO:
                                loginNewUser(usuario,response.getAdminService().getUsuarioExternoId(), response.getAdminService().getUrlServiceMovil(), opcion, correo, numeroDocumento, states);
                                break;
                            case CORREO_UNICO:
                                loginNewUser(usuario, response.getAdminService().getUsuarioExternoId(), response.getAdminService().getUrlServiceMovil(), opcion, correo, numeroDocumento, states);
                                break;
                            case CORREO_NO_VALIDO:
                                if(view!=null)view.showLoginCorreo();
                                if(view!=null)view.onInvalitedCorreo(res.getString(R.string.login_correo_invilite));
                                hideLoginPageListUser();
                                hideLoginPagePassword();
                                hideLoginPageUser();
                                break;
                            case DNI:
                                loginNewUser(usuario, response.getAdminService().getUsuarioExternoId(), response.getAdminService().getUrlServiceMovil(), opcion, correo, numeroDocumento, states);
                                break;
                            case DNI_NO_VALIDO:
                                if(view!=null)view.showLoginDNI();
                                if(view!=null)view.onInvalitedDni(res.getString(R.string.login_dni_invilite));
                                hideLoginPageListUser();
                                hideLoginPagePassword();
                                hideLoginPageUser();
                                hideLoginCorreo();
                                break;
                            case USUARIO_NO_VALIDO:
                                if (states){
                                    showLoginPageUser();
                                    if(view!=null)view.onCredencialesIncorrectos(res.getString(R.string.login_credentiales_invilite));
                                    if(view!=null)view.clearFocusPassword();
                                    hideLoginPageListUser();
                                    hideLoginCorreo();
                                    hideLoginDni();
                                    if(view!=null)view.hideLoginPagePassword();
                                    //if(view!=null)view.hideLoginPasswordUser();
                                    hideLoginPagePassword();
                                }else {
                                    showLoginPassword();
                                    onPasswordInvalidado();
                                    hideLoginPageListUser();
                                    hideLoginPageUser();
                                    hideLoginCorreo();
                                    hideLoginDni();
                                }

                                break;
                        }

                    }

                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                        if (view!=null)view.hideProgressBar();
                        if(view!=null)view.enableOnClick();
                    }
                });

    }




    @Override
    protected String getTag() {
        return TAG;
    }


    public void onBtnIngresarClicked(final String userPassword) {


    }

    @Override
    public void onBtnIngresarClickedDni(String userName, String userPassword, String correo, String dni) {
        if (TextUtils.isEmpty(dni)) {
            showMessage(res.getString(R.string.login_dni_empty));
            return;
        }
        setGetAdminService(3, userName, userPassword, correo,dni, res.getString(R.string.admin_service), true);
    }

    /*@Override
    public void onClickedBtnAtrasPassword() {
        showLoginPageUser();
        hideLoginPagePassword();
        hideLoginCorreo();
        hideLoginDni();
        hideLoginPageListUser();
        hideProgressPage();
       // view.hideLoginPasswordUser();
        clearFocusPassword();
    }*/

    @Override
    public void onClickedBtnAtrasCorreo() {
        view.hideLoginPagePassword();
        //view.showLoginPasswordUser();
        hideLoginCorreo();
        hideLoginPageListUser();
        hideLoginDni();
        showLoginPageUser();
        hideProgressPage();
        clearFocusCorreo();
    }



    protected abstract void cancelCall();

    /*
    private void cancelCall() {
       if(retrofitObtenerDatosLogin!=null) retrofitObtenerDatosLogin.cancel();
        if(retrofitDatosLoginBeDatosCargaAcademica!=null) retrofitDatosLoginBeDatosCargaAcademica.cancel();
        if(retrofitDatosEnvioGrupoLogin!=null) retrofitDatosEnvioGrupoLogin.cancel();
        if(retrofitDatosEnvioHorarioLogin!=null) retrofitDatosEnvioHorarioLogin.cancel();
        if(retrofitMensajeriaLogin!=null) retrofitMensajeriaLogin.cancel();
        if(retrofitDatosEnvioTipoNotaLogin!=null) retrofitDatosEnvioTipoNotaLogin.cancel();
        if(retrofitDatosRubroEvaluacionProceso!=null) retrofitDatosRubroEvaluacionProceso.cancel();
        if(retrofitDatosSilavoEventoEnvio!=null) retrofitDatosSilavoEventoEnvio.cancel();
        if(retrofitDatosEnvioAsistenciaLogin!=null) retrofitDatosEnvioAsistenciaLogin.cancel();
    }*/

    @Override
    public void onClickedBtnAtrasDni() {
        view.showLoginCorreo();
        view.hideLoginDNI();
        hideLoginPageUser();
        hideLoginPageUser();
        hideProgressPage();
        clearFocusDni();
    }

    @Override
    public void onBtnIngresarClickedAll(String userName, String userPassword) {
        if (TextUtils.isEmpty(userName)) {
            showMessage(res.getString(R.string.login_username_empty));
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            if(view!=null)view.onErrorPassword(res.getString(R.string.login_password_empty));
            return;
        }
        //loginNewUser(userName,22 ,"http://192.168.1.104:3000/PortalAcadMovil.ashx", 1, "", "", true);
        //loginNewUser(userName,22 ,"http://192.168.1.151/portalmovil/PortalAcadMovil.ashx", 1, "", "", true);
        setGetAdminService(1, userName, userPassword, "","", res.getString(R.string.admin_service), true);

    }

    @Override
    public void saveServerUrl(String serverUrl) {
        GlobalSettings settings = GlobalSettings.getCurrentSettings();
        if (settings == null) {
            settings = new GlobalSettings();
        }
        settings.setUrlServer(serverUrl);
        boolean success = settings.save();
        if (!success) {
            showImportantMessage(res.getString(R.string.login_error_serverurl_saving));
        }
    }

    @Override
    public void onServerMenuClicked() {
        Log.d(TAG, "onServerMenuClicked");
        if (view != null) {
            List<Object> serverList = new ArrayList<>();
            String path = res.getString(R.string.server_path);

            ServerUi produccion = new ServerUi();
            produccion.setNombre(res.getString(R.string.server_etiqueta_produccion));
            produccion.setDireccion(res.getString(R.string.server_produccion));
            produccion.setPath(path);
            serverList.add(produccion);

            ServerUi pruebas = new ServerUi();
            pruebas.setNombre(res.getString(R.string.server_etiqueta_pruebas));
            pruebas.setDireccion(res.getString(R.string.server_pruebas));
            pruebas.setPath(path);
            serverList.add(pruebas);

            ServerUi local = new ServerUi();
            local.setNombre(res.getString(R.string.server_etiqueta_local));
            local.setDireccion(res.getString(R.string.server_local));
            local.setPath(path);
            serverList.add(local);

            serverList.add(res.getString(R.string.global_add_new));
            String serverUrl = GlobalSettings.getServerUrl();
            int positionSelected = -1;
            if (!TextUtils.isEmpty(serverUrl)) {
                for (int i = 0; i < serverList.size(); i++){
                    Object o = serverList.get(i);
                    if(o instanceof String){
                        String url = serverList.get(i).toString();
                        if (serverUrl.equals(url)) {
                            positionSelected = i;
                            break;
                        }
                    }else if(o instanceof ServerUi){
                        ServerUi serverUi = (ServerUi)o;
                        String url = serverUi.getDireccion() + serverUi.getPath();
                        if (serverUrl.equals(url)) {
                            positionSelected = i;
                            break;
                        }
                    }
                }
            }

            view.showListSingleChooser(res.getString(R.string.dialog_title_serverlist), serverList, positionSelected);

            handler.execute(borrarUsuarioLogin,
                    new BorrarUsuarioLogin.RequestValues(),
                    new UseCase.UseCaseCallback<BorrarUsuarioLogin.ResponseValue>() {
                        @Override
                        public void onSuccess(BorrarUsuarioLogin.ResponseValue response) {
                            if(!response.isSuccess())if(view!=null)view.showMessage(res.getString(R.string.login_msg__error_clear_session));
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }
    }

    protected void showLoginPage() {
        if (view != null) view.showLoginPage();
    }

    private void hideLoginPage() {
        if (view != null) view.hideLoginPage();
    }

    protected void showProgressPage() {
        if (view != null) {
            view.showProgressPage();
            view.setupLogoAnim();
        }
    }

    private void hideLoginDni() {
        if (view!=null)view.hideLoginDNI();
    }

    protected void hideProgressPage() {
        if (view != null) {
            view.hideProgressPage();
        }
    }

    private void hideLoginCorreo() {
        if (view!=null)view.hideLoginCorreo();
    }

    protected void showViewPagerProgressPager(List<LoginProgressPagerUi> loginProgressPagerUiList){
        if(view!=null)view.showProgressPage();
        if(view!=null)view.showViewPagerProgressPager(loginProgressPagerUiList);
    }

    protected void hideViewPagerProgressPager(){
        if(view!=null)view.hideProgressPage();
        if(view!=null)view.hideViewPagerProgressPager();
    }


    protected void startCountDownTimer() {
        final int maxSecondsInMillis = 360000;
        final int countInterval = 1000;
        final String[] progressMessages = res.getStringArray(R.array.activity_login_progress_messages_array);
        final String[] progressMessagesSlowConnection = res.getStringArray(R.array.activity_login_progress_slow_connection_messages_array);

        final int prgsMssgsSize = progressMessages.length;
        final int slowConnectionSize = progressMessagesSlowConnection.length;

        if(countTimer!=null)countTimer.cancel();

        countTimer = new CountDownTimer(maxSecondsInMillis, countInterval) {
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: " + millisUntilFinished);
                long millisElapsed = (maxSecondsInMillis - millisUntilFinished);

                int counter = (int) (millisElapsed / countInterval);

                int progress = counter;
                if (counter <= 40) {
                    progress = counter * 2;
                }

                if (counter > 40 && counter <= 55) {
                    progress = (40 * 2) + (counter - 40);
                }

                if (counter > 55) {
                    progress = 95;
                }

                if(counter == 50 || counter == 25) {
                    if(view!=null)view.showTimeOutConnection();
                }

                updateProgress(progress);

                if (counter % 2 == 0) {//CADA 2 SEGUNDOS, ACTUALIZAR LA VISTA.

                    int position = counter / 2;
                    String message = "";
                    if (position < prgsMssgsSize) {
                        message = progressMessages[position];
                    } else {
                        try {
                            //int randomNum = ThreadLocalRandom.current().nextInt(0, slowConnectionSize);
                            //message = progressMessagesSlowConnection[randomNum];
                            message = progressMessagesSlowConnection[0];
                        }catch (Exception e){
                            message = progressMessagesSlowConnection[0];
                            e.printStackTrace();
                        }

                    }

                    updateProgressText(message);
                }
            }
            public void onFinish() {
                onFinishCountDownTimer();
                //showFinalMessage(res.getString(R.string.unknown_error));
            }
        };

        countTimer.start();
    }

    abstract void onFinishCountDownTimer();

    protected void stopCountDownTimer(){
        if(countTimer!=null)countTimer.cancel();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(countTimer!=null)countTimer.cancel();
        countTimer = null;
        loginPreferentRepository = null;
    }

    abstract int getRolApp();

    private void loginNewUser(final String usuario, int userExternoId, final String urlServiceMovil, final int opcion, final String correo, final String dni, final boolean states) {
        Log.d("urlSerices", urlServiceMovil + " " );
        setGlobalSettings(urlServiceMovil);
        onPrePasswordValidado();
        if(view!=null)view.disabledOnClick();
        getUsuarioSimple.execute(new GetUsuarioSimple.RequestValues(userExternoId),
                new UseCaseSincrono.Callback<GetUsuarioSimple.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, final GetUsuarioSimple.ResponseValue value) {
                        if(view!=null)view.enableOnClick();
                        if(success){
                            boolean rolapp = false;
                            for (Integer rolId: value.getUsuarioUi().getRolIdList()){
                                if(rolId==getRolApp())rolapp=true;
                            }

                            if(rolapp){
                                getPersonaServidor(value.getUsuarioUi().getUserName(),
                                        new UseCase.UseCaseCallback<GetPersonaServidor.ResponseValue>() {
                                            @Override
                                            public void onSuccess(GetPersonaServidor.ResponseValue response) {
                                                if(response.getPersonaUi() == null){
                                                    onUsuarioIncorrectoValidado(usuario);
                                                }else {
                                                    response.getPersonaUi().setUsuario(usuario);
                                                    response.getPersonaUi().setUrlServiceMovil(urlServiceMovil);
                                                    response.getPersonaUi().setCorreo(correo);
                                                    response.getPersonaUi().setDni(dni);
                                                    response.getPersonaUi().setOpcion(opcion);
                                                    response.getPersonaUi().setInstitucionUrl(value.getUsuarioUi().getInstitucionUrl());
                                                    if (states)saveUsuarioPreferents(response.getPersonaUi());
                                                }

                                                onSuccessPasswordValidado(value.getUsuarioUi(),response.getPersonaUi());
                                            }

                                            @Override
                                            public void onError() {
                                                onErrorPersonaServidor();
                                            }
                                        });
                            }else {
                                onUsuarioSinAcceso();
                            }

                        }else {
                            onErrorPasswordValidado();
                        }
                    }
                });
    }

    private void setGlobalSettings(String urlServiveMovil){
        GlobalSettings settings = GlobalSettings.getCurrentSettings();
        if (settings == null) {
            settings = new GlobalSettings();
        }
        settings.setUrlServer(urlServiveMovil);
        settings.save();
    }




    private void saveDatosCompletosLogin() {
        saveDatosCompletosLogin.execute(requestValues, new UseCaseSincrono.Callback<SaveDatosCompletosLogin.ResponseValue>() {
            @Override
            public void onResponse(boolean success, SaveDatosCompletosLogin.ResponseValue value) {
                if (success) {
                    notifyUserSucess(usuarioUi);
                } else {
                    handleImportantError(res.getString(R.string.unknown_error));
                }
            }
        });
    }

    private void updateProgressText(String message) {
        if (view != null) view.updateProgressText(message);
    }

    private void updateProgress(int progress) {
        if (view != null) view.updateProgress(progress);
    }

    private void loadingInformation(int errorType) {
        switch (errorType) {
            case GetUsuarioUI.INF_LOADING_CONSUMING_API:
                updateProgressText("Obteniendo tus datos de docente...");
                updateProgress(25);
                Log.d(TAG, "INF_LOADING_CONSUMING_API");
                break;
            case GetUsuarioUI.INF_LOADING_PARSING_RESPONSE:
                updateProgressText("Preparando tu información...");
                updateProgress(60);
                Log.d(TAG, "INF_LOADING_PARSING_RESPONSE");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_CURSOS:
                updateProgress(65);
                updateProgressText("Cargando Cursos..");
                Log.d(TAG, "INFO_LOADING_PARGING_CURSOS");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_PERSONAS:
                updateProgressText("Cargando Personas...");
                updateProgress(70);
                Log.d(TAG, "INFO_LOADING_PARGING_PERSONAS");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_PERIODOS:
                updateProgress(75);
                updateProgressText("Cargando Periodos.");
                Log.d(TAG, "INFO_LOADING_PARGING_PERIODOS");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_USUARIOS:
                updateProgressText("Cargando Usuarios..");
                updateProgress(80);
                Log.d(TAG, "INFO_LOADING_PARGING_USUARIOS");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_RESULTADO:
                updateProgressText("Cargando Resultado...");
                updateProgress(85);
                Log.d(TAG, "INFO_LOADING_PARGING_RESULTADO");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_CAMPO_TEMATICO:
                updateProgressText("Cargando Campo Tematico.");
                updateProgress(90);
                Log.d(TAG, "INFO_LOADING_PARGING_CAMPO_TEMATICO");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_SILABO_COMPETENCIA:
                updateProgress(92);
                updateProgressText("Cargando Silabos Competencia..");
                Log.d(TAG, "INFO_LOADING_PARGING_SILABO_COMPETENCIA");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_SILABO_EVENTO:
                updateProgress(98);
                updateProgressText("Cargando Silabo Evento...");
                Log.d(TAG, "INFO_LOADING_PARGING_SILABO_EVENTO");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_ASISTENCIA_ALUMNOS:
                updateProgress(99);
                updateProgressText("Cargando Asistencias.");
                Log.d(TAG, "INFO_LOADING_PARGING_ASISTENCIA_ALUMNOS");
                break;
            case GetUsuarioUI.INFO_LOADING_PARGING_INDICADORES:
                updateProgress(100);
                updateProgressText("Cargando Indicadores..");
                Log.d(TAG, "INFO_LOADING_PARGING_INDICADORES");
                break;
            default:
                break;
        }
    }

    protected void handleImportantError(CharSequence message) {
        showImportantMessage(message);
    }

    private void notifyUserSucess(UsuarioUi usuarioUi) {
        //boolean success = saveTemporallyUser(usuarioUi);
        savePreferencesUser(context, usuarioUi.getUserName(), usuarioUi.getPasswordEncrypted());
        if (view != null) {
            view.goToActivity(usuarioUi.getUsuarioId());
        } else {
            showImportantMessage(res.getString(R.string.unknown_error));
        }
    }

    private boolean saveTemporallyUser(UsuarioUi usuarioUi) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioUi.getUsuarioId());
        usuario.setPersonaId(usuarioUi.getPersonaId());
        usuario.setUsuario(usuarioUi.getUserName());
        usuario.setPassword(usuarioUi.getPasswordEncrypted());
        return usuario.save();
    }

    private void savePreferencesUser(Context context, String userName, String userPassword) {
        Log.d(TAG, "savePreferenceUser");
        SharedPreferences miPreferencia = context.getSharedPreferences("PreferenciaUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferencia.edit();
        editor.putString("nombre", userName);
        editor.putString("password", userPassword);
        editor.apply();
    }

    private void enableInputs() {
        if (view != null) {
            view.enableInputs();
        }
    }

    private void disableInputs() {
        if (view != null) {
            view.disableInputs();
        }
    }


    @Override
    public void onSingleItemSelected(Object object, int selectedPosition) {
        Log.d(TAG, "onSingleItemSelected");
        if (object instanceof String) {
            String serverUrl = (String) object;
            if (serverUrl.equals(res.getString(R.string.global_add_new))) {
                showEdtDialog();
                return;
            }

            GlobalSettings settings = GlobalSettings.getCurrentSettings();
            if (settings == null) {
                settings = new GlobalSettings();
            }
            settings.setUrlServer(serverUrl);
            boolean success = settings.save();
            if (!success) {
                showImportantMessage(res.getString(R.string.login_error_serverurl_saving));
            }
        }else if(object instanceof  ServerUi){
            ServerUi serverUi = (ServerUi) object;
            String serverUrl = serverUi.getServer();
            GlobalSettings settings = GlobalSettings.getCurrentSettings();
            if (settings == null) {
                settings = new GlobalSettings();
            }
            settings.setUrlServer(serverUrl + res.getString(R.string.server_path));
            boolean success = settings.save();
            if (!success) {
                showImportantMessage(res.getString(R.string.login_error_serverurl_saving));
            }
        }


    }

    @Override
    public void onCLickAcceptButtom() {
        getPassword();
    }

    private void showEdtDialog() {
        if (view != null) {
            view.showEdtDialog();
        }
    }


    @Override
    public void onCustomUrlOk(String customUrl) {
        onSingleItemSelected(customUrl, 0);
    }

    @Override
    public void onBtnSiguienteUser(String usuario,  String password) {
        comprobarUserEmpty(usuario, password);
    }

    private void comprobarUserEmpty(String usuario, String password) {
        if (TextUtils.isEmpty(usuario)) {
            if(view!=null)view.onErroUsuario(res.getString(R.string.login_username_empty));
            return;
        }else if(TextUtils.isEmpty(password)){
            if(view!=null)view.onErrorPasswordUsuario(res.getString(R.string.login_password_empty));
            return;
        } else {
            //if(view!=null)view.hideLoginPageUser();
            //view.showLoginPasswordUser();
            //view.validadoUser(usuario);
            onBtnIngresarClickedAll(usuario, password);
        }
    }


    private void showLoginPassword(){
        if(view!=null)view.ShowLoginPagePassword();
    }

    private void hideLoginPagePassword(){
        if(view!=null)view.hideLoginPagePassword();
    }

    private void showLoginPageUser(){
        if(view!=null)view.showLoginPageUser();
    }

    private void hideLoginPageUser(){
        if(view!=null)view.hideLoginPageUser();
    }

    private void showLoginPageListUser(){
        if(view!=null)view.showLoginPageListUser();
    }


    private void hideLoginPageListUser(){
        if(view!=null)view.hideLoginPageListUser();
    }

    @Override
    public void onBtnSiguientePassword(String password) {


        if (TextUtils.isEmpty(password)) {
            if(view!=null)view.onErrorPassword(res.getString(R.string.login_password_empty));
            return;
        }

        //loginSave(password);
        Log.d("guardandoUsuario", opcion + usuario + correo + DNI);
        setGetAdminService(opcion, usuario, password, correo, DNI, res.getString(R.string.admin_service), false);

        //clearFocus();
    }

    protected void comprobarDatosCompletosLogin(Object o){
        requestValues.setSeDatosCompletosLogin(o);
        Log.d(TAG, requestValues.toString());
        if(requestValues.isfullRequest()){
            saveDatosCompletosLogin();
        }
    }

    @Override
    public void onClickedBtnClosePassword() {
        getListUsuarioPreferents(false);
        hideLoginPagePassword();
        hideLoginPageUser();
        showLoginPageListUser();
        clearImputs();
        if(view!=null)view.setUrlImgenPassword("");
        if(view!=null)view.setNombrePersona("");
        clearFocus();
    }

    protected void clearFocus() {
        if(view!=null)view.clearFocus();
    }

    private void clearFocusPassword() {
        if(view!=null)view.clearFocusPassword();
    }

    private void clearFocusDni() {
        if(view!=null)view.clearFocusDni();
    }

    private void clearFocusCorreo() {
        if(view!=null)view.clearFocusCorreo();
    }

    @Override
    public void onClickUsuario(PersonaUi personaUi) {
        if(personaUi.isUsarOtraCuenta()){
            showLoginPageUser();
            hideLoginPageListUser();
            hideLoginPagePassword();
            clearImputs();
        }else {
            if(quitarUsuario){
                onClickQuitarUsuario(personaUi);
            }else {
                onClickAgregarUsuario(personaUi);
            }
        }
    }

    private void onClickAgregarUsuario(PersonaUi personaUi) {
        this.usuario = personaUi.getUsuario();
        this.correo = personaUi.getCorreo();
        this.DNI = personaUi.getDni();
        this.opcion = personaUi.getOpcion();
        showLoginPage();
        hideLoginPageUser();
        hideLoginPageListUser();
        showLoginPassword();
        if(view!=null)view.setUrlImgenPassword(personaUi.getImagenUrl());
        if(view!=null)view.setUrlImgenInstitucion(personaUi.getInstitucionUrl());
        if(view!=null)view.setNombrePersona(personaUi.getNombres());
        if(view!=null)view.setUserName(personaUi.getUsuario());
    }

    private void onClickQuitarUsuario(PersonaUi personaUi) {
        loginPreferentRepository.eliminarUsuario(personaUi, new LoginPreferentRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                if(success){
                    getListUsuarioPreferents(true);
                }else {
                    Log.d(TAG, "Error quitar usuario");
                }
            }
        });
    }

    @Override
    public void onClickedBtnQuitarUsuario() {
        if(!quitarUsuario){
            getListUsuarioPreferents(true);
            setTextoBtnQuitarUsuario("Finalizar");
        }else {
            getListUsuarioPreferents(false);
            setTextoBtnQuitarUsuario("Quitar una cuenta");
        }
    }

    @Override
    public void onClickedBtnAtrasListUsua() {
        clearFocus();
        clearFocusPassword();
        getListUsuarioPreferents(false);
    }


    @Override
    public void onConfirmacionPassword() {
        if(view!=null)view.showFinalMessageAceptCancel("¿Desea que le enviemos la contraseña a su correo?", res.getString(R.string.msg_confirmacionTitlle));
    }

    /*@Override
    public void onBtnSiguientePasswordUsuario(String usuario, String password) {
        onBtnIngresarClickedAll(usuario, password);
    }*/

    @Override
    public void onBtnSiguienteCorreo(String usuario, String password, String correo) {
        onBtnIngresarClickedCorreo(usuario, password, correo);
    }

    @Override
    public void onBtnSiguienteDni(String usuario, String password, String correo, String dni) {
        onBtnIngresarClickedDni(usuario, password, correo, dni);
    }



    private void onBtnIngresarClickedCorreo(String usuario, String password, String correo) {
        if (TextUtils.isEmpty(correo)) {
            showMessage(res.getString(R.string.login_correo_empty));
            return;
        }
        setGetAdminService(2, usuario, password, correo,"", res.getString(R.string.admin_service), true);

    }

    private void clearImputs() {
        if(view!=null)view.clearImputs();
    }




    private void  setTextoBtnQuitarUsuario(String texto){
        if(view!=null)view.onSetTextoBtnQuitarUsuario(texto);
    }

    private void getPersonaServidor(final String usuario, UseCase.UseCaseCallback<GetPersonaServidor.ResponseValue> callback){
        onPreUsuarioValidado();
        handler.execute(getPersonaServidor,
                new GetPersonaServidor.RequestValues(usuario), callback);
    }

    private void getPassword(){
        loginPreferentRepository.getTodosUsuarios(new LoginPreferentRepository.Callback<List<PersonaUi>>() {
            @Override
            public void onLoad(boolean success, List<PersonaUi> item) {
                for(PersonaUi p: item)
                    getPwdServidor(p.getId());
            }
        });
    }
    private void getPwdServidor(int idpersona){
        handler.execute(getPasswordServidor,
                new GetPasswordServidor.RequestValues(idpersona),
                new UseCase.UseCaseCallback<GetPasswordServidor.ResponseValue>() {
                    @Override
                    public void onSuccess(GetPasswordServidor.ResponseValue response) {
                        if(response.getCorreo()!=null){
                            if(view!=null) view.showFinalMessage( res.getString(R.string.msg_recuperacionPasswd ) +" "+ response.getCorreo());
                        }
                    }

                    @Override
                    public void onError() {
                        onErrorPersonaServidor();
                    }
                });
    }

    private void onErrorPersonaServidor() {
        showImportantMessage(res.getString(R.string.login_msg_red_error));
        hideProgress();
    }

    private void onPreUsuarioValidado() {
        showProgress();
    }

    private void onUsuarioIncorrectoValidado(String usuario) {
        hideProgress();
        if(view!=null)view.onErroUsuario("Usuario incorrecto");
    }

    private void onSuccessUsuarioValidado(PersonaUi personaUi, String usuario) {
        this.usuario = usuario;//saveUsuarioPreferents(personaUi, usuario);
        showLoginPage();
        hideLoginPageUser();
        showLoginPassword();
        view.validadoUser(usuario);
        if(view!=null)view.setUrlImgenPassword(personaUi.getImagenUrl());
        if(view!=null)view.setNombrePersona(personaUi.getNombres());
        if (view!=null)view.setUserName(personaUi.getUsuario());
        hideProgress();

    }

    private void saveUsuarioPreferents(PersonaUi personaUi) {
        loginPreferentRepository.guardarUsuario(personaUi, new LoginPreferentRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                Log.d(TAG, "saveUsuarioPreferents: " + true);
            }
        });
    }

    private void onPrePasswordValidado() {
        showProgress();
    }

    protected void onPasswordInvalidado() {
        hideProgress();
        if(view!=null)view.onErrorPassword("Contraseña incorrecta");
    }

    private void onSuccessPasswordValidado(UsuarioUi usuarioUi, PersonaUi personaUi) {

        hideProgress();
        pausaDramatica(usuarioUi,personaUi);
    }

    private void pausaDramatica(final UsuarioUi usuarioUi, PersonaUi personaUi) {
        hideLoginPage();
        String nombres = personaUi.getNombres() + " " + Utils.getFirstWord(personaUi.getApellidos());
        if(view!=null)view.showLoginBienvenida(nombres ,personaUi.getImagenUrl(), personaUi.getInstitucionUrl());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view!=null)view.hideLoginBienvenida();


                initLoginData(usuarioUi);
            }
        }, 5000);
    }

    protected abstract void initLoginData(UsuarioUi usuarioUi);


    private void onUsuarioSinAcceso() {
        hideProgress();
        if(view!=null)view.hideProgressBar();
        handleImportantError("Usuario sin acceso.");
    }

    private void onErrorPasswordValidado() {
        hideProgress();
        if(view!=null)view.hideProgressBar();
        handleImportantError(res.getString(R.string.unknown_error));
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        msgError = extras.getString("error", null);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(msgError!=null)showImportantMessage(msgError);
    }

    @Override
    public void onClickBtnCancelar() {
        hideProgressPage();
        hideProgress();
        showLoginPage();
        //showLoginPassword();
        //hideLoginPageUser();
        if(countTimer!=null)countTimer.cancel();
        cancelCall();
    }
}
