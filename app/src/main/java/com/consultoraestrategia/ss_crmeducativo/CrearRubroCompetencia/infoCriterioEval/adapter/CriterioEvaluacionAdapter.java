package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.adapter.holder.CriterioEvaluacionViewHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.listener.InfoCriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

public class CriterioEvaluacionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


    private static String TAG = CriterioEvaluacionAdapter.class.getSimpleName();
    private List<ValorTipoNotaUi> valorTipoNotaUis;
    private RecyclerView recyclerView;
    private InfoCriterioEvalListener listener;

    public CriterioEvaluacionAdapter(List<ValorTipoNotaUi> valorTipoNotaUis, RecyclerView recyclerView, InfoCriterioEvalListener listener) {
        this.valorTipoNotaUis = valorTipoNotaUis;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_criterio_evaluacion_2, viewGroup, false);
        return new CriterioEvaluacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewGroup, int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUis.get(position);
        CriterioEvaluacionViewHolder criterioEvaluacionViewHolder = (CriterioEvaluacionViewHolder) viewGroup;
        criterioEvaluacionViewHolder.bind(listener, valorTipoNotaUi, valorTipoNotaUis, position);
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
