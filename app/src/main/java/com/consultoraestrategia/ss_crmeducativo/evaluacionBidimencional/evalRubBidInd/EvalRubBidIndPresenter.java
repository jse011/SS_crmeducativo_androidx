package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd;

import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui.EvalRubBidIndView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.dialogComentario.EvalRubBidComPredView;

import java.util.List;
import java.util.Map;


/**
 * Created by @stevecampos on 28/02/2018.
 */

public interface EvalRubBidIndPresenter extends BaseFragmentPresenter<EvalRubBidIndView> {

    void attachView(EvalRubBidComPredView evalRubBidComPredView);

    void setAlumno(AlumnoProcesoUi alumnoProcesoUi, RubBidUi rubBidUi);

    void onFinalizar();

    void onRetroceder();

    void onAvanzar();

    void onSelectEvaluacion(ValorTipoNotaUi valorTipoNotaUi, TipoNotaUi tipoNotaUi);

    void onDeSelectEvaluacion(ValorTipoNotaUi valorTipoNotaUi, ValorTipoNotaUi valorTipoNotaHold, TipoNotaUi tipoNotaUi);

    void onDeSelectEvaluacion(ValorTipoNotaUi valorTipoNotaUi, TipoNotaUi tipoNotaUi);

    void onClickRubroEvaluacion(RubEvalProcUi rubEvalProcUi);

    void onClickBtnChange();

    void onLongEvaluacion(TipoNotaUi tipoNotaUi, ValorTipoNotaUi valorTipoNotaUi);

    void onSelectPrecicionEvaluacion(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId);

    void showDialogMsgPred();

    void saveComentario(String text);

    void setData(ColumnHeader columnHeader, RubBidUi rubBidUi, List<ColumnHeader> columnHeaders);

    void onTextChangedEditComentario(String toString);

    void onClickComentarioNormal(MensajeUi mensajeUi);


    void onClickBtnClear();

    void btnPublicar();


    void onClickCamera();

    void onClickGalery();

    void onUpdload(Map<Uri, String> photoPaths);

    void removerComentarioArchivo(ArchivoUi archivoUi);
}