package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row;

import androidx.constraintlayout.widget.ConstraintLayout;
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
}
