package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaRubroHolder extends AbstractViewHolder {

    @BindView(R.id.titulo)
    TextView titulo;

    public ColumnaRubroHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(RubroEvaluacionUi rubroEvaluacionUi){
        titulo.setText(String.valueOf(rubroEvaluacionUi.getTitle()));
    }
}
