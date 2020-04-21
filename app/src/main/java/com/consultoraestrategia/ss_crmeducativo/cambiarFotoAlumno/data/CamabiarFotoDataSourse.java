package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data;

import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

import java.util.List;

public interface CamabiarFotoDataSourse {
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    List<PersonaUi> getPersonasDelCurso(int cargaCursoId);

    void savePathPersona(PersonaUi personaUi);

    void uploadFileCasoRubro(PersonaUi personaUi,  Callback<String> stringCallback);
}
