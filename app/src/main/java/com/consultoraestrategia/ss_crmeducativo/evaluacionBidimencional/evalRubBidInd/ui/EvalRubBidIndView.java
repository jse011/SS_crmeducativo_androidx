package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.EvalRubBidIndPresenter;

import java.util.List;

/**
 * Created by @stevecampos on 28/02/2018.
 */

public interface EvalRubBidIndView extends BaseView<EvalRubBidIndPresenter> , ComentarioView, TablaEvaluacionView {

    void showAlumnoNombre(String nombre, String lastName);
    void showAlumnoProfile(String urlProfile);
    void showPuntos(String puntos);
    void showNota(double nota);
    void showDesempenio(String desempenio);
    void showLogro(String logro);
    void salirDialogo();
    void evaluarAlumno(RubBidUi rubBidUi, AlumnoProcesoUi alumnoProcesoUi, ValorTipoNotaUi valorTipoNotaUi);
    void changeContadorPagina(int inicio, int pocision);
    void callActividad(ColumnHeader evaluado, EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList);
    void callActividadTableNotifyDataSetChanged();
    void showAlertDialog(RubEvalProcUi rubEvalProcUi);
    void callServiceNotifyDataSetChanged();
    void onShowDialogPresicion(double nota, String rubroEvaluacionId, String valorTipoNotaId, String color, boolean validar);
    void changeEyeSimple();
    void changerEyeFocus();

    void showDialogMsgPred();
    void setData(ColumnHeader columnHeader, RubBidUi rubBidUi, List<ColumnHeader> columnHeaders);
    void showPickPhoto(boolean enableVideo);

    void onCleanSelector();

    void showTabComentario();

    void hideTabComentario();

    void setPublicar(PublicarEvaluacionUi publicarEvaluacionUi);

    void showBtnClean(boolean disabledEval);

    void hidePublicar();

    void showPublicar();

    void notiftyDataBaseChange();

    void showCamera();

    void addTareaArchivo(ArchivoUi item);

    void updateTareaArchivo(ArchivoUi item);

    void removeTareaArchivo(ArchivoUi item);

    void showGalery();
}

