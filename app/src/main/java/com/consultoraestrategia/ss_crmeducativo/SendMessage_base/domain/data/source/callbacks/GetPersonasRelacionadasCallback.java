package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetPersonasRelacionadasCallback {
    void onPersonasRelacionadaListLoaded(List<PersonaRelacionesUI> personaRelacionesList);

    void onError(String error);

}
