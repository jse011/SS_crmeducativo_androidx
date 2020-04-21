package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.adapter.holder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.adapter.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 10/05/2018.
 */

public class IndicadoresCamposAccionHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_inidicador)
    TextView textViewIndicador;
    @BindView(R.id.rec_campo_accion)
    RecyclerView recyclerView;
    CamposAccionAdapter camposAccionAdapter;

    public IndicadoresCamposAccionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(IndicadoresCamposAccionUi indicadoresCamposAccionUi) {
        textViewIndicador.setText(indicadoresCamposAccionUi.getTitulo());
        camposAccionAdapter = new CamposAccionAdapter(indicadoresCamposAccionUi.getCampoAccionList());
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        recyclerView.setAdapter(camposAccionAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
