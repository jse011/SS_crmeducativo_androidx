package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetRubrosListCallback {
    void onListLoaded(String nombreCurso, String nombreAlumno, String puntos, double desempenio, String logro, int columnsSize, List<String> response);

    void onError(String error);

}
