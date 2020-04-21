package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters;


import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters.viewHolder.ViewHolderMenuLateral;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.listener.RubroEvaluacionListener;

import java.util.List;

/**
 * Created by kelvi on 23/02/2017.
 */

public class MenuLateralAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<RubroEvaluacionUi> rubros;
    private RecyclerView recyclerView;
    private RubroEvaluacionListener listener;

    public MenuLateralAdapter(RubroEvaluacionListener listener, List<RubroEvaluacionUi> rubro) {
        this.rubros = rubro;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu_rubro_evaluacion_proceso, viewGroup, false);
        return  new ViewHolderMenuLateral(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewGroup, int position) {
        RubroEvaluacionUi rubroEvaluacionProceso = rubros.get(position);
        ViewHolderMenuLateral personaViewHolder = (ViewHolderMenuLateral)viewGroup;
        personaViewHolder.bind(listener, rubroEvaluacionProceso);
    }

    @Override
    public int getItemCount() {
        return rubros.size();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void addRubro(RubroEvaluacionUi rubro){
        rubros.add(rubro);
        notifyItemInserted(getItemCount()-1);
        recyclerView.scrollToPosition(getItemCount()-1);
    }

    public void updateRubro(RubroEvaluacionUi rubro){
        int posicion = rubros.indexOf(rubro);
        if(posicion != -1){
            rubros.set(posicion,rubro);
            notifyItemChanged(posicion);
        }
    }

    public void deleteRubro(RubroEvaluacionUi rubro){
        int posicion = rubros.indexOf(rubro);
        if(posicion != -1){
            rubros.remove(posicion);
            notifyItemRemoved(posicion);
        }
    }

    public void setRubro(List<RubroEvaluacionUi> rubros){
        this.rubros.clear();
        this.rubros.addAll(rubros);
        notifyDataSetChanged();
    }
}
