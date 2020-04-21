package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder.EvaluacionCapacidadHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCapacidadAdapter extends RecyclerView.Adapter<EvaluacionCapacidadHolder> {
    private List<CapacidadUi> capacidadUiList;
    private EvaluacionCompetenciaListener listener;
    private CompetenciaUi competenciaUi;
    private Context context;

    public EvaluacionCapacidadAdapter(List<CapacidadUi> capacidadUiList, EvaluacionCompetenciaListener listener, CompetenciaUi competenciaUi, Context context) {
        this.capacidadUiList = capacidadUiList;
        this.listener = listener;
        this.competenciaUi = competenciaUi;
        this.context = context;
    }

    @Override
    public EvaluacionCapacidadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evaluacion_capacidad_v2, parent, false);
        return new EvaluacionCapacidadHolder(view);
    }

    @Override
    public void onBindViewHolder(EvaluacionCapacidadHolder holder, int position) {
        CapacidadUi capacidadUi = capacidadUiList.get(position);
        holder.bind(capacidadUi, listener,competenciaUi, position+1, context);
    }

    @Override
    public int getItemCount() {
        if (capacidadUiList == null) return 0;
        return capacidadUiList.size();
    }

    public void setListCapacidades(List<CapacidadUi> capacidadUiList){
        Log.d(EvaluacionCapacidadAdapter.class.getSimpleName(), "pasa setListCapacidades");
        this.capacidadUiList.clear();
        this.capacidadUiList.addAll(capacidadUiList);
        notifyDataSetChanged();
    }
}
