package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.CellViewHolder;

/**
 * Created by SCIEV on 19/03/2018.
 */

public abstract class CellHolderUi extends Cell{

    CellViewHolder selectCell;

    public CellViewHolder getSelectCell() {
        return selectCell;
    }

    public void setSelectCell(CellViewHolder selectCell) {
        this.selectCell = selectCell;
    }
}
