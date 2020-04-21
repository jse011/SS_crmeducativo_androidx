package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.AprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.decoration.ApredizajeDecorator;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardCompeteciasUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class CompetenciasViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.lin_Competencias)
    LinearLayout linCompetencias;
    @BindView(R.id.rc_Competencias)
    RecyclerView rcCompetencias;
    @BindView(R.id.txtvacio)
    TextView vacio;
    @BindView(R.id.txt_titulo)
    TextView vacioTitulo;

    public CompetenciasViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CardCompeteciasUi cardCompeteciasUi) {
        if (cardCompeteciasUi.getCompetenciaUis().isEmpty()) {
            vacio.setVisibility(View.VISIBLE);
            vacioTitulo.setVisibility(View.VISIBLE);
        } else {
            vacio.setVisibility(View.GONE);
            vacioTitulo.setVisibility(View.GONE);
        }
        List<Object> objects = cardCompeteciasUi.getCompetenciaUis();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        AprendizajeAdapter adapter = new AprendizajeAdapter(objects, null, null);
        rcCompetencias.setAdapter(adapter);
        rcCompetencias.setLayoutManager(linearLayoutManager);
        rcCompetencias.addItemDecoration(new ApredizajeDecorator(32));
        rcCompetencias.setNestedScrollingEnabled(false);
    }


}
