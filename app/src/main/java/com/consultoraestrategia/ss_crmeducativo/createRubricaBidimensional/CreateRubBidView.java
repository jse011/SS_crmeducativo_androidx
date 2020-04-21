package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera.CabeceraView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera.RubricaCabeceraListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle.DetalleView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle.RubricaDetalleListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public interface CreateRubBidView extends BaseView<CreateRubBidPresenter>, CabeceraView, DetalleView, RubricaCabeceraListener, RubricaDetalleListener, CreateRubBidListener {
    void showCellItemDialog(ColumnHeader columnHeader, RowHeader rowHeader, Cell cell, int x, int y);
    void showListTipoNotaSingleChooser(String dialogTitle, final List<Object> items, int positionSelected, int idProgramaEducativo);
    void onSucess(String rubroEvalId, int countIndicadorList, int programaEducativoId);
    void showBuscarCampoAccion(List<CampoAccionUi> campoAccionUiList);
    void showCriterioEvaluacionItemDialog(CriterioEvaluacionUi criterioEvaluacionUi, int x, int y, IndicadorUi indicadorUi, TipoNotaUi tipoNotaUi);
    void showCriterioEvaluacionItemDialog(IndicadorUi indicadorUi, int x, int y);
    void showEditarRubroDetalle(IndicadorUi indicador, TipoNotaUi tipoNotaUi, List<Cell> cellList, boolean disabledCampoAccion);

    void onAcceptButtom();

    void showTipoNotaSingleChooserCabecera(String string, Object o, int i, int idProgramaEducativo);

    void showDialogEstrategiasEvaluacion(List<EstrategiaEvalUi> args);

    void showTituloEstrategiaSelected(String estrategia);

    void showEstrategiaInput();

    void hideEstrategiaInput();

    void showPreview(List<Object> competenciaList);

    void showDialogInfoTipoNota(String titulo, TipoNotaUi tipoNotaSelected);


    /*INICIALIZAR REACIÓN DE RÚBRICA BIDIMENSIONAL*/
    /*void showIndicadorDialogChooser(List<CompetenciaUi> competenciaList);

    void showCampoAccionDialogChooser(List<IndicadorUi> indicadorList);


    void showCompetenciaList(List<CompetenciaUi> competencias);

    //HEADERS
    void showCompetencia(String competencia);

    void showCampoAccionTitle(String camposDeAccion);

    void showTipoNotaTitle(String title);

    void showTipoNotaList(List<TipoNotaUi> tipoNotas);


    //TABLE VIEW - ALTERNATIVE
    void showTipoNivelChooser(List<TipoNotaUi> tipoNotaList);

    void showTableview(List<ColumnHeader> headerList, List<RowHeader> rows, List<List<com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell>> bodyList);

    void showHeaders(List<Header> headerList);

    void showRows(List<Row> rows);

    void showCells(List<List<Cell>> bodyList);*/

}
