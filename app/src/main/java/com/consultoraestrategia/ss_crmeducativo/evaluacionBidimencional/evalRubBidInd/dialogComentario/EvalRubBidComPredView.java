package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.dialogComentario;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.EvalRubBidIndPresenter;

import java.util.List;

public interface EvalRubBidComPredView {
    void onAttach(EvalRubBidIndPresenter evalRubBidIndPresenter);
    void showListComentarios(List<MensajeUi> mensajeUiList);
}
