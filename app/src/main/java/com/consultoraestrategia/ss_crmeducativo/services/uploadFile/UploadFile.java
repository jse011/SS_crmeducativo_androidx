package com.consultoraestrategia.ss_crmeducativo.services.uploadFile;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.service.Service;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.EnumFile;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadFile {

    private static final String TAG = UploadFile.class.getSimpleName();


    public static void uploadMultiFile(RecursoUploadFile recursoUploadFiles, final CallBackRetrofit<List<RecursoUploadFile>> listCallBackRetrofit) {

        final String BASE_URL = "http://192.168.1.113:3000/RepositorioArchivos.ashx/";



        File file = new File(recursoUploadFiles.getPath());

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody tipos = RequestBody.create(MediaType.parse("*/*"), "1");
        RequestBody tipos2 = RequestBody.create(MediaType.parse("*/*"), "1");

        Service getResponse = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service.class);

        Call<String> call = getResponse.uploadFile(fileToUpload, filename, tipos,tipos2);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Object serverResponse = response.body();
                if (serverResponse != null) {
                    final Gson gson = new Gson();
                    final String representacionJSON = gson.toJson(serverResponse);
                    Log.d("Response", representacionJSON);
                } else {
                    assert serverResponse != null;
                    final Gson gson = new Gson();
                    final String representacionJSON = gson.toJson(serverResponse);
                    Log.d("Response", representacionJSON);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



        /*OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service uploadAPIs = retrofit.create(Service.class);
        //Create a file object using file path
        File file = new File(recursoUploadFiles.getPath());
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("file"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Log.d(TAG,fileToUpload.toString());
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(fileToUpload);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        //
        Call call = uploadAPIs.uploadFile(fileToUpload, filename);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });*/



        /*String SERVER_PATH = "http://192.168.1.151/CRMMovil/PortalAcadMovil.ashx";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("user_name", "Robert");
        builder.addFormDataPart("email", "mobile.apps.pro.vn@gmail.com");
        // Map is used to multipart the file using okhttp3.RequestBody
        // Multiple Images
        for (RecursoUploadFile recursoUploadFile : recursoUploadFiles) {
            java.io.File file = new java.io.File(recursoUploadFile.getPath());
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        MultipartBody requestBody = builder.build();
        Service uploadService = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service.class);
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(requestBody);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        Call<Boolean> fileUpload = uploadService.uploadMultiFile(requestBody);
        fileUpload.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listCallBackRetrofit.onLoad(true, recursoUploadFiles);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.fillInStackTrace();
                listCallBackRetrofit.onLoad(false, null);
            }
        });*/


    }


    public interface CallBackRetrofit<T>{
        void onLoad(boolean success, T item);
    }

    private static class Log{
        public static void d(String TAG, String message) {
            int maxLogSize = 2000;
            for(int i = 0; i <= message.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > message.length() ? message.length() : end;
                android.util.Log.d(TAG, message.substring(start, end));
            }
        }
    }

}
