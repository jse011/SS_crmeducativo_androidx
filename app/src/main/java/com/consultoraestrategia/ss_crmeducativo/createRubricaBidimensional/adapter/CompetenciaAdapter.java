package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder.CapacidadHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder.CompetenciaHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class CompetenciaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    private final int CAPACIDAD = 0, COMPETENCIA = 1;
    private String TAG = CompetenciaAdapter.class.getSimpleName();

    public CompetenciaAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == COMPETENCIA) {
            View v1 = inflater.inflate(R.layout.layout_item_rubro_evaluacion_competencia, viewGroup, false);
            viewHolder = new CompetenciaHolder(v1);
        } else {
            View v2 = inflater.inflate(R.layout.layout_item_crear_rubrica_evaluacion_capacidades, viewGroup, false);
            viewHolder = new CapacidadHolder(v2);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case COMPETENCIA:
                CompetenciaUi competenciaHolder = (CompetenciaUi) items.get(position);
                CompetenciaHolder vh1 = (CompetenciaHolder) viewHolder;
                vh1.bind(competenciaHolder);
                break;
            case CAPACIDAD:
                CapacidadUi capacidadUi = (CapacidadUi) items.get(position);
                CapacidadHolder vh2 = (CapacidadHolder) viewHolder;
                vh2.bind(capacidadUi);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof CompetenciaUi) {
            return COMPETENCIA;
        } else if (items.get(position) instanceof CapacidadUi) {
            return CAPACIDAD;
        }
        return -1;
    }

    public void setItems(List<Object> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}
