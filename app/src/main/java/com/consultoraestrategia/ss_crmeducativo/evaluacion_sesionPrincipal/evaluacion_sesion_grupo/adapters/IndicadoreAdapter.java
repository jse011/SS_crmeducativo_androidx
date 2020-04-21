package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.viewHolder.ViewHolderIndicador;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.listener.IndicadorListener;

import java.util.List;


public class IndicadoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IndicadorUi> indicadorUis;
    private RecyclerView recyclerView;
    private IndicadorListener indicadorListener;

    public IndicadoreAdapter(List<IndicadorUi> indicadorUis, IndicadorListener indicadorListener) {
        this.indicadorUis = indicadorUis;
        this.indicadorListener = indicadorListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_indicadores, viewGroup, false);
        return new ViewHolderIndicador(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        IndicadorUi ui = indicadorUis.get(position);
        ViewHolderIndicador vh = (ViewHolderIndicador) viewHolder;
        vh.bind(ui, indicadorListener);
    }

    @Override
    public int getItemCount() {
        return indicadorUis.size();
    }

    public void addIndicador(IndicadorUi indicadorUi) {
        this.indicadorUis.add(indicadorUi);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public void updateIndicador(IndicadorUi indicadorUi) {
        int posicion = this.indicadorUis.indexOf(indicadorUi);
        if (posicion != -1) {
            this.indicadorUis.set(posicion, indicadorUi);
            notifyItemChanged(posicion);
        }
    }

    public void deleteIndicador(IndicadorUi indicadorUi) {
        int posicion = this.indicadorUis.indexOf(indicadorUi);
        if (posicion != -1) {
            this.indicadorUis.remove(indicadorUi);
            notifyItemRemoved(posicion);
        }
    }

    public void setIndicador(List<IndicadorUi> indicadorUis) {
        this.indicadorUis.clear();
        this.indicadorUis.addAll(indicadorUis);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


}