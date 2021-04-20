package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.raizlabs.android.dbflow.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 7/05/2018.
 */

public class SelectorValorRowViewHolder extends RowHeaderViewHolder<ValorTipoNotaUi> {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.textNota)
    TextView textNota;
    @BindView(R.id.root)
    ConstraintLayout root;
    public SelectorValorRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(ValorTipoNotaUi rowHeader) {

        if(StringUtils.isNullOrEmpty(rowHeader.getTitle())){
            txtTitle.setText("-");
        }else{
            txtTitle.setText(rowHeader.getTitle());
        }
        textNota.setText(String.valueOf(rowHeader.getValorDefecto()));
    }


    public void changeColor(int p_nXPosition) {
        switch (p_nXPosition) {
            case 1:
                txtTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_700));
                textNota.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_700));
                break;
            case 2:
                txtTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_green_700));
                textNota.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_green_700));
                break;
            case 3:
                txtTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_orange_A700));
                textNota.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_orange_A700));
                break;
            case 4:
                txtTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_red_700));
                textNota.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_red_700));
                break;
            default:
                txtTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.black));
                textNota.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.black));
                break;
        }
    }
}
