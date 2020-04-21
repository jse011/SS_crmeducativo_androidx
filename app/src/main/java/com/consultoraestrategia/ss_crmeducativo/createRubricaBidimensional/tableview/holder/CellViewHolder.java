package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 9/02/2018.
 */

public class CellViewHolder extends AbstractViewHolder {

    @BindView(R.id.txtCell)
    public TextView txtCell;
    @BindView(R.id.root)
    public ConstraintLayout root;

    public CellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Cell cell) {
        if (cell.isSelected()) {
            root.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), cell.getBgColor() != 0 ? cell.getBgColor() : R.color.colorAccent));
        } else {
            root.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        }

        txtCell.setText(cell.getTitle());
        root.getLayoutParams().width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        txtCell.requestLayout();
    }
}
