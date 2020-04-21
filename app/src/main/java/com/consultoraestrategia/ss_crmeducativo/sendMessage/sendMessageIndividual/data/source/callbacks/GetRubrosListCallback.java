package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetRubrosListCallback {
    void onListLoaded(String nombreCurso, String nombreAlumno, String puntos, double desempenio, String logro, String urlProfile, String programa, int columnsSize, List<String> response, String seccion, String periodoAcad);

    void onError(String error);

}
