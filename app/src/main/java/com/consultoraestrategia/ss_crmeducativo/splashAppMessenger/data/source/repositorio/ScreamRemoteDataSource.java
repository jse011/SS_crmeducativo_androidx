package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.repositorio;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosContacto;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.ScreamSplasDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.OkHttpClient;
import retrofit2.Call;

public class ScreamRemoteDataSource implements ScreamSplasDataSource {

    private final String urlServidor;
    private UtilServidor utilServidor;
    private String TAG = ScreamRemoteDataSource.class.getSimpleName();
    private ProgressListener progressDownloadingListener;
    private ProgressListener progressUploadingListener;

    public ScreamRemoteDataSource(UtilServidor utilServidor) {
        this.utilServidor = utilServidor;
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
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

    public class UpdateFireBase{
        public long date;
        public int count;
        public boolean update;
    }

    @Override
    public RetrofitCancel sincronizarInformacion(final ProgressCallBack callBack) {
        SessionUser sessionUser = SessionUser.getCurrentUser();
        final int usuarioId = sessionUser!=null?sessionUser.getUserId():0;

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
        int anioAcademicoId = 0;

        if(rolGeoreferencia!=null){
            entidadId = rolGeoreferencia.getEntidadId();
            georeferenciaId = rolGeoreferencia.getGeoReferenciaId();

            List<AnioAcademico> anioAcademicoList = SQLite.select()
                    .from(AnioAcademico.class)
                    .where(AnioAcademico_Table.georeferenciaId.eq(georeferenciaId))
                    .queryList();

            Collections.sort(anioAcademicoList, new Comparator<AnioAcademico>() {
                public int compare(AnioAcademico o1, AnioAcademico o2) {
                    return Utils.convertirfecha(o2.getFechaFin()).compareTo(Utils.convertirfecha(o1.getFechaFin()));
                }
            });


            for(AnioAcademico anioAcademico : anioAcademicoList){
                if(anioAcademico.isToogle())anioAcademicoId = anioAcademico.getIdAnioAcademico();
            }

            if(!anioAcademicoList.isEmpty()&&anioAcademicoId==0)anioAcademicoId = anioAcademicoList.get(0).getIdAnioAcademico();
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

        Set<Integer> cargaCursoIdList = new LinkedHashSet<>();
        for (CargaCursos itemCargaCursos : cargaCursosList){
            cargaCursoIdList.add(itemCargaCursos.getCargaCursoId());
        }


        return flst_ObtenerCargaAcademicaEstudianteLiteFull(usuarioId, georeferenciaId, new ArrayList<Integer>(cargaCursoIdList), new ProgressCallBack() {
            @Override
            public void onLoad(boolean success, BEDatosContacto beListaPadre) {
                if(success){
                    callBack.onLoad(true, beListaPadre);
                }else {
                    callBack.onLoad(false, null);
                }
            }

            @Override
            public void onNewPreLoad() {
                callBack.onNewPreLoad();
            }

            @Override
            public void onRequestProgress(int progress) {
                callBack.onRequestProgress(progress);
            }

            @Override
            public void onResponseProgress(int progress) {
                callBack.onResponseProgress(progress);
            }
        });
    }

    private RetrofitCancel<BEDatosContacto> flst_ObtenerCargaAcademicaEstudianteLiteFull(int usuarioId, int georeferenciaId, final List<Integer> cargaCursoIdList, final ProgressCallBack callBack) {


        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();

        OkHttpClient.Builder builder = ProgressManager.getInstance().with(new OkHttpClient.Builder());

        builder.connectTimeout(5, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(15, TimeUnit.SECONDS) // write timeout
                .readTimeout(15, TimeUnit.SECONDS);

        apiRetrofit.setOkHttpClient(builder.build());

        Call<RestApiResponse<BEDatosContacto>> responseCall = apiRetrofit.flst_getDatosContacto(usuarioId, georeferenciaId, cargaCursoIdList);
        RetrofitCancel<BEDatosContacto> retrofitCancel = new RetrofitCancelImpl<>(responseCall);
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosContacto>() {
            @Override
            public void onResponse(final BEDatosContacto response) {
                if(response == null){
                    callBack.onLoad(false, null);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Contrato> contratoAcadList = SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                                    .from(Contrato.class)
                                    .innerJoin(DetalleContratoAcad.class)
                                    .on(Contrato_Table.idContrato.withTable()
                                            .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                                    .innerJoin(SilaboEvento.class)
                                    .on(DetalleContratoAcad_Table.cargaCursoId.withTable()
                                            .eq(SilaboEvento_Table.cargaCursoId.withTable()))
                                    .where(DetalleContratoAcad_Table.cargaCursoId.withTable().in(new ArrayList<>(cargaCursoIdList)))
                                    .queryList();

                            List<Integer> contratoIdList = new ArrayList<>();
                            List<Integer> personaIdList = new ArrayList<>();
                            List<Integer> cargaCursoDocenteIdList = new ArrayList<>();
                            for (Contrato contrato: contratoAcadList){
                                contratoIdList.add(contrato.getIdContrato());
                                personaIdList.add(contrato.getPersonaId());
                            }

                            List<CargaCursoDocente> cargaCursoDocenteList = SQLite.select()
                                    .from(CargaCursoDocente.class)
                                    .where(CargaCursoDocente_Table.cargaCursoId.in(cargaCursoIdList))
                                    .queryList();

                            for (CargaCursoDocente cargaCursoDocente : cargaCursoDocenteList)cargaCursoDocenteIdList.add(cargaCursoDocente.getCargaCursoDocenteId());

                            TransaccionUtils.deleteTable(Relaciones.class, Relaciones_Table.personaPrincipalId.in(personaIdList));
                            TransaccionUtils.deleteTable(Contrato.class, Contrato_Table.idContrato.in(contratoIdList));
                            TransaccionUtils.deleteTable(CargaCursoDocente.class, CargaCursoDocente_Table.cargaCursoDocenteId.in(cargaCursoDocenteIdList));
                            TransaccionUtils.deleteTable(CargaCursoDocenteDet.class, CargaCursoDocenteDet_Table.cargaCursoDocenteId.in(cargaCursoDocenteIdList));
                            TransaccionUtils.deleteTable(DetalleContratoAcad.class, DetalleContratoAcad_Table.idContrato.in(contratoIdList));
                            TransaccionUtils.deleteTable(Directivos.class);

                            TransaccionUtils.fastStoreListInsert(Contrato.class, response.getContratos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, response.getDetalleContratoAcad(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Persona.class, response.getPersonas(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Usuario.class, response.getUsuariosrelacionados(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Relaciones.class, response.getRelaciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Empleado.class, response.getEmpleados(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, response.getCargaCursoDocente(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, response.getCargaCursoDocenteDet(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Directivos.class, response.getDirectivos(), databaseWrapper, true);

                            callBack.onLoad(true, response);
                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {

                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                            callBack.onLoad(false, null);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                ProgressManager.getInstance().notifyOnErorr(urlServidor, new Exception());
                callBack.onLoad(false, null);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        setupListener(callBack);


        return retrofitCancel;
    }

    @Override
    public String getUrlServidorUrl() {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
       /* String urlServidor = TextUtils.isEmpty(apiRetrofit.getUrl())?"":apiRetrofit.getUrl().trim();
        String path = "";
        try {
            URI uri = new URI(urlServidor);
            path = uri.getAuthority();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Log.d(TAG,"getUrlServidorUrl: " + path);*/
        return apiRetrofit.getUrl();
    }

    private void setupListener(final ProgressCallBack callBack){
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
                callBack.onResponseProgress(progress);

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
                callBack.onRequestProgress(progress);
            }

            @Override
            public void onError(long id, Exception e) {

            }
        };
    }

}
