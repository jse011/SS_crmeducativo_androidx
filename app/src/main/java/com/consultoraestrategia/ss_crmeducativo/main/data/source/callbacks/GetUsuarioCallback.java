package com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.entities.Rol;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioUi;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetUsuarioCallback {
    void onError(String error);

    void onUsuarioLoaded(UsuarioUi usuarioUi);
}
