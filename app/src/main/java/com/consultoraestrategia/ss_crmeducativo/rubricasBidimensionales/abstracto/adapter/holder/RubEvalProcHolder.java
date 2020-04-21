package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 19/03/2018.
 */

public class RubEvalProcHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_cant_rubro)
    TextView textViewNumero;

    public RubEvalProcHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(RubEvalProcUi rubEvalProcUi) {
        textViewNumero.setText(rubEvalProcUi.getTitulo());
    }
}
