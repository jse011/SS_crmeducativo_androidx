package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import android.net.Uri;
import androidx.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GetFotoFirebaseStorage {

    public void execute(int georeferencia, int personaId, int position, final Callback callback){
        final String fileName = String.valueOf(position);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Colegios").child("icrmedu_docente").child(String.valueOf(georeferencia)).child(String.valueOf(personaId)).child(fileName);
        storageReference.getDownloadUrl()
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful())
                {
                    Uri uri = task.getResult();
                    if(uri!=null){
                        callback.onSucess(uri.toString());
                        Log.d("GetFotoFirebaseStorage", "success " + uri.toString());
                    }else {
                        callback.onError();
                        Log.d("GetFotoFirebaseStorage", "error uri");
                    }

                }
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Log.d("GetFotoFirebaseStorage", "error");
                callback.onError();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("GetFotoFirebaseStorage", "onFailure");
                callback.onError();
            }
        });
    }

    public interface Callback{
        void onSucess(String url);
        void onError();
    }
}
