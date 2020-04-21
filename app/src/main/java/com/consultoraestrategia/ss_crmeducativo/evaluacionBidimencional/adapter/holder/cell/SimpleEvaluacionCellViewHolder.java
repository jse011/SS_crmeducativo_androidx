package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class SimpleEvaluacionCellViewHolder extends EvaluacionCellViewHolder{
    @BindView(R.id.text_fondo)
    TextView textFondo;
    @BindView(R.id.txt_evalaucion)
    TextView txtEvalaucion;
    @BindView(R.id.root)
    ConstraintLayout root;


    public SimpleEvaluacionCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }



    @Override
    TextView getTextFondo() {
        return textFondo;
    }

    @Override
    TextView getTexEvalaucion() {
        return txtEvalaucion;
    }


}
