package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder.HijosFinalBimestreHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.HijosFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.PadreFinalBimestreUi;

import java.util.List;

/**
 * Created by kike on 12/04/2018.
 */

public class HijosFinalBimestreAdapter extends RecyclerView.Adapter<HijosFinalBimestreHolder> {
    List<HijosFinalBimestreUi> hijosFinalBimestresList;
    PadreFinalBimestreUi padreFinalBimestre;
    EvaluacionCompetenciaListener listener;
    Context context;

    public HijosFinalBimestreAdapter(List<HijosFinalBimestreUi> hijosFinalBimestresList, PadreFinalBimestreUi padreFinalBimestre, EvaluacionCompetenciaListener listener, Context context) {
        this.hijosFinalBimestresList = hijosFinalBimestresList;
        this.padreFinalBimestre = padreFinalBimestre;
        this.listener= listener;
        this.context = context;
    }

    @Override
    public HijosFinalBimestreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hijos_finales_bimestre_v2, parent, false);
        return new HijosFinalBimestreHolder(view);
    }

    @Override
    public void onBindViewHolder(HijosFinalBimestreHolder holder, int position) {
        HijosFinalBimestreUi hijosFinalBimestre = hijosFinalBimestresList.get(position);
        holder.bind(hijosFinalBimestre, listener, padreFinalBimestre, context);
    }

    @Override
    public int getItemCount() {
        if (hijosFinalBimestresList == null) return 0;
        return hijosFinalBimestresList.size();
    }
}
