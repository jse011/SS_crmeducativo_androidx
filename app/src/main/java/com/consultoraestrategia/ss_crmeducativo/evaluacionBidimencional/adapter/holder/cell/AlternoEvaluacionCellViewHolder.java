package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class AlternoEvaluacionCellViewHolder extends EvaluacionCellViewHolder{
    @BindView(R.id.text_fondo)
    TextView textFondo;
    @BindView(R.id.txt_evalaucion)
    TextView txtEvalaucion;
    @BindView(R.id.root)
    ConstraintLayout root;

    @Override
    TextView getTextFondo() {
        return textFondo;
    }

    @Override
    TextView getTexEvalaucion() {
        return txtEvalaucion;
    }

    public AlternoEvaluacionCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(ValorTipoNotaUi cell) {
        super.bind(cell);
        txtEvalaucion.setText(String.format("%.1f", cell.getValorNumerico()));
    }

}
