package com.consultoraestrategia.ss_crmeducativo.crearEvento;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AgendaUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;

import java.util.List;

public interface CrearEventoView extends BaseView<CrearEventoPresenter> {
    void selectDate();

    void selectTime();

    void hideBtnCloseFecha();

    void showBtnCloseFecha();

    void setFecha(String fecha);

    void hideBtnCloseHora();

    void showBtnCloseHora();

    void setHora(String hora);

    void setNombre(String nombre);

    void setDescripcion(String descripcion);

    void setTipoEvento(String nombre);

    void setTipoCalendario(String nombre);

    void showDialogSearchCalendario();

    boolean isInternetAvailable();

    void showOffline();

    void hideOffline();

    void finishActivity();

    void viewsDisabled();

    void viewsEnabled();

    void showContenPreview();

    void hideContenPreview();

    void hideImage();

    void showImage(String path);

   void showDialogProgress();

   void hideDialogProgress();

    void showListaAlumnos(List<AlumnoUi> alumnoUiList);

    void updateAlumno(AlumnoUi alumnoUi);

    void setCheckboxAllAlumnos(boolean checkbox);

    void setCheckboxAllPadres(boolean checkbox);

    void showAllCheck();

    void hideAllCheck();

    void setEquiteLista(String nombre);

    void setNombresAlumnos(String nombre);

    void panelUpAlumnos();

}
