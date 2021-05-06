package com.consultoraestrategia.ss_crmeducativo.crearEvento;

import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AgendaUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioView;

import java.util.ArrayList;
import java.util.Map;

public interface CrearEventoPresenter extends BasePresenter<CrearEventoView> {
    void onBtnCreateClicked(String nombre, String descripcion);

    void btnSelectDate();

    void btnSelectTime();

    void btnCloseFecha();

    void btnCloseHora();

    void onClikSaveFecha(long timeInMillis);

    void onChangeTime(int hourOfDay, int minute);

    void onClickTipoEvento();

    void onClickTipoCalendario();

    void onItemClickTipoCalendario(TipoCalendarioUi tipoComportamientoUi);

    void onSeleccionarCalendarioViewDestroyed();

    void attachView(SeleccionarCalendarioView seleccionarCalendarioView);

    void onSalirSelectPiket(ArrayList<String> photoPaths);

    void onClickItem(AlumnoUi alumnoUi);

    void onChangePadres(AlumnoUi alumnoUi);

    void onChangeAlumno(AlumnoUi alumnoUi);

    void onClickAllPadres();

    void onClickAllAlumnos();

    void onChangeSearch(String search);

    void onBtnPublicarClicked(String nombre, String descripcion);

    void onClickCloseImage();

    void onClickCamera();

    void onClickGalery();

    void onUpdload(Map<Uri, String> photoPaths);
}
