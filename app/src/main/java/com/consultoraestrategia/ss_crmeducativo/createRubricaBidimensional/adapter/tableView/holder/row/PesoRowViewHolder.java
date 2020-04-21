package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.raizlabs.android.dbflow.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 7/05/2018.
 */

public class PesoRowViewHolder extends RowHeaderViewHolder<IndicadorUi> {
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.root)
    ConstraintLayout root;
    public PesoRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IndicadorUi rowHeader) {
        if(StringUtils.isNullOrEmpty(rowHeader.getTitle())){
            txtNotaEvaluacion.setText("-");
        }else{
            txtNotaEvaluacion.setText(rowHeader.getTitle());
        }
    }
}
