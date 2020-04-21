package com.consultoraestrategia.ss_crmeducativo.services.util;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ImportarCountDownTimer extends CountDownTimer {

    private ImportarCountDownTimerCallback callback;
    private long maxSecondsInMillis;
    private long countInterval;

    public ImportarCountDownTimer(long millisInFuture, long countDownInterval, ImportarCountDownTimerCallback callback) {
        super(millisInFuture, countDownInterval);
        this.callback = callback;
        this.maxSecondsInMillis = millisInFuture;
        this.countInterval = countDownInterval;
        Log.d(ImportarCountDownTimerCallback.class.getSimpleName(), "millisInFuture: " + millisInFuture);

    }


    @Override
    public void onTick(long millisUntilFinished) {
        Log.d(ImportarCountDownTimerCallback.class.getSimpleName(), "onTick: " + millisUntilFinished);
        long millisElapsed = (maxSecondsInMillis - millisUntilFinished);
        int counter = (int) (millisElapsed / countInterval);
        simularProgreso(counter);
        counter(counter);
    }

    private void counter(int counter){
        if(callback!=null)callback.onImportarCountDownTimerCount(counter);
    }

    private void simularProgreso(int counter){
        int progress = counter;
        if (counter <= 40) {
            progress = counter * 2;
        }

        if (counter > 40 && counter <= 55) {
            progress = (40 * 2) + (counter - 40);
        }

        if (counter > 55) {
            progress = 95;
        }
        if(callback == null)return;
        callback.onImportarCountDownProgress(progress);
    }

    @Override
    public void onFinish() {
        if(callback == null)return;
        callback.onImportarCountDownTimerFinish();
        callback = null;
    }

    public interface ImportarCountDownTimerCallback {
        void onImportarCountDownTimerCount(int counter);
        void onImportarCountDownTimerFinish();
        void onImportarCountDownProgress(int progress);
    }

    public void onDestroy(){
        this.cancel();
        callback = null;
    }
}
