package com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;
import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig_Table;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsStorage;
import com.google.android.gms.common.util.IOUtils;
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

public class RepositorioRemoteDataSource implements RepositorioDataSource {


    private final Context context;
    private String TAG = RepositorioRemoteDataSource.class.getSimpleName();

    public RepositorioRemoteDataSource(Context context) {
        this.context = context;
    }

    @Deprecated
    @Override
    public  void dowloadImage(final String url, final String nombre, final String carpeta, final CallbackProgress<String> repositorioFileUiCallback) {

        new Thread(){
            @Override
            public void run() {
                try {
                    ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
                    Log.d(TAG,"url " + url);
                    ResponseBody body = apiRetrofit1.downloadFileByUrl(url);
                    int count;
                    byte data[] = new byte[1024 * 4];
                    long fileSize = body.contentLength();
                    InputStream inputStream = new BufferedInputStream(body.byteStream(), 1024 * 8);

                    String file_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +"/DescargaDm3.0"+ carpeta;
                    File dir = new File(file_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File outputFile = new File(file_path, nombre);
                    OutputStream outputStream = new FileOutputStream(outputFile);
                    long total = 0;
                    boolean downloadComplete = false;
                    //int totalFileSize = (int) (fileSiz
                    // e / (Math.pow(1024, 2)));
                    int gloabalcount = 0;
                    DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
                    repositorioFileUiCallback.onPreLoad(downloadCancelUi);
                    while ((count = inputStream.read(data)) != -1) {
                        if(downloadCancelUi.isCancel()){
                            repositorioFileUiCallback.onLoad(false, null);
                            break;
                        }
                        total += count;
                        int progress = (int) ((double) (total * 100) / (double) fileSize);
                        if(gloabalcount==progress)repositorioFileUiCallback.onProgress(progress);
                        gloabalcount = progress;
                        Log.d(TAG,"progress" + progress + " %");
                        outputStream.write(data, 0, count);
                        downloadComplete = true;
                    }
                    Log.d(TAG,"downloadComplete" + downloadComplete );
                    if(!downloadCancelUi.isCancel()){
                        repositorioFileUiCallback.onLoad(true, outputFile.getPath());
                        outputStream.flush();
                    }
                    outputStream.close();
                    inputStream.close();

                } catch (IOException e) {
                    repositorioFileUiCallback.onLoad(false, null);
                    e.printStackTrace();
                } catch (Exception e){
                    repositorioFileUiCallback.onLoad(false, null);
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void uploadFileJustificacion(String archivoId, String urlServidor, String path, final CallbackProgress<String> callback) {
        Log.d(TAG,"urlServidor "+ urlServidor);

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        final DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
        callback.onPreLoad(downloadCancelUi);

        File file = new File(path);
        RequestBody request = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo",
                new CountingRequestBody(request, new CountingRequestBody.Listener() {
                    int pogress = 0;
                    @Override
                    public void onRequestProgress(long bytesWritten, long contentLength) {
                        double progress = (1.0 * bytesWritten) / contentLength;
                        Log.d(TAG, "progress : " + progress);
                        if(pogress!=(int)(progress*100)||progress==0){
                            callback.onProgress(pogress);
                            pogress = (int)(progress*100);
                        }

                        if(downloadCancelUi.isCancel()){
                            client.dispatcher().cancelAll();
                            Log.d(TAG, "cancel");
                        }

                    }
                }));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(fileToUpload)
                .addFormDataPart("name", archivoId)
                .addFormDataPart("optionTipos", "1")
                .addFormDataPart("option", "1")
                .build();

        uploadArchivo(urlServidor,requestBody, client,callback);
    }

    @Override
    public void getUrlRepositorioArchivo(Callback<String> stringCallback) {

    }

    @Override
    public void uploadFileArchivo(String archivoId, String urlServidor, String path, final CallbackProgress<String> callbackProgress) {
        /*Log.d(TAG,"urlServidor "+ urlServidor);
        ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
        final Call<String> call = apiRetrofit1.uploadMultiFileArchivo(urlServidor, path, archivoId);*/
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        final DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
        callbackProgress.onPreLoad(downloadCancelUi);

        File file = new File(path);
        RequestBody request = RequestBody.create(MediaType.parse("*/*"), file);

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo",
                new CountingRequestBody(request, new CountingRequestBody.Listener() {
                    int pogress = 0;
                    @Override
                    public void onRequestProgress(long bytesWritten, long contentLength) {
                        double progress = (1.0 * bytesWritten) / contentLength;
                        Log.d(TAG, "progress : " + progress);
                        if(pogress!=(int)(progress*100)||progress==0){
                            callbackProgress.onProgress(pogress);
                            pogress = (int)(progress*100);
                        }

                        if(downloadCancelUi.isCancel()){
                            client.dispatcher().cancelAll();
                            Log.d(TAG, "cancel");
                        }

                    }
                }));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(fileToUpload)
                .addFormDataPart("name", archivoId)
                .addFormDataPart("optionTipos", "2")
                .addFormDataPart("option", "1")
                .build();

        uploadArchivo(urlServidor, requestBody, client, callbackProgress);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {

    }

    @Override
    public void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void uploadFileCaso(UpdateRepositorioFileUi updateRepositorioFileUi, String urlServidor, Uri uri, final CallbackProgress<String> callbackProgress) {
        Log.d(TAG,"urlServidor "+ urlServidor);
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        final DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
        callbackProgress.onPreLoad(downloadCancelUi);

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            assert (inputStream!=null);
            RequestBody request;
            if(updateRepositorioFileUi.getTipoFileU()== RepositorioTipoFileU.IMAGEN){
                request = RequestBody.create( MediaType.parse("*/*"), getCompressedBitmap(getFile(context, updateRepositorioFileUi.getUri(), updateRepositorioFileUi.getNombreArchivo()).getPath()));
            }else {
                request = RequestBody.create(MediaType.parse("*/*"), getBytes(inputStream));
            }

            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo",
                    new CountingRequestBody(request, new CountingRequestBody.Listener() {
                        int pogress = 0;
                        @Override
                        public void onRequestProgress(long bytesWritten, long contentLength) {
                            double progress = (1.0 * bytesWritten) / contentLength;
                            Log.d(TAG, "progress : " + progress);
                            if(pogress!=(int)(progress*100)||progress==0){
                                callbackProgress.onProgress(pogress);
                                pogress = (int)(progress*100);
                            }

                            if(downloadCancelUi.isCancel()){
                                client.dispatcher().cancelAll();
                                Log.d(TAG, "cancel");
                            }

                        }
                    }));

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addPart(fileToUpload)
                    .addFormDataPart("name", updateRepositorioFileUi.getExtencionArchivoId())
                    .addFormDataPart("optionTipos", "3")
                    .addFormDataPart("option", "1")
                    .build();

            uploadArchivo(urlServidor, requestBody, client, new CallbackProgress<String>() {
                @Override
                public void onProgress(int count) {
                    callbackProgress.onProgress(count);
                }

                @Override
                public void onLoad(boolean success, String url) {
                    String file = url;
                    try {
                        WebConfig webConfig = SQLite.select()
                                .from(WebConfig.class)
                                .where(WebConfig_Table.nombre.eq("wstr_PathCasoAdjunto"))
                                .querySingle();

                        if(webConfig!=null){
                            int p = Math.max(url.lastIndexOf('/'), url.lastIndexOf('\\'));
                            file = url.substring(p + 1);
                            file = webConfig.getContent() + file;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    callbackProgress.onLoad(success, file);
                }

                @Override
                public void onPreLoad(DownloadCancelUi isCancel) {
                    callbackProgress.onPreLoad(isCancel);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            callbackProgress.onLoad(false, null);
        }
    }

    @Override
    public void updateSucessDowloadRubro(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void uploadFileCasoRubro(String archivoId, String urlServidor, Uri uri, final CallbackProgress<String> callbackProgress) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        final DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
        callbackProgress.onPreLoad(downloadCancelUi);
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            assert (inputStream!=null);
            RequestBody request = RequestBody.create(MediaType.parse("*/*"), getBytes(inputStream));
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo",
                    new CountingRequestBody(request, new CountingRequestBody.Listener() {
                        int pogress = 0;
                        @Override
                        public void onRequestProgress(long bytesWritten, long contentLength) {
                            double progress = (1.0 * bytesWritten) / contentLength;
                            Log.d(TAG, "progress : " + progress);
                            if(pogress!=(int)(progress*100)||progress==0){
                                callbackProgress.onProgress(pogress);
                                pogress = (int)(progress*100);
                            }

                            if(downloadCancelUi.isCancel()){
                                client.dispatcher().cancelAll();
                                Log.d(TAG, "cancel");
                            }

                        }
                    }));

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addPart(fileToUpload)
                    .addFormDataPart("name", archivoId)
                    .addFormDataPart("optionTipos", "4")
                    .addFormDataPart("option", "1")
                    .build();

            uploadArchivo(urlServidor, requestBody, client, callbackProgress);

        } catch (IOException e) {
            e.printStackTrace();
            callbackProgress.onLoad(false, null);
        }


    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 262144;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void uploadArchivo(String urlServidor, RequestBody requestBody, OkHttpClient client, final CallbackProgress<String> callback){

        Request request = new Request.Builder()
                .url(urlServidor+"/")
                .post(requestBody)
                .build();

        try (okhttp3.Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String serverResponse = response.body().string();

            if(!TextUtils.isEmpty(serverResponse)
                    &&!serverResponse.equals("null")){
                Log.d(TAG, serverResponse);
                callback.onLoad(true, serverResponse);
            }else {
                Log.d(TAG, "vacio");
                callback.onLoad(false, null);
            }

            // callback.onLoad(true, "pruebas");
            //downloadCancelUi.setCancel(true);
            //System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            callback.onLoad(false, null);
            Log.d(TAG, "onFailure ");
            e.printStackTrace();
        }


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
