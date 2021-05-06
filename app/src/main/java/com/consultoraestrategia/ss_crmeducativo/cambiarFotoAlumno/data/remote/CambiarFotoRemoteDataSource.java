package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CamabiarFotoDataSourse;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CambiarFotoRemoteDataSource implements CamabiarFotoDataSourse {
    private final static String TAG =CambiarFotoRemoteDataSource.class.getSimpleName();
    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {
        return null;
    }

    @Override
    public void savePathPersona(PersonaUi personaUi) {

    }

    @Override
    public void uploadFileCasoRubro(PersonaUi personaUi, final Callback<String> stringCallback) {

        Rutas rutas = SQLite.select()
                .from(Rutas.class)
                .where(Rutas_Table.rutaId.eq(Rutas.ALUMNO))
                .querySingle();

        if(rutas!=null){
            try {
                //
                String filename=personaUi.getPath().substring(personaUi.getPath().lastIndexOf("/")+1);

                ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
                Call<String> call = apiRetrofit1.uploadMultiFileAlumno(rutas.getRuta_ArchivoAsistencia(),personaUi.getPersonaId(), getCompressedBitmap(personaUi.getPath()), filename);
                call.enqueue(new retrofit2.Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String serverResponse = response.body();

                        if (serverResponse != null) {
                            if(!TextUtils.isEmpty(serverResponse)
                                    &&!serverResponse.equals("null")){
                                Log.d(TAG, "serverResponse: " + serverResponse);
                                stringCallback.onLoad(true, serverResponse);
                            }else {
                                Log.d(TAG, "vacio");
                                stringCallback.onLoad(false, null);
                            }

                        } else {
                            Log.d(TAG, "vacio");
                            stringCallback.onLoad(false, null);
                        }
                        // callback.onLoad(true, "pruebas");
                        //downloadCancelUi.setCancel(true);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        stringCallback.onLoad(false, null);
                        t.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                stringCallback.onLoad(false, null);
            }
        }else {
            stringCallback.onLoad(false, null);
        }

    }


    public  byte[] getCompressedBitmap(String imagePath) {
        float maxHeight = 1920.0f;
        float maxWidth = 1080.0f;
        Bitmap scaledBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(imagePath, options);

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
            bmp = BitmapFactory.decodeFile(imagePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
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

        ExifInterface exif = null;
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
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);

        byte[] byteArray = out.toByteArray();

        //Bitmap updatedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return byteArray;
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
}
