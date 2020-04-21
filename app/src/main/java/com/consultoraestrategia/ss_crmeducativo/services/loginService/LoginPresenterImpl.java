package com.consultoraestrategia.ss_crmeducativo.services.loginService;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.BEDatosSilaboEventoEnvioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.loginService.ui.LoginEvent;

/**
 * Created by SCIEV on 24/05/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private final static String TAG = LoginPresenterImpl.class.getSimpleName();
    private LoginEvent event;
    private UseCaseHandler handler;
    private BEDatosEnvioMensajeriaRepository beDatosEnvioMensajeriaRepository;
    private BEDatosEnvioTipoNotaRepository beDatosEnvioTipoNotaRepository;
    private BEDatosRubroEvaluacionProcesoRepository rubroEvaluacionProcesoRepository;
    private BEDatosSilaboEventoEnvioRepository beDatosSilaboEventoEnvioRepository;
    private SEDatosCompletosLoginRepository seDatosCompletosLoginRepository;
    private BEDatosEnvioAsistenciaRepository cargaAcademicaLoginRepository;
    private BEDatosEnvioGrupoRepository beDatosEnvioGrupoRepository;
    private SEDatosCompletosLogin seDatosCompletosLogin;

    public LoginPresenterImpl(BEDatosEnvioMensajeriaRepository beDatosEnvioMensajeriaRepository, BEDatosEnvioTipoNotaRepository beDatosEnvioTipoNotaRepository, BEDatosRubroEvaluacionProcesoRepository rubroEvaluacionProcesoRepository, BEDatosSilaboEventoEnvioRepository beDatosSilaboEventoEnvioRepository, SEDatosCompletosLoginRepository seDatosCompletosLoginRepository, BEDatosEnvioAsistenciaRepository cargaAcademicaLoginRepository, BEDatosEnvioGrupoRepository beDatosEnvioGrupoRepository) {
        this.beDatosEnvioMensajeriaRepository = beDatosEnvioMensajeriaRepository;
        this.beDatosEnvioTipoNotaRepository = beDatosEnvioTipoNotaRepository;
        this.rubroEvaluacionProcesoRepository = rubroEvaluacionProcesoRepository;
        this.beDatosSilaboEventoEnvioRepository = beDatosSilaboEventoEnvioRepository;
        this.seDatosCompletosLoginRepository = seDatosCompletosLoginRepository;
        this.cargaAcademicaLoginRepository = cargaAcademicaLoginRepository;
        this.beDatosEnvioGrupoRepository = beDatosEnvioGrupoRepository;
        this.seDatosCompletosLogin = new SEDatosCompletosLogin();
    }

    @Override
    public void attachView(LoginEvent event) {
        this.event = event;
    }

    @Override
    public void onCreate() {

    }


    @Override
    public void onDestroy() {
        event = null;
    }

    @Override
    public void setExtra(Bundle extras) {

    }

    @Override
    public void onHandleIntent() {
        if(event != null)event.showNotificacionProgress(R.drawable.logo_consultoraa, "Sincronizando", "Actulizar la Informacion ", "",19);
        onChangeDataBase(String.valueOf(SessionUser.getCurrentUser().getUserId()));

    }

    private void onChangeDataBase(String usuarioId) {
        //getAsistenciaLogin(usuarioId);
        getGrupoLogin(usuarioId);
        getMensajeriaLogin(usuarioId);
        getTipoNotaLogin(usuarioId);
        getRubroEvaluacionProceso(usuarioId);
        getSilaboEvento(usuarioId);
        saveDatosCompletosLogin(seDatosCompletosLogin);
    }


    private void getGrupoLogin(String usarioId){
        /*
        beDatosEnvioGrupoRepository.getDatosLogin(usarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioGrupo>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioGrupo item) {
                        if(success){
                            Log.d(TAG,"getGrupoLogin :)");
                            seDatosCompletosLogin.setBeDatosEnvioGrupo(item);
                        }

                    }
                });*/

    }


    private void getMensajeriaLogin(String usarioId){
  /*
        beDatosEnvioMensajeriaRepository.getDatosLogin(usarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioMensajeria>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioMensajeria item) {
                        if(success){
                            Log.d(TAG,"getGrupoLogin :)");
                            seDatosCompletosLogin.setBeDatosEnvioMensajeria(item);
                        }

                    }
                });
                */
    }

    private void getTipoNotaLogin(String usuarioId){
  /*
        beDatosEnvioTipoNotaRepository.getDatosLogin(usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioTipoNota>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioTipoNota item) {

                        if(success){
                            Log.d(TAG,"getTipoNotaLogin :)");
                            seDatosCompletosLogin.setBeDatosEnvioTipoNota(item);
                        }
                    }
                });

                */
    }

    private void getRubroEvaluacionProceso(String usuarioId){
          /*
        rubroEvaluacionProcesoRepository.getDatosLogin(usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosRubroEvaluacionProceso>() {
                    @Override
                    public void onResponse(boolean success, BEDatosRubroEvaluacionProceso item) {

                        if(success){
                            Log.d(TAG,"getRubroEvaluacionProceso :)");
                            seDatosCompletosLogin.setBeDatosRubroEvaluacionProceso(item);
                        }
                    }
                });*/
    }

    private void getSilaboEvento(String usuarioId){
          /*
        beDatosSilaboEventoEnvioRepository.getDatosLogin(usuarioId,
                new ServiceDataSource.ObjectCallBack<GEDatosSilaboEventoEnvio>() {
                    @Override
                    public void onResponse(boolean success, GEDatosSilaboEventoEnvio item) {
                        if(success){
                            Log.d(TAG,"getRubroEvaluacionProceso :)");
                            seDatosCompletosLogin.setBeDatosSilaboEventoEnvio(item);
                        }
                    }
                });*/
    }


    private void saveDatosCompletosLogin(SEDatosCompletosLogin saveDatosCompletosLogin) {
        seDatosCompletosLoginRepository.saveImportar(saveDatosCompletosLogin, new SEDatosCompletosLoginDataSource.SuccessCallBack() {
            @Override
            public void onResponse(boolean success) {
                int userId;
                try {
                    userId = SessionUser.getCurrentUser().getUserId();
                }catch (Exception e){
                    userId = -1;
                }
                if (success){
                    showNotificacionProgress(userId);
                }else {
                    showNotificacionProgressError(userId);
                }
            }
        });
    }


    private void showNotificacionProgress(int id){
        if(event == null)return;
        event.showNotificacionProgress(R.drawable.logo_consultoraa, "Finalizo! Sincronizando", "Finalizo la actualizar la información ", "",id);
    }

    private void showNotificacionProgressError(int id){
        if(event == null)return;
        event.showNotificacionProgress(R.drawable.logo_consultoraa, "Finalizo! con error la sincronizanción", "Error al actualizar la información ", "",SessionUser.getCurrentUser().getUserId());
    }

}
