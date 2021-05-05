package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas_Table;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.CountingRequestBody;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UploadArchivo {
    Context context;

    public UploadArchivo(Context context) {
        this.context = context;
    }

    public void execute(ArchivoUi archivoUi, CallbackProgress<ArchivoUi> callbackProgress){
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(){
            @Override
            public void run() {
                Rutas rutas = SQLite.select()
                        .from(Rutas.class)
                        .where(Rutas_Table.rutaId.eq(Rutas.ARCHIVOS))
                        .querySingle();

                String urlServidor =  rutas!=null&&!TextUtils.isEmpty(rutas.getRuta_ArchivoAsistencia())?rutas.getRuta_ArchivoAsistencia():"https://www.google.com";

                final OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                        .readTimeout(120, TimeUnit.SECONDS)
                        .build();
                archivoUi.setCancel(false);
                archivoUi.setProgress(0);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callbackProgress.onPreLoad(archivoUi);
                    }
                });


                Bitmap bitmap = null;
                //bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), archivoUi.getUri());
                bitmap = getCompressedBitmap(getFile(context, archivoUi.getUri(), archivoUi.getNombre()).getPath());
                assert (bitmap!=null);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                bitmap.recycle();

                RequestBody requestBody = RequestBody.create(byteArray);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo",
                        new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
                            int pogress = 0;
                            @Override
                            public void onRequestProgress(long bytesWritten, long contentLength) {
                                double progress = (1.0 * bytesWritten) / contentLength;
                                if(pogress!=(int)(progress*100)||progress==0){
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            callbackProgress.onProgress(pogress, archivoUi);
                                        }
                                    });

                                    pogress = (int)(progress*100);
                                }

                                if(archivoUi.isCancel()){
                                    client.dispatcher().cancelAll();
                                }

                            }
                        }));

                requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addPart(fileToUpload)
                        .addFormDataPart("name", archivoUi.getNombre())
                        .addFormDataPart("optionTipos", "4")
                        .addFormDataPart("option", "1")
                        .build();

                Request request = new Request.Builder()
                        .url(urlServidor+"/")
                        .post(requestBody)
                        .build();

                try (okhttp3.Response response = client.newCall(request).execute()) {

                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String serverResponse = response.body().string();

                    if(!TextUtils.isEmpty(serverResponse)
                            &&!serverResponse.equals("null")){
                        archivoUi.setUrl(serverResponse);
                        archivoUi.setUri(null);

                        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                                .from(EvaluacionProcesoC.class)
                                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(archivoUi.getRubroEvaluacionId()))
                                .and(EvaluacionProcesoC_Table.alumnoId.eq(archivoUi.getAlumnoId()))
                                .querySingle();

                        if(evaluacionProcesoC!=null){
                            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select().from(RubroEvaluacionProcesoC.class).where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId())).querySingle();
                            if(rubroEvaluacionProcesoC!=null){
                                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                                rubroEvaluacionProcesoC.save();
                            }

                            ArchivosRubroProceso archivosRubroProceso = new ArchivosRubroProceso();
                            archivosRubroProceso.setEvaluacionProcesoId(evaluacionProcesoC.getKey());
                            archivosRubroProceso.setUrl(serverResponse);
                            archivosRubroProceso.setTipoArchivoId(ArchivosRubroProceso.TIPO_IMAGEN);
                            archivosRubroProceso.setKey(archivoUi.getId());
                            archivosRubroProceso.setArchivoRubroId(archivoUi.getId());
                            archivosRubroProceso.setSyncFlag(ArchivosRubroProceso.FLAG_ADDED);
                            archivosRubroProceso.setDelete(0);
                            archivosRubroProceso.save();
                        }


                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callbackProgress.onLoad(true, archivoUi);
                            }
                        });

                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callbackProgress.onLoad(false, null);
                            }
                        });


                    }

                    // callback.onLoad(true, "pruebas");
                    //downloadCancelUi.setCancel(true);
                    //System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callbackProgress.onLoad(false, null);
                        }
                    });

                    e.printStackTrace();
                }
            }
        }.start();


    }

    public static File getFile(Context context, Uri uri, String nombre) {
        File destinationFilename = new File(context.getFilesDir().getPath() + File.separatorChar + nombre);
        try (InputStream ins = context.getContentResolver().openInputStream(uri)) {
            createFileFromStream(ins, destinationFilename);
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
        return destinationFilename;
    }

    public static void createFileFromStream(InputStream ins, File destination) {
        try (OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = ins.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private Bitmap compressImage(Uri imageUri) {

        Bitmap bmp = null;
        try {
            bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);

            Bitmap scaledBitmap = null;

            BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true;

            int actualHeight = bmp.getHeight();
            int actualWidth = bmp.getWidth();

//      max Height and width values of the compressed image is taken as 816x612

            float maxHeight = 816.0f;
            float maxWidth = 612.0f;
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

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

//      setting inSampleSize value allows to load a scaled down version of the original image

            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16 * 1024];


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

            return scaledBitmap;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }


    }

    public  Bitmap getCompressedBitmap(String imagePath) {
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

        Bitmap updatedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return updatedBitmap;
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

    public interface CallbackProgress<T>  {
        void onProgress(int count, T item);
        void onLoad(boolean success, T item);
        void onPreLoad(T item);
    }
}
