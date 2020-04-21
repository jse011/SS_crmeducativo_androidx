package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

/**
 * Created by kike on 04/04/2018.
 */

public interface TipoNotaEditView extends BaseView<TipoNotaEditPresenter> {
    //void checkBox();
    void mostrarCajatexto();

    void ocultarCajaTexto();

    void mostrarMensaje(String mensaje);

    void onAceptarSuccessDialog(ValoresUi valoresUi);
}
