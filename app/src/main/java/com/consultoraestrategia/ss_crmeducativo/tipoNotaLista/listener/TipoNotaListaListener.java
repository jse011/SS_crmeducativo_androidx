package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener;

/**
 * Created by SCIEV on 26/02/2018.
 */

public interface TipoNotaListaListener {
    void onSuccessDialogTipoNotaList(int tipoNotaId, String nombre);
    void onSuccessDialogTipoNotaList(String tipoNotaId, String nombre);
}
