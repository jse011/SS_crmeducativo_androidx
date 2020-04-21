package com.consultoraestrategia.ss_crmeducativo.login;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetAdminService;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPasswordServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPersonaServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUserSave;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioSimple;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;
import com.consultoraestrategia.ss_crmeducativo.login.entity.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.BorrarUsuarioLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosCargaAcademicaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioAsistenciaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioGrupoLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioHorarioLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioMensajeriaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioTipoNotaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.SaveDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.ArrayList;
import java.util.List;

public class LoginDocentePresenteImpl extends LoginPresenterImpl {
    private int countErrorDatosComLogin  = 0;
    private GetDatosEnvioAsistenciaLogin getDatosEnvioAsistenciaLogin;
    private GetDatosCargaAcademicaLogin getDatosLoginBeDatosCargaAcademica;
    //private GetDatosEnvioAsistenciaLogin getDatosEnvioAsistenciaLogin;
    private GetDatosEnvioGrupoLogin getDatosEnvioGrupoLogin;
    private GetDatosEnvioHorarioLogin getDatosEnvioHorarioLogin;
    private GetDatosEnvioMensajeriaLogin getDatosEnvioMensajeriaLogin;
    private GetDatosEnvioTipoNotaLogin getDatosEnvioTipoNotaLogin;
    //private GetDatosEvaluacionResultadoLogin getDatosEvaluacionResultadoLogin;
    private GetDatosRubroEvaluacionProceso getDatosRubroEvaluacionProceso;
    private GetDatosSilaboEventoEnvio getDatosSilavoEventoEnvio;
    private GetObtenerDatosLogin getObtenerDatosLogin;
    private RetrofitCancel retrofitObtenerDatosLogin;
    private RetrofitCancel retrofitDatosLoginBeDatosCargaAcademica;
    private RetrofitCancel retrofitDatosEnvioGrupoLogin;
    private RetrofitCancel retrofitDatosEnvioHorarioLogin;
    private RetrofitCancel retrofitMensajeriaLogin;
    private RetrofitCancel retrofitDatosEnvioTipoNotaLogin;
    private RetrofitCancel retrofitDatosRubroEvaluacionProceso;
    private RetrofitCancel retrofitDatosSilavoEventoEnvio;
    private RetrofitCancel retrofitDatosEnvioAsistenciaLogin;
    private SaveDatosCompletosLogin.RequestValues requestValues = new SaveDatosCompletosLogin.RequestValues();
    private int posicionPageProgress;

    public LoginDocentePresenteImpl(UseCaseHandler handler, Resources res, GetUsuarioUI getUsuarioUI, Context context, GetUsuarioSimple getUsuarioSimple, SaveDatosCompletosLogin saveDatosCompletosLogin, BorrarUsuarioLogin borrarUsuarioLogin, GetPasswordServidor getPasswordServidor, GetPersonaServidor getPersonaServidor, GetAdminService getAdminService, GetUserSave getUserSave, GetDatosCargaAcademicaLogin getDatosLoginBeDatosCargaAcademica,  GetDatosEnvioAsistenciaLogin getDatosEnvioAsistenciaLogin,GetDatosEnvioGrupoLogin getDatosEnvioGrupoLogin, GetDatosEnvioHorarioLogin getDatosEnvioHorarioLogin, GetDatosEnvioMensajeriaLogin getDatosEnvioMensajeriaLogin, GetDatosEnvioTipoNotaLogin getDatosEnvioTipoNotaLogin, GetDatosRubroEvaluacionProceso getDatosRubroEvaluacionProceso, GetDatosSilaboEventoEnvio getDatosSilavoEventoEnvio, GetObtenerDatosLogin getObtenerDatosLogin) {
        super(handler, res, getUsuarioUI, context, getUsuarioSimple, saveDatosCompletosLogin, borrarUsuarioLogin, getPasswordServidor, getPersonaServidor, getAdminService, getUserSave);
        this.getDatosEnvioAsistenciaLogin = getDatosEnvioAsistenciaLogin;
        this.getDatosLoginBeDatosCargaAcademica = getDatosLoginBeDatosCargaAcademica;
        this.getDatosEnvioGrupoLogin = getDatosEnvioGrupoLogin;
        this.getDatosEnvioHorarioLogin = getDatosEnvioHorarioLogin;
        this.getDatosEnvioMensajeriaLogin = getDatosEnvioMensajeriaLogin;
        this.getDatosEnvioTipoNotaLogin = getDatosEnvioTipoNotaLogin;
        this.getDatosRubroEvaluacionProceso = getDatosRubroEvaluacionProceso;
        this.getDatosSilavoEventoEnvio = getDatosSilavoEventoEnvio;
        this.getObtenerDatosLogin = getObtenerDatosLogin;
    }

