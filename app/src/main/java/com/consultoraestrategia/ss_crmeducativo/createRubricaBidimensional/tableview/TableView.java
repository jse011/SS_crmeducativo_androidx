package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.util.List;

/**
 * Created by @stevecampos on 22/02/2018.
 */

public interface TableView {
    void showTableview(List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, ITableViewListener listener);
    void updateCellItem(int x, int y, Cell item);
}
