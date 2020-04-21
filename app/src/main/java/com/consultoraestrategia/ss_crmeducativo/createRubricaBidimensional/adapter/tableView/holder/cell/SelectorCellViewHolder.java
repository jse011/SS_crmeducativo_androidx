package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by SCIEV on 8/03/2018.
 */

public class SelectorCellViewHolder extends CellViewHolder<CriterioEvaluacionUi> {
    @BindView(R.id.text_fondo)
    TextView textFondo;
    @BindView(R.id.txt_evalaucion)
    TextView txtEvalaucion;
    @BindView(R.id.root)
    ConstraintLayout root;

    public SelectorCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    private CriterioEvaluacionUi criterioEvaluacionUi;
    @Override
    public void bind(CriterioEvaluacionUi cell) {
        this.criterioEvaluacionUi = cell;
        txtEvalaucion.setText(cell.getTitulo());
    }

    public CriterioEvaluacionUi getCriterioEvaluacionUi() {
        return criterioEvaluacionUi;

    }
}
