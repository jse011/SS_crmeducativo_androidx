package com.consultoraestrategia.ss_crmeducativo.utils.AndroidOnline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class OnlineImpl implements Online {
    private final Context context;
    private static final String TAG = "AndroidOnlineImpl";
    private static Long consultTime = null;
    private static Boolean consultOnline = null;

    public OnlineImpl(Context context) {
        this.context = context;

    }
    @Override
    public void online(Callback callback) {
        Log.d(TAG, "online");
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo!=null&&(networkInfo .isAvailable()) && (networkInfo .isConnected())) {
            SimpleCounterAsync simpleCounterAsync = new SimpleCounterAsync(callback, new Response(true));
            simpleCounterAsync.execute();
        } else {
            Log.d(TAG, "No network available!");
            Log.d(TAG, "onLoad: "+false);
            callback.onLoad(false);
        }
    }

    private static class SimpleCounterAsync extends CourtineAsync<Boolean>{
        private Callback callback;;
        private Response response;
        //private boolean success;

        public SimpleCounterAsync(Callback callback, Response response) {
            this.callback = callback;
            this.response = response;
        }

        @Override
        public Boolean onExecute() {
            boolean status = false;
            long time = new Date().getTime();
            long result = time - (consultTime!=null?consultTime:0);
            if((result>1000)||response.isRestart()){
                consultTime = time;
                //success = false;
                //autoCancel();
                try {
                    HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                    urlc.setRequestProperty("User-Agent", "Test");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(1500);
                    urlc.connect();
                    consultOnline = (urlc.getResponseCode() == 200);
                    Log.d(TAG, "onLoad: "+consultOnline);
                    status = consultOnline;
                } catch (Exception e) {
                    Log.e(TAG, "Error checking internet connection", e);
                    consultOnline= false;
                    Log.d(TAG, "onLoad: "+false);
                    //return false;
                }
            }else {
                boolean online = consultOnline!=null?consultOnline:false;
                Log.d(TAG, "onLoad cache: "+online);
                status = online;
            }
            //success = true;
            return status;
        }

        @Override
        public void onPreExecute() {

        }

        @Override
        public void onPostExecute(Boolean result) {
            callback.onLoad(consultOnline);
        }
    }

    private static class Response{
        //private Callback callback;;//
        private boolean restart;

        public Response(/*Callback callback,*/ boolean restart) {
            //this.callback = callback;
            this.restart = restart;
        }
        /*
        public Callback getCallback() {
            return callback;
        }

        public void setCallback(Callback callback) {
            this.callback = callback;
        }*/

        public boolean isRestart() {
            return restart;
        }

        public void setRestart(boolean restart) {
            this.restart = restart;
        }
    }
}
