package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 21/12/2017.
 */

public class CompetenciaHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.view3)
    View view5;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    public CompetenciaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CompetenciaUi competenciaUi) {

        txtTitulo.setText(TextUtils.isEmpty(competenciaUi.getTitle())?"":competenciaUi.getTitle().toUpperCase());
        try {
            txtTitulo.setTextColor(Color.parseColor(competenciaUi.getColor1()));
            view5.setBackgroundColor(Color.parseColor(competenciaUi.getColor1()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
