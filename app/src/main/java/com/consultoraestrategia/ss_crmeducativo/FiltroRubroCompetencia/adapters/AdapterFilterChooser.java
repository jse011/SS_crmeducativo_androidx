package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.listener.FilterChooserCheckItemListener;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

public class AdapterFilterChooser extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    FilterChooserCheckItemListener listener;
    List<CompetenciaCheck> competenciaChecks;

    public AdapterFilterChooser(FilterChooserCheckItemListener listener, List<CompetenciaCheck> competenciaChecks) {
        this.listener = listener;
        this.competenciaChecks = competenciaChecks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialogfilter_item, parent, false);
        return new FilterHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      CompetenciaCheck c= competenciaChecks.get(position);
        FilterHolder filterHolder = (FilterHolder) holder;
        filterHolder.bind(c, listener);
    }

    @Override
    public int getItemCount() {
        return competenciaChecks.size() ;
    }
    public void setList(List<CompetenciaCheck> competenciaCheckList){
        competenciaChecks.clear();
        competenciaChecks.addAll(competenciaCheckList);
        notifyDataSetChanged();

    }
}
