package com.consultoraestrategia.ss_crmeducativo.utils;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class ReferenceFirestore {


    public static CollectionReference getInstance(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        return  db.collection("icrmedu_padre");
    }

    public static CollectionReference getInstanceChat(){

        return getInstance().document("chat").collection("chat");
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
