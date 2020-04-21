package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit;


import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

/**
 * Created by kike on 04/04/2018.
 */

public interface TipoNotaEditPresenter extends BasePresenter {
    void onCheckBox(boolean status);
    void onbtnAceptar(ValoresUi valoresUi);
}
