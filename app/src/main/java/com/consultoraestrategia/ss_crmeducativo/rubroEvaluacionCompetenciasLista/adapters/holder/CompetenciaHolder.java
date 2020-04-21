package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.adapters.holder;


import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.listener.CompetenciaListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 21/12/2017.
 */

public class CompetenciaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.checkCapacidad)
    CheckBox checkCapacidad;
    @BindView(R.id.txtCapacidad)
    TextView txtCapacidad;

    public CompetenciaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final CompetenciaUi competenciaUi, final CompetenciaListener listener) {
        checkCapacidad.setChecked(competenciaUi.isChecked());
        txtCapacidad.setText(competenciaUi.getTitulo());
        checkCapacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickCompetencia(competenciaUi);
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(competenciaUi.isChecked()){
                    checkCapacidad.setChecked(false);
                }else {
                    checkCapacidad.setChecked(true);
                }
                listener.onClickCompetencia(competenciaUi);
            }
        });
    }
}
