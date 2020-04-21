package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listaAlumnos;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

import java.util.List;

public interface ListaAlumnosView extends BaseView<ListaAlumnosPresenter> {
    void showListPersonas(List<PersonaUi> personaUiList);
    void showActivityPreview(int personaId, int cargacursoId, int entidadId, int georeferenciaId);
}
