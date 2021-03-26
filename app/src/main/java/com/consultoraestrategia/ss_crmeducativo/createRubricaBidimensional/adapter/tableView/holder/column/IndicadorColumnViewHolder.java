package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.column;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by SCIEV on 8/03/2018.
 */

public class IndicadorColumnViewHolder extends ColumnHeaderViewHolder<IndicadorUi> {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;
    public IndicadorColumnViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IndicadorUi columnHeader) {

    }

    public void bind(IndicadorUi columnHeader, int postionY) {
        txtTitle.setText(String.valueOf(postionY+1));
    }
}
