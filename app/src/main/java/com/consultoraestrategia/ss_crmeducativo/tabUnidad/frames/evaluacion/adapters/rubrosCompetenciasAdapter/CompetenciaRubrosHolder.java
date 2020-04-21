package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubrosCompetenciasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetenciaRubrosHolder  extends RecyclerView.ViewHolder {
    @BindView(R.id.competencia)
    TextView competencia;

    public CompetenciaRubrosHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CompetenciaUi object) {
        competencia.setText(object.getNombre().toUpperCase());
    }
}
