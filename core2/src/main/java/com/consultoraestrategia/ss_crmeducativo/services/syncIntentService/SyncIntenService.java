package com.consultoraestrategia.ss_crmeducativo.services.syncIntentService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao.AlumnoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.services.cache.CacheImageRepository;
import com.consultoraestrategia.ss_crmeducativo.services.cache.CacheImageRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.util.ImportarCountThread;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by SCIEV on 12/06/2018.
 */

public abstract class  SyncIntenService extends Worker implements ImportarCountThread.ImportarCountDownTimerCallback {

    protected final static String NAME_SERVICE_COMPLEJO = "NAME_SERVICE_COMPLEJO";
    protected final static String NAME_SERVICE_SIMPLE = "NAME_SERVICE_SIMPLE";
    private ImportarCountThread importarCountDownTimer;




    public static final String NOTIFICACION_ASISTENCIA="asistencia";
    public static final String NOTIFICACION_EVALUACION="evaluacion";
    public static final String NOTIFICACION_TAREA="tarea";
    public static final String NOTIFICACION_CASOS="casos";
    public static final String NOTIFICACION_FULL="complejo";

    public final static String TAG = SyncIntenService.class.getSimpleName();
    protected ServiceLocalDataRepository serviceLocalDataRepository;
    protected ServiceRemoteDataRepository serviceRemoteDataRepository;
    protected CacheImageRepository cacheImageRepository;
    protected static int value = 0;
    protected NotificationCompat.InboxStyle inboxStyle;
    protected Bitmap bitmap;
    abstract int getMaxSecondsInMillis();
    abstract int getCountInterval();

    public AlumnoDao alumnoDao= InjectorUtils.provideAlumnoDao();

    public SyncIntenService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Servicio creado...");
        value = 0;
        setupRepository();
        setupNotificacion();


        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = new ImportarCountThread(getMaxSecondsInMillis(), getCountInterval(), this);
        importarCountDownTimer.start();
        Result result = Result.failure();
        try {
            SessionUserDao sessionUserDao = InjectorUtils.provideSessionUserDao();
            SessionUser sessionUser = sessionUserDao.getCurrentUser();
            if(sessionUser==null|| !sessionUser.isDataImported()){
                Log.d(TAG, "Cancelar service ");
                destruirTimer();
                sendFinish();
            }else {
                onHandle(getInputData());
                destruirTimer();
                sendFinish();
            }

            result = Result.success();

        }catch (Exception e){
            e.printStackTrace();
            destruirTimer();
            sendFinish();
        }

        return result;
    }

    protected void destruirTimer(){
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = null;
    }

    abstract void sendFinish();


    private void setupNotificacion() {
        inboxStyle = new NotificationCompat.InboxStyle();
       bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.base_academico);
    }

    private void setupRepository() {
        serviceLocalDataRepository = RepositoryInjector.getServiceLocalDataRepository();
        serviceRemoteDataRepository = RepositoryInjector.getServiceRemoteDataRepository();
        cacheImageRepository = new CacheImageRepositoryImpl(getApplicationContext());
    }


    protected abstract void onHandle(Data data);


    @Override
    public void onImportarCountDownTimerCount(int counter) {

    }

    @Override
    public void onImportarCountDownTimerFinish() {

    }

    @Override
    public void onImportarCountDownProgress(int progress) {
        //Comunicamos el progreso
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_PROGRESO);
        bcIntent.putExtra("progreso", progress);
        Log.d(TAG, "progreso service : "+ progress);
        //sendBroadcast(bcIntent);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }


    @Override
    public void onStopped() {
        sendFinish();
        super.onStopped();
        Log.d(TAG, "Servicio destruido...");
    }




    protected final int NOTIFICATION_IDCONECTIVIDAD = 237;
    protected static final int NOTIFICATION_ID = 238;
    protected static final int NOTIFICATION_IDSAVE = 239;
    protected static final int NOTIFICATION_IDESTADO = 240;

    public void showNotificacionConectividad(String mensaje)
    {
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        value ++;
        NotificationManager nManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setContentTitle("CRMEducativo");
        builder.setContentText(value + " errores de conectividad");
        builder.setSmallIcon(R.drawable.logo_consultoraa);
        builder.setLargeIcon(bitmap);
        builder.setAutoCancel(true);
        inboxStyle.setBigContentTitle("Error en los datos");
        inboxStyle.addLine(value + ". " + mensaje +".");
        builder.setStyle(inboxStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            if(nManager!=null) nManager.createNotificationChannel(mChannel);
        }
        if(nManager!=null) nManager.notify("App Name",NOTIFICATION_IDCONECTIVIDAD,builder.build());
    }

    public void showNotificacion(Context context, int id, String titulo, String mensaje)
    {
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        Notification noti =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo_consultoraa)
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setColor(Color.parseColor("#2980b9"))
                        .build();

        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            if(mNotificationManager!=null) mNotificationManager.createNotificationChannel(mChannel);
        }
        if(mNotificationManager!=null) mNotificationManager.notify(id, noti);
    }

    protected void saveCacheImage(BERespuesta respuesta){
        Set<String> urlImageList = new LinkedHashSet<>();
        BEDatosEnvioTipoNota beDatosEnvioTipoNota = respuesta.getTiponota();
        if(beDatosEnvioTipoNota!=null){
            for (ValorTipoNotaC valorTipoNotaC : beDatosEnvioTipoNota.getValorTipoNota() ){
                urlImageList.add(valorTipoNotaC.getIcono());
            }
        }
        cacheImageRepository.save(new ArrayList<String>(urlImageList), 35);

        Set<String> urlImageList2 = new LinkedHashSet<>();
        BEObtenerDatosLogin beObtenerDatosLogin = respuesta.getLogin();
        if(beObtenerDatosLogin!=null){
            for (Persona persona : beObtenerDatosLogin.getPersonas() ){
                urlImageList2.add(persona.getFoto());
            }
        }
        cacheImageRepository.save(new ArrayList<String>(urlImageList2), 35);

    }

    protected void initNotification(BEGuardarEntidadesGlobal item, BERespuesta respuesta){
        //hallar el cambio-tareacurso
        int programaEducativoId= item.getProgramaEducativoId();
        List<Caso> casosList = item.getCasos().getCaso();
        List<CasoReporte> casosReporteList = item.getCasos().getCasoReporte();
        List<EvaluacionProcesoC>evaluacionProcesoCList = item.getRubroEvaluacionProceso().getBeDatosRubroEvaluacionProceso().getEvaluacionProceso();
        List<AsistenciaSesionAlumnoC>asistenciaSesionAlumnoCList= item.getAsistencia().getBeDatosEnvioAsistencia().getAsistenciaAlumnos();
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
                if(respuesta.isCommit_RubroEvaluacionProceso()){
                    if(evaluacion.syncFlag== BaseEntity.FLAG_UPDATED || evaluacion.syncFlag== BaseEntity.FLAG_ADDED)
                        if(evaluacion.getPublicado()==1) evaluacionAlumnoIdList.add(evaluacion.getAlumnoId());

                }
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
        if(respuesta.isCommit_TareaRecurso()){
            for(TareasC tareasC: item.getTareaRecursos().getTareas()){
                if(tareasC.syncFlag== BaseEntity.FLAG_UPDATED || tareasC.syncFlag== BaseEntity.FLAG_ADDED){
                    countTarea++;
                    break;
                }

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

        Model model = new Model();
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
}

