package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.adapter.holder.IndicadoresCamposAccionHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;

import java.util.List;

/**
 * Created by kike on 10/05/2018.
 */

public class IndicadoresCamposAccionAdapter extends RecyclerView.Adapter<IndicadoresCamposAccionHolder>{
    private List<IndicadoresCamposAccionUi> camposAccionList;

    public IndicadoresCamposAccionAdapter(List<IndicadoresCamposAccionUi> camposAccionList) {
        this.camposAccionList = camposAccionList;
    }

    @Override
    public IndicadoresCamposAccionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_indicadorescamposaccion, parent, false);
        return new IndicadoresCamposAccionHolder(view);
    }

    @Override
    public void onBindViewHolder(IndicadoresCamposAccionHolder holder, int position) {
        IndicadoresCamposAccionUi indicadoresCamposAccionUi = camposAccionList.get(position);
        holder.bind(indicadoresCamposAccionUi);
    }

    @Override
    public int getItemCount() {
        if (camposAccionList==null)return 0;
        return camposAccionList.size();
    }

    public void refrescarLista(List<IndicadoresCamposAccionUi> indicadoresCamposAccionUis) {
        this.camposAccionList.clear();
        this.camposAccionList.addAll(indicadoresCamposAccionUis);
        notifyDataSetChanged();
    }
}
