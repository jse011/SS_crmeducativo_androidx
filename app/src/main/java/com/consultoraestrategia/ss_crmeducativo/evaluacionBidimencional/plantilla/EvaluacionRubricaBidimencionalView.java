package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionRubricaBidimencionalPresenter;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

import java.util.List;

/**
 * Created by SCIEV on 8/03/2018.
 */

public interface EvaluacionRubricaBidimencionalView extends BaseView<EvaluacionRubricaBidimencionalPresenter> {
    void showTableview(String titulo, List<RowHeader> headerList, List<ColumnHeader> rows, List<List<Cell>> bodyList);

    void showTeamList(int cargaCursoId, int cursoId, int idCargaAcademica, int rubroEvaluacionId);

    void showTeamList(int cargaCursoId, int cursoId, int idCargaAcademica, String rubroEvaluacionId);

    void startEvalBidInd();

    void addRowRange(GrupoProcesoUi grupoProcesoUi, List<ColumnHeader> columnHeaders, List<List<Cell>> cellItems);

    void showTitle(String title);

    void tableNotifyDataSetChanged();

    void changeCellItemRange(int posicion, List<Cell> cellList);

    void showAlertDialogIndicador(IndicadorUi indicadorUi);

    void showActividadImportacion(BEVariables beVariables);

    void showMsgActualizacion();

    void hideMsgActualizacion();

    void showinfoUsuario(AlumnoProcesoUi alumnoProcesoUi, String rubBidId);

    void showInfoRubro(RubEvalProcUi rubEvalProcUi);

    void showFrameLayoutGrupos();

    void hideFrameLayoutGrupos();

    void cerrar();

    void onSincronization(int programaEducativoId, String rubBidId);

    void showDialogProgress();

    void hideDialogProgress();

    void showCloseDialogProgress();

    void hideCloseDialogProgress();

    void removeRowRange(GrupoProcesoUi grupoProcesoUi, List<ColumnHeader> columnHeaders, List<List<Cell>> cellList);

    void hideRow(AlumnoProcesoUi alumnoProcesoUi);

    void showRow(int posicion, AlumnoProcesoUi alumnoProcesoUi);

}
