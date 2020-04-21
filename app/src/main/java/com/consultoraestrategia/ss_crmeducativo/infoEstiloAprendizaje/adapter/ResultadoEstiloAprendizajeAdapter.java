package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.holder.ResultadoDetEstiloAprendizajeHolder;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.holder.ResultadoEstiloAprendizajeHolder;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.CaracteristicaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionObservadaUi;

import java.util.List;

public class ResultadoEstiloAprendizajeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Cielo
    //Scopus
    //IEEE
    //
    private final static int  RESULTADO = 2, RESULTADO_DETALLE = 3;
    private List<Object> items;

    public ResultadoEstiloAprendizajeAdapter(List<Object> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case RESULTADO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_resulto_aprendizaje, parent, false);
                viewHolder = new ResultadoEstiloAprendizajeHolder(view);
                break;
            //case RESULTADO_DETALLE:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_det_resulto_aprendizaje, parent, false);
                viewHolder = new ResultadoDetEstiloAprendizajeHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case RESULTADO:
                ResultadoEstiloAprendizajeHolder resultadoEstiloAprendizajeHolder = (ResultadoEstiloAprendizajeHolder)holder;
                DimensionObservadaUi dimensionObservadaUi = (DimensionObservadaUi)items.get(position);
                resultadoEstiloAprendizajeHolder.bind(dimensionObservadaUi);
                break;
            case RESULTADO_DETALLE:
                ResultadoDetEstiloAprendizajeHolder resultadoDetEstiloAprendizajeHolder = (ResultadoDetEstiloAprendizajeHolder)holder;
                CaracteristicaUi caracteristicaUi = (CaracteristicaUi)items.get(position);
                resultadoDetEstiloAprendizajeHolder.bind(caracteristicaUi);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setList(List<Object> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = items.get(position);
        if(o instanceof DimensionObservadaUi)return RESULTADO;
        else if(o instanceof CaracteristicaUi) return RESULTADO_DETALLE;
        else return RESULTADO;
    }
}
