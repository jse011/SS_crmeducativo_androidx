package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data;

import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;

import java.util.List;

public class InfoRubroRepository implements InfoRubroDataSource {

    InfoRubroDataLocalSource infoRubroDataLocalSource;

    public InfoRubroRepository(InfoRubroDataLocalSource infoRubroDataLocalSource) {
        this.infoRubroDataLocalSource = infoRubroDataLocalSource;
    }


    @Override
    public List<MensajeUi> getComentarios(String evaluacionId) {
        return infoRubroDataLocalSource.getComentarios(evaluacionId);
    }

    @Override
    public List<ArchivoUi> getArchivoComentarioList(String evaluacionId) {
        return infoRubroDataLocalSource.getArchivoComentarioList(evaluacionId);
    }

    @Override
    public boolean saveComentario(MensajeUi mensajeUi) {
        return infoRubroDataLocalSource.saveComentario(mensajeUi);
    }

    @Override
    public boolean deleteComentario(MensajeUi mensajeUi) {
        return infoRubroDataLocalSource.deleteComentario(mensajeUi);
    }

    @Override
    public boolean deleteArchivoComentario(ArchivoUi archivoComentarioUi) {
        return infoRubroDataLocalSource.deleteArchivoComentario(archivoComentarioUi);
    }
}
