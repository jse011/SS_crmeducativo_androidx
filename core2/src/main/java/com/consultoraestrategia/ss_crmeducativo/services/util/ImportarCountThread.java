package com.consultoraestrategia.ss_crmeducativo.services.util;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ImportarCountThread extends Thread{

    private ImportarCountDownTimerCallback callback;
    private long maxCount;
    private long countInterval;
    private int counter;

    public ImportarCountThread(long maxCount, long countInterval, ImportarCountDownTimerCallback callback) {
        this.callback = callback;
        this.maxCount = maxCount;
        this.countInterval = countInterval;
        Log.d(ImportarCountDownTimerCallback.class.getSimpleName(), "maxCount: " + maxCount);
    }

    @Override
    public void run() {
        super.run();
        try {
            counter=0;
            while (!isInterrupted()) {
                counter++;
                Thread.sleep(countInterval);
                System.out.println("ExtendsThread : Counter : " + counter);
                if(maxCount==counter){
                    onFinish();
                    break;
                }
                simularProgreso(counter);
                counter(counter);
            }
        } catch (InterruptedException e) {
        }

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

    public void onFinish() {
        if(callback == null)return;
        callback.onImportarCountDownTimerFinish();
        interrupt();
        callback = null;
    }

    public interface ImportarCountDownTimerCallback {
        void onImportarCountDownTimerCount(int counter);
        void onImportarCountDownTimerFinish();
        void onImportarCountDownProgress(int progress);
    }

    public void onDestroy(){
        counter=0;
        interrupt();
        callback = null;
    }
}
