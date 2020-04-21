package com.consultoraestrategia.ss_crmeducativo.crearEvento;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioView;

import java.util.ArrayList;

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
}
