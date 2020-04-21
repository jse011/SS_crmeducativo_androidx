package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by SCIEV on 8/03/2018.
 */

public abstract class RowHeaderViewHolder<T extends RowHeader> extends AbstractViewHolder {
    public RowHeaderViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T rowHeader);
}
