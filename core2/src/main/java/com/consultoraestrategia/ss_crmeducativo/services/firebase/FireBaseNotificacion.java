package com.consultoraestrategia.ss_crmeducativo.services.firebase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SesionUserDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.firebase.entidad.FireBaseEstado;
import com.consultoraestrategia.ss_crmeducativo.services.firebase.entidad.FireBaseModel;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.Date;


public class FireBaseNotificacion implements ChildEventListener, ValueEventListener {

    private static FireBaseNotificacion mInstance;
    private final SessionUserDao sessionUserDao;
    private final FirebaseDatabase database;
    private DatabaseReference myRef;
    private final static String TAG = FireBaseNotificacion.class.getSimpleName();
    private static final String REFERENCIA = "DataBase";
    private FireBaseModel fireBaseModel;
    private SessionUser sessionUser;
    private DatabaseReference connectedRef;

    private FireBaseNotificacion() {
        database = FirebaseDatabase.getInstance();
        this.sessionUserDao = InjectorUtils.provideSessionUserDao();
        fireBaseModel = new FireBaseModel();
        setupFireBase();
    }


    private void setupFireBase() {
        connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        sessionUser = sessionUserDao.getCurrentUser();
        if(sessionUser != null){
            myRef = database.getReference(REFERENCIA).child(String.valueOf(sessionUser.getUserId()));
            onInit();
        }

    }


    private void onInit(){
        if(myRef==null)return;
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild("basedatos")) dataSnapshot.getRef().setValue(fireBaseModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static FireBaseNotificacion getInstance() {
        if (mInstance == null) {
            mInstance = new FireBaseNotificacion();
        }
        return mInstance;
    }
    
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildAdded :" + dataSnapshot);

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildChanged :" + dataSnapshot);
        /*
        try {
            if(dataSnapshot.getKey().equals("basedatos")){
                FireBaseEstado estadoFireBase = dataSnapshot.getValue(FireBaseEstado.class);
                if(estadoFireBase!=null){
                    //sessionUser.setFechaServidor(estadoFireBase.getFechaservidor());
                    //sessionUserDao.changeFechaServidor(estadoFireBase.getFechaservidor());
                    if(sessionUser.getUserId() == estadoFireBase.getUsuarioid()){
                        if(!id.equals(estadoFireBase.getId()))
                            SyncIntenService.start(database.getApp().getApplicationContext());
                    }
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

//        SyncIntenService.start(database.getApp().getApplicationContext());

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d(TAG, "onChildRemoved :" + dataSnapshot);

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildMoved :" + dataSnapshot);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        boolean connected = dataSnapshot.getValue(Boolean.class);
        if (connected) {
            System.out.println("connected");
        } else {
            System.out.println("not connected");
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "onCancelled" );
    }

    public static void onStarted(){
        if(mInstance!=null) if(mInstance.myRef!=null)mInstance.myRef.addChildEventListener(mInstance);
        if(mInstance!=null) if(mInstance.connectedRef!=null)mInstance.connectedRef.addValueEventListener(mInstance);
    }

    public static void onDestroy(){
        if(mInstance!=null) if(mInstance.myRef!=null)mInstance.myRef.removeEventListener((ChildEventListener)mInstance);
        if(mInstance!=null) if(mInstance.myRef!=null)mInstance.myRef.removeEventListener((ValueEventListener)mInstance);
    }

}
