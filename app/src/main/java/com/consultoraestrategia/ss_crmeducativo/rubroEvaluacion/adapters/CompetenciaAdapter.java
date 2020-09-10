package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder.CapacidadHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder.CompetenciaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder.RubroProcesoHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class CompetenciaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{

    private List<Object> items;
    public final static int LIST_HORIZONTAL = 1, LIST_VERTICAL=2;
    private final int CAPACIDAD = 0, COMPETENCIA = 1, RUBROPROCESOS = 2;
    private int tipoLista = LIST_HORIZONTAL;
    private RecyclerView recyclerView;
    private RubroEvaluacionProcesoListener listener;
    private String TAG = CompetenciaAdapter.class.getSimpleName();
    private List<Object> mFilteredList;

    public CompetenciaAdapter(List<Object> items, RubroEvaluacionProcesoListener listener, int tipoLista) {
        this.items = items;
        this.mFilteredList = items;
        this.listener = listener;
        this.tipoLista = tipoLista;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case COMPETENCIA:
                View v1 = inflater.inflate(R.layout.layout_item_rubro_evaluacion_competencia, viewGroup, false);
                viewHolder = new CompetenciaHolder(v1);
                break;
            case CAPACIDAD:
                View v2 = inflater.inflate(R.layout.layout_item_rubro_evaluacion_capacidades, viewGroup, false);
                viewHolder = new CapacidadHolder(v2);
                break;
            case RUBROPROCESOS:
                View v3 = inflater.inflate(R.layout.layout_item_rubro_proceso, viewGroup, false);
                viewHolder = new RubroProcesoHolder(v3);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case COMPETENCIA:
                CompetenciaUi competenciaHolder = (CompetenciaUi) mFilteredList.get(position);
                CompetenciaHolder vh1 = (CompetenciaHolder) viewHolder;
                vh1.bind(competenciaHolder);
                break;
            case CAPACIDAD:
                CapacidadUi capacidadUi = (CapacidadUi) mFilteredList.get(position);
                CapacidadHolder vh2 = (CapacidadHolder) viewHolder;
                vh2.bind(capacidadUi, listener, mFilteredList, tipoLista);
                break;
            case RUBROPROCESOS:
                RubroProcesoUi rubroProcesoUi = (RubroProcesoUi) mFilteredList.get(position);
                RubroProcesoHolder vh3 = (RubroProcesoHolder) viewHolder;
                vh3.bind(rubroProcesoUi,listener,null);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mFilteredList.get(position) instanceof CompetenciaUi) {
            return COMPETENCIA;
        } else if (mFilteredList.get(position) instanceof CapacidadUi) {
            return CAPACIDAD;
        } else if (mFilteredList.get(position) instanceof RubroProcesoUi) {
            return RUBROPROCESOS;
        }
        return -1;
    }

    public void addItem(Object o) {
        this.mFilteredList.add(o);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
        if(items.equals(mFilteredList))return;
        this.items.add(o);

    }

    public void updateItem(Object o) {
        int position = this.mFilteredList.indexOf(o);
        if (position != -1) {
            this.mFilteredList.set(position, o);
            notifyItemChanged(position);
            //notifyDataSetChanged();
        }
        if(items.equals(mFilteredList))return;
        int positionItem = this.items.indexOf(o);
        if (positionItem != -1) this.items.set(positionItem, o);
    }

    public void changeAncladoCompetencia(Object o){
        CapacidadHolder capacidadHolder = getCapasidadHolder(o);
        if(capacidadHolder != null){
           capacidadHolder.initAncla();
        }
    }



    public void deleteItem(Object o) {
        int position = this.mFilteredList.indexOf(o);
        if (position != -1) {
            this.mFilteredList.remove(o);
            notifyItemRemoved(position);
        }
        if(items.equals(mFilteredList))return;
        int positionItem = this.items.indexOf(o);
        if (positionItem != -1)this.items.remove(o);
    }

    public void setItems(List<Object> items) {
        this.mFilteredList = this.items;
        this.mFilteredList.clear();
        this.mFilteredList.addAll(items);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

//    public void setRubroProceso(Object o, List<RubroProcesoUi> rubroProcesoUiList) {
//        CapacidadHolder capacidadHolder = getCapasidadHolder(o);
//        if(capacidadHolder != null){
//            RubroProcesoAdapter rubroProcesoAdapter = capacidadHolder.getRubroProcesoAdapter();
//            rubroProcesoAdapter.setItems(rubroProcesoUiList);
//            capacidadHolder.setCantiadadRubro(rubroProcesoUiList.size());
//        }else {
//            if(items.equals(mFilteredList))return;
//            int posicionItem = this.items.indexOf(o);
//            if (posicionItem != -1) {
//                if(items.get(posicionItem) instanceof CapacidadUi){
//                    CapacidadUi capacidadUi = (CapacidadUi)o;
//                    List<RubroProcesoUi> rubroProcesoUiListRubro = capacidadUi.getRubroProcesoUis();
//                    rubroProcesoUiListRubro.clear();
//                    rubroProcesoUiListRubro.addAll(rubroProcesoUiList);
//                    capacidadUi.setCantidad(rubroProcesoUiList.size());
//                }
//            }
//        }
//
//    }

    public void updateRubroProceso(Object o, RubroProcesoUi rubroProcesoUi) {
        CapacidadHolder capacidadHolder = getCapasidadHolder(o);
        if(capacidadHolder != null){
            RubroProcesoAdapter rubroProcesoAdapter = capacidadHolder.getRubroProcesoAdapter();
            rubroProcesoAdapter.updateItem(rubroProcesoUi);
        }else{
            if(items.equals(mFilteredList))return;
            int posicionItem = this.items.indexOf(o);
            if (posicionItem != -1) {
                if(items.get(posicionItem) instanceof CapacidadUi){
                    CapacidadUi capacidadUi = (CapacidadUi)o;
                    List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                    if(rubroProcesoUiList == null||rubroProcesoUiList.isEmpty())return;
                    int posicionRubro = rubroProcesoUiList.indexOf(rubroProcesoUi);
                    if(posicionRubro!=-1)rubroProcesoUiList.set(posicionRubro,rubroProcesoUi);
                }
            }
        }

    }

    public void addRubroProceso(Object o, RubroProcesoUi rubroProcesoUi) {

        CapacidadHolder capacidadHolder = getCapasidadHolder(o);
        if(capacidadHolder != null){
            RubroProcesoAdapter rubroProcesoAdapter = capacidadHolder.getRubroProcesoAdapter();
            // recyclerView.scrollToPosition(getItemCount() - 1);
            rubroProcesoAdapter.addItem(rubroProcesoUi);
            CapacidadUi capacidadUi = capacidadHolder.getCapacidadUi();
            capacidadHolder.setCantiadadRubro(capacidadUi.getCantidad()+1);
        }else {
            if(items.equals(mFilteredList))return;
            int posicionItem = this.items.indexOf(o);
            if (posicionItem != -1) {
                if(items.get(posicionItem) instanceof CapacidadUi){
                    CapacidadUi capacidadUi = (CapacidadUi)o;
                    List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                    if(rubroProcesoUiList == null||rubroProcesoUiList.isEmpty())return;
                    int position = 0;
                    rubroProcesoUiList.add(position, rubroProcesoUi);
                    capacidadUi.setCantidad(rubroProcesoUiList.size());
                }
            }
        }


    }

    public void deleteRubroProceso(Object o, RubroProcesoUi rubroProcesoUi) {

        CapacidadHolder capacidadHolder = getCapasidadHolder(o);
        if(capacidadHolder != null){
            RubroProcesoAdapter rubroProcesoAdapter = capacidadHolder.getRubroProcesoAdapter();
            rubroProcesoAdapter.deleteItem(rubroProcesoUi);
            CapacidadUi capacidadUi = capacidadHolder.getCapacidadUi();
            capacidadHolder.setCantiadadRubro(capacidadUi.getCantidad());
            capacidadHolder.setCapacidadUi(rubroProcesoUi);
        }else{
            if(items.equals(mFilteredList))return;
            int posicionItem = this.items.indexOf(o);
            if (posicionItem != -1) {
                if(items.get(posicionItem) instanceof CapacidadUi){
                    CapacidadUi capacidadUi = (CapacidadUi)o;
                    List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                    if(rubroProcesoUiList == null||rubroProcesoUiList.isEmpty())return;
                    int posicionRubro = rubroProcesoUiList.indexOf(rubroProcesoUi);
                    if(posicionRubro!=-1)rubroProcesoUiList.remove(rubroProcesoUi);
                    capacidadUi.setCantidad(rubroProcesoUiList.size());
                }
            }
        }



    }

    private CapacidadHolder getCapasidadHolder(Object o){
        CapacidadHolder capacidadHolder = null;
        int posicion = this.mFilteredList.indexOf(o);
        Log.d(TAG,"CapacidadUi posicion: "+posicion);
        if (posicion != -1) {
            if(mFilteredList.get(posicion) instanceof CapacidadUi){
                capacidadHolder = (CapacidadHolder)recyclerView.findViewHolderForLayoutPosition(posicion);
            }
        }
        return capacidadHolder;
    }

    public void refreshList(Object o){
        CapacidadHolder capacidadHolder = getCapasidadHolder(o);
        if(capacidadHolder == null)return;
        RubroProcesoAdapter rubroProcesoAdapter = capacidadHolder.getRubroProcesoAdapter();
        rubroProcesoAdapter.notifyDataSetChanged();

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            private final int COMPETBASE = 1, COMPET_ENFOQUE_TRANS = 2;
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = items;
                } else {
                    try {
                        mFilteredList = filtrarItems(charString);
                    } catch (Exception e) {
                        mFilteredList = items;
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<Object>) filterResults.values;
                notifyDataSetChanged();
            }


            private List<Object> filtrarItems(String parametros) throws Exception{
                List<Object> filteredList = new ArrayList<>();
                CompetenciaUi.Tipo tipo = CompetenciaUi.Tipo.valueOf(parametros);

                for (Object object : items) {
                    if(object instanceof CompetenciaUi){
                        CompetenciaUi competenciaUi = (CompetenciaUi) object;
                        if(convertTipoFiltro(competenciaUi.getTipo()) == convertTipoFiltro(tipo)){
                            filteredList.add(object);
                        }

                    }else if(object instanceof CapacidadUi){
                        CapacidadUi capacidadUi = (CapacidadUi) object;
                        if(convertTipoFiltro(capacidadUi.getCompetenciaUi().getTipo())==convertTipoFiltro(tipo)){
                            filteredList.add(object);
                        }
                    }

                }
                return filteredList;
            }

            private int convertTipoFiltro(CompetenciaUi.Tipo tipo){
                int filtro;
                switch (tipo){
                    case COMPETENCIA_BASE:
                        filtro = COMPETBASE;
                        break;
                    case COMPETENCIA_ENFQ:
                        filtro = COMPET_ENFOQUE_TRANS;
                        break;
                    case COMPETENCIA_TRANS:
                        filtro = COMPET_ENFOQUE_TRANS;
                        break;
                    default:
                        filtro = COMPETBASE;
                        break;

                }
                return filtro;
            }
        };
    }

    public void savePositionList() {
        Log.d(TAG,"recyclerView cantidad child: "+recyclerView.getChildCount());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
            if(viewHolder instanceof CapacidadHolder){
                CapacidadHolder holder = (CapacidadHolder) recyclerView.findViewHolderForAdapterPosition(i);
                if(holder!=null)holder.savePositionList();
            }

        }
    }

    public void clearPositionList() {
        Log.d(TAG,"recyclerView cantidad child: "+recyclerView.getChildCount());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
            if(viewHolder instanceof CapacidadHolder){
                CapacidadHolder holder = (CapacidadHolder) recyclerView.findViewHolderForAdapterPosition(i);
                if(holder!=null)holder.clearPositionList();
            }

        }
    }

    public List<Object> getItems() {
        return items;
    }
}
