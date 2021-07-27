package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter.TabCreateComportPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;

import java.util.ArrayList;
import java.util.List;

public interface TabCreateComportView extends BaseView<TabCreateComportPresenter> {
  void setAutoCompleteList(ArrayList<AlumnoUi> nombres);
  void showListTipos(List<TipoUi> tipoUis);
  void setDatosC(ComportamientoUi comportamientoUi);
  void setDatosAlumno(AlumnoUi alumno);
  void setColorParametroDisenio(String colorDisenio, long fecha);
  void showTiposPadres(List<TipoUi> tipoUis);

    void clearEditText();
    void setDatosArchivo(List<ArchivoUi> archivoUiList);

    void setError();

    void showDialogComportamientoTipo();

    void showTipoComportamiento(TipoComportamientoUi tipoComportamientoUi);

  void hideDialogComportamientoTipo();

  void showHoraComportamiento(long fechaseleted);

    void showCalendarComportamineto(long fechaseleted);

  AlumnoUi getAlumno();

  void updateUsuario(UsuarioUi usuarioUi);

  void setSelectedTutor(boolean selectedTutor);

  void setSelectedApoderado(boolean selectedTutor);

  void setSelectedPadre(boolean selectedTutor);
}
