package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.FirstColumn;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.HeaderTable;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.ListCells;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetRubrosListCallback {
    void onListLoaded(String nombreCurso, String nombreAlumno,String apellidoAlumno, String puntos, double desempenio, String logro, String urlProfile, String programa, int columnsSize, List<String> response, String seccion, String periodoAcad, List<FirstColumn> nombresRubros, List<HeaderTable> headerTableList, List<ListCells> listCellsParent);

    void onError(String error);

}
