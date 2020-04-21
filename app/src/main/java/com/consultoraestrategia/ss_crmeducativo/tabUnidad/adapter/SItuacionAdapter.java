package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder.SituacionHolder;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

import java.util.List;

public class SItuacionAdapter extends RecyclerView.Adapter {

    private List<SituacionUi> situacionUiList;

    public SItuacionAdapter(List<SituacionUi> situacionUiList) {
        this.situacionUiList = situacionUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_situacion_unidad, viewGroup, false);
        return new SituacionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SituacionUi situacionUi = situacionUiList.get(i);
        SituacionHolder holder = (SituacionHolder) viewHolder;
        holder.bind(situacionUi);
    }

    @Override
    public int getItemCount() {
        return situacionUiList.size();
    }

    public void setSituacionUiList(List<SituacionUi> situacionUiList){
        this.situacionUiList.clear();
        this.situacionUiList.addAll(situacionUiList);
        notifyDataSetChanged();
    }
}
