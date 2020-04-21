package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class AlternoSelectorIconosRowViewHolder extends RowHeaderViewHolder<ValorTipoNotaUi>
{
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txt_nota)
    TextView txtNota;
    @BindView(R.id.txt_intervalo)
    TextView txtIntervalo;
    public AlternoSelectorIconosRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(ValorTipoNotaUi rowHeader) {
        txtNota.setText(String.valueOf((int)rowHeader.getValorNumerico()));
        txtIntervalo.setText(rowHeader.getIntervalo());
    }
}
