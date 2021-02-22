package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.MatrizResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;

import java.util.ArrayList;
import java.util.List;

public interface RegistroCentProcesamientoView {
    void setListMatrizResultado(MatrizResultadoUi matrizResultadoUi);
    void setupTable(String color1, String color2, String color3);
    void setupList(List<PeriodoUi> periodoUis, String color2);
    void setPresenter(CentProcesoPresenter presenter);
    void onToogle(PeriodoUi oldPeriodoUi, PeriodoUi selectedperiodoUi);
    void clearListMatrizResultado();

    void showProgress();

    void hideProgress();

    void showCorner();

    void hideCorner();

    void bloqueoBotones();

    void desbloqueoBotones();

    void notifyChange();

    void setTituloCompetencia(String titulo);

    void showErrorSinInternet();

    void showErrorInterno();

    void hideError();

    void showSelecionCalendarioPerido();
}
