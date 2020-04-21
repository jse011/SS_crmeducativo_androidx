package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder.ViewHolderActividades;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder.ViewHolderRecursos;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder.ViewHolderRecursosTitulo;

import java.util.List;


public class AdapterActividades extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;

    private final int  RECURSO = 0,  SESION = 1;
    private int vint_backgroudColor;
    private int vint_personaId;
    public AdapterActividades(List<Object> items, int backgroudColor,int personaId) {
        this.items = items;
        this.vint_backgroudColor = backgroudColor;
        this.vint_personaId = personaId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SESION:
                View v1 = inflater.inflate(R.layout.layout_item_actividades_v3, viewGroup, false);
                viewHolder = new ViewHolderActividades(v1);
                break;
            case RECURSO:
                View v2 = inflater.inflate(R.layout.layout_item_recursos, viewGroup, false);
                viewHolder = new ViewHolderRecursos(v2);
                break;
            default:
                View v3 = inflater.inflate(R.layout.layout_item_recursos_titulo, viewGroup, false);
                viewHolder = new ViewHolderRecursosTitulo(v3);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case SESION:
                ActividadAprendizaje aprendizaje = (ActividadAprendizaje) items.get(position);
                ViewHolderActividades vh1 = (ViewHolderActividades) viewHolder;
                vh1.bind(aprendizaje,vint_backgroudColor,position,vint_personaId);
                break;
            case RECURSO:
                final RecursoDidacticoEventoC recursoDidacticoEvento = (RecursoDidacticoEventoC) items.get(position);
                ViewHolderRecursos vh2 = (ViewHolderRecursos) viewHolder;
                vh2.bind(recursoDidacticoEvento);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof ActividadAprendizaje) {
            return SESION;
        }else if (items.get(position) instanceof RecursoDidacticoEventoC) {
            return RECURSO;
        }
        return -1;
    }

}