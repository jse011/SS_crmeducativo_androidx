package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter.holder.ValorTipoNotaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import java.util.List;

public class ValorTipoNotaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private static String TAG = ValorTipoNotaAdapter.class.getSimpleName();
    private List<ValorTipoNotaUi> valorTipoNotaUis;
    private RecyclerView recyclerView;

    public ValorTipoNotaAdapter(List<ValorTipoNotaUi> valorTipoNotaUis, RecyclerView recyclerView ) {
        this.valorTipoNotaUis = valorTipoNotaUis;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_criterio_evaluacion_info, parent, false);
        return new ValorTipoNotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUis.get(position);
        ValorTipoNotaViewHolder valorTipoNotaViewHolder = (ValorTipoNotaViewHolder) holder;
        valorTipoNotaViewHolder.bind(valorTipoNotaUi);
    }

    @Override
    public int getItemCount() {
        return valorTipoNotaUis.size();
    }

    public void setcriterioEvalUis(List<ValorTipoNotaUi> valorTipoNotaUis) {
        this.valorTipoNotaUis.clear();
        this.valorTipoNotaUis.addAll(valorTipoNotaUis);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaUis() {
        return valorTipoNotaUis;
    }
}
