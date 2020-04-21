package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder.CompetenciaHolder;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;

import java.util.List;

public class CompetenciaAdapter extends RecyclerView.Adapter {

    private List<CompetenciaUi> competenciaUiList;

    public CompetenciaAdapter(List<CompetenciaUi> competenciaUiList) {
        this.competenciaUiList = competenciaUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_competencia_aprendizaje, viewGroup, false);
        return new CompetenciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CompetenciaUi competenciaUi = competenciaUiList.get(i);
        CompetenciaHolder competenciaHolder = (CompetenciaHolder) viewHolder;
        competenciaHolder.bind(competenciaUi, i+1);
    }

    @Override
    public int getItemCount() {
        return competenciaUiList.size();
    }

    public void setCompetenciaUiList(List<CompetenciaUi> competenciaUiList) {
        this.competenciaUiList.clear();
        this.competenciaUiList.addAll(competenciaUiList);
        notifyDataSetChanged();
    }
}
