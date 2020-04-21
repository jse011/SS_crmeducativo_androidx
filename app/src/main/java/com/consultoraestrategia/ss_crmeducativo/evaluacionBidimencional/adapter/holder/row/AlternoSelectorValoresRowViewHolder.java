package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row;

import android.graphics.Color;
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

public class AlternoSelectorValoresRowViewHolder extends RowHeaderViewHolder<ValorTipoNotaUi>
{
    @BindView(R.id.viewFondo)
    TextView viewFondo;
    @BindView(R.id.textNota)
    TextView textNota;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txt_intervalo)
    TextView txtIntervalo;
    public AlternoSelectorValoresRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(ValorTipoNotaUi rowHeader) {
        textNota.setText(String.format("%.1f", rowHeader.getValorNumerico()));
        switch (rowHeader.getEstilo()){
            case AZUL:
                viewFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_700));
                textNota.setTextColor(Color.WHITE);
                txtIntervalo.setTextColor(Color.WHITE);
                break;
            case ROJO:
                viewFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_pink_100));
                textNota.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_red_700));
                txtIntervalo.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_red_700));
                break;
            case ANARANJADO:
                viewFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_orange_A700));
                textNota.setTextColor(Color.WHITE);
                txtIntervalo.setTextColor(Color.WHITE);
                break;
            case GREY:
                viewFondo.setBackgroundColor(Color.WHITE);
                textNota.setTextColor(Color.BLACK);
                txtIntervalo.setTextColor(Color.BLACK);
                break;
            case VERDE:
                viewFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_green_700));
                textNota.setTextColor(Color.WHITE);
                txtIntervalo.setTextColor(Color.WHITE);
                break;
        }
        txtIntervalo.setText(rowHeader.getIntervalo());
    }
}
