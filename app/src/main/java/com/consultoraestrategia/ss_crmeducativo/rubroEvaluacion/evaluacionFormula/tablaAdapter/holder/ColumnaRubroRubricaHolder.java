package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto.CeldasViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 11/05/2018.
 */

public class ColumnaRubroRubricaHolder extends CeldasViewHolder<EvaluacionFormula_RubroEvaluacionUi> {
    @BindView(R.id.txtTitle)TextView textViewRubros;
    private EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi;

    public ColumnaRubroRubricaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi) {
        this.rubroProcesoUi = rubroProcesoUi;
        textViewRubros.setText(rubroProcesoUi.getTitle());
    }

    public EvaluacionFormula_RubroEvaluacionUi getRubroProcesoUi() {
        return rubroProcesoUi;
    }
}
