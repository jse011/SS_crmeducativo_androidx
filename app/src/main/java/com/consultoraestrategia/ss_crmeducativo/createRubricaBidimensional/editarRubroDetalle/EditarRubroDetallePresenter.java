package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogKeyBoard.DialogkeyBoardView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui.EditarRubroDetalleView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;


/**
 * Created by @stevecampos on 28/02/2018.
 */

public interface EditarRubroDetallePresenter extends BaseFragmentPresenter<EditarRubroDetalleView> {

    void postCantidadLineasDesempenio(int lineCount);

    void onClickVerMasDesempenio();

    void onCampoTematicoCancel();

    void onCampoTematicoListOk();

    void onTextChangedTitulo(String titulo);

    void onTextChangedSubTitulo(String subTitulo);

    void onClickCriterioEvaluacion(CriterioEvaluacionUi criterioEvaluacionUi);

    void onClickAceptarDialogkeyboard(String contenido);

    void onCreateDialogKeyBoard(DialogkeyBoardView view);

    void onDismissDialogkeyboard();

}