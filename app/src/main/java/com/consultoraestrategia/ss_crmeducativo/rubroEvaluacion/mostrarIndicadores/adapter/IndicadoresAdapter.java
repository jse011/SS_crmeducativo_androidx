package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.adapter.holder.IndicadoresHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.entidades.IndicadoresUi;

import java.util.List;

/**
 * Created by kike on 10/05/2018.
 */

public class IndicadoresAdapter extends RecyclerView.Adapter<IndicadoresHolder> {

    private List<IndicadoresUi> indicadoresUiList;

    public IndicadoresAdapter(List<IndicadoresUi> indicadoresUiList) {
        this.indicadoresUiList = indicadoresUiList;
    }

    @Override
    public IndicadoresHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_indicadores, parent, false);
        return new IndicadoresHolder(view);
    }

    @Override
    public void onBindViewHolder(IndicadoresHolder holder, int position) {
        IndicadoresUi indicadoresUi = indicadoresUiList.get(position);
        holder.bind(indicadoresUi);
    }

    @Override
    public int getItemCount() {
        if (indicadoresUiList == null) return 0;
        return indicadoresUiList.size();
    }

    public void refrescarLista(List<IndicadoresUi> indicadoresUiList) {
        this.indicadoresUiList.clear();
        this.indicadoresUiList.addAll(indicadoresUiList);
        notifyDataSetChanged();

    }
}
