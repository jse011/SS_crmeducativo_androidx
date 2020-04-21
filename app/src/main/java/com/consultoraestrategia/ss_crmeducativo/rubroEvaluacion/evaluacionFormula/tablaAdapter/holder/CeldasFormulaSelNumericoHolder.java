package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.EvaluacionFormulaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto.CeldasViewHolder;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 16/05/2018.
 */

public class CeldasFormulaSelNumericoHolder extends CeldasViewHolder<NotaUi> {
    @BindView(R.id.textViewValorNumerico)
    TextView textViewValorNumerico;
    @BindView(R.id.root)
    ConstraintLayout root;

    public CeldasFormulaSelNumericoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(NotaUi notaUi) {
        change(notaUi.getRubro());
        double nota = notaUi.getNota();
        if (nota!=0) {
            String notaFormatted = String.format("%.0f", Double.valueOf(nota));
            textViewValorNumerico.setText(notaFormatted);
        } else {
            clearNota();
        }
    }


    private void clearNota() {
        textViewValorNumerico.setText("-");
    }

    private void change(EvaluacionFormula_RubroEvaluacionUi rubroEvaluacionUi) {
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        if (rubroEvaluacionUi.getTipoRubro() == EvaluacionFormulaAdapter.COLUMMNA_RUBRO_FORMULA) {
            layoutParams.width = (int) root.getResources().getDimension(R.dimen.table_header_width_eval_session);
        } else {
            layoutParams.width = (int) Utils.convertDpToPixel(90, root.getContext());
        }
    }
}
