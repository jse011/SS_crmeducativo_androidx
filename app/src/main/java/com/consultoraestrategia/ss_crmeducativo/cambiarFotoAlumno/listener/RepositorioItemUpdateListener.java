package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.listener;


import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

public interface RepositorioItemUpdateListener {
    void btnAddFoto(PersonaUi personaUi);

    void subirFoto(PersonaUi personaUi);
}
