package com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetUsuarioCallback {
    void onUsuarioLoaded(Usuario usuario);

    void onError(String error);


}
