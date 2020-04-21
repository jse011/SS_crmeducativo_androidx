package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.List;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public interface MensajePredeterminadoView extends BaseView<MensajePredeterminadoPresenter> {
    void showListaMensajesPredeterminador(List<MensajePredeterminadoUI> mensajePredeterminadoList);

    void setOpcionSelected(String objectSelected);

    void showListSingleChooser(String title, List<String> stringList, int position);

    void showMessage(String msj);

    void removeItemMensajePredRV(MensajePredeterminadoUI mensajePredeterminadoUI);

    void showCreateMensajePredActivity(int idIntencionSelected);

    void showEditMensajePredActivity(MensajePredeterminadoUI mensajePredeterminadoUI, int idIntencionSelected);
}
