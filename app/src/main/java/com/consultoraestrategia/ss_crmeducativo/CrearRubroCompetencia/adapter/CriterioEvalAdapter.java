package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.CriterioEvalViewHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

/**
 * Created by SCIEV on 18/10/2017.
 */

public class CriterioEvalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = "CriterioEvaluacionAdapter";
    private List<ValorTipoNotaUi> valorTipoNotaUis;
    private RecyclerView recyclerView;
    private CriterioEvalListener listener;

    public CriterioEvalAdapter(List<ValorTipoNotaUi> valorTipoNotaUis, RecyclerView recyclerView, CriterioEvalListener listener) {
        this.valorTipoNotaUis = valorTipoNotaUis;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_criterio_evaluacion, viewGroup, false);
        return new CriterioEvalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewGroup, int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUis.get(position);
        CriterioEvalViewHolder criterioEvalViewHolder = (CriterioEvalViewHolder) viewGroup;
        criterioEvalViewHolder.bind(listener, valorTipoNotaUi, valorTipoNotaUis);
    }

    @Override
    public int getItemCount() {
        return valorTipoNotaUis.size();
    }

    public void addCriterio(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUis.add(valorTipoNotaUi);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public int updateCriterio(ValorTipoNotaUi valorTipoNotaUi) {
        int position = this.valorTipoNotaUis.indexOf(valorTipoNotaUi);
        Log.d(TAG, "updateCriterio: " + position);
        if (position != -1) {
            this.valorTipoNotaUis.set(position, valorTipoNotaUi);
            notifyItemChanged(position);
        }
        return position;
    }

    public void deleteCriterio(ValorTipoNotaUi valorTipoNotaUi) {
        int position = this.valorTipoNotaUis.indexOf(valorTipoNotaUi);
        if (position != -1) {
            this.valorTipoNotaUis.remove(valorTipoNotaUi);
            notifyItemRemoved(position);
        }

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
