package com.consultoraestrategia.ss_crmeducativo.main.changePerfil;

import com.consultoraestrategia.ss_crmeducativo.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;

public interface ChangePerfilView {
    void setPresenter(MainPresenter presenter);
    void setPersona(PersonaUi personaUi);
    void close();

    void showFaceDectecion(int georeferenciaId, PersonaUi personaId);

    void updatePersona(PersonaUi personaUi);

    void disabledButtons();

    void showProgress();

    void enabledButtons();

    void hideProgress();
}
