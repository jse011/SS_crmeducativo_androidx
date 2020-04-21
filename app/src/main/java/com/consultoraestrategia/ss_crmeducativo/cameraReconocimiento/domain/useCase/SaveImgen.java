package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class SaveImgen {
    private String NameOfFolder = "/DetectorRostro";
    private String NameOfFile = "imagen-";
    private byte[]jpg;
    private final FirebaseStorage storage;
    private final FirebaseDatabase database;
    private PersonaUi personaUi;
    private int currentProgress;
    private int mOrientation;

    public SaveImgen() {
        storage = FirebaseStorage.getInstance(); //return an object of Firebase Storage
        database = FirebaseDatabase.getInstance(); //return an object of Firebase Database
    }

    public void execute(PersonaUi personaUi, byte[] jpg, int mOrientation, CallBack callBack){
        this.jpg = jpg;
        this.personaUi = personaUi;
        this.mOrientation = mOrientation;
        currentProgress = 0;
        multiplEnvio(callBack);
    }

    public void executeReintentar(final CallBack callBack){
        currentProgress=0;
        if(personaUi!=null&&jpg!=null){
            final String fileName = personaUi.getPersonaId() + "_"+cantidad+".jpg";
            String url = SaveImage(jpg);
            StorageReference storageReference = storage.getReference();// returns root path
            Uri myUri = Uri.fromFile(new File(url));
            storageReference.child("Docentes").child(String.valueOf(personaUi.getGeoreferenciId())).child(fileName).putFile(myUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getMetadata().getReference().getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String generatedFilePath = uri.toString();
                                            //store the url in realtime database
                                            DatabaseReference reference = database.getReference();// return the path to root
                                            reference.child("Fotos").child("icrmedu_docente").child("Docentes").child(String.valueOf(personaUi.getEntidadId())).child(String.valueOf(personaUi.getPersonaId())).setValue(generatedFilePath).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                            callBack.onSuccess();
                                                    }else {
                                                        callBack.onError();
                                                    }
                                                }
                                            });
                                        }
                                    }); //return the url of you upload file
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    callBack.onError();
                }}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    //track the progress of = our upload
                    int currentProgress = (int)((double)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()));
                    Log.d("SaveImgen", "currentProgress: "+currentProgress);
                    callBack.onProgress(currentProgress);
                }
            });

        }

    }

    public void executeMultiploReintentar(CallBack callBack){
        currentProgress = 0;
        multiplEnvio(callBack);
    }

    private int cantidad = 10;

    public void multiplEnvio(final CallBack callBack){

        if(personaUi!=null&&jpg!=null){
            final String fileName = personaUi.getPersonaId() + "_"+cantidad+".jpg";
            StorageReference storageReference = storage.getReference();// returns root path

            String url = SaveImage(jpg);
            if(TextUtils.isEmpty(url)){
                callBack.onError();
                return;
            }
            Uri myUri = Uri.fromFile(new File(url));

            storageReference.child("Docentes").child("icrmedu_docente").child(String.valueOf(personaUi.getGeoreferenciId())).child(fileName).putFile(myUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            cantidad--;
                            if(cantidad>0){
                                multiplEnvio(callBack);
                            }else {
                                callBack.onSuccess();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    callBack.onError();
                }}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    //track the progress of = our upload
                    currentProgress += (int)((double)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()) / 10D);
                    Log.d("SaveImgen", "currentProgress: "+currentProgress);
                    callBack.onProgress(currentProgress);
                }
            });

        }else {
            callBack.onError();
        }


    }

    public String compressImage2(byte[] bytes) {
        Bitmap scaledBitmap = null;

        float maxHeight = 1280.0f;
        float maxWidth = 1280.0f;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

        float imgRatio = (float) actualWidth / (float) actualHeight;
        float maxRatio = maxWidth / maxHeight;

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
            bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.RGB_565);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        bmp.recycle();


       /*ExifInterface exif;
        try {
            exif = new ExifInterface(imagePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            } else if (orientation == 3) {
                matrix.postRotate(180);
            } else if (orientation == 8) {
                matrix.postRotate(270);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        FileOutputStream out = null;

        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = new Date().getTime()+"";
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir,  NameOfFile + CurrentDateAndTime + ".jpg");

        try {
            out = new FileOutputStream(file);

            //write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return file.getPath();
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public String SaveImage(byte[] bytes) {

        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap ImageToSave = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        Bitmap rotatedBitmap = Bitmap.createBitmap(ImageToSave, 0, 0, ImageToSave.getWidth(), ImageToSave.getHeight(), matrix, true);


        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = new Date().getTime()+"";
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir,  NameOfFile + CurrentDateAndTime + ".jpg");
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            return file.getPath();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface CallBack{
        void onProgress(int progress);

        void onSuccess();

        void onError();
    }
}
