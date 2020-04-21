package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubrosCompetenciasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionUnidadListener;

import java.util.List;

public class CompetenciaRubrosAdapter extends RecyclerView.Adapter {


    static final int COMPETENCIA=1,CAPACIDAD=2;
    List<Object>objects;

    EvaluacionUnidadListener unidadListener;
    public CompetenciaRubrosAdapter(List<Object> objects, EvaluacionUnidadListener unidadListener) {
        this.objects = objects;
        this.unidadListener=unidadListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case CAPACIDAD:
                return new CapacidadRubroHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_capacidad_rubro, viewGroup, false));
            default:
                return new CompetenciaRubrosHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_competencia_rubro, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object object= objects.get(i);
        if (viewHolder instanceof CapacidadRubroHolder){
            CapacidadRubroHolder capacidadRubroHolder= (CapacidadRubroHolder)viewHolder;
            capacidadRubroHolder.bind((CapacidadUi)object, unidadListener);
        }
        else {
            CompetenciaRubrosHolder competenciaRubrosHolder= (CompetenciaRubrosHolder)viewHolder;
            competenciaRubrosHolder.bind((CompetenciaUi)object);
        }

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
    public void setList(List<Object>objectList){
        this.objects.clear();
        this.objects.addAll(objectList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object object= objects.get(position);
        if(object instanceof CompetenciaUi)
            return COMPETENCIA;
        else return CAPACIDAD;
    }
}

