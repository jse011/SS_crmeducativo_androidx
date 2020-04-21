package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public interface RubricasView extends BaseView<RubricasAbstractPresenter> {
    void mostrarProgressBar();

    void ocultarProgressBar();

    void mostrarMensaje(String stringRes);

    void mostrarMensajeSnack(String mensaje);

    void ocultarMensaje();

    void mostrarListaRubricas(List<RubBidUi> objectList);

    void ocultarListaRubrica();

    void mostrarListaRubrica();

    void actualizarVista(RubBidUi rubBidUi);




    /*Vistas Rubrica Bidim*/
    void evaluacionRubricaBidimensional(RubBidUi rubBidUi, int cargaCursoId);

    void launchCreateRubBidActivity(String idCargaCurso, String idCurso, int idCalendarioPeriodo, int idProgramaEducativo);

    void iniciarEditarRubrica(RubBidUi rubBidUi);

    void mostrarDialogEliminarRubrica(RubBidUi rubBidUi, int programaEducativoId);

    void eliminarRubricaBid(RubBidUi rubBidUi);

    void editarRubricaBid(RubBidUi rubBidUi);

    void succesDelete(int programaEducativoId, String key);

    void showSendMessage(List<Persona> personaList, int cargaCursoId, String rubBidUi, int tipoLayoutMensaje, int programaEducativoId);

    void showTipoRubricaBidimensional();

    void showDialogPublicarTodoEvaluaciones(RubBidUi rubBidUi);

    void succesChangePublicar(int programaEDucativoId, String key);
}
