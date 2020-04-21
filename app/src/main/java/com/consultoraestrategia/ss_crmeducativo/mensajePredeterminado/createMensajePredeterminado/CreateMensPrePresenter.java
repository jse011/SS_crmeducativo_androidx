package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado;

import com.google.android.material.textfield.TextInputEditText;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public interface CreateMensPrePresenter extends BasePresenter<CreateMensPreView> {


    void onSomeViewClick(int idView);

    void setImputs(
            TextInputEditText txtAsuntoMensajePred,
            TextInputEditText txtPresentacionMensajePred,
            TextInputEditText txtCabeceraMensajePred,
            TextInputEditText txtCuerpoMensajePred,
            TextInputEditText txtDespedidaMensajePred);

}
