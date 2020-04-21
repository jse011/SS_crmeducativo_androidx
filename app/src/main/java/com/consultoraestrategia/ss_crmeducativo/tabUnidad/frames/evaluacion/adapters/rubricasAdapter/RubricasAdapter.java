package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubricasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubrosCompetenciasAdapter.RubroHolder;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionUnidadListener;

import java.util.List;

public class RubricasAdapter extends RecyclerView.Adapter {


    List<RubricaUi> rubricaUis;
    EvaluacionUnidadListener listener;
    static final int RUBRICA = 1, RUBRO = 2;

    public RubricasAdapter(List<RubricaUi> rubricaUis, EvaluacionUnidadListener listener) {
        this.rubricaUis = rubricaUis;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case RUBRO:
                return new RubroHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rubro_unidad, viewGroup, false));
            default:
                return new RubricaHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rubrica_unidad, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RubricaUi rubro = rubricaUis.get(i);
        if (viewHolder instanceof RubricaHolder) {
            RubricaHolder rubricaHolder = (RubricaHolder) viewHolder;
            rubricaHolder.bind(rubro, listener);
        } else {
            RubroHolder rubroHolder = (RubroHolder) viewHolder;
            rubroHolder.bind(rubro, listener);
        }
    }

    @Override
    public int getItemCount() {
        return rubricaUis.size();
    }

    public void setListRubricas(List<RubricaUi> rubricas) {
        this.rubricaUis.clear();
        this.rubricaUis.addAll(rubricas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        RubricaUi rubro = rubricaUis.get(position);
        switch (rubro.getTipo()) {
            case RUBRO:
                return RUBRO;
            default:
                return RUBRICA;
        }
    }
}
