package com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio;

import com.consultoraestrategia.ss_crmeducativo.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;

import java.util.List;

public interface SeleccionarAnioAcademicoView {
    void setListAnioAcademico(List<AnioAcademicoUi> listAnioAcademico);
    void setPresenter(MainPresenter presenter);
    void hideProgress();
    void showProgress();

    void close();
}
