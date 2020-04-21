package com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoHorarioDiaUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldaHorarioHolder extends AbstractViewHolder {
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.fondo_banner)
    View fondoBanner;
    @BindView(R.id.position)
    TextView position;
    public CeldaHorarioHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindHorario(CursoHorarioDiaUi celdaHorarioUi, int p_nYPosition) {
        showFondoHorario();
        alternarFondoHorario(p_nYPosition);

        if(celdaHorarioUi.getCursoUi()!=null){
            position.setVisibility(View.VISIBLE);
            Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.circled_rounded);
            try {
                circle.mutate().setColorFilter(Color.parseColor(celdaHorarioUi.getCursoUi().getColor1()), PorterDuff.Mode.SRC_ATOP);
            }catch (Exception e){
                e.printStackTrace();
            }
            position.setBackground(circle);
            position.setText(String.valueOf(celdaHorarioUi.getCursoUi().getPosicion()));
            position.setTextColor(Color.parseColor("#ffffff"));
        }else {
            position.setVisibility(View.GONE);
        }

    }

    private void alternarFondoHorario(int p_nYPosition) {
        if((p_nYPosition % 2)==0){
            fondo.setAlpha(0.7f);
        }else {
            fondo.setAlpha(1.0f);
        }
    }

    public void bindBanner(BanerUi banerUi) {
        showFondoBanner();
    }

    private void showFondoHorario(){
        fondo.setVisibility(View.VISIBLE);
        fondoBanner.setVisibility(View.GONE);
    }

    private void showFondoBanner(){
        fondo.setVisibility(View.GONE);
        fondoBanner.setVisibility(View.VISIBLE);
    }
}
