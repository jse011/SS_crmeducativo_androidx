package com.consultoraestrategia.ss_crmeducativo.services.loginService.ui;

import com.consultoraestrategia.ss_crmeducativo.services.loginService.LoginPresenter;

/**
 * Created by SCIEV on 24/05/2018.
 */

public interface LoginEvent {
    void setPresenter(LoginPresenter presenter);
    void showNotificacionProgress(int icono, String titulo, String subtitulo, String textoLargo, int usuarioId);
}
