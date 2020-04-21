package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.AprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SCIEV on 13/02/2018.
 */

public class ItemCompetenciaViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtTipoCompetencia)
    TextView txtTipoCompetencia;
    @BindView(R.id.txtCompetencia)
    TextView txtCompetencia;
    @BindView(R.id.ver_mas)
    ImageView verMas;
    @BindView(R.id.constraintLayoutCompentencia)
    ConstraintLayout constraintLayout;
    private boolean verMasState=true;
    private AprendizajeAdapter aprendizajeAdapter;

    public ItemCompetenciaViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(CompetenciaUi competenciaUi, AprendizajeAdapter aprendizajeAdapter, boolean views) {
        this.aprendizajeAdapter = aprendizajeAdapter;
        this.verMasState=views;
        if (views){
            if (TextUtils.isEmpty(competenciaUi.getTipo())) {
                txtTipoCompetencia.setVisibility(View.GONE);
                verMas.setVisibility(View.GONE);
            } else {
                txtTipoCompetencia.setVisibility(View.VISIBLE);
                txtTipoCompetencia.setText(competenciaUi.getTipo());
                verMas.setVisibility(View.VISIBLE);
                verMas.setRotation(0);
            }

            String competencia = "<b>Competencia:</b> " + competenciaUi.getTitulo();
            txtCompetencia.setVisibility(View.VISIBLE);
            txtCompetencia.setText(Html.fromHtml(competencia));
        }else {
            if (TextUtils.isEmpty(competenciaUi.getTipo())){
                txtTipoCompetencia.setVisibility(View.GONE);
                verMas.setVisibility(View.GONE);
            }else {
                txtCompetencia.setVisibility(View.GONE);
                verMas.setVisibility(View.VISIBLE);
                verMas.setRotation(180);
            }

        }
    }

    @OnClick({R.id.constraintLayoutCompentencia, R.id.ver_mas})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraintLayoutCompentencia:
                aprendizajeAdapter.changeStateView();
                verMasMethod();
                break;
            case R.id.ver_mas:
                aprendizajeAdapter.changeStateView();
                verMasMethod();
                break;
        }
    }

    private void verMasMethod(){
        if (verMasState){
            verMas.setRotation(0);
            verMasState=false;
        }
        else {
            verMas.setRotation(180);
            verMasState=true;
        }
    }


}
