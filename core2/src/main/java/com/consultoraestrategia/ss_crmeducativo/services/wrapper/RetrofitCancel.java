package com.consultoraestrategia.ss_crmeducativo.services.wrapper;

import java.io.IOException;

public interface RetrofitCancel<T> {

    void enqueue(Callback<T> callback);

    boolean isExecuted();

    void cancel();

    boolean isCanceled();

    interface Callback<T> {
        void onResponse(T response);
        void onFailure(Throwable t);
    }
}
