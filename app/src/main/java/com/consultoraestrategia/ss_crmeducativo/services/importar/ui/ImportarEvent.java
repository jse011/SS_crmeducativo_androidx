package com.consultoraestrategia.ss_crmeducativo.services.importar.ui;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ImportarPresenter;

/**
 * Created by SCIEV on 24/05/2018.
 */

public interface ImportarEvent {

    void setPresenter(ImportarPresenter presenter);
    void showNotificacionProgress(int icono, String titulo, String subtitulo, String textoLargo, int usuarioId);
    void llamarDefaultService();
    void onFinishMessaje(TipoImportacion tipoImportacion, boolean success, BEVariables importarWrapper);
}
