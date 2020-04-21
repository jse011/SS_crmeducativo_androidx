package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.List;

/**
 * Created by kike on 11/04/2018.
 */

public interface FiltradoPresenter extends BasePresenter<FiltradoView> {
    void filtradoCheckItemListener(FiltradoUi filtradoUi);

    void onbtnAceptar();

}
