package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class NotaRowViewHolder extends RowHeaderViewHolder<NotaUi>
{
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.root)
    ConstraintLayout root;
    public NotaRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(NotaUi rowHeader) {
        txtNotaEvaluacion.setText(rowHeader.getNombre());
    }
}
