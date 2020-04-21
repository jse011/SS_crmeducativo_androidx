package com.consultoraestrategia.ss_crmeducativo.situacion;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;

import java.util.List;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public interface SituacionView extends BaseView<SituacionPresenter> {


    void showProgress();

    void hideProgress();


    void showMsjLong(String msaje);
    void showMessage();
    void hideMessage();

    void showSituacionUIList(List<SituacionUI> list);
}
