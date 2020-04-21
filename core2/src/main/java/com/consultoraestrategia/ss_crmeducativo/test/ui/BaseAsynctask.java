package com.consultoraestrategia.ss_crmeducativo.test.ui;

import android.os.AsyncTask;

/**
 * Created by @stevecampos on 24/01/2018.
 */

public abstract class BaseAsynctask<T, R> extends AsyncTask<T, Void, R> {

    private Callback<R> callback;

    protected abstract R doInBack(T[] request);

    public interface Callback<T> {
        void onFinish(T object);
    }

    public BaseAsynctask(Callback<R> callback) {
        this.callback = callback;
    }

    @Override
    protected R doInBackground(T[] ts) {
        return doInBack(ts);
    }

    @Override
    protected void onPostExecute(R result) {
        super.onPostExecute(result);
        if (callback != null) {
            callback.onFinish(result);
        }
        callback = null;
    }
}
