package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.CambiarFotoAlumnoPresenter;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

import java.util.List;

public interface CambiarFotoAlumnoView extends BaseView<CambiarFotoAlumnoPresenter> {

    void showListPersonas(List<PersonaUi> personaUiList);

    void startCropImageActivity(String path);

    void updatePersona(PersonaUi value);
    boolean isInternetAvailable();
}
