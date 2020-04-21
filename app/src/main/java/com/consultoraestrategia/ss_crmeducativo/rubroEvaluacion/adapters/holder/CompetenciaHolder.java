package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 21/12/2017.
 */

public class CompetenciaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.view3)
    View view5;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    public CompetenciaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayout.setOnClickListener(this);
    }

    public void bind(CompetenciaUi competenciaUi) {
        txtTitulo.setText(TextUtils.isEmpty(competenciaUi.getTitulo())?"":competenciaUi.getTitulo().toUpperCase());
        if (competenciaUi.getParametroDisenioUi()!=null){
            txtTitulo.setTextColor(Color.parseColor(competenciaUi.getParametroDisenioUi().getColor1()));
            view5.setBackgroundColor(Color.parseColor(competenciaUi.getParametroDisenioUi().getColor1()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraintLayout:

                break;
        }
    }
}
