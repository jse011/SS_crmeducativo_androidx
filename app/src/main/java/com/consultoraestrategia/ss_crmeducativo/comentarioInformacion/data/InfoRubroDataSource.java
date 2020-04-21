package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data;


import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;

import java.util.List;

public interface InfoRubroDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    List<MensajeUi> getComentarios(String evaluacionId);

    List<ArchivoComentarioUi> getArchivoComentarioList(String evaluacionId);

    boolean saveComentario(MensajeUi mensajeUi);

    boolean deleteComentario(MensajeUi mensajeUi);

    boolean saveComentarioArchivo(ArchivoComentarioUi archivoComentarioUi);

    boolean deleteArchivoComentario(ArchivoComentarioUi archivoComentarioUi);
}
