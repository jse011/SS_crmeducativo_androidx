package com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks;

/**
 * Created by @stevecampos on 18/01/2018.
 */

public interface ImportCallback {
    void onSuccess();
    void onError();
    void onProgressInformationChanged(int informationType);
}
