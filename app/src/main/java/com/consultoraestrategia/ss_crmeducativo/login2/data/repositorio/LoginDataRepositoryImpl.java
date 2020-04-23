package com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao.AlumnoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.*;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CalendarioPeriodoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.UnidadAprendizajeCargaCursoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CasosEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CerrarCursoEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.GrupoEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ResultadoEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.RubroEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.SesionesEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.TareaEnviarUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.DatosProgressUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioExternoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosCasos;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosEstudiante;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosGrupo;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosInicioSesion;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosResultado;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosRubro;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosTarea;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosTipoNota;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosUnidades;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.OkHttpClient;
import retrofit2.Call;

public class LoginDataRepositoryImpl implements LoginDataRepository {
    private static LoginDataRepositoryImpl mInstance;
    private String TAG = LoginDataRepositoryImpl.class.getSimpleName();
    private SessionUserDao sessionUserDao;
    private CursoDao cursoDao;
    private AlumnoDao alumnoDao;
    private ParametrosDisenioDao parametrosDisenioDao;
    private String urlServidor;
    private ApiRetrofit  apiRetrofit;
    private ProgressListener progressDownloadingListener;
    private ProgressListener progressUploadingListener;

    public LoginDataRepositoryImpl(ApiRetrofit apiRetrofit,
                                   SessionUserDao sessionUserDao,
                                   ParametrosDisenioDao parametrosDisenioDao,
                                   CursoDao cursoDao,
                                   AlumnoDao alumnoDao) {
        this.apiRetrofit = apiRetrofit;
        this.sessionUserDao = sessionUserDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
        this.cursoDao = cursoDao;
        this.alumnoDao = alumnoDao;
        urlServidor = TextUtils.isEmpty(apiRetrofit.getUrl())?"":apiRetrofit.getUrl().trim() + "/";
        Log.d(TAG,"urlServidor: " + urlServidor);
        ProgressManager.getInstance().addResponseListener(urlServidor, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                Log.d(TAG,"onProgress");
                if(progressDownloadingListener!=null)progressDownloadingListener.onProgress(progressInfo);
            }

            @Override
            public void onError(long id, Exception e) {
                if(progressDownloadingListener!=null)progressDownloadingListener.onError(id, e);
            }
        });
        ProgressManager.getInstance().addRequestListener(urlServidor, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                Log.d(TAG,"onProgress");
                if(progressUploadingListener!=null)progressUploadingListener.onProgress(progressInfo);
            }

            @Override
            public void onError(long id, Exception e) {
                if(progressUploadingListener!=null)progressUploadingListener.onError(id, e);
            }
        });
    }

    @Override
    public RetrofitCancel getUsuarioExterno(String urlAdminServicio, String usuario, String password, final Callback<UsuarioExternoUi> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15, TimeUnit.SECONDS);
        RetrofitCancel<AdminService> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.f_BuscarUsuarioCent(1, usuario, password, "", "", urlAdminServicio));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<AdminService>() {
            @Override
            public void onResponse(AdminService response) {
                if(response == null){
                    callback.onResponse(false, null);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");
                    UsuarioExternoUi usuarioLocalUi = new UsuarioExternoUi();
                    usuarioLocalUi.setEntidadIdLocal(response.getEntidadId());
                    usuarioLocalUi.setUrlServiceLocal(response.getUrlServiceMovil());
                    //usuarioLocalUi.setUrlServiceLocal("http://demo.consultoraestrategia.com/CRMMovil/PortalAcadMovil.ashx");
                    usuarioLocalUi.setUsuarioExternoId(response.getUsuarioId());
                    usuarioLocalUi.setUsuarioIdLocal(response.getUsuarioExternoId());
                    callback.onResponse(true, usuarioLocalUi);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onResponse(false,null);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });
        return retrofitCancel;
    }

    @Override
    public void saveUrlServidorLocal(String url) {
        GlobalSettings settings = GlobalSettings.getCurrentSettings();
        if (settings == null) {
            settings = new GlobalSettings();
        }
        settings.setUrlServer(url);
        settings.save();


        apiRetrofit.updateServerUrl();
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(15, TimeUnit.SECONDS) // write timeout
                .readTimeout(15, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        urlServidor = TextUtils.isEmpty(apiRetrofit.getUrl())?"":apiRetrofit.getUrl().trim() + "/";

        ProgressManager.getInstance().addResponseListener(urlServidor, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                Log.d(TAG,"onProgress");
                if(progressDownloadingListener!=null)progressDownloadingListener.onProgress(progressInfo);
            }

            @Override
            public void onError(long id, Exception e) {
                if(progressDownloadingListener!=null)progressDownloadingListener.onError(id, e);
            }
        });
        ProgressManager.getInstance().addRequestListener(urlServidor, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                Log.d(TAG,"onProgress");
                if(progressUploadingListener!=null)progressUploadingListener.onProgress(progressInfo);
            }

            @Override
            public void onError(long id, Exception e) {
                if(progressUploadingListener!=null)progressUploadingListener.onError(id, e);
            }
        });


    }

    @Override
    public RetrofitCancel getUsuarioLocalPorCorreo(String urlAdminServicio, String usuario, String password, String correo, final Callback<UsuarioExternoUi> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15, TimeUnit.SECONDS);
        RetrofitCancel<AdminService> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.f_BuscarUsuarioCent(2, usuario, password, correo, "", urlAdminServicio));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<AdminService>() {
            @Override
            public void onResponse(AdminService response) {
                if(response == null){
                    callback.onResponse(false, null);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");
                    UsuarioExternoUi usuarioLocalUi = new UsuarioExternoUi();
                    usuarioLocalUi.setEntidadIdLocal(response.getEntidadId());
                    usuarioLocalUi.setUrlServiceLocal(response.getUrlServiceMovil());
                    usuarioLocalUi.setUsuarioExternoId(response.getUsuarioId());
                    usuarioLocalUi.setUsuarioIdLocal(response.getUsuarioExternoId());
                    callback.onResponse(true, usuarioLocalUi);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onResponse(false,null);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });
        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getUsuarioLocalPorDni(String urlAdminServicio, String usuario, String password, String correo, String dni, final Callback<UsuarioExternoUi> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15, TimeUnit.SECONDS);
        RetrofitCancel<AdminService> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.f_BuscarUsuarioCent(3, usuario, password, correo, dni, urlAdminServicio));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<AdminService>() {
            @Override
            public void onResponse(AdminService response) {
                if(response == null){
                    callback.onResponse(false, null);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");
                    UsuarioExternoUi usuarioLocalUi = new UsuarioExternoUi();
                    usuarioLocalUi.setEntidadIdLocal(response.getEntidadId());
                    usuarioLocalUi.setUrlServiceLocal(response.getUrlServiceMovil());
                    usuarioLocalUi.setUsuarioExternoId(response.getUsuarioId());
                    usuarioLocalUi.setUsuarioIdLocal(response.getUsuarioExternoId());
                    callback.onResponse(true, usuarioLocalUi);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onResponse(false,null);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });
        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosInicioSesion(int usuarioId, final CallBackComplejo<DatosProgressUi> callback) {
        final ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(15,30,30, TimeUnit.SECONDS);
        RetrofitCancel<BEDatosInicioSesion> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_getDatosInicioSesion(usuarioId));

        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosInicioSesion>() {

            @Override
            public void onResponse(final BEDatosInicioSesion beDatosInicioSesion) {
                if(beDatosInicioSesion == null){
                    callback.onResponse(false, null);
                    Log.d(TAG,"response Inicio Sesion usuario null");
                }else {
                    Log.d(TAG,"response Inicio Sesion usuario true");

                    int empleadoId = 0;
                    int anioAcademicoId = beDatosInicioSesion.getAnioAcademicoId();
                    if(beDatosInicioSesion.getEmpleados()!=null&&!beDatosInicioSesion.getEmpleados().isEmpty()){
                        empleadoId = beDatosInicioSesion.getEmpleados().get(0).getEmpleadoId();
                    }


                    RetrofitCancel<BEDatosAnioAcademico> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_getDatosAnioAcademico(empleadoId, anioAcademicoId));
                    callback.onChangeRetrofit(retrofitCancel);
                    retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosAnioAcademico>() {
                        DatosProgressUi datosProgressUi = new DatosProgressUi();
                        @Override
                        public void onResponse(final BEDatosAnioAcademico response) {
                            if(response == null){
                                callback.onResponse(false, null);
                                Log.d(TAG,"response AnioAcademico usuario null");
                            }else {
                                Log.d(TAG,"response AnioAcademico usuario true");

                                DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                                Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                                    @Override
                                    public void execute(DatabaseWrapper databaseWrapper) {

                                        TransaccionUtils.fastStoreListInsert(Empleado.class, beDatosInicioSesion.getEmpleados(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(AnioAcademico.class, beDatosInicioSesion.getAnioAcademicos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(ParametroConfiguracion.class, beDatosInicioSesion.getParametroConfiguracion(), databaseWrapper, true);


                                        TransaccionUtils.fastStoreListInsert(Aula.class, response.getAulas(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CargaAcademica.class, response.getCargasAcademicas(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, response.getCargaCursoDocente(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, response.getCargaCursoDocenteDet(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CargaCursos.class, response.getCargaCursos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Cursos.class, response.getCursos(), databaseWrapper, true);

                                        TransaccionUtils.fastStoreListInsert(NivelAcademico.class, response.getNivelesAcademicos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(ParametrosDisenio.class, response.getParametrosDisenio(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Periodo.class, response.getPeriodos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(PlanCursos.class, response.getPlanCursos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(PlanEstudios.class, response.getPlanEstudios(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(ProgramasEducativo.class, response.getProgramasEducativos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Seccion.class, response.getSecciones(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(SilaboEvento.class, response.getSilaboEvento(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, response.getCalendarioPeriodos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CalendarioPeriodoDetalle.class, response.getCalendarioPeriodoDetalles(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CargaCursoCalendarioPeriodo.class, response.getCargaCursoCalendarioPeriodo(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, response.getCalendarioAcademico(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Rutas.class, response.getRutas(), databaseWrapper, true);

                                        TransaccionUtils.fastStoreListInsert(Hora.class, response.getHora(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(HorarioPrograma.class, response.getHorarioPrograma(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(HorarioHora.class, response.getHorarioHora(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(DetalleHorario.class, response.getDetalleHorario(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Dia.class, response.getDia(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(HorarioDia.class, response.getHorarioDia(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(CursosDetHorario.class, response.getCursosDetHorario(), databaseWrapper, true);
                                        TransaccionUtils.fastStoreListInsert(Horario.class, response.getHorario(), databaseWrapper, true);

                                        datosProgressUi.setAnioAcademicoId(beDatosInicioSesion.getAnioAcademicoId());

                                    }
                                }).success(new Transaction.Success() {
                                    @Override
                                    public void onSuccess(@NonNull Transaction transaction) {
                                        callback.onResponse(true, datosProgressUi);

                                    }
                                }).error(new Transaction.Error() {
                                    @Override
                                    public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                                        callback.onResponse(false, new DatosProgressUi());
                                    }
                                }).build();

                                transaction.execute();


                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            callback.onResponse(false, null);
                            t.printStackTrace();
                            Log.d(TAG,"onFailure AnioAcademico");
                        }
                    });

                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onResponse(false,null);
                t.printStackTrace();
                Log.d(TAG,"Inicio Sesion onFailure ");
            }
        });
        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getPersonaLocal(String usuario, final Callback<PersonaUi> callback) {

        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15,TimeUnit.SECONDS);
        RetrofitCancel<Persona> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_ObtenerPersona(usuario));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<Persona>() {
            @Override
            public void onResponse(Persona persona) {
                if(persona==null){
                    callback.onResponse(false, null);
                }else {
                    PersonaUi personaUi = new PersonaUi();
                    personaUi.setId(persona.getPersonaId());
                    personaUi.setNombres(persona.getNombres());
                    personaUi.setApellidos(persona.getApellidos());
                    personaUi.setImagenUrl(persona.getUrlPicture());

                    try {
                        persona.save();
                        callback.onResponse(true, personaUi);
                    }catch (Exception e){
                        callback.onResponse(false, null);
                    }

                    Log.d(TAG,"isSuccessful true");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onResponse(false,null);
                Log.d(TAG,"onFailure");
            }
        });
        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getUsuarioLocal(int usuarioId, final Callback<UsuarioUi> callback) {
        Log.d(TAG,"isSuccessful usuarioId: " + usuarioId);
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15,TimeUnit.SECONDS);
        RetrofitCancel<Usuario> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.fobj_ObtenerUsuario(usuarioId));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<Usuario>() {
            @Override
            public void onResponse(final Usuario usuario) {
                if(usuario!=null){
                    if(usuario.getUsuarioId() < 1){
                        callback.onResponse(false, null);
                        Log.d(TAG,"isSuccessful true usuarioId < 1");
                    }else {

                        final UsuarioUi usuarioUi = new UsuarioUi();
                        usuarioUi.setUsuarioId(usuario.getUsuarioId());
                        usuarioUi.setUserName(usuario.getUsuario());
                        usuarioUi.setPasswordEncrypted(usuario.getPassword());
                        usuarioUi.setPersonaId(usuario.getPersonaId());
                        usuarioUi.setPersonaImagenUrl(usuario.getFotoPersona());
                        usuarioUi.setInstitucionUrl(usuario.getFotoEntidad());
                        List<Rol> rolList = usuario.getRoles();
                        List<Integer> rolIdList = new ArrayList<>();
                        if(rolList!=null){
                            for (Rol rol : rolList){
                                Log.d(TAG,"rol "+ rol.getRolId());
                                rolIdList.add(rol.getRolId());
                            }
                        }
                        usuarioUi.setRolIdList(rolIdList);

                        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
                        appDatabase.beginTransactionAsync(new ITransaction() {
                            @Override
                            public void execute(DatabaseWrapper databaseWrapper) {

                                SQLite.delete()
                                        .from(Usuario.class)
                                        .execute(databaseWrapper);

                                SQLite.delete()
                                        .from(Entidad.class)
                                        .execute(databaseWrapper);
                                SQLite.delete()
                                        .from(Georeferencia.class)
                                        .execute(databaseWrapper);
                                SQLite.delete()
                                        .from(Rol.class)
                                        .execute(databaseWrapper);
                                SQLite.delete()
                                        .from(UsuarioRolGeoreferencia.class)
                                        .execute(databaseWrapper);
                                SQLite.delete()
                                        .from(PersonaGeoreferencia.class)
                                        .execute(databaseWrapper);
                                SQLite.delete()
                                        .from(UsuarioAcceso.class)
                                        .execute(databaseWrapper);
                                SQLite.delete()
                                        .from(SessionUser.class)
                                        .execute(databaseWrapper);

                                usuario.save();

                                TransaccionUtils.fastStoreListSave(Entidad.class, usuario.getEntidades(), databaseWrapper, true);
                                TransaccionUtils.fastStoreListSave(Georeferencia.class, usuario.getGeoreferencias(),databaseWrapper, true);
                                TransaccionUtils.fastStoreListSave(Rol.class, usuario.getRoles(),databaseWrapper, true);
                                TransaccionUtils.fastStoreListSave(UsuarioRolGeoreferencia.class, usuario.getUsuarioRolGeoreferencias(),databaseWrapper,true);
                                TransaccionUtils.fastStoreListSave(PersonaGeoreferencia.class, usuario.getPersonaGeoreferencias(),databaseWrapper,true);
                                TransaccionUtils.fastStoreListSave(UsuarioAcceso.class, usuario.getAccesos(),databaseWrapper,false);

                                sessionUserDao.guardarUsuario(usuario.getUsuarioId(),usuario.getPersonaId(),usuario.getUsuario(), usuario.getPassword());

                            }

                        }).success(new Transaction.Success() {
                            @Override
                            public void onSuccess(@NonNull Transaction transaction) {
                                callback.onResponse(true, usuarioUi);
                                Log.d(TAG,"isSuccessful true");
                            }
                        }).error(new Transaction.Error() {
                            @Override
                            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                                callback.onResponse(false, usuarioUi);
                                Log.d(TAG,"isSuccessful error save");
                            }
                        }).build().execute();


                    }
                }else {
                    Log.d(TAG,"isSuccessful false");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onResponse(false,null);
                Log.d(TAG,"onFailure");
            }
        });

        return retrofitCancel;
    }



    @Override
    public List<ActualizarUi> geTodosLosCurso(int usuarioId, int anioAcademicoId) {

        Set<ActualizarUi> actualizarUiList = new LinkedHashSet<>();
        UsuarioRolGeoreferencia rolGeoreferencia = SQLite.select()
                .from(UsuarioRolGeoreferencia.class)
                .innerJoin(Entidad.class)
                .on(UsuarioRolGeoreferencia_Table.entidadId.withTable()
                        .eq(Entidad_Table.entidadId.withTable()))
                .where(UsuarioRolGeoreferencia_Table.usuarioId
                        .is(usuarioId))
                .and(Entidad_Table.estadoId.eq(Entidad.ESTADO_AUTORIZADO))
                .querySingle();

        int entidadId = 0;
        int georeferenciaId = 0;
        int empleadoId = 0;

        if(rolGeoreferencia!=null){
            entidadId = rolGeoreferencia.getEntidadId();
            georeferenciaId = rolGeoreferencia.getGeoReferenciaId();
        }

        Usuario usuario = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.usuarioId.eq(usuarioId))
                .querySingle();

        Empleado empleado = SQLite.select()
                .from(Empleado.class)
                .where(Empleado_Table.personaId.eq(
                        usuario==null ? 0: usuario.getPersonaId()
                ))
                .querySingle();

        empleadoId = empleado==null? 0 :  empleado.getEmpleadoId();



        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                .and(CargaCursos_Table.complejo.withTable().eq(0))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        List<CargaCursos> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);

        Set<Integer> planCursoIdList = new LinkedHashSet<>();
        for (CargaCursos itemCargaCursos : cargaCursosList){
            planCursoIdList.add(itemCargaCursos.getPlanCursoId());
        }
        Log.d(TAG, "planCursoIdList: " + planCursoIdList);
        List<ProgramasEducativo> programasEducativoList = SQLite.select()
                .from(ProgramasEducativo.class)
                .innerJoin(PlanEstudios.class)
                .on(ProgramasEducativo_Table.programaEduId.withTable()
                        .eq(PlanEstudios_Table.programaEduId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(PlanCursos_Table.planEstudiosId.withTable()))
                .where(PlanCursos_Table.planCursoId.withTable()
                        .in(planCursoIdList))
                .and(ProgramasEducativo_Table.estadoId.withTable().eq(37))
                .groupBy(ProgramasEducativo_Table.programaEduId.withTable())
                .queryList();

        for (ProgramasEducativo programasEducativo: programasEducativoList){

            List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                    .from(CalendarioPeriodo.class)
                    .innerJoin(CalendarioAcademico.class)
                    .on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()
                            .eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                    .innerJoin(ProgramasEducativo.class)
                    .on(CalendarioAcademico_Table.programaEduId.withTable()
                            .eq(ProgramasEducativo_Table.programaEduId.withTable()))
                    .innerJoin(AnioAcademico.class)
                    .on(AnioAcademico_Table.idAnioAcademico.withTable()
                            .eq(CalendarioAcademico_Table.idAnioAcademico.withTable()))
                    .where(ProgramasEducativo_Table.programaEduId.withTable()
                            .eq(programasEducativo.getProgramaEduId()))
                    .and(CalendarioAcademico_Table.estadoId.withTable()
                            .is(CalendarioAcademico.CALENDARIO_ACADEMICO_AUTORIZADO))
                    .and(AnioAcademico_Table.estadoId.withTable()
                            .in(AnioAcademico.ANIO_ACADEMICO_MATRICULA, AnioAcademico.ANIO_ACADEMICO_ACTIVO))
                    .queryList();

            Collections.sort(calendarioPeriodoList, new Comparator<CalendarioPeriodo>() {
                public int compare(CalendarioPeriodo o1, CalendarioPeriodo o2) {
                    return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
                }
            });

            int calendarioPeriodoId = 0;
            //#region Buscar calendario periodo con el estado vigente
            for (CalendarioPeriodo item_CalendarioPeriodo : calendarioPeriodoList){
                if (item_CalendarioPeriodo.getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE) {
                    calendarioPeriodoId = item_CalendarioPeriodo.getCalendarioPeriodoId();
                    break;
                }
            }
            //#endregion

            int size = calendarioPeriodoList.size();

            if (size > 0 && calendarioPeriodoId == 0)
            {
                //#region Buscar el calendario en el estado creado proximo a estar vigente
                int count = 0;
                calendarioPeriodoId = calendarioPeriodoList.get(0).getCalendarioPeriodoId();
                for (CalendarioPeriodo item_CalendarioPeriodo : calendarioPeriodoList) {
                    if (item_CalendarioPeriodo.getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_CREADO)
                    {
                        calendarioPeriodoId = item_CalendarioPeriodo.getEstadoId();
                        if (count != 0 &&
                                calendarioPeriodoList.get(count - 1).getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO)
                        {
                            calendarioPeriodoId = calendarioPeriodoList.get(count - 1).getCalendarioPeriodoId();
                            break;
                        }
                    }

                    count++;
                }
                //#endregion
            }

            if (calendarioPeriodoId == 0) continue;


            for (CargaCursos itemCargaCursos : cargaCursosList){


                PlanCursos planCursos = SQLite.select()
                        .from(PlanCursos.class)
                        .innerJoin(PlanEstudios.class)
                        .on(PlanEstudios_Table.planEstudiosId.withTable().eq(PlanCursos_Table.planEstudiosId.withTable()))
                        .where(PlanCursos_Table.planCursoId.is(itemCargaCursos.getPlanCursoId()))
                        .and(PlanEstudios_Table.programaEduId.is(programasEducativo.getProgramaEduId()))
                        .querySingle();

                if(planCursos!=null){

                    Log.d(TAG, "itemCargaCursos: " + itemCargaCursos.getCargaCursoId()+"calendarioPeriodoId");

                    SilaboEvento silaboEvento = SQLite.select()
                            .from(SilaboEvento.class)
                            .where(SilaboEvento_Table.cargaCursoId.eq(itemCargaCursos.getCargaCursoId()))
                            .querySingle();


                    if(silaboEvento!=null&&silaboEvento.getEstadoId()!=SilaboEvento.ESTADO_CREADO){

                        Cursos cursos = SQLite.select()
                                .from(Cursos.class)
                                .where(Cursos_Table.cursoId.eq(planCursos.getCursoId()))
                                .querySingle();

                        int cursoId = cursos!=null? cursos.getCursoId() : 0;


                        ActualizarUi unidades = new ActualizarUi();
                        unidades.setNombre("Unidadades");
                        unidades.setTipo(ActualizarTipoUi.Unidades);
                        unidades.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        unidades.setCalendarioPeriodoId(calendarioPeriodoId);
                        unidades.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        unidades.setEncoloa(true);
                        unidades.generarId();


                        ActualizarUi tipoNota = new ActualizarUi();
                        tipoNota.setNombre("Nivel de logro");
                        tipoNota.setTipo(ActualizarTipoUi.TipoNota);
                        tipoNota.setProgramaEducativoId(programasEducativo.getProgramaEduId());
                        tipoNota.setUsuarioId(usuarioId);
                        tipoNota.setEncoloa(true);
                        tipoNota.generarId();

                        ActualizarUi estudiantes = new ActualizarUi();
                        estudiantes.setNombre("Estudiantes y su familia");
                        estudiantes.setTipo(ActualizarTipoUi.Estudiantes);
                        estudiantes.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        estudiantes.setDocenteId(empleadoId);
                        estudiantes.setCargaAcademicaId(itemCargaCursos.getCargaAcademicaId());
                        estudiantes.setEncoloa(true);
                        estudiantes.generarId();


                        ActualizarUi rubros = new ActualizarUi();
                        rubros.setNombre("Rubro evaluación");
                        rubros.setTipo(ActualizarTipoUi.Rubros);
                        rubros.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        rubros.setCalendarioPeriodoId(calendarioPeriodoId);
                        rubros.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        rubros.setEncoloa(true);
                        rubros.generarId();


                        ActualizarUi resultado = new ActualizarUi();
                        resultado.setNombre("Resultado evaluación");
                        resultado.setTipo(ActualizarTipoUi.Resultado);
                        resultado.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        resultado.setCalendarioPeriodoId(calendarioPeriodoId);
                        resultado.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        resultado.setEncoloa(true);
                        resultado.generarId();

                        ActualizarUi grupos = new ActualizarUi();
                        grupos.setNombre("Grupos");
                        grupos.setTipo(ActualizarTipoUi.Grupos);
                        grupos.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        grupos.setEncoloa(true);
                        grupos.generarId();

                        ActualizarUi tareas = new ActualizarUi();
                        tareas.setNombre("Tarea");
                        tareas.setTipo(ActualizarTipoUi.Tareas);
                        tareas.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        tareas.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        tareas.setCalendarioPeriodoId(calendarioPeriodoId);
                        tareas.setEncoloa(true);
                        tareas.generarId();

                        ActualizarUi casos = new ActualizarUi();
                        casos.setNombre("Casos");
                        casos.setTipo(ActualizarTipoUi.Casos);
                        casos.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        casos.setCalendarioPeriodoId(calendarioPeriodoId);
                        casos.setGeoreferenciaId(georeferenciaId);
                        casos.setEntidadId(entidadId);
                        casos.setEncoloa(true);
                        casos.generarId();

                        ActualizarUi asistencia = new ActualizarUi();
                        asistencia.setNombre("Asistencia");
                        asistencia.setTipo(ActualizarTipoUi.Asistencias);
                        asistencia.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        asistencia.setCalendarioPeriodoId(calendarioPeriodoId);
                        asistencia.setEncoloa(true);
                        asistencia.generarId();

                        ActualizarUi docente = new ActualizarUi();
                        docente.setNombre("Docente");
                        docente.setTipo(ActualizarTipoUi.Docente);
                        docente.setGeoreferenciaId(georeferenciaId);
                        docente.setEncoloa(true);
                        docente.generarId();

                        ActualizarUi dimencionDesarrollo = new ActualizarUi();
                        dimencionDesarrollo.setNombre("Dimensión Desarrollo");
                        dimencionDesarrollo.setTipo(ActualizarTipoUi.Dimencion_Desarrollo);
                        dimencionDesarrollo.setCursoId(cursoId);
                        dimencionDesarrollo.setDocenteId(empleadoId);
                        dimencionDesarrollo.setGeoreferenciaId(georeferenciaId);
                        dimencionDesarrollo.setEntidadId(entidadId);
                        dimencionDesarrollo.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        dimencionDesarrollo.setEncoloa(true);
                        dimencionDesarrollo.generarId();

                        actualizarUiList.add(unidades);

                        actualizarUiList.add(estudiantes);

                        actualizarUiList.add(tipoNota);
                        actualizarUiList.add(resultado);
                        actualizarUiList.add(rubros);
                        actualizarUiList.add(grupos);
                        actualizarUiList.add(tareas);
                        actualizarUiList.add(casos);
                        actualizarUiList.add(dimencionDesarrollo);
                        //actualizarUiList.add(docente);
                        //actualizarUiList.add(asistencia);

                    }


                }

            }

        }





        return new ArrayList<>(actualizarUiList);
    }

    @Override
    public String getNombreColegio(int usuarioId) {

        UsuarioRolGeoreferencia rolGeoreferencia = SQLite.select()
                .from(UsuarioRolGeoreferencia.class)
                .innerJoin(Entidad.class)
                .on(UsuarioRolGeoreferencia_Table.entidadId.withTable()
                        .eq(Entidad_Table.entidadId.withTable()))
                .where(UsuarioRolGeoreferencia_Table.usuarioId
                        .is(usuarioId))
                .and(Entidad_Table.estadoId.eq(Entidad.ESTADO_AUTORIZADO))
                .querySingle();

        Georeferencia georeferencia = SQLite.select()
                .from(Georeferencia.class)
                .where(Georeferencia_Table.georeferenciaId.eq(rolGeoreferencia!=null? rolGeoreferencia.getGeoReferenciaId(): 0))
                .querySingle();

        return georeferencia!=null?georeferencia.getNombre():"";
    }

    @Override
    public String getNombreAnioActual(int anioAcademicoId) {
        AnioAcademico anioAcademico = SQLite.select()
                .from(AnioAcademico.class)
                .where(AnioAcademico_Table.idAnioAcademico.eq(anioAcademicoId))
                .querySingle();
        return anioAcademico!=null?anioAcademico.getNombre():"";
    }

    @Override
    public List<ProgramaEducativoUi> getCursosAnioPrograma(int usuarioId, int anioAcademicoId, int calendarioPeriodoId, int programaEducativoId) {
        List<ProgramaEducativoUi> programaEducativoUiList = new ArrayList<>();
        UsuarioRolGeoreferencia rolGeoreferencia = SQLite.select()
                .from(UsuarioRolGeoreferencia.class)
                .innerJoin(Entidad.class)
                .on(UsuarioRolGeoreferencia_Table.entidadId.withTable()
                        .eq(Entidad_Table.entidadId.withTable()))
                .where(UsuarioRolGeoreferencia_Table.usuarioId
                        .is(usuarioId))
                //.and(Entidad_Table.estadoId.eq(Entidad.ESTADO_AUTORIZADO))
                .querySingle();

        int entidadId = 0;
        int georeferenciaId = 0;
        int empleadoId = 0;

        if(rolGeoreferencia!=null){
            entidadId = rolGeoreferencia.getEntidadId();
            georeferenciaId = rolGeoreferencia.getGeoReferenciaId();
        }

        Usuario usuario = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.usuarioId.eq(usuarioId))
                .querySingle();

        Empleado empleado = SQLite.select()
                .from(Empleado.class)
                .where(Empleado_Table.personaId.eq(
                        usuario==null ? 0: usuario.getPersonaId()
                ))
                .querySingle();

        empleadoId = empleado==null? 0 :  empleado.getEmpleadoId();



        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                .and(CargaCursos_Table.complejo.withTable().eq(0))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        List<CargaCursos> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);

        Set<Integer> planCursoIdList = new LinkedHashSet<>();
        for (CargaCursos itemCargaCursos : cargaCursosList){
            planCursoIdList.add(itemCargaCursos.getPlanCursoId());
        }
        Log.d(TAG, "planCursoIdList: " + planCursoIdList);
        List<ProgramasEducativo> programasEducativoList = new ArrayList<>();
        if(programaEducativoId!=0){
            programasEducativoList = SQLite.select()
                    .from(ProgramasEducativo.class)
                    .where(ProgramasEducativo_Table.programaEduId.eq(programaEducativoId))
                    .queryList();
        }else {
            programasEducativoList = SQLite.select()
                    .from(ProgramasEducativo.class)
                    .innerJoin(PlanEstudios.class)
                    .on(ProgramasEducativo_Table.programaEduId.withTable()
                            .eq(PlanEstudios_Table.programaEduId.withTable()))
                    .innerJoin(PlanCursos.class)
                    .on(PlanEstudios_Table.planEstudiosId.withTable()
                            .eq(PlanCursos_Table.planEstudiosId.withTable()))
                    .where(PlanCursos_Table.planCursoId.withTable()
                            .in(planCursoIdList))
                    .and(ProgramasEducativo_Table.estadoId.withTable().eq(37))
                    .groupBy(ProgramasEducativo_Table.programaEduId.withTable())
                    .queryList();
        }

        for (ProgramasEducativo programasEducativo: programasEducativoList){
            if(calendarioPeriodoId==0){
                //#region get calendarioPerio
                List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                        .from(CalendarioPeriodo.class)
                        .innerJoin(CalendarioAcademico.class)
                        .on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()
                                .eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                        .innerJoin(AnioAcademico.class)
                        .on(CalendarioAcademico_Table.idAnioAcademico.withTable()
                                .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                        .where(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                        .and(CalendarioAcademico_Table.programaEduId.withTable().eq(programasEducativo.getProgramaEduId()))
                        .queryList();

                Collections.sort(calendarioPeriodoList, new Comparator<CalendarioPeriodo>() {
                    public int compare(CalendarioPeriodo o1, CalendarioPeriodo o2) {
                        return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
                    }
                });

                calendarioPeriodoId = 0;
                //#region Buscar calendario periodo con el estado vigente
                for (CalendarioPeriodo item_CalendarioPeriodo : calendarioPeriodoList){
                    if (item_CalendarioPeriodo.getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE) {
                        calendarioPeriodoId = item_CalendarioPeriodo.getCalendarioPeriodoId();
                        break;
                    }
                }
                //#endregion

                int size = calendarioPeriodoList.size();

                if (size > 0 && calendarioPeriodoId == 0)
                {
                    //#region Buscar el calendario en el estado creado proximo a estar vigente
                    int count = 0;
                    calendarioPeriodoId = calendarioPeriodoList.get(0).getCalendarioPeriodoId();
                    for (CalendarioPeriodo item_CalendarioPeriodo : calendarioPeriodoList) {
                        if (item_CalendarioPeriodo.getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_CREADO)
                        {
                            calendarioPeriodoId = item_CalendarioPeriodo.getCalendarioPeriodoId();
                            if (count != 0 &&
                                    calendarioPeriodoList.get(count - 1).getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO)
                            {
                                calendarioPeriodoId = calendarioPeriodoList.get(count - 1).getCalendarioPeriodoId();
                                break;
                            }
                        }

                        count++;
                    }
                    //#endregion
                }
                //#endregion

                if (calendarioPeriodoId == 0) continue;
            }


            Tipos tipos = SQLite.select()
                    .from(Tipos.class)
                    .innerJoin(CalendarioPeriodo.class)
                    .on(CalendarioPeriodo_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                    .where(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                    .querySingle();


            ProgramaEducativoUi programaEducativoUi = new ProgramaEducativoUi();
            programaEducativoUi.setNombre(programasEducativo.getNombre());
            programaEducativoUi.setDocenteId(empleadoId);
            programaEducativoUi.setEntidadId(entidadId);
            programaEducativoUi.setCalendarioPeriodoId(calendarioPeriodoId);
            programaEducativoUi.setGeoreferenciaId(georeferenciaId);
            programaEducativoUi.setBimestre(tipos!=null?tipos.getNombre():"");
            programaEducativoUi.setEncola(true);
            List<ActualizarUi> actualizarUiList = new ArrayList<>();

            for (CargaCursos itemCargaCursos : cargaCursosList){


                PlanCursos planCursos = SQLite.select()
                        .from(PlanCursos.class)
                        .innerJoin(PlanEstudios.class)
                        .on(PlanEstudios_Table.planEstudiosId.withTable().eq(PlanCursos_Table.planEstudiosId.withTable()))
                        .where(PlanCursos_Table.planCursoId.is(itemCargaCursos.getPlanCursoId()))
                        .and(PlanEstudios_Table.programaEduId.is(programasEducativo.getProgramaEduId()))
                        .querySingle();

                if(planCursos!=null){

                    Log.d(TAG, "itemCargaCursos: " + itemCargaCursos.getCargaCursoId()+"calendarioPeriodoId");

                    SilaboEvento silaboEvento = SQLite.select()
                            .from(SilaboEvento.class)
                            .where(SilaboEvento_Table.cargaCursoId.eq(itemCargaCursos.getCargaCursoId()))
                            .querySingle();


                    if(silaboEvento!=null&&silaboEvento.getEstadoId()!=SilaboEvento.ESTADO_CREADO){

                        Cursos cursos = SQLite.select()
                                .from(Cursos.class)
                                .where(Cursos_Table.cursoId.eq(planCursos.getCursoId()))
                                .querySingle();

                        int cursoId = cursos!=null? cursos.getCursoId() : 0;

                        ActualizarUi unidades = new ActualizarUi();
                        unidades.setNombre("Unidadades");
                        unidades.setTipo(ActualizarTipoUi.Unidades);
                        unidades.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        unidades.setCalendarioPeriodoId(calendarioPeriodoId);
                        unidades.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        unidades.generarId();
                        unidades.setFecha(getTimeSesionData(unidades));
                        unidades.setEncoloa(unidades.getFecha()==0);
                        actualizarUiList.add(unidades);

                        ActualizarUi tipoNota = new ActualizarUi();
                        tipoNota.setNombre("Nivel de logro");
                        tipoNota.setTipo(ActualizarTipoUi.TipoNota);
                        tipoNota.setProgramaEducativoId(programasEducativo.getProgramaEduId());
                        tipoNota.setUsuarioId(usuarioId);
                        tipoNota.generarId();
                        tipoNota.setFecha(getTimeSesionData(tipoNota));
                        tipoNota.setEncoloa(tipoNota.getFecha()==0);
                        actualizarUiList.add(tipoNota);

                        ActualizarUi estudiantes = new ActualizarUi();
                        estudiantes.setNombre("Estudiantes y su familia");
                        estudiantes.setTipo(ActualizarTipoUi.Estudiantes);
                        estudiantes.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        estudiantes.setDocenteId(empleadoId);
                        estudiantes.setCargaAcademicaId(itemCargaCursos.getCargaAcademicaId());
                        estudiantes.generarId();
                        estudiantes.setFecha(getTimeSesionData(estudiantes));
                        estudiantes.setEncoloa(estudiantes.getFecha()==0);
                        actualizarUiList.add(estudiantes);


                        ActualizarUi rubros = new ActualizarUi();
                        rubros.setNombre("Rubro evaluación");
                        rubros.setTipo(ActualizarTipoUi.Rubros);
                        rubros.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        rubros.setCalendarioPeriodoId(calendarioPeriodoId);
                        rubros.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        rubros.generarId();
                        rubros.setFecha(getTimeSesionData(rubros));
                        rubros.setEncoloa(rubros.getFecha()==0);
                        actualizarUiList.add(rubros);

                        ActualizarUi resultado = new ActualizarUi();
                        resultado.setNombre("Resultado evaluación");
                        resultado.setTipo(ActualizarTipoUi.Resultado);
                        resultado.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        resultado.setCalendarioPeriodoId(calendarioPeriodoId);
                        resultado.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        resultado.generarId();
                        resultado.setFecha(getTimeSesionData(resultado));
                        resultado.setEncoloa(resultado.getFecha()==0);
                        actualizarUiList.add(resultado);

                        ActualizarUi grupos = new ActualizarUi();
                        grupos.setNombre("Grupos");
                        grupos.setTipo(ActualizarTipoUi.Grupos);
                        grupos.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        grupos.generarId();
                        grupos.setFecha(getTimeSesionData(grupos));
                        grupos.setEncoloa(grupos.getFecha()==0);
                        actualizarUiList.add(grupos);


                        ActualizarUi tareas = new ActualizarUi();
                        tareas.setNombre("Tarea");
                        tareas.setTipo(ActualizarTipoUi.Tareas);
                        tareas.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        tareas.setSilaboEventoId(silaboEvento.getSilaboEventoId());
                        tareas.setCalendarioPeriodoId(calendarioPeriodoId);
                        tareas.generarId();
                        tareas.setFecha(getTimeSesionData(tareas));
                        tareas.setEncoloa(tareas.getFecha()==0);
                        actualizarUiList.add(tareas);

                        ActualizarUi casos = new ActualizarUi();
                        casos.setNombre("Casos");
                        casos.setTipo(ActualizarTipoUi.Casos);
                        casos.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        casos.setCalendarioPeriodoId(calendarioPeriodoId);
                        casos.setGeoreferenciaId(georeferenciaId);
                        casos.setEntidadId(entidadId);
                        casos.generarId();
                        casos.setFecha(getTimeSesionData(casos));
                        casos.setEncoloa(casos.getFecha()==0);
                        actualizarUiList.add(casos);

                        ActualizarUi asistencia = new ActualizarUi();
                        asistencia.setNombre("Asistencia");
                        asistencia.setTipo(ActualizarTipoUi.Asistencias);
                        asistencia.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        asistencia.setCalendarioPeriodoId(calendarioPeriodoId);
                        asistencia.generarId();
                        asistencia.setFecha(getTimeSesionData(asistencia));
                        asistencia.setEncoloa(asistencia.getFecha()==0);
                        actualizarUiList.add(asistencia);

                        ActualizarUi docente = new ActualizarUi();
                        docente.setNombre("Docente");
                        docente.setTipo(ActualizarTipoUi.Docente);
                        docente.setGeoreferenciaId(georeferenciaId);
                        docente.generarId();
                        docente.setFecha(getTimeSesionData(asistencia));
                        docente.setEncoloa(asistencia.getFecha()==0);
                        actualizarUiList.add(asistencia);

                        ActualizarUi dimencionDesarrollo = new ActualizarUi();
                        dimencionDesarrollo.setNombre("Dimensión desarrollo");
                        dimencionDesarrollo.setTipo(ActualizarTipoUi.Dimencion_Desarrollo);
                        dimencionDesarrollo.setCursoId(cursoId);
                        dimencionDesarrollo.setDocenteId(empleadoId);
                        dimencionDesarrollo.setGeoreferenciaId(georeferenciaId);
                        dimencionDesarrollo.setEntidadId(entidadId);
                        dimencionDesarrollo.setCargacursoId(itemCargaCursos.getCargaCursoId());
                        dimencionDesarrollo.generarId();
                        docente.setFecha(getTimeSesionData(dimencionDesarrollo));
                        dimencionDesarrollo.setEncoloa(dimencionDesarrollo.getFecha()==0);
                        actualizarUiList.add(dimencionDesarrollo);

                    }

                }

            }

            programaEducativoUi.setActualizarUiList(actualizarUiList);
            programaEducativoUiList.add(programaEducativoUi);
        }
        return programaEducativoUiList;
    }

    @Override
    public RetrofitCancel saveDatosUnidades(ServiceEnvioUi serviceEnvioUi,CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof SesionesEnviarUi){
            SesionesEnviarUi sesionesEnviarUi = (SesionesEnviarUi)serviceEnvioUi;
            BEDatosSesionAprendizaje  beDatosSesionAprendizaje = new BEDatosSesionAprendizaje();
            beDatosSesionAprendizaje.setSesionAprendizaje(SQLite.select()
                    .from(SesionAprendizaje.class)
                    .where(SesionAprendizaje_Table.sesionAprendizajeId.in(sesionesEnviarUi.getSesionesIdList()))
                    .queryList());
            beGuardarEntidadesGlobal.setSesionAprendizaje(beDatosSesionAprendizaje);
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );

    }


    private RetrofitCancel saveDatos(ApiRetrofit apiRetrofit, final ServiceEnvioUi serviceEnvioUi, final BEGuardarEntidadesGlobal beGuardarEntidadesGlobal, final CallBackSucces<ServiceEnvioUi> callBackSucces){
        RetrofitCancel<BERespuesta> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.fins_GuardarEntidades_GlobalSimple(beGuardarEntidadesGlobal));

        retrofitCancel.enqueue(new RetrofitCancel.Callback<BERespuesta>() {
            @Override
            public void onResponse(final BERespuesta response) {
                if(response == null) {
                    callBackSucces.onLoad(false, serviceEnvioUi);
                    Log.d(TAG, "SendDatos Successful body null ");
                }else {

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            final Gson gson = new Gson();
                            final String representacionJSON = gson.toJson(beGuardarEntidadesGlobal);
                            ApiRetrofit.Log.d(TAG, "saveDatos : " + representacionJSON);

                            GEDatosEnvioAsistencia geDatosEnvioAsistencia = beGuardarEntidadesGlobal.getAsistencia();
                            if(geDatosEnvioAsistencia!=null)changeEstadoGlobals(geDatosEnvioAsistencia,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            BEDatosEnvioGrupo beDatosEnvioGrupo = beGuardarEntidadesGlobal.getGrupo();
                            if(beDatosEnvioGrupo!=null)changeEstadoGlobals(beDatosEnvioGrupo,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            BEDatosEnvioMensajeria beDatosEnvioMensajeria = beGuardarEntidadesGlobal.getMensajeria();
                            if(beDatosEnvioMensajeria!=null)changeEstadoGlobals(beDatosEnvioMensajeria,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = beGuardarEntidadesGlobal.getRubroEvaluacionProceso();
                            if(geDatosRubroEvaluacionProceso!=null)changeEstadoGlobals(geDatosRubroEvaluacionProceso,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            BEDatosSesionAprendizaje beDatosSesionAprendizaje = beGuardarEntidadesGlobal.getSesionAprendizaje();
                            if(beDatosSesionAprendizaje!=null)changeEstadoGlobals(beDatosSesionAprendizaje,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            GEDatosTareasRecursos geDatosTareasRecursos = beGuardarEntidadesGlobal.getTareaRecursos();
                            if(geDatosTareasRecursos!=null)changeEstadoGlobals(geDatosTareasRecursos,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos beDatosCasos = beGuardarEntidadesGlobal.getCasos();
                            if(beDatosCasos!=null)changeEstadoGlobals(beDatosCasos,BaseEntity.FLAG_EXPORTED,databaseWrapper);

                            BEDatosCargaAcademica beDatosCargaAcademica = beGuardarEntidadesGlobal.getCargaAcademica();
                            if(beDatosCargaAcademica!=null){
                                changeEstadoGlobals(beDatosCargaAcademica, BaseEntity.FLAG_EXPORTED, databaseWrapper);
                            }

                            initNotification(beGuardarEntidadesGlobal);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, serviceEnvioUi);

                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            callBackSucces.onLoad(false, serviceEnvioUi);
                        }
                    }).build();

                    transaction.execute();

                    Log.d(TAG, "SendDatos Successful : false");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                callBackSucces.onLoad(false,serviceEnvioUi);
                Log.d(TAG, "getDatosLogin Throwable : false - "+t.getMessage());
            }
        });

        setupListener( callBackSucces);

        return retrofitCancel;
    }

    @Override
    public RetrofitCancel saveDatosResultado(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof ResultadoEnvioUi){
            ResultadoEnvioUi resultadoEnvioUi = (ResultadoEnvioUi)serviceEnvioUi;
            GEDatosRubroEvaluacionProceso datosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();
            BEDatosEvaluacionResultado beDatosEvaluacionResultado = new BEDatosEvaluacionResultado();

            List<RubroEvaluacionResultado> rubroEvaluacionResultados = SQLite.select()
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable().in(resultadoEnvioUi.getRubroEvaluacionResultadoIdList()))
                    .and(RubroEvaluacionResultado_Table.syncFlag.in(RubroEvaluacionResultado.FLAG_ADDED, RubroEvaluacionResultado.FLAG_UPDATED))
                    .queryList();

            beDatosEvaluacionResultado.setRubroEvaluacionResultado(rubroEvaluacionResultados);

            List<EvaluacionResultadoC> evaluacionResultadoList = SQLite.select()
                    .from(EvaluacionResultadoC.class)
                    .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.withTable().in(resultadoEnvioUi.getRubroEvaluacionResultadoIdList()))
                    .and(EvaluacionResultadoC_Table.syncFlag.in(EvaluacionResultadoC.FLAG_ADDED, EvaluacionResultadoC.FLAG_UPDATED))
                    .queryList();

            beDatosEvaluacionResultado.setEvaluacionResultado(evaluacionResultadoList);

            Set<String> rubroAncladoId = new LinkedHashSet<>();
            for (RubroEvaluacionResultado rubroEvaluacionResultado : rubroEvaluacionResultados)rubroAncladoId.add(rubroEvaluacionResultado.getRubroEvalProcesoId());

            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();

            getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, new ArrayList<String>(rubroAncladoId));
            datosRubroEvaluacionProceso.setBeDatosRubroEvaluacionResultado(beDatosEvaluacionResultado);
            datosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);

            beGuardarEntidadesGlobal.setRubroEvaluacionProceso(datosRubroEvaluacionProceso);
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );

    }

    @Override
    public RetrofitCancel saveDatosRubro(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof RubroEnviarUi){
            RubroEnviarUi rubroEnviarUi = (RubroEnviarUi)serviceEnvioUi;
            GEDatosRubroEvaluacionProceso datosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();

            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();
            getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, rubroEnviarUi.getRubroEvaluacionIdList());
            datosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);
            datosRubroEvaluacionProceso.setBeDatosTareaRecursos(getTareaRubro(rubroEnviarUi.getRubroEvaluacionIdList(), beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula() ));
            datosRubroEvaluacionProceso.setBeDatosEnvioGrupo(getGrupoRubro(beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()));
            beGuardarEntidadesGlobal.setRubroEvaluacionProceso(datosRubroEvaluacionProceso);
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );
    }

    @Override
    public RetrofitCancel saveDatosRubrica(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof RubroEnviarUi){
            RubroEnviarUi rubroEnviarUi = (RubroEnviarUi)serviceEnvioUi;
            GEDatosRubroEvaluacionProceso datosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();

            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();

            getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, rubroEnviarUi.getRubroEvaluacionIdList());

            datosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);

            datosRubroEvaluacionProceso.setBeDatosTareaRecursos(getTareaRubro(rubroEnviarUi.getRubroEvaluacionIdList(), beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula()));

            datosRubroEvaluacionProceso.setBeDatosEnvioGrupo(getGrupoRubro(beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()));

            beGuardarEntidadesGlobal.setRubroEvaluacionProceso(datosRubroEvaluacionProceso);
        }

        Gson gson = new Gson();
        String representacionJSON = gson.toJson(beGuardarEntidadesGlobal);
        ApiRetrofit.Log.d(TAG, "saveDatosRubrica : " + representacionJSON);

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );
    }

    @Override
    public RetrofitCancel saveDatosFormula(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof RubroEnviarUi){
            RubroEnviarUi rubroEnviarUi = (RubroEnviarUi)serviceEnvioUi;
            GEDatosRubroEvaluacionProceso datosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();
            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();
            getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, rubroEnviarUi.getRubroEvaluacionIdList());
            datosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);
            datosRubroEvaluacionProceso.setBeDatosTareaRecursos(getTareaRubro(rubroEnviarUi.getRubroEvaluacionIdList(), beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula()));
            datosRubroEvaluacionProceso.setBeDatosEnvioGrupo(getGrupoRubro(beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()));
            beGuardarEntidadesGlobal.setRubroEvaluacionProceso(datosRubroEvaluacionProceso);
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );
    }

    @Override
    public RetrofitCancel saveDatosTarea(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof TareaEnviarUi){
            TareaEnviarUi tareaEnviarUi = (TareaEnviarUi)serviceEnvioUi;
            beGuardarEntidadesGlobal.setTareaRecursos(getTareasRecursos(tareaEnviarUi.getTareaIdLis()));
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );

    }

    @Override
    public RetrofitCancel saveDatosGrupo(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof GrupoEnviarUi){
            GrupoEnviarUi grupoEnviarUi = (GrupoEnviarUi)serviceEnvioUi;
            beGuardarEntidadesGlobal.setGrupo(getGrupo(grupoEnviarUi.getGrupoEquipoList()));
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );
    }

    @Override
    public RetrofitCancel saveDatosCasos(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof CasosEnviarUi){
            CasosEnviarUi casosEnviarUi = (CasosEnviarUi)serviceEnvioUi;
            beGuardarEntidadesGlobal.setCasos(getCaso(casosEnviarUi.getCasosIdList()));
        }

        return saveDatos(apiRetrofit, serviceEnvioUi,beGuardarEntidadesGlobal, callBackSucces );
    }

    @Override
    public RetrofitCancel saveDatosCerrarCurso(ServiceEnvioUi serviceEnvioUi, CallBackSucces<ServiceEnvioUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
        if(serviceEnvioUi instanceof CerrarCursoEnviarUi){
            CerrarCursoEnviarUi cerrarCursoEnviarUi = (CerrarCursoEnviarUi)serviceEnvioUi;
            BEDatosCargaAcademica beDatosCargaAcademica = new BEDatosCargaAcademica();
            beDatosCargaAcademica.setObtenerCargaCursosCalendarioPeriodo(ConsultaUtils.getChangeItemsTableChild(CargaCursoCalendarioPeriodo.class,
                    CargaCursoCalendarioPeriodo_Table.cargaCursoCalendarioPeriodoId.in(cerrarCursoEnviarUi.getCargaCursoCalendarioPeriodoIdList())));
            beGuardarEntidadesGlobal.setCargaAcademica(beDatosCargaAcademica);
        }
        return saveDatos(apiRetrofit, serviceEnvioUi, beGuardarEntidadesGlobal, callBackSucces );
    }

    @Override
    public AlarmaUi getPlanSinck() {
        SessionUser sesion = sessionUserDao.getCurrentUser();
        AlarmaUi alarmaUi= null;
        if(sesion.getHourSync()!=-1){
            alarmaUi= new AlarmaUi();
            alarmaUi.setHora(sesion.getHourSync());
            alarmaUi.setMinute(sesion.getMinuteSync());
            String amPm="";
            if(sesion.getHourSync()<12)amPm= "AM";
            else amPm="PM";
            alarmaUi.setTiempo(amPm);
        }
        return alarmaUi;
    }

    @Override
    public boolean savePlanSinck(int hora, int minute) {
        boolean success=false;
        try {
            SessionUser sesion = sessionUserDao.getCurrentUser();
            sesion.setHourSync(hora);
            sesion.setMinuteSync(minute);
            success = sesion.save();
        }catch (Exception e){
            e.getStackTrace();
        }
        return success;
    }


    private void getRubroEvaluacionProceso(BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso ,List<String> rubroEvaluacionId){

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvaluacionId))
                .and(RubroEvaluacionProcesoC_Table.syncFlag.in(RubroEvaluacionProcesoC.FLAG_ADDED, RubroEvaluacionProcesoC.FLAG_UPDATED))
                .queryList();

        beDatosRubroEvaluacionProceso.addRubroEvaluacionProceso(rubroEvaluacionProcesoCList);


        beDatosRubroEvaluacionProceso.addRubro_evaluacion_proceso_campotematico(SQLite.select()
                .from(RubroEvaluacionProcesoCampotematicoC.class)
                .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(RubroEvaluacionProcesoCampotematicoC_Table.syncFlag.in(RubroEvaluacionProcesoCampotematicoC.FLAG_ADDED,RubroEvaluacionProcesoCampotematicoC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerCriterioRubroEvaluacionProceso(SQLite.select()
                .from(CriterioRubroEvaluacionC.class)
                .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(CriterioRubroEvaluacionC_Table.syncFlag.in(CriterioRubroEvaluacionC.FLAG_ADDED,CriterioRubroEvaluacionC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerRubroEvaluacionProcesoEquipo(SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.syncFlag.withTable().in(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.FLAG_ADDED,T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerRubroEvaluacionProcesoIntegrante(SQLite.select(Utils.f_allcolumnTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.ALL_COLUMN_PROPERTIES))
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .on(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.withTable().eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.withTable()))
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.withTable().in(rubroEvaluacionId))
                .and(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.syncFlag.withTable().in(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.FLAG_ADDED,T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addObtenerEquipoEvaluacionProceso(SQLite.select()
                .from(EquipoEvaluacionProcesoC.class)
                .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(EquipoEvaluacionProcesoC_Table.syncFlag.in(EquipoEvaluacionProcesoC.FLAG_ADDED,EquipoEvaluacionProcesoC.FLAG_UPDATED ))
                .queryList());

        beDatosRubroEvaluacionProceso.addEvaluacionProceso(SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionId))
                .and(EvaluacionProcesoC_Table.syncFlag.in(EvaluacionProcesoC.FLAG_ADDED, EvaluacionProcesoC.FLAG_UPDATED))
                .queryList());

        beDatosRubroEvaluacionProceso.addRubroEvaluacionProcesoComentario(SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoComentario_Table.ALL_COLUMN_PROPERTIES))
                .from(RubroEvaluacionProcesoComentario.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.withTable().eq(EvaluacionProcesoC_Table.key.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().in(rubroEvaluacionId))
                .and(RubroEvaluacionProcesoComentario_Table.syncFlag.withTable().in(RubroEvaluacionProcesoComentario.FLAG_ADDED, RubroEvaluacionProcesoComentario.FLAG_UPDATED))
                .queryList());
        beDatosRubroEvaluacionProceso.addArchivoRubroProceso(SQLite.select(Utils.f_allcolumnTable(ArchivosRubroProceso_Table.ALL_COLUMN_PROPERTIES))
                .from(ArchivosRubroProceso.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(ArchivosRubroProceso_Table.evaluacionProcesoId.withTable().eq(EvaluacionProcesoC_Table.key.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().in(rubroEvaluacionId))
                .and(ArchivosRubroProceso_Table.syncFlag.withTable().in(ArchivosRubroProceso.FLAG_ADDED, ArchivosRubroProceso.FLAG_UPDATED))
                .queryList());

        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.syncFlag.withTable().in(RubroEvalRNPFormulaC.FLAG_ADDED, RubroEvalRNPFormulaC.FLAG_UPDATED))
                .queryList();

        beDatosRubroEvaluacionProceso.addRubroEvalProcesoFormula(rubroEvalRNPFormulaCList);

        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList2 = SQLite.select()
                .from(RubroEvalRNPFormulaC.class)
                .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(rubroEvaluacionId))
                .queryList();

        Set<String> rubroDetalleId = new LinkedHashSet<>();
        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC : rubroEvalRNPFormulaCList2)rubroDetalleId.add(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());

        if(!rubroDetalleId.isEmpty()){
            Log.d(TAG, "rubroDetalleId: " + rubroDetalleId);
            getRubroEvaluacionProceso(beDatosRubroEvaluacionProceso, new ArrayList<String>(rubroDetalleId));
        }


        /*

        rubroActualizarUi.setTareaRubroEvaluacionProcesoList(new ArrayList<Object>(SQLite.select()
                .from(TareaRubroEvaluacionProceso.class)
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                .and(TareaRubroEvaluacionProceso_Table.syncFlag.in(TareaRubroEvaluacionProceso.FLAG_ADDED, TareaRubroEvaluacionProceso.FLAG_UPDATED))
                .queryList()));

        rubroActualizarUi.setTareaCList(new ArrayList<Object>(SQLite.select(Utils.f_allcolumnTable(TareasC_Table.ALL_COLUMN_PROPERTIES))
                .from(TareasC.class)
                .innerJoin(TareaRubroEvaluacionProceso.class)
                .on(TareasC_Table.key.withTable().eq(TareaRubroEvaluacionProceso_Table.tareaId.withTable()))
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionProcesoC.getKey()))
                .and(TareaRubroEvaluacionProceso_Table.syncFlag.withTable().in(TareaRubroEvaluacionProceso.FLAG_ADDED, TareaRubroEvaluacionProceso.FLAG_UPDATED))
                .queryList()));*/


    }

    private GEDatosTareasRecursos getTareaRubro(List<String> rubroEvaluacionIdList, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList) {

        Set<String> rubroDetalleId = new LinkedHashSet<>(rubroEvaluacionIdList);
        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC : rubroEvalRNPFormulaCList)rubroDetalleId.add(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());

        List<String> tareaIdList = new ArrayList<>();
        List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = SQLite.select()
                .from(TareaRubroEvaluacionProceso.class)
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.in(rubroDetalleId))
                .and(TareaRubroEvaluacionProceso_Table.syncFlag.in(TareaRubroEvaluacionProceso.FLAG_ADDED, TareaRubroEvaluacionProceso.FLAG_UPDATED))
                .queryList();

        for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso : tareaRubroEvaluacionProcesoList){
            tareaIdList.add(tareaRubroEvaluacionProceso.getTareaId());
        }

        GEDatosTareasRecursos geDatosTareasRecursos = getTareasRecursos(tareaIdList);
        geDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);
        return geDatosTareasRecursos;

    }


    private GEDatosTareasRecursos getTareasRecursos(List<String> tareaIdList){
        GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();

        List<TareasC> tareasCList = SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.key.in(tareaIdList))
                .and(TareasC_Table.syncFlag.in(TareasC.FLAG_ADDED, TareasC.FLAG_UPDATED))
                .queryList();
        tareaIdList.clear();
        for (TareasC tareasC : tareasCList)tareaIdList.add(tareasC.getKey());

        beDatosTareasRecursos.setTareas(tareasCList);

        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaIdList)));

        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }

        List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey));
        beDatosTareasRecursos.setRecursoDidactico(recursoDidacticoEventoCList);

        List<String> recusoDidacticoIdList = new ArrayList<>();
        for (RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recusoDidacticoIdList.add(recursoDidacticoEventoC.getKey());

        List<RecursoArchivo> recursoArchivoList = ConsultaUtils.getChangeItemsTableChild(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recusoDidacticoIdList));
        beDatosTareasRecursos.setRecursoArchivo(recursoArchivoList);

        List<String> archivoIdList = new ArrayList<>();
        for (RecursoArchivo recursoArchivo: recursoArchivoList)archivoIdList.add(recursoArchivo.getArchivoId());
        List<Archivo> archivoList = ConsultaUtils.getChangeItemsTableChild(Archivo.class, Archivo_Table.key.in(archivoIdList));
        for (Archivo archivo : archivoList){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
                Log.d(getClass().getSimpleName(),"file: " + file);
            }
        }
        beDatosTareasRecursos.setArchivo(archivoList);

        return beDatosTareasRecursos;
    }

    private BEDatosEnvioGrupo getGrupoRubro(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> evaluacionProcesoEquipocs){

        Set<String> equiposIdList = new LinkedHashSet<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC rubroEvaluacionProcesoEquipoc : evaluacionProcesoEquipocs)equiposIdList.add(rubroEvaluacionProcesoEquipoc.getEquipoId());

        List<EquipoC> equipoCList = SQLite.select()
                .from(EquipoC.class)
                .where(EquipoC_Table.key.in(equiposIdList))
                .and(EquipoC_Table.syncFlag.in(EquipoC.FLAG_ADDED, EquipoC.FLAG_UPDATED))
                .queryList();

        List<EquipoIntegranteC> equipoIntegranteCS = SQLite.select()
                .from(EquipoIntegranteC.class)
                .where(EquipoIntegranteC_Table.equipoId.in(equiposIdList))
                .and(EquipoIntegranteC_Table.syncFlag.in(EquipoIntegranteC.FLAG_ADDED, EquipoIntegranteC.FLAG_UPDATED))
                .queryList();

        Set<String> grupoEquipoIdList = new LinkedHashSet<>();
        for (EquipoC equipoC: equipoCList)grupoEquipoIdList.add(equipoC.getGrupoEquipoId());

        List<GrupoEquipoC> grupoEquipoCList = SQLite.select()
                .from(GrupoEquipoC.class)
                .where(GrupoEquipoC_Table.key.in(grupoEquipoIdList))
                .and(GrupoEquipoC_Table.syncFlag.in(GrupoEquipoC.FLAG_ADDED, GrupoEquipoC.FLAG_UPDATED))
                .queryList();

        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setGrupo_equipo(grupoEquipoCList);
        beDatosEnvioGrupo.setEquipo(equipoCList);
        beDatosEnvioGrupo.setEquipo_integrante(equipoIntegranteCS);
        return beDatosEnvioGrupo;
    }

    private BEDatosEnvioGrupo getGrupo(List<String> grupoEqupoIdList){

        List<GrupoEquipoC> grupoEquipoCList = SQLite.select()
                .from(GrupoEquipoC.class)
                .where(GrupoEquipoC_Table.key.in(grupoEqupoIdList))
                .and(GrupoEquipoC_Table.syncFlag.in(GrupoEquipoC.FLAG_ADDED, GrupoEquipoC.FLAG_UPDATED))
                .queryList();


        List<EquipoC> equipoCList = SQLite.select()
                .from(EquipoC.class)
                .where(EquipoC_Table.grupoEquipoId.in(grupoEqupoIdList))
                .and(EquipoC_Table.syncFlag.in(EquipoC.FLAG_ADDED, EquipoC.FLAG_UPDATED))
                .queryList();

        List<String> equiposIdList = new ArrayList<>();
        for (EquipoC equipoC: equipoCList)equiposIdList.add(equipoC.getKey());

        List<EquipoIntegranteC> equipoIntegranteCS = SQLite.select()
                .from(EquipoIntegranteC.class)
                .where(EquipoIntegranteC_Table.equipoId.in(equiposIdList))
                .and(EquipoIntegranteC_Table.syncFlag.in(EquipoIntegranteC.FLAG_ADDED, EquipoIntegranteC.FLAG_UPDATED))
                .queryList();


        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setGrupo_equipo(grupoEquipoCList);
        beDatosEnvioGrupo.setEquipo(equipoCList);
        beDatosEnvioGrupo.setEquipo_integrante(equipoIntegranteCS);

        return beDatosEnvioGrupo;
    }

    private com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos getCaso(List<String> casoIdList){
        com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos beDatosCasos = new com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos();

        List<Caso> casoList = SQLite.select()
                .from(Caso.class)
                .where(Caso_Table.key.in(casoIdList))
                .queryList();

        beDatosCasos.setCaso(casoList);

        List<CasoArchivo> casoArchivos = ConsultaUtils.getChangeItemsTableChild(CasoArchivo.class, CasoArchivo_Table.casoId.in(casoIdList));
        for (CasoArchivo archivo : casoArchivos){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
            }
        }
        beDatosCasos.setCasoArchivo(casoArchivos);
        beDatosCasos.setCasoReporte(ConsultaUtils.getChangeItemsTableChild(CasoReporte.class, CasoReporte_Table.casoId.in(casoIdList)));
        return beDatosCasos;
    }


    @Override
    public void updateTimeSesionData(ActualizarUi actualizarUi) {
        SessionData2 sessionData2 = SQLite.select()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(actualizarUi.getId()))
                .querySingle();
        if(sessionData2!=null){
            sessionData2.setFecha(new Date().getTime());
            sessionData2.save();
        }else {
            SessionData2 nuevo = new SessionData2();
            nuevo.setId(actualizarUi.getId());
            nuevo.setCalendarioPeriodoId(actualizarUi.getCalendarioPeriodoId());
            nuevo.setCargaCursoId(actualizarUi.getCargacursoId());
            nuevo.setFecha(new Date().getTime());
            nuevo.save();
        }
    }

    @Override
    public List<ServiceEnvioUi> getDataForSynck(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaAcademicoId) {
        List<ServiceEnvioUi> serviceEnvioUiList = new ArrayList<>();
        serviceEnvioUiList.addAll(getGrupoEnviar(cargaCursoId));
        serviceEnvioUiList.addAll(getCasosEnviar(cargaCursoId));
        serviceEnvioUiList.addAll(getSesionesEnviar(cargaCursoId, anioAcademicoId));
        serviceEnvioUiList.addAll(getTareaEnviar(cargaCursoId, anioAcademicoId, silaboEventoId));
        serviceEnvioUiList.addAll(getResultadoEnvio(silaboEventoId));
        serviceEnvioUiList.addAll(getRubroEnviar(silaboEventoId));
        serviceEnvioUiList.addAll(getcerrarCursoEnviarUi());
        return serviceEnvioUiList;
    }

    @Override
    public List<CalendarioPeriodoUi> getListCalendarioAcademico(int anioAcademicoId, int programaEducativoId) {
        List<CalendarioPeriodo> calendarioPeriodos =  SQLite.select()
                .from(CalendarioPeriodo.class)
                .innerJoin(CalendarioAcademico.class)
                .on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable().eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                .where(CalendarioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .and(CalendarioAcademico_Table.programaEduId.withTable().eq(programaEducativoId))
                .queryList();

        Collections.sort(calendarioPeriodos, new Comparator<CalendarioPeriodo>() {
            public int compare(CalendarioPeriodo o1, CalendarioPeriodo o2) {
                return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
            }
        });

        List<CalendarioPeriodoUi> calendarioPeriodoUiList = new ArrayList<>();
        for (CalendarioPeriodo calendarioPeriodo : calendarioPeriodos){
            Tipos tipos = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                    .querySingle();

            CalendarioPeriodoUi calendarioPeriodoUi = new CalendarioPeriodoUi();
            calendarioPeriodoUi.setCalendarioId(calendarioPeriodo.getCalendarioPeriodoId());
            calendarioPeriodoUi.setNombre(tipos!=null?tipos.getNombre():"desconocido");
            calendarioPeriodoUi.setFechaFin(calendarioPeriodo.getFechaFin());
            calendarioPeriodoUi.setFechaInicio(calendarioPeriodo.getFechaInicio());
            calendarioPeriodoUiList.add(calendarioPeriodoUi);


        }
        return calendarioPeriodoUiList;
    }

    @Override
    public CalendarioPeriodoUi getCalendarioPeriodo(int calendarioPeriodoId) {
        CalendarioPeriodoUi calendarioPeriodoUi = new CalendarioPeriodoUi();
        Tipos calendarioPeriodo = SQLite.select()
                .from(Tipos.class)
                .innerJoin(CalendarioPeriodo.class)
                .on(Tipos_Table.tipoId.withTable()
                        .eq(CalendarioPeriodo_Table.tipoId.withTable()))
                .where(CalendarioPeriodo_Table.calendarioPeriodoId.withTable()
                        .eq(calendarioPeriodoId))
                .querySingle();

        if(calendarioPeriodo!=null){
            calendarioPeriodoUi.setCalendarioId(calendarioPeriodoId);
            calendarioPeriodoUi.setNombre(calendarioPeriodo.getNombre());
        }
        return calendarioPeriodoUi;
    }

    @Override
    public boolean isfirstTimeHere(int cargaCursoId, int calendarioPeridoId) {
        long countSessionData =  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.cargaCursoId.eq(cargaCursoId))
                .and(SessionData2_Table.calendarioPeriodoId.eq(calendarioPeridoId))
                .count();
        return countSessionData == 0;
    }

    @Override
    public long getTimeSesionData(ActualizarUi actualizarUi) {
        long fecha = 0;
        SessionData2 sessionData2 = SQLite.select()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(actualizarUi.getId()))
                .querySingle();
        if(sessionData2!=null){
            fecha = sessionData2.getFecha();
        }
        return fecha;
    }

    @Override
    public RetrofitCancel getDatosUnidades(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {

        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        silaboEventoIdList.add(actualizarUi.getSilaboEventoId());

        Call<RestApiResponse<BEDatosUnidades>> responseCall = apiRetrofit.flst_getDatosUnidades(silaboEventoIdList, actualizarUi.getCalendarioPeriodoId());

        RetrofitCancel<BEDatosUnidades> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosUnidades>() {
            @Override
            public void onResponse(final BEDatosUnidades response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Integer> unidadAprendizajeIdList = new ArrayList<>();
                            List<Integer> relUnidadCompetenciaDesempenioIcdIdList = new ArrayList<>();
                            List<Integer> sesionAprendizajeIdList = new ArrayList<>();
                            List<Integer> sesionCompetenciaDesempenioIcdIdList = new ArrayList<>();
                            List<Integer> actividadesIdList = new ArrayList<>();
                            List<String> recursoDidacticoIdList = new ArrayList<>();

                            //region Unidades
                            List<UnidadAprendizaje> unidadAprendizajeList = SQLite.select()
                                    .from(UnidadAprendizaje.class)
                                    .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                                    .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()
                                            .eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()))
                                    .innerJoin(CalendarioPeriodo.class)
                                    .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()
                                            .eq(CalendarioPeriodo_Table.tipoId.withTable()))
                                    .where(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(actualizarUi.getSilaboEventoId()))
                                    .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(actualizarUi.getCalendarioPeriodoId()))
                                    .queryList();

                            for (UnidadAprendizaje unidadAprendizaje : unidadAprendizajeList){
                                unidadAprendizajeIdList.add(unidadAprendizaje.getUnidadAprendizajeId());
                            }

                            List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> relUnidadEventoCompetenciaDesempenioIcdList = SQLite.select()
                                    .from(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                                    .innerJoin(CompetenciaUnidad.class)
                                    .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()
                                            .eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                                    .where(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()
                                            .in(unidadAprendizajeIdList))
                                    .queryList();

                            for (T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD unidadCompetenciaDesempenioIcd : relUnidadEventoCompetenciaDesempenioIcdList){
                                relUnidadCompetenciaDesempenioIcdIdList.add(unidadCompetenciaDesempenioIcd.getUnidadCompetenciaDesempenioIcdId());
                            }
                            //endregion

                            //region Sesion Aprendizaje
                            List<SesionAprendizaje> sesionAprendizajeList = SQLite.select()
                                    .from(SesionAprendizaje.class)
                                    .where(SesionAprendizaje_Table.unidadAprendizajeId.in(unidadAprendizajeIdList))
                                    .queryList();
                            for (SesionAprendizaje sesionAprendizaje : sesionAprendizajeList){
                                sesionAprendizajeIdList.add(sesionAprendizaje.getSesionAprendizajeId());
                            }

                            List<SesionEventoCompetenciaDesempenioIcd> sesionEventoCompetenciaDesempenioIcdList = SQLite.select()
                                    .from(SesionEventoCompetenciaDesempenioIcd.class)
                                    .where(SesionEventoCompetenciaDesempenioIcd_Table.sesionAprendizajeId.in(sesionAprendizajeIdList))
                                    .queryList();

                            for (SesionEventoCompetenciaDesempenioIcd sesionEventoCompetenciaDesempenioIcd: sesionEventoCompetenciaDesempenioIcdList){
                                sesionCompetenciaDesempenioIcdIdList.add(sesionEventoCompetenciaDesempenioIcd.getSesionCompetenciaDesempenioIcdId());
                            }
                            //endregion

                            List<ActividadAprendizaje> actividadAprendizajeList = SQLite.select()
                                    .from(ActividadAprendizaje.class)
                                    .where(ActividadAprendizaje_Table.sesionAprendizajeId.in(sesionAprendizajeIdList))
                                    .queryList();

                            for (ActividadAprendizaje actividadAprendizaje : actividadAprendizajeList){
                                actividadesIdList.add(actividadAprendizaje.getActividadAprendizajeId());
                            }

                            //region recurso referencia
                            List<RecursoReferenciaC> recursoReferenciaCList = SQLite.select()
                                    .from(RecursoReferenciaC.class)
                                    .where(RecursoReferenciaC_Table.sesionAprendizajeId.in(sesionAprendizajeIdList))
                                    .queryList();

                            recursoReferenciaCList.addAll(SQLite.select()
                                    .from(RecursoReferenciaC.class)
                                    .where(RecursoReferenciaC_Table.unidadAprendizajeId.in(unidadAprendizajeIdList))
                                    .queryList());

                            recursoReferenciaCList.addAll(SQLite.select()
                                    .from(RecursoReferenciaC.class)
                                    .where(RecursoReferenciaC_Table.actividadAprendizajeId.in(actividadesIdList))
                                    .queryList());

                            for (RecursoReferenciaC recursoReferenciaC : recursoReferenciaCList){
                                recursoDidacticoIdList.add(recursoReferenciaC.getRecursoDidacticoId());
                            }
                            //endregion


                            List<Integer> productoEventoIdList = new ArrayList<>();
                            List<ProductoEventoReferencia> productoEventoReferencias = SQLite.select()
                                    .from(ProductoEventoReferencia.class)
                                    .where(ProductoEventoReferencia_Table.unidadAprendizajeId.in(unidadAprendizajeIdList))
                                    .queryList();

                            productoEventoReferencias.addAll(SQLite.select()
                                    .from(ProductoEventoReferencia.class)
                                    .where(ProductoEventoReferencia_Table.sesionAprendizajeId.in(sesionAprendizajeIdList))
                                    .queryList());

                            for (ProductoEventoReferencia productoEventoReferencia : productoEventoReferencias)productoEventoIdList.add(productoEventoReferencia.getProductoAprendizajeId());


                            TransaccionUtils.deleteTable(UnidadAprendizaje.class, UnidadAprendizaje_Table.unidadAprendizajeId.in(unidadAprendizajeIdList));
                            TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.in(unidadAprendizajeIdList));
                            TransaccionUtils.deleteTable(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(unidadAprendizajeIdList));
                            TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.in(relUnidadCompetenciaDesempenioIcdIdList));
                            TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.in(relUnidadCompetenciaDesempenioIcdIdList));

                            TransaccionUtils.deleteTable(SesionAprendizaje.class, SesionAprendizaje_Table.sesionAprendizajeId.in(sesionAprendizajeIdList));
                            TransaccionUtils.deleteTable(SesionEventoCompetenciaDesempenioIcd.class, SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaDesempenioIcdId.in(sesionCompetenciaDesempenioIcdIdList));
                            TransaccionUtils.deleteTable(SesionEventoDesempenioIcdCampotematico.class, SesionEventoDesempenioIcdCampotematico_Table.sesionCompetenciaDesempenioIcdId.in(sesionCompetenciaDesempenioIcdIdList));

                            TransaccionUtils.deleteTable(ActividadAprendizaje.class, ActividadAprendizaje_Table.actividadAprendizajeId.in(actividadesIdList));

                            TransaccionUtils.deleteTable(RecursoReferenciaC.class, RecursoReferenciaC_Table.sesionAprendizajeId.in(sesionAprendizajeIdList));
                            TransaccionUtils.deleteTable(RecursoReferenciaC.class, RecursoReferenciaC_Table.actividadAprendizajeId.in(actividadesIdList));
                            TransaccionUtils.deleteTable(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recursoDidacticoIdList));
                            TransaccionUtils.deleteTable(ProductoEventoReferencia.class, ProductoEventoReferencia_Table.sesionAprendizajeId.in(sesionAprendizajeIdList));
                            TransaccionUtils.deleteTable(ProductoEventoReferencia.class, ProductoEventoReferencia_Table.unidadAprendizajeId.in(unidadAprendizajeIdList));
                            TransaccionUtils.deleteTable(ProductoEventoCampoTematico.class, ProductoEventoCampoTematico_Table.productoAprendizaje.in(productoEventoIdList));
                            TransaccionUtils.deleteTable(UnidadEventoCompetenciaDesempenioIcdInstrumento.class, UnidadEventoCompetenciaDesempenioIcdInstrumento_Table.unidadCompetenciaDesempenioIcdId.in(relUnidadCompetenciaDesempenioIcdIdList));

                            TransaccionUtils.fastStoreListInsert(CampoTematico.class, response.getCampoTematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Competencia.class, response.getCompetencia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, response.getRelCompetenciaUnidad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Desempenio.class, response.getDesempenio(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DesempenioIcd.class, response.getRelDesempenioIcd(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Icds.class, response.getIcds(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, response.getRelSesionEventoCompetenciaDesempenioicd(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, response.getSesionDesempenioIcdCampotematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, response.getRelCompetenciaSesion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, response.getRelUnidadAprenEvento_tipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, response.getRelUnidadEventoCompetenciaDesempenioIcd(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, response.getRelUnidadEventoCompetenciaDesempenioIcdCampoTematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, response.getUnidadAprendizaje(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, response.getSesion(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, response.getRelRecursoReferencia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, response.getRecursoDidactico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, response.getRelRecursoArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Archivo.class, response.getArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ActividadAprendizaje.class, response.getActividad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(ProductoEventoReferencia.class, response.getRelProductoReferencia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ProductoAprendizajeEvento.class, response.getProducto(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ProductoEventoCampoTematico.class, response.getRelProductoEventoCampoTematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, response.getInstrumentoEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(UnidadEventoCompetenciaDesempenioIcdInstrumento.class, response.getUnidadCompetenciaDesempenioIcdInstrumento(), databaseWrapper, true);




                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();


                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosUnidades(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());


        List<Integer> silaboEventoIdList = new ArrayList<>();
        for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Unidades){
                silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
            }

            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosUnidades>> responseCall = apiRetrofit.flst_getDatosUnidades(silaboEventoIdList, programaEducativoUi.getCalendarioPeriodoId());

        RetrofitCancel<BEDatosUnidades> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosUnidades>() {
            @Override
            public void onResponse(final BEDatosUnidades response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            TransaccionUtils.fastStoreListInsert(CampoTematico.class, response.getCampoTematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Competencia.class, response.getCompetencia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, response.getRelCompetenciaUnidad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Desempenio.class, response.getDesempenio(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DesempenioIcd.class, response.getRelDesempenioIcd(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Icds.class, response.getIcds(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, response.getRelSesionEventoCompetenciaDesempenioicd(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, response.getSesionDesempenioIcdCampotematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, response.getRelCompetenciaSesion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, response.getRelUnidadAprenEvento_tipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, response.getRelUnidadEventoCompetenciaDesempenioIcd(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, response.getRelUnidadEventoCompetenciaDesempenioIcdCampoTematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, response.getUnidadAprendizaje(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, response.getSesion(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, response.getRelRecursoReferencia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, response.getRecursoDidactico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, response.getRelRecursoArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Archivo.class, response.getArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ActividadAprendizaje.class, response.getActividad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(ProductoEventoReferencia.class, response.getRelProductoReferencia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ProductoAprendizajeEvento.class, response.getProducto(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ProductoEventoCampoTematico.class, response.getRelProductoEventoCampoTematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, response.getInstrumentoEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(UnidadEventoCompetenciaDesempenioIcdInstrumento.class, response.getUnidadCompetenciaDesempenioIcdInstrumento(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Unidades){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(programaEducativoUi.getFecha());
                                }
                            }

                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Unidades){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();


                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Unidades){
                        actualizarUi.setSuccess(-1);
                    }
                }
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosTipoNota(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        Call<RestApiResponse<BEDatosTipoNota>> responseCall;
        if(actualizarUi.getFecha()==0){
            responseCall = apiRetrofit.flst_getDatosTipoNotaInicial(actualizarUi.getProgramaEducativoId(), actualizarUi.getUsuarioId());
        }else {
            responseCall = apiRetrofit.flst_getDatosTipoNotaInicial(actualizarUi.getProgramaEducativoId(), actualizarUi.getUsuarioId());
        }

        RetrofitCancel<BEDatosTipoNota> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosTipoNota>() {
            @Override
            public void onResponse(final BEDatosTipoNota response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {


                            SQLite.delete()
                                    .from(RelProgramaEducativoTipoNota.class)
                                    .where(RelProgramaEducativoTipoNota_Table.programaEducativoId.in(actualizarUi.getProgramaEducativoId()))
                                    .execute();

                            TransaccionUtils.fastStoreListInsert(TipoNotaC.class, response.getTipoNota(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ValorTipoNotaC.class, response.getValorTipoNota(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RelProgramaEducativoTipoNota.class, response.getRelProgramaEducativoTipoNota(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, response.getEscalaEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, response.getTipoEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosTipoNota(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        ActualizarUi actualizarTipoNota = null;
        for (ActualizarUi itemActualizarUi : programaEducativoUi.getActualizarUiList()){
            if(/*itemActualizarUi.isEncoloa()&&*/itemActualizarUi.getTipo()== ActualizarTipoUi.TipoNota){
                actualizarTipoNota = itemActualizarUi;
                break;
            }
        }

        if(actualizarTipoNota!=null){
            Call<RestApiResponse<BEDatosTipoNota>> responseCall = apiRetrofit.flst_getDatosTipoNotaInicial(actualizarTipoNota.getProgramaEducativoId(), actualizarTipoNota.getUsuarioId());

            RetrofitCancel<BEDatosTipoNota> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
            retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosTipoNota>() {
                @Override
                public void onResponse(final BEDatosTipoNota response) {
                    if(response == null){
                        callBackSucces.onLoad(false, programaEducativoUi);
                        Log.d(TAG,"response usuario null");
                    }else {
                        Log.d(TAG,"response usuario true");

                        programaEducativoUi.setFecha(response.getFecha_servidor());

                        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                            @Override
                            public void execute(DatabaseWrapper databaseWrapper) {
                                TransaccionUtils.fastStoreListInsert(TipoNotaC.class, response.getTipoNota(), databaseWrapper, true);
                                TransaccionUtils.fastStoreListInsert(ValorTipoNotaC.class, response.getValorTipoNota(), databaseWrapper, true);
                                TransaccionUtils.fastStoreListInsert(RelProgramaEducativoTipoNota.class, response.getRelProgramaEducativoTipoNota(), databaseWrapper, true);
                                TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, response.getEscalaEvaluacion(), databaseWrapper, true);
                                TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, response.getTipoEvaluacion(), databaseWrapper, true);
                                TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);

                            }
                        }).success(new Transaction.Success() {
                            @Override
                            public void onSuccess(@NonNull Transaction transaction) {
                                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.TipoNota){
                                        actualizarUi.setSuccess(1);
                                        actualizarUi.setFecha(programaEducativoUi.getFecha());
                                    }
                                }
                                callBackSucces.onLoad(true, programaEducativoUi);
                            }
                        }).error(new Transaction.Error() {
                            @Override
                            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.TipoNota){
                                        actualizarUi.setSuccess(-1);
                                    }
                                }
                                error.printStackTrace();
                                Log.d(TAG,"response save false");
                                callBackSucces.onLoad(false, programaEducativoUi);
                            }
                        }).build();

                        transaction.execute();

                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                        if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.TipoNota){
                            actualizarUi.setSuccess(-1);
                        }
                    }
                    ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                    callBackSucces.onLoad(false, programaEducativoUi);
                    t.printStackTrace();
                    Log.d(TAG,"onFailure ");
                }
            });

            setupListener( callBackSucces);

            return retrofitCancel;
        }else {
            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.TipoNota){
                    actualizarUi.setSuccess(0);
                }
            }
            callBackSucces.onLoad(true, programaEducativoUi);
            return  null;
        }

    }

    @Override
    public RetrofitCancel getDatosEstudiantes(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> cargaCursoIdList = new ArrayList<>();
        cargaCursoIdList.add(actualizarUi.getCargacursoId());

        Call<RestApiResponse<BEDatosEstudiante>> responseCall = apiRetrofit.flst_getDatosEstudiante( cargaCursoIdList, actualizarUi.getDocenteId());

        RetrofitCancel<BEDatosEstudiante> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosEstudiante>() {
            @Override
            public void onResponse(final BEDatosEstudiante response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Integer> cargaCursoDocenteIdList = new ArrayList<>();
                            List<CargaCursoDocente> cargaCursoDocenteList = SQLite.select()
                                    .from(CargaCursoDocente.class)
                                    .where(CargaCursoDocente_Table.cargaCursoId.eq(actualizarUi.getCargacursoId()))
                                    .queryList();

                            for (CargaCursoDocente cargaCursoDocente : cargaCursoDocenteList){
                                cargaCursoDocenteIdList.add(cargaCursoDocente.getCargaCursoDocenteId());
                            }

                            TransaccionUtils.deleteTable(CargaCursoDocenteDet.class, CargaCursoDocenteDet_Table.cargaCursoDocenteId.in(cargaCursoDocenteIdList));
                            TransaccionUtils.deleteTable(DetalleContratoAcad.class, DetalleContratoAcad_Table.cargaCursoId.eq(actualizarUi.getCargacursoId()));

                            TransaccionUtils.fastStoreListInsert(Persona.class, response.getPersonas(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Relaciones.class, response.getRelaciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Usuario.class, response.getUsuarios(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Contrato.class, response.getContratos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, response.getDetalleContratoAcad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, response.getCargaCursoDocenteDet(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosEstudiantes(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> cargaCursoIdList = new ArrayList<>();
        for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Estudiantes){
                cargaCursoIdList.add(actualizarUi.getCargacursoId());
            }

            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosEstudiante>> responseCall = apiRetrofit.flst_getDatosEstudiante(cargaCursoIdList, programaEducativoUi.getDocenteId());


        RetrofitCancel<BEDatosEstudiante> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosEstudiante>() {
            @Override
            public void onResponse(final BEDatosEstudiante response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(Persona.class, response.getPersonas(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Relaciones.class, response.getRelaciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Usuario.class, response.getUsuarios(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Contrato.class, response.getContratos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, response.getDetalleContratoAcad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, response.getCargaCursoDocenteDet(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Estudiantes){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(programaEducativoUi.getFecha());
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Estudiantes){
                                    actualizarUi.setSuccess(-1);
                                    actualizarUi.setFecha(programaEducativoUi.getFecha());
                                }
                            }
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Estudiantes){
                        actualizarUi.setSuccess(-1);
                        actualizarUi.setFecha(programaEducativoUi.getFecha());
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosResultado(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
        Call<RestApiResponse<BEDatosResultado>> responseCall = apiRetrofit.flst_getDatosResultado(silaboEventoIdList, actualizarUi.getCalendarioPeriodoId());


        RetrofitCancel<BEDatosResultado> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosResultado>() {
            @Override
            public void onResponse(final BEDatosResultado response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Integer> rubroResultadoIdList = new ArrayList<>();
                            List<RubroEvaluacionResultado> rubroEvaluacionResultadoList = SQLite.select()
                                    .from(RubroEvaluacionResultado.class)
                                    .where(RubroEvaluacionResultado_Table.silaboEventoId.eq(actualizarUi.getSilaboEventoId()))
                                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.eq(actualizarUi.getCalendarioPeriodoId()))
                                    .queryList();

                            for(RubroEvaluacionResultado rubroEvaluacionResultado: rubroEvaluacionResultadoList){
                                rubroResultadoIdList.add(rubroEvaluacionResultado.getRubroEvalResultadoId());
                            }

                            TransaccionUtils.deleteTable(RubroEvaluacionResultado.class, RubroEvaluacionResultado_Table.rubroEvalResultadoId.in(rubroResultadoIdList));
                            TransaccionUtils.deleteTable(EvaluacionResultadoC.class, EvaluacionResultadoC_Table.rubroEvalResultadoId.in(rubroResultadoIdList));
                            TransaccionUtils.deleteTable(CriterioRubroEvalResultadoC.class, CriterioRubroEvalResultadoC_Table.rubroEvalResultadoId.in(rubroResultadoIdList));
                            TransaccionUtils.deleteTable(RubroEvalRNRFormula.class, RubroEvalRNRFormula_Table.rubroEvaluacionPrinId.in(rubroResultadoIdList));

                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionResultado.class, response.getRubroEvaluacionResultado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EvaluacionResultadoC.class, response.getEvaluacionResultado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CriterioRubroEvalResultadoC.class, response.getCriterioRubroEvaluacionResultado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvalRNRFormula.class, response.getRubroEvalResultadoRnrFormula(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosResultado(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Resultado){
                silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
            }

            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosResultado>> responseCall = apiRetrofit.flst_getDatosResultado(silaboEventoIdList, programaEducativoUi.getCalendarioPeriodoId());

        RetrofitCancel<BEDatosResultado> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosResultado>() {
            @Override
            public void onResponse(final BEDatosResultado response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionResultado.class, response.getRubroEvaluacionResultado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EvaluacionResultadoC.class, response.getEvaluacionResultado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CriterioRubroEvalResultadoC.class, response.getCriterioRubroEvaluacionResultado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvalRNRFormula.class, response.getRubroEvalResultadoRnrFormula(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Resultado){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(response.getFecha_servidor());
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Resultado){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Resultado){
                        actualizarUi.setSuccess(-1);
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosRubro(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
        Call<RestApiResponse<BEDatosRubro>> responseCall = apiRetrofit.flst_getDatosRubro(silaboEventoIdList, actualizarUi.getCalendarioPeriodoId());

        RetrofitCancel<BEDatosRubro> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosRubro>() {
            @Override
            public void onResponse(final BEDatosRubro response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<String> rubroEvaluacionProcesoIdList = new ArrayList<>();
                            List<String> rubroEquipoIdList = new ArrayList<>();
                            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = SQLite.select()
                                    .from(RubroEvaluacionProcesoC.class)
                                    .where(RubroEvaluacionProcesoC_Table.silaboEventoId.eq(actualizarUi.getSilaboEventoId()))
                                    .and(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.eq(actualizarUi.getCalendarioPeriodoId()))
                                    .queryList();

                            for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC : rubroEvaluacionProcesoCList){
                                rubroEvaluacionProcesoIdList.add(rubroEvaluacionProcesoC.getKey());
                            }

                            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> tRnMaeRubroEvaluacionProcesoEquipocList = SQLite.select()
                                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoIdList))
                                    .queryList();

                            for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC rubroEvaluacionProcesoEquipoc : tRnMaeRubroEvaluacionProcesoEquipocList){
                                rubroEquipoIdList.add(rubroEvaluacionProcesoEquipoc.getKey());
                            }

                            List<String> evaluacionProcesoIdList = new ArrayList<>();
                            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                                    .from(EvaluacionProcesoC.class)
                                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoIdList))
                                    .queryList();
                            for (EvaluacionProcesoC evaluacionProcesoC : evaluacionProcesoCList){
                                evaluacionProcesoIdList.add(evaluacionProcesoC.getKey());
                            }

                            TransaccionUtils.deleteTable(RubroEvaluacionProcesoC.class, RubroEvaluacionProcesoC_Table.key.in(rubroEvaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(EvaluacionProcesoC.class, EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(RubroEvalRNPFormulaC.class, RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(rubroEvaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.in(rubroEquipoIdList));
                            TransaccionUtils.deleteTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(rubroEquipoIdList));
                            TransaccionUtils.deleteTable(EquipoEvaluacionProcesoC.class, EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(CriterioRubroEvaluacionC.class, CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(RubroEvaluacionProcesoCampotematicoC.class, RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(ArchivosRubroProceso.class, ArchivosRubroProceso_Table.evaluacionProcesoId.in(evaluacionProcesoIdList));
                            TransaccionUtils.deleteTable(RubroEvaluacionProcesoComentario.class, RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.in(evaluacionProcesoIdList));

                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoC.class, response.getRubroEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvalRNPFormulaC.class, response.getRelRubroEvaluacionRNPFormula(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, response.getEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, response.getRubroEvaluacionProcesoEquipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, response.getRubroEvaluacionProcesoIntegrante(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EquipoEvaluacionProcesoC.class, response.getEquipoEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CriterioRubroEvaluacionC.class, response.getCriterioRubroEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoCampotematicoC.class, response.getRubroEvaluacionProcesoCampotematico(), databaseWrapper, true);
                            //TransaccionUtils.fastStoreListInsert(ComentarioPredeterminado.class, response.getComentarioPredeterminado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ArchivosRubroProceso.class, response.getArchivoRubroProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoComentario.class, response.getRubroEvaluacionProcesoComentario(), databaseWrapper, true);
                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosRubro(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Rubros){
                silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
            }

            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosRubro>> responseCall = apiRetrofit.flst_getDatosRubro(silaboEventoIdList, programaEducativoUi.getCalendarioPeriodoId());


        RetrofitCancel<BEDatosRubro> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosRubro>() {
            @Override
            public void onResponse(final BEDatosRubro response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoC.class, response.getRubroEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvalRNPFormulaC.class, response.getRelRubroEvaluacionRNPFormula(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, response.getEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, response.getRubroEvaluacionProcesoEquipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, response.getRubroEvaluacionProcesoIntegrante(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EquipoEvaluacionProcesoC.class, response.getEquipoEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CriterioRubroEvaluacionC.class, response.getCriterioRubroEvaluacionProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoCampotematicoC.class, response.getRubroEvaluacionProcesoCampotematico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ComentarioPredeterminado.class, response.getComentarioPredeterminado(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ArchivosRubroProceso.class, response.getArchivoRubroProceso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoComentario.class, response.getRubroEvaluacionProcesoComentario(), databaseWrapper, true);
                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Rubros){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(response.getFecha_servidor());
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Rubros){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Rubros){
                        actualizarUi.setSuccess(-1);
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosGrupo(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> cargaCursoIdList = new ArrayList<>();
        cargaCursoIdList.add(actualizarUi.getCargacursoId());
        Call<RestApiResponse<BEDatosGrupo>> responseCall = apiRetrofit.flst_getDatosGrupo(cargaCursoIdList);


        RetrofitCancel<BEDatosGrupo> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosGrupo>() {
            @Override
            public void onResponse(final BEDatosGrupo response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<String> equipoIdList = new ArrayList<>();
                            List<EquipoC> equipoCList = SQLite.select()
                                    .from(EquipoC.class)
                                    .innerJoin(GrupoEquipoC.class)
                                    .on(GrupoEquipoC_Table.key.withTable()
                                            .eq(EquipoC_Table.grupoEquipoId.withTable()))
                                    .where(GrupoEquipoC_Table.cargaCursoId.eq(actualizarUi.getCargacursoId()))
                                    .queryList();

                            for (EquipoC equipoC : equipoCList){
                                equipoIdList.add(equipoC.getKey());
                            }

                            TransaccionUtils.deleteTable(GrupoEquipoC.class, GrupoEquipoC_Table.cargaCursoId.eq(actualizarUi.getCargacursoId()));
                            TransaccionUtils.deleteTable(EquipoC.class, EquipoC_Table.key.in(equipoIdList));
                            TransaccionUtils.deleteTable(EquipoIntegranteC.class, EquipoIntegranteC_Table.equipoId.in(equipoIdList));

                            TransaccionUtils.fastStoreListInsert(EquipoC.class, response.getEquipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(GrupoEquipoC.class, response.getGrupoEquipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EquipoIntegranteC.class, response.getEquipoIntegrante(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosGrupo(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> cargaCursoIdList = new ArrayList<>();
        for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Grupos){
                cargaCursoIdList.add(actualizarUi.getCargacursoId());
            }
            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosGrupo>> responseCall = apiRetrofit.flst_getDatosGrupo(cargaCursoIdList);


        RetrofitCancel<BEDatosGrupo> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosGrupo>() {
            @Override
            public void onResponse(final BEDatosGrupo response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(EquipoC.class, response.getEquipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(GrupoEquipoC.class, response.getGrupoEquipo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EquipoIntegranteC.class, response.getEquipoIntegrante(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Grupos){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(response.getFecha_servidor());
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Grupos){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Grupos){
                        actualizarUi.setSuccess(-1);
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosTarea(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
        Call<RestApiResponse<BEDatosTarea>> responseCall = apiRetrofit.flst_getDatosTarea(silaboEventoIdList, actualizarUi.getCalendarioPeriodoId());


        RetrofitCancel<BEDatosTarea> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosTarea>() {
            @Override
            public void onResponse(final BEDatosTarea response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Integer> unidadAprendizajeIdList = new ArrayList<>();
                            List<Integer> sesionAprendizajeIdList = new ArrayList<>();
                            List<String> tareaIdList = new ArrayList<>();
                            List<String> recursoIdList = new ArrayList<>();

                            List<UnidadAprendizaje> unidadAprendizajeList = SQLite.select(Utils.f_allcolumnTable(UnidadAprendizaje_Table.ALL_COLUMN_PROPERTIES))
                                    .from(UnidadAprendizaje.class)
                                    .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                                    .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()
                                            .eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()))
                                    .innerJoin(CalendarioPeriodo.class)
                                    .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()
                                            .eq(CalendarioPeriodo_Table.tipoId.withTable()))
                                    .where(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(actualizarUi.getSilaboEventoId()))
                                    .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(actualizarUi.getCalendarioPeriodoId()))
                                    .queryList();


                            Log.d(TAG,"getSilaboEventoId " + actualizarUi.getSilaboEventoId());
                            Log.d(TAG,"getCalendarioPeriodoId " + actualizarUi.getCalendarioPeriodoId());
                            for(UnidadAprendizaje unidadAprendizaje: unidadAprendizajeList)unidadAprendizajeIdList.add(unidadAprendizaje.getUnidadAprendizajeId());

                            List<SesionAprendizaje> sesionAprendizajeList = SQLite.select()
                                    .from(SesionAprendizaje.class)
                                    .where(SesionAprendizaje_Table.unidadAprendizajeId.in(unidadAprendizajeIdList))
                                    .queryList();

                            for(SesionAprendizaje sesionAprendizaje: sesionAprendizajeList)sesionAprendizajeIdList.add(sesionAprendizaje.getSesionAprendizajeId());


                            List<TareasC> tareasCList = SQLite.select()
                                    .from(TareasC.class)
                                    .where(TareasC_Table.unidadAprendizajeId.in(unidadAprendizajeIdList))
                                    .queryList();

                            tareasCList.addAll( SQLite.select()
                                    .from(TareasC.class)
                                    .where(TareasC_Table.sesionAprendizajeId.in(sesionAprendizajeIdList))
                                    .queryList());

                            for (TareasC tareasC : tareasCList){
                                tareaIdList.add(tareasC.getKey());
                                //Log.d(TAG,"tareasC " + tareasC.getKey());
                            }

                            List<TareasRecursosC> tareasRecursosCList = SQLite.select()
                                    .from(TareasRecursosC.class)
                                    .where(TareasRecursosC_Table.tareaId.in(tareaIdList))
                                    .queryList();

                            for (TareasRecursosC tareasRecursosC : tareasRecursosCList){
                                recursoIdList.add(tareasRecursosC.getRecursoDidacticoId());
                                //Log.d(TAG,"tareasRecursosC " + tareasRecursosC.getTareaId());
                            }

                            TransaccionUtils.deleteTable(TareasC.class, TareasC_Table.key.in(tareaIdList));
                            TransaccionUtils.deleteTable(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaIdList));
                            TransaccionUtils.deleteTable(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recursoIdList));
                            TransaccionUtils.deleteTable(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.recursoDidacticoId.in(recursoIdList));
                            TransaccionUtils.deleteTable(TareaRubroEvaluacionProceso.class, TareaRubroEvaluacionProceso_Table.tareaId.in(tareaIdList));


                            TransaccionUtils.fastStoreListInsert(TareasC.class, response.getTarea(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, response.getTareasRecursos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, response.getRecursoArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, response.getRecursoDidactico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Archivo.class, response.getArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, response.getTareaRubroEvaluacion(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosTarea(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> silaboEventoIdList = new ArrayList<>();
        for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Tareas){
                silaboEventoIdList.add(actualizarUi.getSilaboEventoId());
            }

            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosTarea>> responseCall = apiRetrofit.flst_getDatosTarea(silaboEventoIdList, programaEducativoUi.getCalendarioPeriodoId());

        RetrofitCancel<BEDatosTarea> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosTarea>() {
            @Override
            public void onResponse(final BEDatosTarea response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(TareasC.class, response.getTarea(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, response.getTareasRecursos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, response.getRecursoArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, response.getRecursoDidactico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Archivo.class, response.getArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, response.getTareaRubroEvaluacion(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Tareas){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(response.getFecha_servidor());
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Tareas){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Tareas){
                        actualizarUi.setSuccess(-1);
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosCasos(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> cargaCursoIdList = new ArrayList<>();
        cargaCursoIdList.add(actualizarUi.getCargacursoId());
        Call<RestApiResponse<BEDatosCasos>> responseCall = apiRetrofit.flst_getDatosCasos(actualizarUi.getEntidadId(), actualizarUi.getGeoreferenciaId(), cargaCursoIdList, actualizarUi.getCalendarioPeriodoId());


        RetrofitCancel<BEDatosCasos> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosCasos>() {
            @Override
            public void onResponse(final BEDatosCasos response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<String> casoIdList = new ArrayList<>();

                            List<Caso> casoList = SQLite.select()
                                    .from(Caso.class)
                                    .where(Caso_Table.cargaCursoId.eq(actualizarUi.getCargacursoId()))
                                    .and(Caso_Table.calendarioPeriodoId.eq(actualizarUi.getCalendarioPeriodoId()))
                                    .queryList();

                            for (Caso caso : casoList) {
                                casoIdList.add(caso.getKey());
                            }

                            TransaccionUtils.deleteTable(Caso.class, Caso_Table.key.in(casoIdList));
                            TransaccionUtils.deleteTable(CasoArchivo.class, Caso_Table.casoId.in(casoIdList));
                            TransaccionUtils.deleteTable(CasoReporte.class, CasoReporte_Table.casoId.in(casoIdList));
                            TransaccionUtils.deleteTable(GeoRefOrganigrama.class, GeoRefOrganigrama_Table.geoReferenciaId.eq(actualizarUi.getGeoreferenciaId()));
                            TransaccionUtils.deleteTable(TipoEntidadGeo.class, TipoEntidadGeo_Table.comportamientoId.notEq(0));

                            TransaccionUtils.fastStoreListInsert(GeoRefOrganigrama.class, response.getGeoRefOrganigrama(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Caso.class, response.getCaso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CasoArchivo.class, response.getCasoArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CasoReporte.class, response.getCasoReporte(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Archivo.class, response.getArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Comportamiento.class, response.getComportamiento(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(TipoEntidadGeo.class, response.getRelTipoEntidadGeo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDatosCasos(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        List<Integer> cargaCursoIdList = new ArrayList<>();
        for(ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo() == ActualizarTipoUi.Casos){
                cargaCursoIdList.add(actualizarUi.getCargacursoId());
            }
            actualizarUi.setSuccess(0);
        }

        Call<RestApiResponse<BEDatosCasos>> responseCall = apiRetrofit.flst_getDatosCasos(programaEducativoUi.getEntidadId(), programaEducativoUi.getGeoreferenciaId(), cargaCursoIdList, programaEducativoUi.getCalendarioPeriodoId());


        RetrofitCancel<BEDatosCasos> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosCasos>() {
            @Override
            public void onResponse(final BEDatosCasos response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            TransaccionUtils.fastStoreListInsert(Caso.class, response.getCaso(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CasoArchivo.class, response.getCasoArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CasoReporte.class, response.getCasoReporte(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Archivo.class, response.getArchivo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Comportamiento.class, response.getComportamiento(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(TipoEntidadGeo.class, response.getRelTipoEntidadGeo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(GeoRefOrganigrama.class, response.getGeoRefOrganigrama(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Casos){
                                    actualizarUi.setFecha(response.getFecha_servidor());
                                    actualizarUi.setSuccess(1);
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Casos){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Casos){
                        actualizarUi.setSuccess(-1);
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }


    public void getEntidad(int entidadId, List<Integer> integerList){
        Entidad entidad = SQLite.select()
                .from(Entidad.class)
                .where(Entidad_Table.entidadId.eq(entidadId))
                .querySingle();

        if(entidad!=null){
            integerList.add(entidad.getEntidadId());
            getEntidad(entidad.getParentId(), integerList);
        }

    }


    @Override
    public RetrofitCancel getDimendinoDesarrollo(final ActualizarUi actualizarUi, final CallBackSucces<ActualizarUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        final List<Integer> cursoIdList = new ArrayList<>();
        cursoIdList.add(actualizarUi.getCursoId());
        List<Integer> cargaCursoIdList = new ArrayList<>();
        cargaCursoIdList.add(actualizarUi.getCargacursoId());
        Call<RestApiResponse<BEDimensionDesarrollo>> responseCall = apiRetrofit.flst_getDatosDimensionDesarollo(cursoIdList, cargaCursoIdList, actualizarUi.getGeoreferenciaId(), actualizarUi.getEntidadId(), actualizarUi.getDocenteId());

        RetrofitCancel<BEDimensionDesarrollo> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDimensionDesarrollo>() {
            @Override
            public void onResponse(final BEDimensionDesarrollo response) {
                if(response == null){
                    callBackSucces.onLoad(false, actualizarUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    actualizarUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {



                            List<DimensionDesarrolloDetalle> dimensionDesarrolloDetalleList = SQLite.select()
                                    .from(DimensionDesarrolloDetalle.class)
                                    .where(DimensionDesarrolloDetalle_Table.cursoId.in(cursoIdList))
                                    .queryList();
                            List<Integer> dimensionDesarolloIdList = new ArrayList<>();
                            for(DimensionDesarrolloDetalle dimensionDesarrolloDetalle : dimensionDesarrolloDetalleList)dimensionDesarolloIdList.add(dimensionDesarrolloDetalle.getDimensionDesarrolloId());

                            List<Integer> entidadIdList = new ArrayList<>();
                            getEntidad(actualizarUi.getEntidadId(), entidadIdList);

                            List<InstrumentoEvaluacion> instrumentoEvaluacionList = SQLite.select()
                                    .from(InstrumentoEvaluacion.class)
                                    .where(InstrumentoEvaluacion_Table.georeferenciaid.eq(actualizarUi.getGeoreferenciaId()))
                                    .queryList();

                            instrumentoEvaluacionList.addAll(SQLite.select()
                                    .from(InstrumentoEvaluacion.class)
                                    .where(InstrumentoEvaluacion_Table.entidadid.in(entidadIdList))
                                    .queryList());


                            List<Integer> intrumentoIdList = new ArrayList<>();
                            for(InstrumentoEvaluacion instrumentoEvaluacion : instrumentoEvaluacionList)intrumentoIdList.add(instrumentoEvaluacion.getInstrumentoevalid());


                            List<Persona> personaList = SQLite.select(
                                    Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable()))
                                    .from(Persona.class)
                                    .innerJoin(Contrato.class)
                                    .on(Persona_Table.personaId.withTable()
                                            .eq(Contrato_Table.personaId.withTable()))
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable()
                                            .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaCursoId.eq(actualizarUi.getCargacursoId()))
                                    .queryList();

                            personaList.addAll(SQLite.select(
                                    Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES, Contrato_Table.idContrato.withTable(), Contrato_Table.vigente.withTable()))
                                    .from(Persona.class)
                                    .innerJoin(CargaCursoDocenteDet.class)
                                    .on(Persona_Table.personaId.withTable().eq(CargaCursoDocenteDet_Table.alumnoId.withTable()))
                                    .innerJoin(CargaCursoDocente.class)
                                    .on(CargaCursoDocenteDet_Table.cargaCursoDocenteId.withTable().eq(CargaCursoDocente_Table.cargaCursoDocenteId.withTable()))
                                    .innerJoin(Contrato.class)
                                    .on(Contrato_Table.personaId.withTable().eq(Persona_Table.personaId.withTable()))
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable()
                                            .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(actualizarUi.getCargacursoId()))
                                    .and(DetalleContratoAcad_Table.cargaCursoId.withTable()
                                            .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                                    //  .and(Contrato_Table.vigente.withTable().eq(1))
                                    .and(CargaCursoDocente_Table.docenteId.withTable().eq(actualizarUi.getDocenteId()))
                                    .queryList());

                            List<Integer> personaIdList = new ArrayList<>();
                            for(Persona persona : personaList)personaIdList.add(persona.getPersonaId());

                            List<InstrumentoObservado> instrumentoObservadoList = SQLite.select()
                                    .from(InstrumentoObservado.class)
                                    .where(InstrumentoObservado_Table.personaId.in(personaIdList))
                                    .and(InstrumentoObservado_Table.instrumentoEvalId.in(intrumentoIdList))
                                    .queryList();

                            List<String> instrumentoObservadoIdList = new ArrayList<>();
                            for(InstrumentoObservado instrumentoObservado : instrumentoObservadoList)instrumentoObservadoIdList.add(instrumentoObservado.getInstrumentoObservadoId());

                            TransaccionUtils.deleteTable(InstrumentoObservado.class, InstrumentoObservado_Table.instrumentoObservadoId.in(instrumentoObservadoIdList));
                            TransaccionUtils.deleteTable(DimensionObservada.class, DimensionObservada_Table.instrumentoObservadaId.in(instrumentoObservadoIdList));
                            TransaccionUtils.deleteTable(DimensionDesarrolloDetalle.class, DimensionDesarrolloDetalle_Table.cursoId.in(cursoIdList));
                            TransaccionUtils.deleteTable(DimensionDesarrolloEstrategiaEvaluacionC.class, DimensionDesarrolloEstrategiaEvaluacionC_Table.dimensionDesarrolloId.in(dimensionDesarolloIdList));


                            TransaccionUtils.fastStoreListInsert(DimensionDesarrollo.class, response.getDimensionDesarrollo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DimensionDesarrolloDetalle.class, response.getDimensionDesarrolloDetalle(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EstrategiaEvaluacion.class, response.getEstrategiaEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DimensionDesarrolloEstrategiaEvaluacionC.class, response.getDimensionDesarrolloEstrategiaEval(), databaseWrapper, true);


                            TransaccionUtils.fastStoreListInsert(Dimension.class, response.getDimension(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DimensionObservada.class, response.getDimensionObservada(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, response.getInstrumentoEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(InstrumentoObservado.class, response.getInstrumentoObservado(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callBackSucces.onLoad(true, actualizarUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBackSucces.onLoad(false, actualizarUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, actualizarUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);


        return retrofitCancel;
    }

    @Override
    public RetrofitCancel getDimendinoDesarrollo(final ProgramaEducativoUi programaEducativoUi, final CallBackSucces<ProgramaEducativoUi> callBackSucces) {
        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());
        builder.connectTimeout(10, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        apiRetrofit.setOkHttpClient(builder.build());

        Set<Integer> cursoIdList = new LinkedHashSet<>();
        Set<Integer> cargaCursoIdList = new LinkedHashSet<>();
        for (ActualizarUi actualizarUi:programaEducativoUi.getActualizarUiList()){
            if(actualizarUi.isEncoloa()&&actualizarUi.getTipo() == ActualizarTipoUi.Dimencion_Desarrollo){
                cursoIdList.add(actualizarUi.getCursoId());
                cargaCursoIdList.add(actualizarUi.getCargacursoId());
            }
            actualizarUi.setSuccess(0);
        }
        Call<RestApiResponse<BEDimensionDesarrollo>> responseCall = apiRetrofit.flst_getDatosDimensionDesarollo(new ArrayList<Integer>(cursoIdList), new ArrayList<Integer>(cargaCursoIdList), programaEducativoUi.getGeoreferenciaId(), programaEducativoUi.getEntidadId(), programaEducativoUi.getDocenteId());

        RetrofitCancel<BEDimensionDesarrollo> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDimensionDesarrollo>() {
            @Override
            public void onResponse(final BEDimensionDesarrollo response) {
                if(response == null){
                    callBackSucces.onLoad(false, programaEducativoUi);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    programaEducativoUi.setFecha(response.getFecha_servidor());

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            TransaccionUtils.fastStoreListInsert(DimensionDesarrollo.class, response.getDimensionDesarrollo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DimensionDesarrolloDetalle.class, response.getDimensionDesarrolloDetalle(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EstrategiaEvaluacion.class, response.getEstrategiaEvaluacion(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(DimensionDesarrolloEstrategiaEvaluacionC.class, response.getDimensionDesarrolloEstrategiaEval(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Dimension.class, response.getDimension(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DimensionObservada.class, response.getDimensionObservada(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, response.getInstrumentoEvaluacion(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(InstrumentoObservado.class, response.getInstrumentoObservado(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Dimencion_Desarrollo){
                                    actualizarUi.setSuccess(1);
                                    actualizarUi.setFecha(response.getFecha_servidor());
                                }
                            }
                            callBackSucces.onLoad(true, programaEducativoUi);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Dimencion_Desarrollo){
                                    actualizarUi.setSuccess(-1);
                                }
                            }
                            callBackSucces.onLoad(false, programaEducativoUi);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                    if(actualizarUi.isEncoloa()&&actualizarUi.getTipo()== ActualizarTipoUi.Dimencion_Desarrollo){
                        actualizarUi.setSuccess(-1);
                    }
                }
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBackSucces.onLoad(false, programaEducativoUi);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener( callBackSucces);

        return retrofitCancel;
    }


    private void setupListener(final CallBackSucces callBackSucces){
        progressDownloadingListener = new ProgressListener() {
            ProgressInfo mLastDownloadingInfo;
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                // 如果你不屏蔽用户重复点击上传或下载按钮,就可能存在同一个 Url 地址,上一次的上传或下载操作都还没结束,
                // 又开始了新的上传或下载操作,那现在就需要用到 id(请求开始时的时间) 来区分正在执行的进度信息
                // 这里我就取最新的下载进度用来展示,顺便展示下 id 的用法

                if (mLastDownloadingInfo == null) {
                    mLastDownloadingInfo = progressInfo;
                }

                //因为是以请求开始时的时间作为 Id ,所以值越大,说明该请求越新
                if (progressInfo.getId() < mLastDownloadingInfo.getId()) {
                    return;
                } else if (progressInfo.getId() > mLastDownloadingInfo.getId()) {
                    mLastDownloadingInfo = progressInfo;
                }
                // 如果getCurrentbytes 等于 -1 说明二进制已经读取完,可能是成功下载完所有数据,也可能是遭遇了错误
                int progress = (int) ((100 * mLastDownloadingInfo.getCurrentbytes()) / mLastDownloadingInfo.getContentLength());
                Log.d(TAG, mLastDownloadingInfo.getId() + "--download--" + progress + " % " + mLastDownloadingInfo.getCurrentbytes() + "  " + mLastDownloadingInfo.getContentLength());
                callBackSucces.onResponseProgress(progress);

            }

            @Override
            public void onError(long id, Exception e) {

            }
        };

        progressUploadingListener = new ProgressListener() {
            ProgressInfo mLastUploadingingInfo;
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                // 如果你不屏蔽用户重复点击上传或下载按钮,就可能存在同一个 Url 地址,上一次的上传或下载操作都还没结束,
                // 又开始了新的上传或下载操作,那现在就需要用到 id(请求开始时的时间) 来区分正在执行的进度信息
                // 这里我就取最新的上传进度用来展示,顺便展示下 id 的用法

                if (mLastUploadingingInfo == null) {
                    mLastUploadingingInfo = progressInfo;
                }

                //因为是以请求开始时的时间作为 Id ,所以值越大,说明该请求越新
                if (progressInfo.getId() < mLastUploadingingInfo.getId()) {
                    return;
                } else if (progressInfo.getId() > mLastUploadingingInfo.getId()) {
                    mLastUploadingingInfo = progressInfo;
                }

                int progress = (int) ((100 * mLastUploadingingInfo.getCurrentbytes()) / mLastUploadingingInfo.getContentLength());
                Log.d(TAG, mLastUploadingingInfo.getId() + "--upload--" + progress + " %  " + mLastUploadingingInfo.getCurrentbytes() + "  " + mLastUploadingingInfo.getContentLength());
                callBackSucces.onRequestProgress(progress);
            }

            @Override
            public void onError(long id, Exception e) {

            }
        };
    }

    private List<RubroEnviarUi> getRubroEnviar(int silaboEventoId) {

        clearDataRubroEvaluacionProceso();

        Set<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = new LinkedHashSet<>(ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoC.class));
        List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> tRnMaeRubroEvaluacionProcesoEquipocList = ConsultaUtils.getChangeItemsTable(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class);
        List<EquipoEvaluacionProcesoC> equipoEvaluacionProcesoCList = ConsultaUtils.getChangeItemsTable(EquipoEvaluacionProcesoC.class);
        Set<EvaluacionProcesoC> evaluacionProcesoCList = new LinkedHashSet<>(ConsultaUtils.getChangeItemsTable(EvaluacionProcesoC.class));
        List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentarioList = ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoComentario.class);
        List<ArchivosRubroProceso> archivosRubroProcesoList = ConsultaUtils.getChangeItemsTable(ArchivosRubroProceso.class);
        List<CriterioRubroEvaluacionC> criterioRubroEvaluacionCList = ConsultaUtils.getChangeItemsTable(CriterioRubroEvaluacionC.class);
        List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTable(TareaRubroEvaluacionProceso.class);
        List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = ConsultaUtils.getChangeItemsTable(RubroEvalRNPFormulaC.class);

        Set<String> evaluacionProcesoIdList = new LinkedHashSet<>();
        for (RubroEvaluacionProcesoComentario itemComentario : rubroEvaluacionProcesoComentarioList) {
            evaluacionProcesoIdList.add(itemComentario.getEvaluacionProcesoId());
        }
        for (ArchivosRubroProceso itemArchivo : archivosRubroProcesoList) {
            evaluacionProcesoIdList.add(itemArchivo.getEvaluacionProcesoId());
        }
        evaluacionProcesoCList.addAll(SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.in(evaluacionProcesoIdList))
                .queryList());

        Set<String> rubroEvaluacionIdList = new LinkedHashSet<>();
        for (RubroEvaluacionProcesoC itemRubroEvaluacion : rubroEvaluacionProcesoCList) {
            Log.d(TAG, "itemRubroEvaluacion : " + itemRubroEvaluacion.key);
            rubroEvaluacionIdList.add(itemRubroEvaluacion.getKey());
        }

        for (RubroEvaluacionProcesoC itemRubroEvaluacion : rubroEvaluacionProcesoCList) {
            Log.d(TAG, "itemRubroEvaluacion : " + itemRubroEvaluacion.key);
            rubroEvaluacionIdList.add(itemRubroEvaluacion.getKey());
        }

        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC itemEquipo : tRnMaeRubroEvaluacionProcesoEquipocList) {
            Log.d(TAG, "T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC : " + itemEquipo.key);
            rubroEvaluacionIdList.add(itemEquipo.getRubroEvalProcesoId());
        }
        for (EquipoEvaluacionProcesoC itemEquipoEvaluacion : equipoEvaluacionProcesoCList) {
            Log.d(TAG, "itemEquipoEvaluacion : " + itemEquipoEvaluacion.key);
            rubroEvaluacionIdList.add(itemEquipoEvaluacion.getRubroEvalProcesoId());
        }
        for (EvaluacionProcesoC itemEvaluacion : evaluacionProcesoCList) {
            Log.d(TAG, "itemEvaluacion : " + itemEvaluacion.key);
            rubroEvaluacionIdList.add(itemEvaluacion.getRubroEvalProcesoId());
        }
        for (CriterioRubroEvaluacionC itemCriterio : criterioRubroEvaluacionCList) {
            Log.d(TAG, "itemCriterio : " + itemCriterio.key);
            rubroEvaluacionIdList.add(itemCriterio.getRubroEvalProcesoId());
        }
        for (TareaRubroEvaluacionProceso itemTarea : tareaRubroEvaluacionProcesoList) {
            Log.d(TAG, "itemTarea : " + itemTarea.key);
            rubroEvaluacionIdList.add(itemTarea.getRubroEvalProcesoId());
        }

        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC : rubroEvalRNPFormulaCList) {
            Log.d(TAG, "rubroEvalRNPFormulaC : " + rubroEvalRNPFormulaC.key);
            rubroEvaluacionIdList.add(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
        }


        for (RubroEvaluacionProcesoC itemRubroEvaluacionProceso : rubroEvaluacionProcesoCList) {
            if (itemRubroEvaluacionProceso.getTiporubroid() == RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE) {
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                        .from(RubroEvaluacionProcesoC.class)
                        .innerJoin(RubroEvalRNPFormulaC.class)
                        .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                        .where(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().eq(0))
                        .and(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(itemRubroEvaluacionProceso.getKey()))
                        .querySingle();

                if (rubroEvaluacionProcesoC != null)
                    rubroEvaluacionIdList.add(rubroEvaluacionProcesoC.getKey());
            } else {
                rubroEvaluacionIdList.add(itemRubroEvaluacionProceso.getKey());
            }
        }

        List<RubroEnviarUi> rubroEnviarUiList = new ArrayList<>();

        if(silaboEventoId>0){
            List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                    .from(CalendarioPeriodo.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.withTable()))
                    .where(RubroEvaluacionProcesoC_Table.key.withTable().in(rubroEvaluacionIdList))
                    .and(RubroEvaluacionProcesoC_Table.silaboEventoId.withTable().eq(silaboEventoId))
                    .groupBy(CalendarioPeriodo_Table.calendarioPeriodoId.withTable())
                    .queryList();

            Collections.sort(calendarioPeriodoList, new Comparator<CalendarioPeriodo>() {
                public int compare(CalendarioPeriodo o1, CalendarioPeriodo o2) {
                    return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
                }
            });

            for (CalendarioPeriodo itemCalendarioPeriodo : calendarioPeriodoList){

                Tipos tipos = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(itemCalendarioPeriodo.getTipoId()))
                        .querySingle();

                String bimestre = tipos!=null? tipos.getNombre() : "-";

                List<String> rubroEvaluacionLocalList = new ArrayList<>();
                List<String> rubricaEvaluacionLocalList = new ArrayList<>();
                List<String> rubroFormulaLocalList = new ArrayList<>();

                for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvaluacionIdList))
                        .and(RubroEvaluacionProcesoC_Table.silaboEventoId.withTable().eq(silaboEventoId))
                        .and(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.eq(itemCalendarioPeriodo.getCalendarioPeriodoId()))
                        .queryList()){
                    if(rubroEvaluacionProcesoC.getTipoFormulaId()!=0){
                        rubroFormulaLocalList.add(rubroEvaluacionProcesoC.getKey());
                    }else if(rubroEvaluacionProcesoC.getTiporubroid() == RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL){
                        rubroEvaluacionLocalList.add(rubroEvaluacionProcesoC.getKey());
                    }else if(rubroEvaluacionProcesoC.getTiporubroid() == RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL){
                        rubricaEvaluacionLocalList.add(rubroEvaluacionProcesoC.getKey());
                    }
                }



                if(!rubricaEvaluacionLocalList.isEmpty()){
                    RubroEnviarUi rubroEnviarRubricaLocalUi = new RubroEnviarUi();
                    rubroEnviarRubricaLocalUi.setNombre("Rúbrica evaluación " + bimestre);
                    rubroEnviarRubricaLocalUi.setRubroEvaluacionIdList(rubricaEvaluacionLocalList);
                    rubroEnviarRubricaLocalUi.setBimestre(true);
                    for (String key : rubricaEvaluacionLocalList)Log.d(TAG, "rubrica " + key);
                    String descripcion = "Existen " + rubricaEvaluacionLocalList.size() + " rúbricas modificados a enviar";
                    rubroEnviarRubricaLocalUi.setDescripcion(descripcion);
                    rubroEnviarUiList.add(rubroEnviarRubricaLocalUi);
                }

                if(!rubroEvaluacionLocalList.isEmpty()){
                    RubroEnviarUi rubroEnviarLocalUi = new RubroEnviarUi();
                    rubroEnviarLocalUi.setNombre("Rubro evaluación " + bimestre);
                    rubroEnviarLocalUi.setRubroEvaluacionIdList(rubroEvaluacionLocalList);
                    rubroEnviarLocalUi.setBimestre(true);
                    String descripcion = "Existen " + rubroEvaluacionLocalList.size() + " rubro modificados a enviar";
                    rubroEnviarLocalUi.setDescripcion(descripcion);
                    rubroEnviarUiList.add(rubroEnviarLocalUi);
                }

                if(!rubroFormulaLocalList.isEmpty()){
                    RubroEnviarUi rubroEnviarFormulaLocalUi = new RubroEnviarUi();
                    rubroEnviarFormulaLocalUi.setNombre("Fórmula evaluación " + bimestre);
                    rubroEnviarFormulaLocalUi.setRubroEvaluacionIdList(rubroFormulaLocalList);
                    rubroEnviarFormulaLocalUi.setBimestre(true);
                    String descripcion = "Existen " + rubroFormulaLocalList.size() + " fórmulas modificados a enviar";
                    rubroEnviarFormulaLocalUi.setDescripcion(descripcion);
                    rubroEnviarUiList.add(rubroEnviarFormulaLocalUi);
                }
            }
        }else {
            List<String> rubroEvaluacionLocalList = new ArrayList<>();
            List<String> rubricaEvaluacionLocalList = new ArrayList<>();
            List<String> rubroFormulaLocalList = new ArrayList<>();

            for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvaluacionIdList))
                    .queryList()){

                if(rubroEvaluacionProcesoC.getTipoFormulaId()!=0){
                    rubroFormulaLocalList.add(rubroEvaluacionProcesoC.getKey());
                }else if(rubroEvaluacionProcesoC.getTiporubroid() == RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL){
                    rubroEvaluacionLocalList.add(rubroEvaluacionProcesoC.getKey());
                }else if(rubroEvaluacionProcesoC.getTiporubroid() == RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL){
                    rubricaEvaluacionLocalList.add(rubroEvaluacionProcesoC.getKey());
                }

            }

            if(!rubricaEvaluacionLocalList.isEmpty()){
                RubroEnviarUi rubroEnviarRubricaLocalUi = new RubroEnviarUi();
                rubroEnviarRubricaLocalUi.setNombre("Rúbrica evaluación");
                rubroEnviarRubricaLocalUi.setRubroEvaluacionIdList(rubricaEvaluacionLocalList);
                rubroEnviarRubricaLocalUi.setBimestre(true);
                String descripcion = "Existen " + rubricaEvaluacionLocalList.size() + " rúbricas modificados a enviar";
                rubroEnviarRubricaLocalUi.setDescripcion(descripcion);
                rubroEnviarUiList.add(rubroEnviarRubricaLocalUi);
            }

            if(!rubroEvaluacionLocalList.isEmpty()){
                RubroEnviarUi rubroEnviarLocalUi = new RubroEnviarUi();
                rubroEnviarLocalUi.setNombre("Rubro evaluación");
                rubroEnviarLocalUi.setRubroEvaluacionIdList(rubroEvaluacionLocalList);
                rubroEnviarLocalUi.setBimestre(true);
                String descripcion = "Existen " + rubroEvaluacionLocalList.size() + " rubro modificados a enviar";
                rubroEnviarLocalUi.setDescripcion(descripcion);
                rubroEnviarUiList.add(rubroEnviarLocalUi);
            }

            if(!rubroFormulaLocalList.isEmpty()){
                RubroEnviarUi rubroEnviarFormulaLocalUi = new RubroEnviarUi();
                rubroEnviarFormulaLocalUi.setNombre("Fórmula evaluación");
                rubroEnviarFormulaLocalUi.setRubroEvaluacionIdList(rubroFormulaLocalList);
                rubroEnviarFormulaLocalUi.setBimestre(true);
                String descripcion = "Existen " + rubroFormulaLocalList.size() + " fórmulas modificados a enviar";
                rubroEnviarFormulaLocalUi.setDescripcion(descripcion);
                rubroEnviarUiList.add(rubroEnviarFormulaLocalUi);
            }
        }


        return rubroEnviarUiList;
    }


    private void clearDataRubroEvaluacionProceso() {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCListEliminado = SQLite.select().
                    from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.estadoId.eq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                    .queryList(databaseWrapper);

            List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = new ArrayList<>();
            for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoCListEliminado){
                String key = rubroEvaluacionProcesoC.getKey();
                if(TextUtils.isEmpty(key))rubroEvaluacionProcesoCList.add(rubroEvaluacionProcesoC);
            }

            List<String> keyLis = new ArrayList<>();
            for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoCList)keyLis.add(rubroEvaluacionProcesoC.getKey());

            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);

            List<String> evaluacionKeyList = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList)evaluacionKeyList.add(evaluacionProcesoC.getKey());

            List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = SQLite.select()
                    .from(RubroEvalRNPFormulaC.class)
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(keyLis))
                    .queryList(databaseWrapper);

            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> tRnMaeRubroEvaluacionProcesoEquipocList = SQLite.select()
                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);
            List<String> rubroEquipokeyList = new ArrayList<>();
            for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC rubroEvaluacionProcesoEquipoc: tRnMaeRubroEvaluacionProcesoEquipocList)rubroEquipokeyList.add(rubroEvaluacionProcesoEquipoc.getKey());

            List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> tRnMaeRubroEvaluacionProcesoIntegrantecList = SQLite.select()
                    .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                    .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(rubroEquipokeyList))
                    .queryList(databaseWrapper);

            List<EquipoEvaluacionProcesoC> equipoEvaluacionProcesoCList = SQLite.select()
                    .from(EquipoEvaluacionProcesoC.class)
                    .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().in(keyLis))
                    .queryList();

            List<RubroEvaluacionProcesoCampotematicoC> rubroEvaluacionProcesoCampotematicoCList = SQLite.select()
                    .from(RubroEvaluacionProcesoCampotematicoC.class)
                    .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);

            List<CriterioRubroEvaluacionC> criterioRubroEvaluacionCList = SQLite.select()
                    .from(CriterioRubroEvaluacionC.class)
                    .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);

            List<RubroEvaluacionProcesoComentario> evaluacionProcesoComentarioList = SQLite.select()
                    .from(RubroEvaluacionProcesoComentario.class)
                    .where(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.in(evaluacionKeyList))
                    .queryList(databaseWrapper);

            List<ArchivosRubroProceso> archivosRubroProcesoList = SQLite.select()
                    .from(ArchivosRubroProceso.class)
                    .where(ArchivosRubroProceso_Table.evaluacionProcesoId.in(evaluacionKeyList))
                    .queryList(databaseWrapper);

            List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = SQLite.select()
                    .from(TareaRubroEvaluacionProceso.class)
                    .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.in(keyLis))
                    .queryList(databaseWrapper);



            Log.d(TAG,"rubroEvaluacionProcesoCList delete export: " + rubroEvaluacionProcesoCList.size());
            Log.d(TAG,"evaluacionProcesoCList delete export: " + evaluacionProcesoCList.size());

            //TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoC.class, rubroEvaluacionProcesoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EvaluacionProcesoC.class, evaluacionProcesoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvalRNPFormulaC.class, rubroEvalRNPFormulaCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, tRnMaeRubroEvaluacionProcesoEquipocList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, tRnMaeRubroEvaluacionProcesoIntegrantecList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoCampotematicoC.class, rubroEvaluacionProcesoCampotematicoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(CriterioRubroEvaluacionC.class, criterioRubroEvaluacionCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoComentario.class, evaluacionProcesoComentarioList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class, archivosRubroProcesoList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(TareaRubroEvaluacionProceso.class, tareaRubroEvaluacionProcesoList, BaseEntity.FLAG_DELETED, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoEvaluacionProcesoC.class, equipoEvaluacionProcesoCList, BaseEntity.FLAG_DELETED, databaseWrapper, true);

            databaseWrapper.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    private List<TareaEnviarUi> getTareaEnviar(int cargaCursoId , int anioAcademicoId, int silaboEventoId) {
        List<TareaEnviarUi> tareaEnviarUiList = new ArrayList<>();
        if(silaboEventoId>0){

            List<CalendarioPeriodo> calendarioPeriodoList = CalendarioPeriodoModel.SQLView()
                    .select(CalendarioPeriodo_Table.calendarioPeriodoId.withTable(),
                            CalendarioPeriodo_Table.tipoId.withTable(),
                            CalendarioPeriodo_Table.estadoId.withTable(),
                            CalendarioPeriodo_Table.fechaFin.withTable())
                    .getQuery(cargaCursoId, anioAcademicoId)
                    .queryList();


            Collections.sort(calendarioPeriodoList, new Comparator<CalendarioPeriodo>() {
                public int compare(CalendarioPeriodo o1, CalendarioPeriodo o2) {
                    return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
                }
            });

            for (CalendarioPeriodo itemCalendarioPeriodo : calendarioPeriodoList){

                Tipos tipos = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(itemCalendarioPeriodo.getTipoId()))
                        .querySingle();

                String bimestre = tipos!=null? tipos.getNombre() : "-";


                List<UnidadAprendizaje> unidadAprendizajeList = UnidadAprendizajeCargaCursoModel.SQLView()
                        .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(UnidadAprendizaje_Table.ALL_COLUMN_PROPERTIES)))
                        //.select(UnidadAprendizaje_Table.unidadAprendizajeId.withTable(), UnidadAprendizaje_Table.titulo.withTable())
                        .getQuery(cargaCursoId, itemCalendarioPeriodo.getCalendarioPeriodoId(), anioAcademicoId)
                        .queryList();

                List<Integer> unidadAprendizajeIdList =new ArrayList<>();
                for (UnidadAprendizaje unidadAprendizaje: unidadAprendizajeList)unidadAprendizajeIdList.add(unidadAprendizaje.getUnidadAprendizajeId());

                Set<TareasC> tareasCList = new LinkedHashSet<>(SQLite.select()
                        .from(TareasC.class)
                        .where(TareasC_Table.unidadAprendizajeId.withTable().in(unidadAprendizajeIdList))
                        .and(TareasC_Table.syncFlag.withTable().in(TareasC.FLAG_ADDED, TareasC.FLAG_UPDATED))
                        .queryList());

                tareasCList.addAll(SQLite.select(Utils.f_allcolumnTable(TareasC_Table.ALL_COLUMN_PROPERTIES))
                        .from(TareasC.class)
                        .innerJoin(SesionAprendizaje.class)
                        .on(TareasC_Table.sesionAprendizajeId.withTable().eq(SesionAprendizaje_Table.sesionAprendizajeId.withTable()))
                        .where(SesionAprendizaje_Table.unidadAprendizajeId.withTable().in(unidadAprendizajeIdList))
                        .and(TareasC_Table.syncFlag.withTable().in(TareasC.FLAG_ADDED, TareasC.FLAG_UPDATED))
                        .queryList());

                Set<String> tareaIdList = new LinkedHashSet<>();
                for(TareasC tareasC : tareasCList)tareaIdList.add(tareasC.getKey());
                if(!tareaIdList.isEmpty()){
                    TareaEnviarUi tareaEnviarUi = new TareaEnviarUi();
                    tareaEnviarUi.setTipo(ServiceEnvioUi.Tipo.Tareas);
                    tareaEnviarUi.setNombre("Tarea "+ bimestre);
                    String descripcion = "Existen " + tareaIdList.size() + " tareas modificadas a enviar";
                    tareaEnviarUi.setDescripcion(descripcion);
                    tareaEnviarUi.setTareaIdLis(new ArrayList<String>(tareaIdList));
                    tareaEnviarUiList.add(tareaEnviarUi);
                }

            }

        }else {
            List<String> tareaIdList = new ArrayList<>();
            for(TareasC tareasC : ConsultaUtils.getChangeItemsTable(TareasC.class))tareaIdList.add(tareasC.getKey());
            if(!tareaIdList.isEmpty()){
                TareaEnviarUi tareaEnviarUi = new TareaEnviarUi();
                tareaEnviarUi.setNombre("Tarea");
                String descripcion = "Existen " + tareaIdList.size() + " tareas modificadas a enviar";
                tareaEnviarUi.setDescripcion(descripcion);
                tareaEnviarUi.setTipo(ServiceEnvioUi.Tipo.Tareas);
                tareaEnviarUi.setTareaIdLis(new ArrayList<String>(tareaIdList));
                tareaEnviarUiList.add(tareaEnviarUi);
            }
        }

        return tareaEnviarUiList;
    }

    private List<SesionesEnviarUi> getSesionesEnviar(int cargaCursoId , int anioAcademicoId){
        List<SesionesEnviarUi> sesionesEnviarUiList = new ArrayList<>();
        if(cargaCursoId>0){

            List<CalendarioPeriodo> calendarioPeriodoList = CalendarioPeriodoModel.SQLView()
                    .select(CalendarioPeriodo_Table.calendarioPeriodoId.withTable(),
                            CalendarioPeriodo_Table.tipoId.withTable(),
                            CalendarioPeriodo_Table.estadoId.withTable(),
                            CalendarioPeriodo_Table.fechaFin.withTable())
                    .getQuery(cargaCursoId, anioAcademicoId)
                    .queryList();


            Collections.sort(calendarioPeriodoList, new Comparator<CalendarioPeriodo>() {
                public int compare(CalendarioPeriodo o1, CalendarioPeriodo o2) {
                    return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
                }
            });

            for (CalendarioPeriodo itemCalendarioPeriodo : calendarioPeriodoList){

                Tipos tipos = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(itemCalendarioPeriodo.getTipoId()))
                        .querySingle();

                String bimestre = tipos!=null? tipos.getNombre() : "-";


                List<UnidadAprendizaje> unidadAprendizajeList = UnidadAprendizajeCargaCursoModel.SQLView()
                        .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(UnidadAprendizaje_Table.ALL_COLUMN_PROPERTIES)))
                        //.select(UnidadAprendizaje_Table.unidadAprendizajeId.withTable(), UnidadAprendizaje_Table.titulo.withTable())
                        .getQuery(cargaCursoId, itemCalendarioPeriodo.getCalendarioPeriodoId(), anioAcademicoId)
                        .queryList();

                List<Integer> unidadAprendizajeIdList =new ArrayList<>();
                for (UnidadAprendizaje unidadAprendizaje: unidadAprendizajeList)unidadAprendizajeIdList.add(unidadAprendizaje.getUnidadAprendizajeId());

                List<SesionAprendizaje> sesionAprendizajeList = SQLite.select()
                        .from(SesionAprendizaje.class)
                        .where(SesionAprendizaje_Table.unidadAprendizajeId.in(unidadAprendizajeIdList))
                        .and(SesionAprendizaje_Table.syncFlag.withTable().in(SesionAprendizaje.FLAG_ADDED, SesionAprendizaje.FLAG_UPDATED))
                        .queryList();

                List<Integer> sesionesIdList = new ArrayList<>();
                for(SesionAprendizaje sesion : sesionAprendizajeList)sesionesIdList.add(sesion.getSesionAprendizajeId());
                if(!sesionesIdList.isEmpty()){
                    SesionesEnviarUi sesionesEnviarUi = new SesionesEnviarUi();
                    sesionesEnviarUi.setTipo(ServiceEnvioUi.Tipo.Unidades);
                    sesionesEnviarUi.setNombre("Sesión "+ bimestre);
                    String descripcion = "Existen " + sesionesIdList.size() + " sesiones modificadas a enviar";
                    sesionesEnviarUi.setDescripcion(descripcion);
                    sesionesEnviarUi.setSesionesIdList(sesionesIdList);
                    sesionesEnviarUiList.add(sesionesEnviarUi);
                }

            }

        }else {

            List<Integer> sesionesIdList = new ArrayList<>();
            for(SesionAprendizaje sesion : ConsultaUtils.getChangeItemsTable(SesionAprendizaje.class))sesionesIdList.add(sesion.getSesionAprendizajeId());
            if(!sesionesIdList.isEmpty()){
                SesionesEnviarUi sesionesEnviarUi = new SesionesEnviarUi();
                sesionesEnviarUi.setNombre("Sesión");
                String descripcion = "Existen " + sesionesIdList.size() + " sesiones modificadas a enviar";
                sesionesEnviarUi.setDescripcion(descripcion);
                sesionesEnviarUi.setTipo(ServiceEnvioUi.Tipo.Unidades);
                sesionesEnviarUi.setSesionesIdList(sesionesIdList);
                sesionesEnviarUiList.add(sesionesEnviarUi);
            }
        }

        return sesionesEnviarUiList;
    }

    private List<GrupoEnviarUi> getGrupoEnviar(int cargaCursoId){

        List<GrupoEnviarUi> grupoEnviarUiList = new ArrayList<>();

        if(cargaCursoId>0){

            Set<GrupoEquipoC> grupoEquipoCList = new LinkedHashSet<GrupoEquipoC>(SQLite.select()
                    .from(GrupoEquipoC.class)
                    .where(GrupoEquipoC_Table.cargaCursoId.eq(cargaCursoId))
                    .and(GrupoEquipoC_Table.syncFlag.in(GrupoEquipoC.FLAG_ADDED, GrupoEquipoC.FLAG_UPDATED))
                    .queryList());

            grupoEquipoCList.addAll(SQLite.select(Utils.f_allcolumnTable(GrupoEquipoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(GrupoEquipoC.class)
                    .innerJoin(EquipoC.class)
                    .on(GrupoEquipoC_Table.key.withTable().eq(EquipoC_Table.grupoEquipoId.withTable()))
                    .where(GrupoEquipoC_Table.cargaCursoId.withTable().eq(cargaCursoId))
                    .and(EquipoC_Table.syncFlag.withTable().in(EquipoC.FLAG_ADDED, EquipoC.FLAG_UPDATED))
                    .queryList());

            grupoEquipoCList.addAll(SQLite.select(Utils.f_allcolumnTable(GrupoEquipoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(GrupoEquipoC.class)
                    .innerJoin(EquipoC.class)
                    .on(GrupoEquipoC_Table.key.withTable().eq(EquipoC_Table.grupoEquipoId.withTable()))
                    .innerJoin(EquipoIntegranteC.class)
                    .on(EquipoC_Table.key.withTable().eq(EquipoIntegranteC_Table.equipoId.withTable()))
                    .where(GrupoEquipoC_Table.cargaCursoId.withTable().eq(cargaCursoId))
                    .and(EquipoIntegranteC_Table.syncFlag.withTable().in(EquipoIntegranteC.FLAG_ADDED, EquipoIntegranteC.FLAG_UPDATED))
                    .queryList());


            List<String> grupoEquipoIdList = new ArrayList<>();
            for (GrupoEquipoC grupoEquipoC : grupoEquipoCList)grupoEquipoIdList.add(grupoEquipoC.getKey());

            if(!grupoEquipoIdList.isEmpty()){
                GrupoEnviarUi grupoEnviarUi = new GrupoEnviarUi();
                grupoEnviarUi.setTipo(ServiceEnvioUi.Tipo.Grupos);
                grupoEnviarUi.setNombre("Lista de grupos");
                String descripcion = "Existen " + grupoEquipoIdList.size() + " lista de grupos modificadas a enviar";
                grupoEnviarUi.setDescripcion(descripcion);
                grupoEnviarUi.setGrupoEquipoList(grupoEquipoIdList);
                grupoEnviarUiList.add(grupoEnviarUi);
            }



        }else {
            Set<GrupoEquipoC> grupoEquipoCList = new LinkedHashSet<GrupoEquipoC>(SQLite.select()
                    .from(GrupoEquipoC.class)
                    .where(GrupoEquipoC_Table.syncFlag.in(GrupoEquipoC.FLAG_ADDED, GrupoEquipoC.FLAG_UPDATED))
                    .queryList());

            grupoEquipoCList.addAll(SQLite.select(Utils.f_allcolumnTable(GrupoEquipoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(GrupoEquipoC.class)
                    .innerJoin(EquipoC.class)
                    .on(GrupoEquipoC_Table.key.withTable().eq(EquipoC_Table.grupoEquipoId.withTable()))
                    .where(EquipoC_Table.syncFlag.withTable().in(EquipoC.FLAG_ADDED, EquipoC.FLAG_UPDATED))
                    .queryList());

            grupoEquipoCList.addAll(SQLite.select(Utils.f_allcolumnTable(GrupoEquipoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(GrupoEquipoC.class)
                    .innerJoin(EquipoC.class)
                    .on(GrupoEquipoC_Table.key.withTable().eq(EquipoC_Table.grupoEquipoId.withTable()))
                    .innerJoin(EquipoIntegranteC.class)
                    .on(EquipoC_Table.key.withTable().eq(EquipoIntegranteC_Table.equipoId.withTable()))
                    .where(EquipoIntegranteC_Table.syncFlag.withTable().in(EquipoIntegranteC.FLAG_ADDED, EquipoIntegranteC.FLAG_UPDATED))
                    .queryList());

            List<String> grupoEquipoIdList = new ArrayList<>();
            for (GrupoEquipoC grupoEquipoC : grupoEquipoCList)grupoEquipoIdList.add(grupoEquipoC.getKey());

            if(!grupoEquipoIdList.isEmpty()){
                GrupoEnviarUi grupoEnviarUi = new GrupoEnviarUi();
                grupoEnviarUi.setTipo(ServiceEnvioUi.Tipo.Grupos);
                grupoEnviarUi.setNombre("Lista de Grupo");
                String descripcion = "Existen " + grupoEquipoIdList.size() + " lista de grupos modificadas a enviar";
                grupoEnviarUi.setDescripcion(descripcion);
                grupoEnviarUi.setGrupoEquipoList(grupoEquipoIdList);
                grupoEnviarUiList.add(grupoEnviarUi);
            }
        }



        return grupoEnviarUiList;
    }

    private List<CasosEnviarUi> getCasosEnviar(int cargaCursoId){

        Set<String> integerList = new LinkedHashSet<>();
        for (Caso caso:  ConsultaUtils.getChangeItemsTable(Caso.class)){
            integerList.add(caso.getKey());
        }

        for (CasoArchivo casoArchivo:  ConsultaUtils.getChangeItemsTable(CasoArchivo.class)){
            integerList.add(casoArchivo.getCasoId());
        }

        List<CasosEnviarUi> casosEnviarUiList = new ArrayList<>();
        if(cargaCursoId>0){

            List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                    .from(CalendarioPeriodo.class)
                    .innerJoin(Caso.class)
                    .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().
                            eq(Caso_Table.calendarioPeriodoId.withTable()))
                    .where(Caso_Table.key.withTable().in(integerList))
                    .and(Caso_Table.cargaCursoId.withTable().eq(cargaCursoId))
                    .groupBy(CalendarioPeriodo_Table.calendarioPeriodoId.withTable())
                    .queryList();

            for (CalendarioPeriodo calendarioPeriodo : calendarioPeriodoList){
                Tipos tipos = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                        .querySingle();

                String bimestre = tipos!=null? tipos.getNombre() : "-";

                List<Caso> casoList = SQLite.select()
                        .from(Caso.class)
                        .where(Caso_Table.key.in(integerList))
                        .and(Caso_Table.calendarioPeriodoId.eq(calendarioPeriodo.getCalendarioPeriodoId()))
                        .and(Caso_Table.cargaCursoId.withTable().eq(cargaCursoId))
                        .queryList();
                List<String> casosIdList = new ArrayList<>();
                for (Caso caso: casoList)casosIdList.add(caso.getKey());

                if(!casosIdList.isEmpty()){
                    CasosEnviarUi casosEnviarUi = new CasosEnviarUi();
                    casosEnviarUi.setTipo(ServiceEnvioUi.Tipo.Casos);
                    casosEnviarUi.setNombre("Casos "+ bimestre);
                    String descripcion = "Existen " + casosIdList.size() + " casos modificadas a enviar";
                    casosEnviarUi.setDescripcion(descripcion);
                    casosEnviarUi.setCasosIdList(casosIdList);
                    casosEnviarUiList.add(casosEnviarUi);
                }

            }



        }else {

            if(!integerList.isEmpty()){
                CasosEnviarUi casosEnviarUi = new CasosEnviarUi();
                casosEnviarUi.setTipo(ServiceEnvioUi.Tipo.Casos);
                casosEnviarUi.setNombre("Casos");
                String descripcion = "Existen " + integerList.size() + " casos modificadas a enviar";
                casosEnviarUi.setDescripcion(descripcion);
                casosEnviarUi.setCasosIdList(new ArrayList<String>(integerList));
                casosEnviarUiList.add(casosEnviarUi);
            }
        }

        return casosEnviarUiList;

    }

    private List<ResultadoEnvioUi> getResultadoEnvio(int silavoEventoId){
        Set<Integer> integerList = new LinkedHashSet<>();
        for (RubroEvaluacionResultado rubroEvaluacionResultado:  ConsultaUtils.getChangeItemsTable(RubroEvaluacionResultado.class)){
            integerList.add(rubroEvaluacionResultado.getRubroEvalResultadoId());
        }

        for (EvaluacionResultadoC evaluacionResultadoC:  ConsultaUtils.getChangeItemsTable(EvaluacionResultadoC.class)){
            integerList.add(evaluacionResultadoC.getRubroEvalResultadoId());
        }

        List<ResultadoEnvioUi> resultadoEnvioUiList = new ArrayList<>();
        if(silavoEventoId>0){

            List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                    .from(CalendarioPeriodo.class)
                    .innerJoin(RubroEvaluacionResultado.class)
                    .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().
                            eq(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                    .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable().in(integerList))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silavoEventoId))
                    .groupBy(CalendarioPeriodo_Table.calendarioPeriodoId.withTable())
                    .queryList();


            for (CalendarioPeriodo calendarioPeriodo : calendarioPeriodoList){
                Tipos tipos = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                        .querySingle();

                String bimestre = tipos!=null? tipos.getNombre() : "-";

                List<RubroEvaluacionResultado> casoList = SQLite.select()
                        .from(RubroEvaluacionResultado.class)
                        .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.in(integerList))
                        .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.eq(calendarioPeriodo.getCalendarioPeriodoId()))
                        .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silavoEventoId))
                        .queryList();
                List<Integer> rubroResultadoIdList = new ArrayList<>();
                for (RubroEvaluacionResultado rubroEvaluacionResultado: casoList)rubroResultadoIdList.add(rubroEvaluacionResultado.getRubroEvalResultadoId());

                if(!rubroResultadoIdList.isEmpty()){
                    ResultadoEnvioUi resultadoEnvioUi = new ResultadoEnvioUi();
                    resultadoEnvioUi.setTipo(ServiceEnvioUi.Tipo.Resultado);
                    resultadoEnvioUi.setNombre("Rubro resultado "+ bimestre);
                    String descripcion = "Existen " + rubroResultadoIdList.size() + " rubros resultados modificados a enviar";
                    resultadoEnvioUi.setDescripcion(descripcion);
                    resultadoEnvioUi.setRubroEvaluacionResultadoIdList(rubroResultadoIdList);
                    resultadoEnvioUiList.add(resultadoEnvioUi);
                }

            }



        }else {

            if(!integerList.isEmpty()){
                ResultadoEnvioUi resultadoEnvioUi = new ResultadoEnvioUi();
                resultadoEnvioUi.setTipo(ServiceEnvioUi.Tipo.Resultado);
                resultadoEnvioUi.setNombre("Rubro resultado");
                String descripcion = "Existen " + integerList.size() + " rubros resultados modificados a enviar";
                resultadoEnvioUi.setDescripcion(descripcion);
                resultadoEnvioUi.setRubroEvaluacionResultadoIdList(new ArrayList<Integer>(integerList));
                resultadoEnvioUiList.add(resultadoEnvioUi);
            }
        }

        return resultadoEnvioUiList;
    }

    private List<CerrarCursoEnviarUi> getcerrarCursoEnviarUi() {

        List<CerrarCursoEnviarUi> cerrarCursoEnviarUiList = new ArrayList<>();
        List<Integer> cargaCursoIdList = new ArrayList<>();
        List<CargaCursoCalendarioPeriodo> cargaCursoCalendarioPeriodoList = ConsultaUtils.getChangeItemsTable(CargaCursoCalendarioPeriodo.class);
        for (CargaCursoCalendarioPeriodo cargaCursoCalendarioPeriodo : cargaCursoCalendarioPeriodoList){
            cargaCursoIdList.add(cargaCursoCalendarioPeriodo.getCargaCursoId());
        }

        for (CursoCustom cursoCustom : cursoDao.obtenerPorCargaCursos(cargaCursoIdList)){
            CerrarCursoEnviarUi cerrarCursoEnviarUi = new CerrarCursoEnviarUi();
            cerrarCursoEnviarUi.setTipo(ServiceEnvioUi.Tipo.CerrarCurso);
            cerrarCursoEnviarUi.setNombre(cursoCustom.getNombre()+ " "+cursoCustom.getPeriodo()+" "+cursoCustom.getSeccion());
            List<Integer> cargaCursoCalendarioPeriodoIdList = new ArrayList<>();
            for (CargaCursoCalendarioPeriodo cargaCursoCalendarioPeriodo : cargaCursoCalendarioPeriodoList){
                if(cargaCursoCalendarioPeriodo.getCargaCursoId() == cursoCustom.getCargaCursoId()){
                    cargaCursoCalendarioPeriodoIdList.add(cargaCursoCalendarioPeriodo.getCargaCursoCalendarioPeriodoId());
                }
            }
            String descripcion = "se modificó el cursos";
            cerrarCursoEnviarUi.setDescripcion(descripcion);
            cerrarCursoEnviarUi.setCargaCursoCalendarioPeriodoIdList(cargaCursoCalendarioPeriodoIdList);
            cerrarCursoEnviarUiList.add(cerrarCursoEnviarUi);

        }



        return cerrarCursoEnviarUiList;
    }

    //#region changeEstadoGlobals

    private void changeEstadoGlobals(BEDatosCargaAcademica beDatosCargaAcademica, int syncFlag, DatabaseWrapper databaseWrapper) {
        if(beDatosCargaAcademica!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(CargaCursoCalendarioPeriodo.class, beDatosCargaAcademica.getObtenerCargaCursosCalendarioPeriodo(), syncFlag, databaseWrapper, false);
        }
    }


    private void changeEstadoGlobals(com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos beDatosCasos, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(Caso.class, beDatosCasos.getCaso(), syncFlag, databaseWrapper, false);
        SQLite.update(CasoArchivo.class)
                .set(CasoArchivo_Table.syncFlag.eq(syncFlag))
                .execute(databaseWrapper);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CasoReporte.class, beDatosCasos.getCasoReporte(), syncFlag,databaseWrapper, false);
    }

    //#region changeEstadoGlobals GEDatosEnvioAsistencia
    private void changeEstadoGlobals(GEDatosEnvioAsistencia geDatosEnvioAsistencia, int syncFlag, DatabaseWrapper databaseWrapper) {

        BEDatosEnvioAsistencia beDatosEnvioAsistencia = geDatosEnvioAsistencia.getBeDatosEnvioAsistencia();
        if(beDatosEnvioAsistencia!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdate(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(JustificacionC.class, beDatosEnvioAsistencia.getJustificacion(), syncFlag, databaseWrapper, false);

            SQLite.update(ArchivoAsistencia.class)
                    .set(ArchivoAsistencia_Table.syncFlag.eq(syncFlag))
                    .execute(databaseWrapper);
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = geDatosEnvioAsistencia.getBeDatosEnvioTipoNota();
        if(beDatosEnvioTipoNota!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag, databaseWrapper, false);
        }


        //TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivoAsistencia.class, beDatosEnvioAsistencia.getArchivoAsistencia(), syncFlag,databaseWrapper, false);



    }
    //#endregion changeEstadoGlobals GEDatosEnvioAsistencia

    //#region changeEstadoGlobals BEDatosEnvioGrupo
    private void changeEstadoGlobals(BEDatosEnvioGrupo beDatosEnvioGrupo, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals BEDatosEnvioGrupo

    //#region changeEstadoGlobals BEDatosEnvioMensajeria
    private void changeEstadoGlobals(BEDatosEnvioMensajeria beDatosEnvioMensajeria, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(InteraccionTextual.class, beDatosEnvioMensajeria.getListInteraccionTextual(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals BEDatosEnvioMensajeria

    //#region changeEstadoGlobals GEDatosRubroEvaluacionProceso
    private void changeEstadoGlobals(GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso, int syncFlag, DatabaseWrapper databaseWrapper) {
        BEDatosEnvioGrupo beDatosEnvioGrupo = geDatosRubroEvaluacionProceso.getBeDatosEnvioGrupo();
        if(beDatosEnvioGrupo!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);
        }


        BEDatosEnvioTipoNota beDatosEnvioTipoNota = geDatosRubroEvaluacionProceso.getBeDatosEnvioTipoNota();
        if(beDatosEnvioTipoNota!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag,databaseWrapper, false);
        }

        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = geDatosRubroEvaluacionProceso.getBeDatosRubroEvaluacionProceso();
        if(beDatosRubroEvaluacionProceso!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), syncFlag, databaseWrapper, true);
            Log.d(TAG, "Size getRubroEvalProcesoFormula: "+ beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula().size());
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);

            Log.d(TAG, "Size eval: "+ beDatosRubroEvaluacionProceso.getEvaluacionProceso().size());
            TransaccionUtils.fastStoreListSyncFlagUpdate(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), syncFlag,databaseWrapper, true);

            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoComentario.class,beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class,beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), syncFlag, databaseWrapper, false);



            /*SQLite.update(ArchivosRubroProceso.class)
                    .set(ArchivosRubroProceso_Table.syncFlag.eq(syncFlag))
                    .execute(databaseWrapper);*/

            //TransaccionUtils.fastStoreListSyncFlagUpdate(ComentarioPredeterminado.class, beDatosRubroEvaluacionProceso.getComentarioPredeterminado(), syncFlag,databaseWrapper, false);
            //TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoComentario.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), syncFlag,databaseWrapper, false);
            //TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class, beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), syncFlag,databaseWrapper, false);
        }


        GEDatosTareasRecursos beDatosTareaRecursos = geDatosRubroEvaluacionProceso.getBeDatosTareaRecursos();
        if(beDatosTareaRecursos!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class,beDatosTareaRecursos.getRecursoDidactico(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class,beDatosTareaRecursos.getTareasRecursos(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class,beDatosTareaRecursos.getTareas(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(TareaRubroEvaluacionProceso.class,beDatosTareaRecursos.getTareaRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);
        }


        BEDatosEvaluacionResultado beDatosEvaluacionResultado = geDatosRubroEvaluacionProceso.getBeDatosRubroEvaluacionResultado();
        if(beDatosEvaluacionResultado!=null){
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionResultado.class,beDatosEvaluacionResultado.getRubroEvaluacionResultado(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(EvaluacionResultadoC.class,beDatosEvaluacionResultado.getEvaluacionResultado(), syncFlag, databaseWrapper, false);
        }

    }
    //#endregion changeEstadoGlobals GEDatosRubroEvaluacionProceso

    //#region changeEstadoGlobals BEDatosSesionAprendizaje
    private void changeEstadoGlobals(BEDatosSesionAprendizaje beDatosSesionAprendizaje, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(SesionAprendizaje.class, beDatosSesionAprendizaje.getSesionAprendizaje(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, beDatosSesionAprendizaje.getCompetencias(),syncFlag,databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, beDatosSesionAprendizaje.getCapacidades(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(DesempenioIcd.class, beDatosSesionAprendizaje.getDesempenioIcd(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Icds.class, beDatosSesionAprendizaje.getIcd(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(CampoTematico.class, beDatosSesionAprendizaje.getCampoTematico(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, beDatosSesionAprendizaje.getRecursoDidacticoEvento(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, beDatosSesionAprendizaje.getTareaEvento(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, beDatosSesionAprendizaje.getTareaEventoRecurso(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(ActividadAprendizaje.class, beDatosSesionAprendizaje.getActividadEvento(),syncFlag,databaseWrapper,  false);

        TransaccionUtils.fastStoreListSyncFlagUpdateRel(UnidadAprendizaje.class, beDatosSesionAprendizaje.getUnidadAprendizaje(),syncFlag,databaseWrapper,  false);
    }
    //#endregion changeEstadoGlobals BEDatosSesionAprendizaje

    //#region changeEstadoGlobals GEDatosTareasRecursos
    private void changeEstadoGlobals(GEDatosTareasRecursos geDatosTareasRecursos, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, geDatosTareasRecursos.getTareas(), syncFlag, databaseWrapper, true);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, geDatosTareasRecursos.getTareasRecursos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RecursoArchivo.class, geDatosTareasRecursos.getRecursoArchivo(), syncFlag, databaseWrapper, false);

        SQLite.update(Archivo.class)
                .set(Archivo_Table.syncFlag.eq(syncFlag))
                .execute(databaseWrapper);
    }
    //#endregion changeEstadoGlobals GEDatosTareasRecursos
    //#endregion

    //#region initNotification Padre Mentor
    protected void initNotification(BEGuardarEntidadesGlobal beGuardarEntidadesGlobal){
        //hallar el cambio-tareacurso
        int programaEducativoId= beGuardarEntidadesGlobal.getProgramaEducativoId();
        List<Caso> casosList = beGuardarEntidadesGlobal.getCasos()!=null?beGuardarEntidadesGlobal.getCasos().getCaso():new ArrayList<Caso>();
        List<CasoReporte> casosReporteList = beGuardarEntidadesGlobal.getCasos()!=null?beGuardarEntidadesGlobal.getCasos().getCasoReporte(): new ArrayList<CasoReporte>();
        List<EvaluacionProcesoC>evaluacionProcesoCList = beGuardarEntidadesGlobal.getRubroEvaluacionProceso()!=null?beGuardarEntidadesGlobal.getRubroEvaluacionProceso().getBeDatosRubroEvaluacionProceso().getEvaluacionProceso(): new ArrayList<EvaluacionProcesoC>();
        List<AsistenciaSesionAlumnoC>asistenciaSesionAlumnoCList= beGuardarEntidadesGlobal.getAsistencia()!=null?beGuardarEntidadesGlobal.getAsistencia().getBeDatosEnvioAsistencia().getAsistenciaAlumnos(): new ArrayList<AsistenciaSesionAlumnoC>();
        List<TareasC> tareasCList = beGuardarEntidadesGlobal.getTareaRecursos()!=null?beGuardarEntidadesGlobal.getTareaRecursos().getTareas(): new ArrayList<TareasC>();
        Log.d(TAG, "asistencia  Size: "+  asistenciaSesionAlumnoCList.size());


        Set<Integer> evaluacionAlumnoIdList = new LinkedHashSet<>();

        if(evaluacionProcesoCList.size()!=0)
        {
            List<String>Stringlist= new ArrayList<>();
            for( EvaluacionProcesoC evaluacionProcesoC: evaluacionProcesoCList){
                Stringlist.add(evaluacionProcesoC.key);
            }

            List<EvaluacionProcesoC >evaluacionProcesoCList2= SQLite
                    .select(Utils.f_allcolumnTable(EvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                    .from(EvaluacionProcesoC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(EvaluacionProcesoC_Table.key.withTable().in(Stringlist))
                    .and(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable().isNotNull())
                    .and(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable().notEq(""))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().eq(0))
                    .and(RubroEvaluacionProcesoC_Table.tiporubroid.withTable().notEq(RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.withTable().notEq(280))
                    .groupBy(Utils.f_allcolumnTable(EvaluacionProcesoC_Table.ALL_COLUMN_PROPERTIES))
                    .orderBy(EvaluacionProcesoC_Table.fechaCreacion.withTable().desc()).queryList();

            for(EvaluacionProcesoC evaluacion:evaluacionProcesoCList2)
            {
                if(evaluacion.syncFlag== BaseEntity.FLAG_UPDATED || evaluacion.syncFlag== BaseEntity.FLAG_ADDED)
                    if(evaluacion.getPublicado()==1) evaluacionAlumnoIdList.add(evaluacion.getAlumnoId());
            }
        }

        Log.d(TAG, "casosReporteList "+casosReporteList.size()+ " casoList "+casosList.size());
        Set<Integer> casoAlumnoIdList = new LinkedHashSet<>();
        if(casosReporteList.size()!=0){
            for(CasoReporte casoReporte:casosReporteList ){
                for(Caso caso:casosList){
                    if(casoReporte.getCasoId().equals(caso.getKey())){
                        List<Usuario>padres= alumnoDao.getPadres(caso.getAlumnoId());
                        for(Usuario padre: padres){
                            if(casoReporte.getUsuarioDestinoId()==padre.getUsuarioId())
                                casoAlumnoIdList.add(caso.getAlumnoId());
                        }
                    }
                }
            }
        }

        int countTarea = 0;
        for(TareasC tareasC: tareasCList){
            if(tareasC.syncFlag== BaseEntity.FLAG_UPDATED || tareasC.syncFlag== BaseEntity.FLAG_ADDED){
                countTarea++;
                break;
            }

        }

        Set<Integer> asistenciaAlumnoIdList = new LinkedHashSet<>();
        if(!asistenciaSesionAlumnoCList.isEmpty()){
            for(AsistenciaSesionAlumnoC asistencia: asistenciaSesionAlumnoCList)
                asistenciaAlumnoIdList.add(asistencia.getAlumnoId());
        }

        int tareaCount = countTarea;
        int[] evaluacionIdList = ArrayUtils.toPrimitiveArray(new ArrayList<Integer>(evaluacionAlumnoIdList));
        int[] casosIdList  = ArrayUtils.toPrimitiveArray(new ArrayList<Integer>(casoAlumnoIdList));
        int[] asistenciaIdList  = ArrayUtils.toPrimitiveArray(new ArrayList<Integer>(asistenciaAlumnoIdList));

        SyncIntenService.Model model = new SyncIntenService.Model();
        if(tareaCount!=0){
            model.setTarea(true);
        }

        if(evaluacionIdList!=null){
            model.setEvaluacion(evaluacionIdList);
        }

        if(casosIdList!=null){
            model.setCasos(casosIdList);
        }

        if(asistenciaIdList!=null){
            model.setAsistencia(asistenciaIdList);
        }


        if(tareaCount!=0 ||
                (evaluacionIdList!= null && evaluacionIdList.length != 0) ||
                (casosIdList!= null && casosIdList.length != 0) ||
                (asistenciaIdList!= null && asistenciaIdList.length != 0)) {

            final String json =  new Gson().toJson(model);
            String NOTIFICACION_FULL = "complejo";
            sendNotificacionPadre(NOTIFICACION_FULL, programaEducativoId,json);
        }

    }

    public void sendNotificacionPadre(String objeto, int programaEducativoId, String complejo){
        long date= Calendar.getInstance().getTimeInMillis();
        DatabaseReference myRef =  FirebaseDatabase.getInstance()
                .getReference("padre_mentor")
                .child("icrmedu_padre").child("programa_edu");
        DatabaseReference reference = myRef
                .child("PE_"+programaEducativoId)
                .child(objeto);
        reference.child("fecha").setValue(date);
        reference.child("alumnoId").setValue(complejo);
    }

    public static class Model{
        private int[] asistencia = new int[0];
        private int[] evaluacion = new int[0];
        private int[] casos = new int[0];
        private boolean tarea;


        public int[] getAsistencia() {
            return asistencia;
        }

        public void setAsistencia(int[] asistencia) {
            this.asistencia = asistencia;
        }

        public int[] getEvaluacion() {
            return evaluacion;
        }

        public void setEvaluacion(int[] evaluacion) {
            this.evaluacion = evaluacion;
        }

        public int[] getCasos() {
            return casos;
        }

        public void setCasos(int[] casos) {
            this.casos = casos;
        }

        public boolean isTarea() {
            return tarea;
        }

        public void setTarea(boolean tarea) {
            this.tarea = tarea;
        }
    }
    //#endregion
}
