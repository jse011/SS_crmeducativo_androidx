package com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetHijosListCallback {
    void onHijosListLoaded(List<Persona> hijoUIList);
    void onError(String error);
}
