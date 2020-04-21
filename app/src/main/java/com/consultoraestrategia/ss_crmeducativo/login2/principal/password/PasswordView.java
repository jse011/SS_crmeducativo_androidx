package com.consultoraestrategia.ss_crmeducativo.login2.principal.password;

import com.consultoraestrategia.ss_crmeducativo.login2.principal.Login2Presenter;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;

public interface PasswordView {

    void onAttach(Login2Presenter presenter);

    void setPersona(PersonaUi personaUiSelected);

    void onInvalitedPassword(String error);

    void hideProgress();

    void showProgress();
}
