package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.useCase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.ScreamSplashRepository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUrlServidorLocal  {
    private ScreamSplashRepository repository;
    private boolean success;
    private String TAG = "GetUrlServidorLocal";
    private  HttpURLConnection urlc = null;

    public GetUrlServidorLocal(ScreamSplashRepository repository) {
        this.repository = repository;
    }

    public void excute(final CallBack callBack){

        String url = repository.getUrlServidorUrl();

        try {
            urlc = (HttpURLConnection) (new URL(url).openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        success = false;

      if(urlc!=null){
          Thread thread = new Thread() {
              @Override
              public void run() {
                  try {
                      if(urlc!=null)urlc.connect();
                      success = true;
                      callBack.onLoad(urlc != null && urlc.getResponseCode() == 200);
                  } catch (IOException e) {
                      e.printStackTrace();
                      Log.d(TAG, "Error checking internet connection");
                      success = true;
                      callBack.onLoad(false);
                  }

              }
          };
          thread.start();


          Thread threadTime = new Thread() {
              @Override
              public void run() {
                  try {
                      Thread.sleep(2000);
                      if (!success) {
                          if(urlc!=null)urlc.getErrorStream();
                          if(urlc!=null)urlc.disconnect();
                          urlc=null;
                          callBack.onLoad(false);
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }

              }
          };
          threadTime.start();

      }else {
          Log.d(TAG, "Error checking internet connection");
          callBack.onLoad(false);
      }


    }




    public interface CallBack{
        void onLoad(boolean success);
    }

}

