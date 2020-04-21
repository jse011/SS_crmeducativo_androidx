package com.consultoraestrategia.ss_crmeducativo.services.daemon.ui;

import com.consultoraestrategia.ss_crmeducativo.services.daemon.CRMEPresenter;

/**
 * Created by SCIEV on 24/05/2018.
 */

public interface CRMEEvent {

    void setPresenter(CRMEPresenter presenter);
    void showNotificacionProgress(int icono, String titulo, String subtitulo, int max, int progress);
    void setNotificacionProgress(int max, int progress);
    void hideNotificacionProgress();
    void showNotificacion(int icono, String titulo, String subtitulo, String textoLargo, int usuarioId);
    void hideNotificacion();
}
