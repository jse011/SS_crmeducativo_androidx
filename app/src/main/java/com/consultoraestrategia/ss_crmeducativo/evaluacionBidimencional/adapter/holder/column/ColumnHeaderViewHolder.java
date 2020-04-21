package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;

/**
 * Created by SCIEV on 8/03/2018.
 */

public abstract class ColumnHeaderViewHolder<T extends ColumnHeader> extends AbstractSorterViewHolder {
    public ColumnHeaderViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T columnHeader);
}
