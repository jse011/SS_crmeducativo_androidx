package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks;

import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetPersonaCallback {
    void onPersonaLoaded(Persona persona);

    //arreglado
    void onError(String error);
}
