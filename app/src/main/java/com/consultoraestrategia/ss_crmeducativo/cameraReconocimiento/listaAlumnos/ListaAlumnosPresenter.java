package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listaAlumnos;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

public interface ListaAlumnosPresenter extends BasePresenter<ListaAlumnosView> {
    void onClikSubirFoto(PersonaUi personaUi);
}
