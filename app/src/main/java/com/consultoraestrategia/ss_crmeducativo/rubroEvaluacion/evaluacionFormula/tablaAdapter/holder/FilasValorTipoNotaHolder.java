package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder;


import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.NotaUi;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto.CeldasViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 11/05/2018.
 */

public class FilasValorTipoNotaHolder extends CeldasViewHolder<NotaUi> {


    @BindView(R.id.textViewValorNumerico)
    TextView textViewValorNumerico;

    public FilasValorTipoNotaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(NotaUi notaUi) {
        if(notaUi.getNota()==-1){
            clearNota();
            return;
        }
        textViewValorNumerico.setText(String.valueOf(notaUi.getNota()));

    }

    private void clearNota() {
        textViewValorNumerico.setText("-");
        // txtResultado.setVisibility(View.VISIBLE);
    }
}
