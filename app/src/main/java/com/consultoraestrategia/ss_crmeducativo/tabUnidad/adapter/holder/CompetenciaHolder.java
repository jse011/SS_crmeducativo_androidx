package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.CapacidadAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetenciaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.competenciaTitulo)
    TextView competenciaTitulo;
    @BindView(R.id.rvCapacidades)
    RecyclerView rvCapacidades;

    public CompetenciaHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(CompetenciaUi competenciaUi, int i){
        competenciaTitulo.setText(i+". "+competenciaUi.getNombre());
        CapacidadAdapter capacidadAdapter = new CapacidadAdapter(competenciaUi.getCapacidadUis(), i);
        rvCapacidades.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rvCapacidades.setAdapter(capacidadAdapter);
    }
}
