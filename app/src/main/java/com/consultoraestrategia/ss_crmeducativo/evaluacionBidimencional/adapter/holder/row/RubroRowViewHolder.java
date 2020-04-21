package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row;

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

public class RubroRowViewHolder extends RowHeaderViewHolder<RubEvalProcUi>
{
    @BindView(R.id.txt_rubro_evaluacion)
    TextView txtRubroEvaluacion;
    @BindView(R.id.root)
    ConstraintLayout root;
    private RubEvalProcUi rubEvalProcUi;

    public RubroRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(RubEvalProcUi rowHeader) {
        this.rubEvalProcUi = rowHeader;
        txtRubroEvaluacion.setText(rowHeader.getTitulo());
    }

    public RubEvalProcUi getRubEvalProcUi() {
        return rubEvalProcUi;
    }
}
