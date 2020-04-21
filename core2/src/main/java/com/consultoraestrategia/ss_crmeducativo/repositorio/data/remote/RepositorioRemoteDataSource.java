package com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.io.BufferedInputStream;
import java.io.File;
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

    private final ApiRetrofit apiRetrofit;
    private String TAG = RepositorioRemoteDataSource.class.getSimpleName();

    public RepositorioRemoteDataSource(ApiRetrofit apiRetrofit) {
        this.apiRetrofit = apiRetrofit;
    }

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

                    String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/DescargaDm3.0"+ carpeta;
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
    public void uploadFileCaso(String archivoId, String urlServidor, String path, final CallbackProgress<String> callbackProgress) {
        Log.d(TAG,"urlServidor "+ urlServidor);
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
                .addFormDataPart("optionTipos", "3")
                .addFormDataPart("option", "1")
                .build();

        uploadArchivo(urlServidor, requestBody, client, callbackProgress);
    }

    @Override
    public void updateSucessDowloadRubro(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void uploadFileCasoRubro(String archivoId, String urlServidor, String path, final CallbackProgress<String> callbackProgress) {
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
                .addFormDataPart("optionTipos", "4")
                .addFormDataPart("option", "1")
                .build();

        uploadArchivo(urlServidor, requestBody, client, callbackProgress);
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



}
