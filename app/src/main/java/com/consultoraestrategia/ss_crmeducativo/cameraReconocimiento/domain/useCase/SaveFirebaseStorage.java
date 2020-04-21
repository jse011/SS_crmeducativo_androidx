package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class SaveFirebaseStorage {


    private StorageTask<UploadTask.TaskSnapshot> snapshotStorageTask;
    private Callback callback;

    public void execute(String url, int georeferencia, int personaId, int position, final Callback callback){
        this.callback = callback;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        File file = new File(url);
        Uri myUri = Uri.fromFile(file);

        StorageReference storageReference = storage.getReference().child("Colegios").child("icrmedu_docente").child(String.valueOf(georeferencia)).child(String.valueOf(personaId)).child(String.valueOf(position));
        snapshotStorageTask = storageReference.putFile(myUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        callback.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        callback.onError();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        //track the progress of = our upload
                        callback.onProgress((int) ((double) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount())));
                    }
                });
    }

    public void cancel(){
        if(snapshotStorageTask!=null)snapshotStorageTask.pause();
       if(snapshotStorageTask!=null)snapshotStorageTask.cancel();
       if(callback!=null)callback.onCancel();
    }

    public interface Callback{
        void onSuccess();
        void onError();
        void onProgress(int position);
        void onCancel();
    }
}
