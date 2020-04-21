package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.evaluacionesAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class EvaluacionAdapter extends RecyclerView.Adapter {

    List<IndicadorUi> indicadorUis;

    public EvaluacionAdapter(List<IndicadorUi> indicadorUis) {
        this.indicadorUis = indicadorUis;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_unidad_evaluacion_indicador, viewGroup, false);
        return new EvaluacionHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        IndicadorUi indicadorUi=indicadorUis.get(i);
        EvaluacionHolder evaluacionHolder= (EvaluacionHolder)viewHolder;
        evaluacionHolder.bind(indicadorUi);
    }

    @Override
    public int getItemCount() {
        return indicadorUis.size();
    }

    public void setList(  List<IndicadorUi> indicadorUis){
        this.indicadorUis.clear();
        this.indicadorUis.addAll(indicadorUis);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
}

