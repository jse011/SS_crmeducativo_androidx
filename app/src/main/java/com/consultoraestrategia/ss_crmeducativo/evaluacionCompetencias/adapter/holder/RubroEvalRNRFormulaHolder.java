package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.RubroEvalRNRFormulaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 19/04/2018.
 */

public class RubroEvalRNRFormulaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_cant_rubro)
    TextView textViewCantidad;

    public RubroEvalRNRFormulaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(RubroEvalRNRFormulaUi rubroEvalRNRFormulaUi) {
        textViewCantidad.setText(rubroEvalRNRFormulaUi.getContador()+"");
    }
}
