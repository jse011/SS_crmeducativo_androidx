package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderCompetencia  extends SectioningAdapter.HeaderViewHolder {

    @BindView(R.id.textTitle)
    TextView textTitle;

    public ViewHolderCompetencia(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CompetenciaUi competenciaUi) {
            textTitle.setText(competenciaUi.getTitulo());
    }
}
