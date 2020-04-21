package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.adapters.holder.CompetenciaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.listener.CompetenciaListener;

import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class CompetenciaAdapter extends RecyclerView.Adapter<CompetenciaHolder> {

    private List<CompetenciaUi> items;
    private RecyclerView recyclerView;
    private CompetenciaListener listener;
    private String TAG = CompetenciaAdapter.class.getSimpleName();

    public CompetenciaAdapter(List<CompetenciaUi> items, CompetenciaListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public CompetenciaHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v2 = inflater.inflate(R.layout.layout_item_rubro_capacidad, viewGroup, false);
        return new CompetenciaHolder(v2);
    }

    @Override
    public void onBindViewHolder(CompetenciaHolder viewHolder, int position) {
        CompetenciaUi CompetenciaUi = items.get(position);
        viewHolder.bind(CompetenciaUi, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    
    public void addItem(CompetenciaUi competenciaUi) {
        this.items.add(competenciaUi);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public void updateItem(CompetenciaUi competenciaUi) {
        int position = this.items.indexOf(competenciaUi);
        if (position != -1) {
            this.items.set(position, competenciaUi);
            notifyItemChanged(position);
            //notifyDataSetChanged();
        }
    }

    public void deleteItem(CompetenciaUi competenciaUi) {
        int position = this.items.indexOf(competenciaUi);
        if (position != -1) {
            this.items.remove(competenciaUi);
            notifyItemRemoved(position);
        }

    }

    public void setItems(List<CompetenciaUi> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

}
