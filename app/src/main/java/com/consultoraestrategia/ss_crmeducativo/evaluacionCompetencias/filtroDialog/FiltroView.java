package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtroDialog;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.ArrayList;

public interface FiltroView {
    void setLisFiltroDialog(ArrayList<FiltradoUi> filtradoUiList);

    void hideDialog();
}