    @Override
    protected void cancelCall() {
        if(retrofitObtenerDatosLogin!=null) retrofitObtenerDatosLogin.cancel();
        if(retrofitDatosLoginBeDatosCargaAcademica!=null) retrofitDatosLoginBeDatosCargaAcademica.cancel();
        if(retrofitDatosEnvioGrupoLogin!=null) retrofitDatosEnvioGrupoLogin.cancel();
        if(retrofitDatosEnvioHorarioLogin!=null) retrofitDatosEnvioHorarioLogin.cancel();
        if(retrofitMensajeriaLogin!=null) retrofitMensajeriaLogin.cancel();
        if(retrofitDatosEnvioTipoNotaLogin!=null) retrofitDatosEnvioTipoNotaLogin.cancel();
        if(retrofitDatosRubroEvaluacionProceso!=null) retrofitDatosRubroEvaluacionProceso.cancel();
        if(retrofitDatosSilavoEventoEnvio!=null) retrofitDatosSilavoEventoEnvio.cancel();
        if(retrofitDatosEnvioAsistenciaLogin!=null) retrofitDatosEnvioAsistenciaLogin.cancel();
    }

    @Override
    void onFinishCountDownTimer() {
        if(view!=null)view.showBtnReintentarProgress();
    }

    @Override
    int getRolApp() {
        return 4;
    }


