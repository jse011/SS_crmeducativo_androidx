package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.List;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public interface CreateMensPreView extends BaseView<CreateMensPrePresenter> {


    void setAlcanceSelected(String opcionSelected);

    void setObjetivoSelected(String opcionSelected);

    void closeActivity();

    void getImputs();

    void setDataToEditImputs(String asuntoMensaje, String cabeceraMensaje, String presentacionMensaje, String cuerpoMensaje, String despedidaMensaje, String tipoMensaje);

}
