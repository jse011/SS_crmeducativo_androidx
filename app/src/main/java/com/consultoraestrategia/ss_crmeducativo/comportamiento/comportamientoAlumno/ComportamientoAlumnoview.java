package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.presenter.ComportamientoAlumnoPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.CursoUi;

import java.util.List;

public interface ComportamientoAlumnoview extends BaseView<ComportamientoAlumnoPresenter> {
  void setDatos(CursoUi cursoUi);
}