    private List<LoginProgressPagerUi> getLoginProgress(){
        List<LoginProgressPagerUi> loginProgressPagerUiList =new ArrayList<>();
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(0,"Librería General","descripción", R.color.md_blue_500, R.drawable.ic_datos_generales));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(1,"Librería de Carga Academica","descripción", R.color.md_red_500, R.drawable.ic_datos_carga_academica));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(2,"Librería de Grupo Alumnos","descripción", R.color.md_green_500, R.drawable.ic_datos_grupo));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(3,"Librería de Horario","descripción", R.color.md_amber_500, R.drawable.ic_datos_horario));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(4,"Librería de Mensajeria","descripción", R.color.md_yellow_500, R.drawable.ic_datos_mensajeria));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(5,"Librería de Niveles de logro","descripción", R.color.md_brown_500, R.drawable.ic_datos_tipo_nota));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(6,"Librería de Evaluaciones","descripción", R.color.md_orange_500, R.drawable.ic_datos_evaluacion));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(7,"Librería de Sílabo","descripción", R.color.md_pink_500, R.drawable.ic_datos_silabo));
        loginProgressPagerUiList.add(LoginProgressPagerUi.bind(8,"Librería de Asistencia","descripción", R.color.md_amber_500, R.drawable.ic_datos_asistencia));
        return  loginProgressPagerUiList;
    }

    private synchronized void changeDataPager(final int posicion, String usuarioId, final LoginCallBack callBack){
        if(view!=null)view.changePagerProgressPager(posicion);
        switch (posicion){
            case 0:
                retrofitObtenerDatosLogin = getObtenerDatosLogin.execute(new GetObtenerDatosLogin.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetObtenerDatosLogin.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetObtenerDatosLogin.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"setGetObtenerDatosLogin :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 1:
                retrofitDatosLoginBeDatosCargaAcademica = getDatosLoginBeDatosCargaAcademica.execute(new GetDatosCargaAcademicaLogin.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosCargaAcademicaLogin.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosCargaAcademicaLogin.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getDatosCargaAcademicaLogin :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 2:
                retrofitDatosEnvioGrupoLogin = getDatosEnvioGrupoLogin.execute(new GetDatosEnvioGrupoLogin.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosEnvioGrupoLogin.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosEnvioGrupoLogin.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getDatosAsistencia :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 3:
                retrofitDatosEnvioHorarioLogin = getDatosEnvioHorarioLogin.execute(new GetDatosEnvioHorarioLogin.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosEnvioHorarioLogin.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosEnvioHorarioLogin.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getHorarioLogin :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 4:
                retrofitMensajeriaLogin = getDatosEnvioMensajeriaLogin.execute(new GetDatosEnvioMensajeriaLogin.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosEnvioMensajeriaLogin.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosEnvioMensajeriaLogin.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getMensajeriaLogin :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 5:
                retrofitDatosEnvioTipoNotaLogin = getDatosEnvioTipoNotaLogin.execute(new GetDatosEnvioTipoNotaLogin.RequestValues(usuarioId), new UseCaseLoginSincrono.Callback<GetDatosEnvioTipoNotaLogin.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, GetDatosEnvioTipoNotaLogin.ResponseValue value) {
                        if(success){
                            Log.d(TAG,"getTipoNotaLogin :)");
                            tablasList.add(value.getItem());
                            comprobarDatosCompletosLogin(value.getItem());
                        }else {
                            onErrorDatosCompletosLogin();
                            //onPasswordInvalidado();
                        }
                        callBack.onLoad(success, posicion);
                    }
                });
                break;
            case 6:
                retrofitDatosRubroEvaluacionProceso = getDatosRubroEvaluacionProceso.execute(new GetDatosRubroEvaluacionProceso.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosRubroEvaluacionProceso.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosRubroEvaluacionProceso.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getRubroEvaluacionProceso :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 7:
                retrofitDatosSilavoEventoEnvio = getDatosSilavoEventoEnvio.execute(new GetDatosSilaboEventoEnvio.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosSilaboEventoEnvio.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosSilaboEventoEnvio.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getRubroEvaluacionProceso :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;
            case 8:
                retrofitDatosEnvioAsistenciaLogin = getDatosEnvioAsistenciaLogin.execute(new GetDatosEnvioAsistenciaLogin.RequestValues(usuarioId),
                        new UseCaseLoginSincrono.Callback<GetDatosEnvioAsistenciaLogin.ResponseValue>() {
                            @Override
                            public void onResponse(boolean success, GetDatosEnvioAsistenciaLogin.ResponseValue value) {
                                if(success){
                                    Log.d(TAG,"getAsistenciaLogin :)");
                                    tablasList.add(value.getItem());
                                    comprobarDatosCompletosLogin(value.getItem());
                                }else {
                                    onErrorDatosCompletosLogin();
                                    //onPasswordInvalidado();
                                }
                                callBack.onLoad(success, posicion);
                            }
                        });
                break;

        }
    }


    @Override
    protected void initLoginData(UsuarioUi usuarioUi) {
        this.usuarioUi = usuarioUi;
        if (usuarioUi != null) {

            clearFocus();
            countErrorDatosComLogin = 0;
            Log.d(TAG, "GETUSER_IMPORT_SUCCESS");
            Log.d(TAG, "usuarioId"+ usuarioUi.getUsuarioId());
            this.posicionPageProgress = 0;

            showViewPagerProgressPager(getLoginProgress());
            onSiguiendoPageProgress();



            //comprobarDatosCompletosLogin(new BEDatosEnvioGrupo());
            //getRubroEvaluacionResultado(usuarioId);

            //notifyUserSucess(user);
        }

    }


    private void onErrorDatosCompletosLogin() {
        countErrorDatosComLogin++;
        if(countErrorDatosComLogin == 1){
            //hideProgressPage();
            //hideProgress();
            //showLoginPage();
            stopCountDownTimer();
            if(view!=null)view.showBtnReintentarProgress();
            handleImportantError(res.getString(com.consultoraestrategia.ss_crmeducativo.core2.R.string.login_msg_red_error));
            cancelCall();
        }
    }

    /*
    private void getAsistenciaLogin(String usuarioId){

        handler.execute(getDatosEnvioAsistenciaLogin,
                new GetDatosEnvioAsistenciaLogin.RequestValues(usuarioId),
                new UseCase.UseCaseCallback<GetDatosEnvioAsistenciaLogin.ResponseValue>() {
                    @Override
                    public void onSuccess(GetDatosEnvioAsistenciaLogin.ResponseValue response) {
                        Log.d(TAG,"getDatosAsistencia :)");
                        tablasList.add(response.getItem());
                        comprobarDatosCompletosLogin(response.getItem());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
*/


    private void getRubroEvaluacionResultado(String usuarioId){

        /*handler.execute(getDatosEvaluacionResultadoLogin,
                new GetDatosEvaluacionResultadoLogin.RequestValues(usuarioId),
                new UseCase.UseCaseCallback<GetDatosEvaluacionResultadoLogin.ResponseValue>() {
                    @Override
                    public void onSuccess(GetDatosEvaluacionResultadoLogin.ResponseValue response) {
                        Log.d(TAG,"getRubroEvaluacionResultado :)");
                        tablasList.add(response.getItem());
                        comprobarDatosCompletosLogin(response.getItem());
                    }

                    @Override
                    public void onError() {
                        onErrorDatosCompletosLogin();
                    }
                });*/
    }


    public void onSiguiendoPageProgress() {
        if (usuarioUi != null) {
            if(view!=null)view.hideBtnReintentarProgress();
            final String usuarioId = Integer.toString(usuarioUi.getUsuarioId());
            startCountDownTimer();
            changeDataPager(posicionPageProgress, usuarioId, new LoginCallBack() {
                @Override
                public void onLoad(boolean success, int pocision) {
                    stopCountDownTimer();
                    if(success){
                        posicionPageProgress++;
                        if(posicionPageProgress < getLoginProgress().size())onSiguiendoPageProgress();
                        if(view!=null)view.hideBtnReintentarProgress();
                    }else {
                        if(view!=null)view.showBtnReintentarProgress();
                    }
                }
            });
        }

    }

    @Override
    public void onClickTimeOutConnectionSalir() {
        if(view!=null)view.showBtnReintentarProgress();
        stopCountDownTimer();
        cancelCall();
    }

    @Override
    public void onClickBtnReiniciarPageProgress() {
        onSiguiendoPageProgress();
    }
}
