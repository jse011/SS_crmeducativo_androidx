package com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetAccesosListCallback {
    void onAccesosListLoaded(List<UsuarioAccesoUI> usuarioAccesoUIList);

    void onError(String error);
}
