package com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.data.source.CrmNotificationGetwayImpl;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.entities.Paquete;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEFireBase;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ui.ImportarJobService;
import com.consultoraestrategia.ss_crmeducativo.services.importar.util.LlamarImportarService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;


public class CrmeNotificationServiceImpl implements CrmeNotificationService, ChildEventListener {

    private final FirebaseDatabase database;
    private Context context;
    private MyWebRequestReceiver receiver;
    private CrmeNotificationPresenter crmeNotificationPresenter;
    private DatabaseReference myRef;
    private EventNotificacion eventNotificacion;
    private final static String TAG = CrmeNotificationServiceImpl.class.getSimpleName();



    public static CrmeNotificationServiceImpl init(Context context) {
        return new CrmeNotificationServiceImpl(context);
    }

    private CrmeNotificationServiceImpl(Context context) {
        this.context = context;
        database = FirebaseDatabase.getInstance();
        setupPresenter();
        setupReceiver();
        Log.d(TAG, "init");
    }



    private void setupReceiver() {
        IntentFilter filter = new IntentFilter(MyWebRequestReceiver.PROCESS_RESPONSE);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new MyWebRequestReceiver();
        context.registerReceiver(receiver, filter);
    }

    private void setupPresenter() {
        crmeNotificationPresenter = new CrmeNotificationPresenterImpl(new CrmNotificationGetwayImpl());
        crmeNotificationPresenter.attachService(this);
        crmeNotificationPresenter.onInicio();
    }

    @Override
    public void setListnerFirebase(String referenciaFirebase, String nodo) {
        myRef = database.getReference(referenciaFirebase).child(nodo);
        myRef.addChildEventListener(this);
    }

    @Override
    public void setListnerSingleFirebase(String referenciaFirebase, String nodo, final Paquete[] paquetes) {
       /* database.getReference(referenciaFirebase).child(nodo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,BEFireBase> beFireBaseList = new HashMap<>();
                for (Paquete paquete : paquetes){
                    if(dataSnapshot.hasChild(paquete.getNombre())){
                        try {
                            BEFireBase beFireBase = dataSnapshot.child(paquete.getNombre()).getValue(BEFireBase.class);
                            beFireBaseList.put(paquete.getNombre(), beFireBase);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        //dataSnapshot.getReference(paquete.getNombre()).setValue(fireBaseModel);
                    }
                }
                crmeNotificationPresenter.onFinalizarSingleFirebase(beFireBaseList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    @Override
    public void ejecutarbedatoscalendarioperiodo(BEVariables beVariables) {
       // LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.CALENDARIO_PERIODO, beVariables);
    }

    @Override
    public void ejecutarbedatostiponota(BEVariables beVariables) {
        LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.TIPONOTA, beVariables);
    }

    @Override
    public void ejecutarbedatosunidadprendizaje(BEVariables beVariables) {
       // LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.UNIDAD, beVariables);
    }

    @Override
    public void ejecutarstrategylogin() {
        //LoginImportarService.jobServiceLogin(context);
    }

    @Override
    public void ejecutarbedatossesionaprendizaje(BEVariables beVariables) {

        LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.SESIONES, beVariables);
    }

    @Override
    public void ejecutargedatosenvioasistencia(BEVariables beVariables) {
        //LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.ASISTENCIA, beVariables);
    }

    @Override
    public void ejecutargedatosrubroevaluacionproceso(BEVariables beVariables) {
       // LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.RUBROEVALUACION, beVariables);
    }

    @Override
    public void ejecutarbedatostarearecursos(BEVariables beVariables) {
        //LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.TAREA, beVariables);
    }

    @Override
    public void ejecutarcutarAlumnoVigencia(BEVariables beVariables) {

    }

    @Override
    public void subscribeToTopic(String id) {
        FirebaseMessaging.getInstance().subscribeToTopic(id);
    }

    @Override
    public void unsubscribeFromTopic(String id) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(id);
    }

    @Override
    public void ejecutarcutarCalendarioPeriodo(BEVariables beVariables) {
        Log.d(TAG, "ejecutarcutarCalendarioPeriodo");
        LlamarImportarService.jobServiceExportarTipos(context,TipoImportacion.CALENDARIO_PERIODO, beVariables);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildAdded2 :" + dataSnapshot);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildChanged :" + dataSnapshot);
        try {
            crmeNotificationPresenter.onChildChanged(dataSnapshot.getKey(), dataSnapshot.getValue(BEFireBase.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "onCancelled" );
        crmeNotificationPresenter.onCancelled();
    }


    public class MyWebRequestReceiver extends BroadcastReceiver {

        public static final String PROCESS_RESPONSE = "com.as400samplecode.intent.action.PROCESS_RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean success = intent.getBooleanExtra(ImportarJobService.RESPONSE_BOOLEAN, false);
            String reponseMessage = intent.getStringExtra(ImportarJobService.RESPONSE_MESSAGE);
            BEVariables beVariables = BEVariables.getBundle(intent.getExtras());
            Log.d(TAG, "success: " + success);
            Log.d(TAG, "mesaje: " + reponseMessage);
            Log.d(TAG, beVariables.toString());
            try {
                crmeNotificationPresenter.onReceive(TipoImportacion.valueOf(reponseMessage), success,beVariables);
                if(eventNotificacion!=null)eventNotificacion.onReceive(TipoImportacion.valueOf(reponseMessage), success,beVariables);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    public void onDestroy(){
        crmeNotificationPresenter.onDestroy();
        if(myRef!=null)myRef.removeEventListener(this);
        if(context!=null)if(receiver!=null)context.unregisterReceiver(receiver);
        context = null;
        eventNotificacion = null;
    }


    public interface EventNotificacion{
        void onReceive(TipoImportacion tipoImportacion, boolean success, BEVariables beVariables);
    }

    public void setEventNotificacion(EventNotificacion eventNotificacion) {
        this.eventNotificacion = eventNotificacion;
    }
}
