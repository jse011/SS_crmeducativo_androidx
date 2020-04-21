package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.holder.InstrumetnoAprendizajeHolder;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.InstrumentoObservadoUi;

import java.util.List;

public class InstrumentoAprendizajeAdapter extends RecyclerView.Adapter<InstrumetnoAprendizajeHolder> {
    private List<InstrumentoObservadoUi> instrumentoObservadoUiList;

    public InstrumentoAprendizajeAdapter(List<InstrumentoObservadoUi> instrumentoObservadoUiList) {
        this.instrumentoObservadoUiList = instrumentoObservadoUiList;
    }

    @NonNull
    @Override
    public InstrumetnoAprendizajeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_estilo_aprendizaje, parent, false);
        return new InstrumetnoAprendizajeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstrumetnoAprendizajeHolder holder, int position) {
        InstrumentoObservadoUi instrumentoObservadoUi = instrumentoObservadoUiList.get(position);
        holder.bind(instrumentoObservadoUi);
    }

    @Override
    public int getItemCount() {
        return instrumentoObservadoUiList.size();
    }

    public void setList(List<InstrumentoObservadoUi> dimensionObservadaUiList) {
        instrumentoObservadoUiList.clear();
        instrumentoObservadoUiList.addAll(dimensionObservadaUiList);
        notifyDataSetChanged();
    }
}
