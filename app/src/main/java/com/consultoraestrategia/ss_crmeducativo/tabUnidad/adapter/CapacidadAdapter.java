package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder.CapacidadHolder;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CapacidadUi;

import java.util.List;

public class CapacidadAdapter extends RecyclerView.Adapter {

    private List<CapacidadUi> capacidadUiList;
    private int position;

    public CapacidadAdapter(List<CapacidadUi> capacidadUiList, int position) {
        this.capacidadUiList = capacidadUiList;
        this.position = position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_unidad_competencia, viewGroup, false);
        return new CapacidadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CapacidadUi capacidadUi = capacidadUiList.get(i);
        CapacidadHolder capacidadHolder = (CapacidadHolder) viewHolder;
        capacidadHolder.bind(capacidadUi, i+1, position);
    }

    @Override
    public int getItemCount() {
        return capacidadUiList.size();
    }
}
