package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public interface UseCaseLoginSincrono<J,S>  {
    RetrofitCancel execute(J request , Callback<S> callback);

    interface Callback<S>{
        void onResponse(boolean success, S value);
    }
}
