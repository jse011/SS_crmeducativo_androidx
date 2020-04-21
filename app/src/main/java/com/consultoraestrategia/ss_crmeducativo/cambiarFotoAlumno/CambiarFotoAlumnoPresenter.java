package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.ui.CambiarFotoAlumnoView;

public interface CambiarFotoAlumnoPresenter extends BasePresenter<CambiarFotoAlumnoView> {

    void btnAddFoto(PersonaUi personaUi);

    void onCropImageActivityResult(String uri);

    void subirFoto(PersonaUi personaUi);
}
