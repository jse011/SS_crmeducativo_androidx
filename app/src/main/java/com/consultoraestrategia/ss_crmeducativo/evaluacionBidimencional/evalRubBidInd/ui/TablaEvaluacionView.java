package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;

import java.util.List;

public interface TablaEvaluacionView  {

    void showTableModoAvanzado(String titulo, List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, boolean disabledEval);

    void showTableModoSimple(String titulo,List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, boolean disabledEval);

    void updateCellTableview( List<List<Cell>> bodyList);

    void cleanCellTableview(List<ColumnHeader> columnHeaderList);
}
