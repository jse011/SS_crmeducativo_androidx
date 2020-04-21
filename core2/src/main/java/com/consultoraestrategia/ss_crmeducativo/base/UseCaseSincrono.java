package com.consultoraestrategia.ss_crmeducativo.base;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public abstract class UseCaseSincrono<J,S> {

    public abstract void execute(J request , Callback<S> callback);

    public interface Callback<S>{
        void onResponse(boolean success, S value);
    }
}

