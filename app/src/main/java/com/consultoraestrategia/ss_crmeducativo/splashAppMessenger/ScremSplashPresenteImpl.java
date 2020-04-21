package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger;

import android.content.res.Resources;
import android.os.Handler;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.useCase.GetUrlServidorLocal;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.useCase.UseCaseSincronizar;

public class ScremSplashPresenteImpl extends BasePresenterImpl<ScremSplashView> implements ScremSplashPresenter{
    private UseCaseSincronizar useCaseSincronizar;
    private GetUrlServidorLocal getUrlServidorLocal;
    private int porcentaje = 2;

    public ScremSplashPresenteImpl(UseCaseHandler handler, Resources res, UseCaseSincronizar useCaseSincronizar, GetUrlServidorLocal getUrlServidorLocal) {
        super(handler, res);
        this.useCaseSincronizar = useCaseSincronizar;
        this.getUrlServidorLocal = getUrlServidorLocal;
    }


    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        SessionUser sessionUser = SessionUser.getCurrentUser();
        if(sessionUser==null||!sessionUser.isDataImported()){
            if(view!=null)view.starLoginActivity();
            if(view!=null)view.close();
            return;
        }

        if(view!=null &&view.isInternetAvailable()){
            getUrlServidorLocal.excute(new GetUrlServidorLocal.CallBack() {
                @Override
                public void onLoad(boolean success) {
                    if(success){
                        useCaseSincronizar.execute(new UseCaseSincronizar.Request(), new UseCaseLoginSincrono.Callback<UseCaseSincronizar.Response>() {
                            @Override
                            public void onResponse(boolean success, UseCaseSincronizar.Response value) {
                                if(value instanceof UseCaseSincronizar.ResponseDownloadProgress){
                                    porcentaje  = (int)(((double)((UseCaseSincronizar.ResponseDownloadProgress) value).getProgress()/(double)2))+45;
                                    if(view!=null)view.updateListaEnviar(porcentaje<=0?1:porcentaje);
                                }else if(value instanceof UseCaseSincronizar.ResponseUploadProgress){
                                    porcentaje  = (int)(((double)((UseCaseSincronizar.ResponseUploadProgress) value).getProgress()/(double)2));
                                    if(view!=null)view.updateListaEnviar(porcentaje<=0?1:porcentaje);
                                }else {
                                    if(success){
                                        if(view!=null)view.updateListaEnviar(98);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(view!=null)view.updateListaEnviar(100);
                                            }
                                        },300);
                                        if(view!=null)view.startMainActivity();
                                    }else {
                                        if(view!=null)view.startOfflineMainActivity();
                                    }

                                }
                            }
                        });
                    }else {
                        if(view!=null)view.startOfflineMainActivity();
                    }
                }
            });


        }else {
            if(view!=null)view.startOfflineMainActivity();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
