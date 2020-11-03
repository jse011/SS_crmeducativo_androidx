package com.consultoraestrategia.ss_crmeducativo.utils;


import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;
import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig_Table;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class ReferenceFirestore {


    public static CollectionReference getInstance(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        WebConfig webConfig = SQLite.select()
                .from(WebConfig.class)
                .where(WebConfig_Table.nombre.eq("wstr_Servidor"))
                .querySingle();
        String servidor = webConfig!=null?webConfig.getContent():"appmessage_error";
        return  db.collection(servidor);
    }

    public static CollectionReference getInstanceChat(){
        return getInstance().document("chat").collection("chat");
    }

    public static CollectionReference getInstanceStikerDefaul(){
        return getInstance().document("stikers").collection("stikers");
    }


    /*public  static CollectionReference getMessage(){
        return  getInstance().document("message").collection("message");
    }*/

    /*public  static CollectionReference getSeen(){
        return  getInstance().document("seen").collection("seen");
    }*/


    /*public  static CollectionReference getPersons(){
        return  getInstance().document("person").collection("persons");
    }*/

    public static void  init(){
//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(true)
//                .build();
//        FirebaseFirestore db=FirebaseFirestore.getInstance();
//        db.setFirestoreSettings(settings);

    }

}
