package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.column.ColumnHeaderViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorNombreUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by SCIEV on 8/03/2018.
 */

public class IndicadorCellViewHolder extends ColumnHeaderViewHolder<IndicadorNombreUi> {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;
    public IndicadorCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IndicadorNombreUi columnHeader) {
        if(TextUtils.isEmpty(columnHeader.getIndicadorUi().getTituloRubro())){
            txtTitle.setText(TextUtils.isEmpty(columnHeader.getIndicadorUi().getAlias())?columnHeader.getIndicadorUi().getTitulo():columnHeader.getIndicadorUi().getAlias());
        }else {
            txtTitle.setText(columnHeader.getIndicadorUi().getTituloRubro());
        }
    }
}
