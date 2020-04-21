package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui.EvalRubBidIndView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.EvaluacionRubricaBidimencionalView;

import java.util.List;

/**
 * Created by SCIEV on 8/03/2018.
 */

public interface EvaluacionRubricaBidimencionalPresenter extends BasePresenter<EvaluacionRubricaBidimencionalView> {
    void onClickAlumno(AlumnoProcesoUi alumnoProcesoUi);
    void onChangeRubricaBidimencional();
    void onSelectGrupo(GrupoProcesoUi grupoProcesoUi);
    void onSaveEvaluacion(EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList);
    void onClickRubroEvaluacion(RubEvalProcUi rubEvalProcUi);
    void onBtnActualizarRubro();
    void onQueryTextChange(String newText);
    void onClikCornerTableView();
    void onQueryTextSubmit(String query);
    void onClickSelector(EvalProcUi evalProcUi);
    void recalcularMediaDesviacion();
    void onAttachView(EvalRubBidIndView evalRubBidIndView);
    void onCreateDialogEvalRubrica();
    void onDestroyDialogEvalRubrica();
}
