package com.consultoraestrategia.ss_crmeducativo.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.local.LocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.remote.RemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetAdminService;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPasswordServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPersonaServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUserSave;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioSimple;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.login.ui.LoginActivity;
import com.consultoraestrategia.ss_crmeducativo.main.ui.MainActivity;
import com.consultoraestrategia.ss_crmeducativo.services.cache.CacheImageRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.local.SEDatosCompletosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.remote.SEDatosCompletosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.local.BEDatosCargaAcademicaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.remote.BEDatosCargaAcademicaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.local.BEDatosEnvioAsistenciaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.remote.AsistenciaLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.local.BEDatosEnvioGrupoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.remote.BEDatosEnvioGrupoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.BEDatosEnvioHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.local.BEDatosEnvioHorarioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.remote.BEDatosEnvioHorarioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.local.BEDatosEnvioMensajeriaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.remote.BEDatosEnvioMensajeriaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.local.BEDatosEnvioTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.remote.BEDatosEnvioTipoNotaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local.BEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.remote.BEDatosRubroEvaluacionProcesoRemotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.BEDatosSilaboEventoEnvioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.local.BEDatosSilaboEventoEnvioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.remote.BEDatosSilaboEventoEnvioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.BEObtenerDatosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.local.BEObtenerDatosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.remote.BEObtenerDatosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
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
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.IOException;

public class Login extends LoginActivity {
    private String TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //SessionUser.getCurrentUser().delete();
            FlowManager.getDatabase(AppDatabase.class).reset(getApplicationContext());
            FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
            Log.d(TAG, "borrar BD : ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void goToActivity(int idUsuario) {

        Log.d(TAG, "idUsuario : " + idUsuario);
        Intent intent = MainActivity.launchMainActivity(this, idUsuario);
        startActivity(intent);
        finish();
    }


    @Override
    protected LoginPresenter getPresenter() {
        this.apiRetrofit = UtilServidor.getInstance().getApiRetrofit();
        return new LoginDocentePresenteImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetUsuarioUI(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                this,
                new GetUsuarioSimple(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new SaveDatosCompletosLogin(
                        SEDatosCompletosLoginRepository.getInstance(
                                new SEDatosCompletosLoginLocalDataSource(),
                                new SEDatosCompletosLoginRemoteDataSource()),
                        new CacheImageRepositoryImpl(this)
                ),
                new BorrarUsuarioLogin(RepositoryInjector.getSEDatosCompletosLoginRepository()),
                new GetPasswordServidor(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetPersonaServidor(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetAdminService(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetUserSave(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetDatosCargaAcademicaLogin(BEDatosCargaAcademicaRepository.getInstance(
                        new BEDatosCargaAcademicaLocalDataSource(),
                        new BEDatosCargaAcademicaRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance())
                ),
                new GetDatosEnvioAsistenciaLogin(BEDatosEnvioAsistenciaRepository.getInstance(
                        new BEDatosEnvioAsistenciaLocalDataSource(),
                        new AsistenciaLoginRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioGrupoLogin(BEDatosEnvioGrupoRepository.getInstance(
                        new BEDatosEnvioGrupoLocalDataSource(),
                        new BEDatosEnvioGrupoRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioHorarioLogin(BEDatosEnvioHorarioRepository.getInstance(
                        new BEDatosEnvioHorarioLocalDataSource(),
                        new BEDatosEnvioHorarioRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioMensajeriaLogin(BEDatosEnvioMensajeriaRepository.getInstance(
                        new BEDatosEnvioMensajeriaLocalDataSource(),
                        new BEDatosEnvioMensajeriaRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioTipoNotaLogin(
                        BEDatosEnvioTipoNotaRepository.getInstance(
                                new BEDatosEnvioTipoNotaLocalDataSource(),
                                new BEDatosEnvioTipoNotaRemoteDataSource(UtilServidor.getInstance()),
                                UtilServidor.getInstance()
                        )),
                new GetDatosRubroEvaluacionProceso(BEDatosRubroEvaluacionProcesoRepository.getInstance(
                        new BEDatosRubroEvaluacionProcesoLocalDataSource(InjectorUtils.provideEvaluacionProcesoDao(), InjectorUtils.provideRubroProcesoDao()),
                        new BEDatosRubroEvaluacionProcesoRemotaDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosSilaboEventoEnvio(BEDatosSilaboEventoEnvioRepository.getInstance(
                        new BEDatosSilaboEventoEnvioLocalDataSource(),
                        new BEDatosSilaboEventoEnvioRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetObtenerDatosLogin(BEObtenerDatosLoginRepository.getInstance(
                        new BEObtenerDatosLoginLocalDataSource(),
                        new BEObtenerDatosLoginRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                ))
        );
    }


}
