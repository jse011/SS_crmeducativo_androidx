package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

/**
 * Created by kike on 08/06/2018.
 */

public interface EditarRubricaBidView extends BaseView<EditarRubricaBidPresenter> {

    void mostrarTitulos(String tituloRubrica, String alias);

    void mostrarTipoFormaSelected(String nombre);

    void mostrarTipoEvaluacion(String nombre);

    void mostrarCajaTextoTipoEvaluacion(String nombre);

    void mostrarCajaTextoTipoNota(String nombre);

    void mostrarCajaTextoTipoFormaEvaluacion(String nombre);

    void mostrarMensaje(String mensaje);

    void onSuccessMensaje(RubBidUi rubBidUi, int programaEducativoId);

    void showTipoNotaSelected(com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi  tipoNotaUi);
}
