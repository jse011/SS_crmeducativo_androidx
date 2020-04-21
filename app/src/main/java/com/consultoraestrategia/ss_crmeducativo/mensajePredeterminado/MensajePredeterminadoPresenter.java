package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado;


import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public interface MensajePredeterminadoPresenter extends BasePresenter<MensajePredeterminadoView> {
    void setExtras(Bundle arguments);

    void onSingleItemSelected(String objectSelected, int selectedPosition);

    void getListTiposMensajePredeterninado();

    void onClickEditarMensajePred(MensajePredeterminadoUI mensajePredeterminadoUI);

    void deleteLogicMensajePred(MensajePredeterminadoUI mensajePredeterminadoUI);

    void onFabAddClicked();

    void restoreItemMensajePred(MensajePredeterminadoUI mensajePredeterminadoUI);
}
