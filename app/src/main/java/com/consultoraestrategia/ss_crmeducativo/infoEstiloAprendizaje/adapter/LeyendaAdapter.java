package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.LeyendaUi;

import java.util.List;

public class LeyendaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LeyendaUi>items;

    public LeyendaAdapter(List<LeyendaUi> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leyenda, parent, false);
        RecyclerView.ViewHolder viewHolder = new LeyendaHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeyendaHolder leyendaHolder = (LeyendaHolder) holder;
        LeyendaUi leyendaUi = (LeyendaUi) items.get(position);
        leyendaHolder.bind(leyendaUi);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setList(List<LeyendaUi> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
