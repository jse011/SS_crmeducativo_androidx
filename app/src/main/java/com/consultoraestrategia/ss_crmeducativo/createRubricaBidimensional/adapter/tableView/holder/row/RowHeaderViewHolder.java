package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by SCIEV on 8/03/2018.
 */

public abstract class RowHeaderViewHolder<T extends Cell> extends AbstractViewHolder {
    public RowHeaderViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T rowHeader);
}
