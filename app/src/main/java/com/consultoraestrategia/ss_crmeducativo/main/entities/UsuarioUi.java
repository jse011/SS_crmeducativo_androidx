package com.consultoraestrategia.ss_crmeducativo.main.entities;

import java.util.List;

public class UsuarioUi {
    private int usuarioid;
    private List<UsuarioRolGeoReferenciaUi> entidadUiList;
    private PersonaUi personaUi;
    private boolean successData;

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public List<UsuarioRolGeoReferenciaUi> getEntidadUiList() {
        return entidadUiList;
    }

    public void setEntidadUiList(List<UsuarioRolGeoReferenciaUi> entidadUiList) {
        this.entidadUiList = entidadUiList;
    }

    public PersonaUi getPersonaUi() {
        return personaUi;
    }

    public void setPersonaUi(PersonaUi personaUi) {
        this.personaUi = personaUi;
    }

    public boolean isSuccessData() {
        return successData;
    }

    public void setSuccessData(boolean successData) {
        this.successData = successData;
    }
}
