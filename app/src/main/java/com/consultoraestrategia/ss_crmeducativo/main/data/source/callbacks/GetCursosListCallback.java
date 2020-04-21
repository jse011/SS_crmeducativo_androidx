package com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface GetCursosListCallback {
    void onCursosListLoaded(List<CursosUI> cursosUIList, List<CargaCursos> cargaCursosList);

    void onError(String error);


}
