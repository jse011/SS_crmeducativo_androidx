package com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;

/**
 * Created by @stevecampos on 18/01/2018.
 */

public interface LoginCallback {
    void onSuccess(SessionUser user);
    void onInvalidCredentials();
    void onRedError();
}
