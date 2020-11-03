package com.consultoraestrategia.ss_crmeducativo.stiker2.repositorio;

import android.util.Log;

import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.CategoriaUi;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.StikersUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.ReferenceFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StickersRepositorioImpl implements StickersRepositorio {

    private String TAG = StickersRepositorioImpl.class.getSimpleName();

    @Override
    public void getStikersFirebase(final Callback<List<CategoriaUi>> callback) {


        ReferenceFirestore.getInstanceStikerDefaul()
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    if (queryDocumentSnapshots != null) {
                        List<CategoriaUi> categoryUiList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            CategoriaUi categoriaUi = new CategoriaUi();
                            categoriaUi.setId(documentSnapshot.getId());
                            categoriaUi.setNombre(UtilsFirebase.convert(documentSnapshot.get("nombre"), ""));
                            categoriaUi.setTipo(UtilsFirebase.convert(documentSnapshot.get("tipo"),""));
                            List<StikersUi> stikersUiList = new ArrayList<>();
                            try {
                                List<String> imagenes = (List<String>) documentSnapshot.get("imagenes");
                                for (String imagen : imagenes!=null?imagenes:new ArrayList<String>()){
                                    StikersUi stikersUi = new StikersUi(imagen);
                                    stikersUi.setTipo(categoriaUi.getTipo());
                                    stikersUiList.add(stikersUi);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            categoriaUi.setStikersUiList(stikersUiList);
                            categoryUiList.add(categoriaUi);
                        }
                        callback.onLoad(true, categoryUiList);
                    } else {
                        callback.onLoad(false, null);
                    }

                } else {
                    callback.onLoad(false, null);
                }
            }
        });
    }
}
