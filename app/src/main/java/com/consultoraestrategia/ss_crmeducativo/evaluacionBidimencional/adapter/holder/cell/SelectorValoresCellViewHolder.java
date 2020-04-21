package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.raizlabs.android.dbflow.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class SelectorValoresCellViewHolder extends CellViewHolder<EvalProcUi> {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;
    private EvalProcUi evalProcUi;

    public SelectorValoresCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(EvalProcUi cell) {
        evalProcUi = cell;
        if(StringUtils.isNullOrEmpty(cell.getEscala()) || StringUtils.isNullOrEmpty(cell.getEscala())){
            txtTitle.setText("-");
        }else{
            txtTitle.setText(cell.getEscala());
        }
    }

    public EvalProcUi getEvalProcUi() {
        return evalProcUi;
    }
}
