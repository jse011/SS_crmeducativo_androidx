package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class RubroEvaluacionColumnViewHolder extends ColumnHeaderViewHolder<RubEvalProcUi> {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;
    private RubEvalProcUi rubEvalProcUi;

    public RubroEvaluacionColumnViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(RubEvalProcUi columnHeader) {
        this.rubEvalProcUi = columnHeader;
        txtTitle.setText(columnHeader.getTitulo());
    }

    public RubEvalProcUi getRubEvalProcUi() {
        return rubEvalProcUi;
    }
}
