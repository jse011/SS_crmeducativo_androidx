package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.BuscarCampoHijoHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.BuscarCampoHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.BuscarCampoPadreHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.CampoIndicadorHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class CamposAccionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {


    private List<Object> items;
    private List<Object> mFilteredList;
    private CamposAccionChooserCallback listener;
    private static final int CAMPO_PADRE = 1, INDICADOR = 2, CAMPO = 3, CAMPO_HIJO = 4;

    public CamposAccionAdapter(List<Object> items, CamposAccionChooserCallback listener) {
        this.items = items;
        this.listener = listener;
        mFilteredList = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        switch (viewType) {
            case INDICADOR:
                return new CampoIndicadorHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crearrubri_campo_indicador, parent, false));
            case CAMPO_PADRE:
                return new BuscarCampoPadreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crearrubi_fragment_campoaccion_padre, parent, false));
            case CAMPO_HIJO:
                return new BuscarCampoHijoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crearrubi_fragment_campoaccion_hijo, parent, false));
            default:
                return new BuscarCampoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crearrubi_fragment_campoaccion, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object object = mFilteredList.get(position);
        switch (holder.getItemViewType()){
            case INDICADOR:
                ((CampoIndicadorHolder) holder).bind((IndicadorCampoAccionUi) object, listener);
                break;
            case CAMPO_PADRE:
                ((BuscarCampoPadreHolder) holder).bind((CampoAccionUi) object);
                break;
            case CAMPO_HIJO:
                ((BuscarCampoHijoHolder) holder).bind((CampoAccionUi) object, listener);
                break;
            case CAMPO:
                ((BuscarCampoHolder) holder).bind((CampoAccionUi) object, listener);
                break;
        }

    }

    public void setItems(List<CampoAccionUi> objects) {
        this.items.clear();
        this.items.addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = items;
                } else {

                    List<Object> filteredList = new ArrayList<>();

                    for (Object object : items) {
                        if (object instanceof CampoAccionUi) {
                            CampoAccionUi campoAccionUi = (CampoAccionUi) object;
                            if (campoAccionUi.getTitulo().toLowerCase().contains(charString)) {
                                filteredList.add(campoAccionUi);
                            }
                        }
                    }

                    mFilteredList = filteredList;
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
        };
    }

    @Override
    public int getItemViewType(int position) {
        Object object = items.get(position);
        if (object instanceof CampoAccionUi) {
            CampoAccionUi campoAccionUi = (CampoAccionUi)object;
            if(campoAccionUi.getTipo() == CampoAccionUi.Tipo.PARENT ){
                return CAMPO_PADRE;
            }else if(campoAccionUi.getTipo() == CampoAccionUi.Tipo.DEFAULD){
                return CAMPO;
            }else if(campoAccionUi.getTipo() == CampoAccionUi.Tipo.CHILDREN){
                return CAMPO_HIJO;
            }
        } else if(object instanceof IndicadorCampoAccionUi) {
            return INDICADOR;
        }

        return 0;
    }


    public void notifyAllChildChanged(final RecyclerView recyclerView) {

        for (int i=0; i<recyclerView.getChildCount(); i++){
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
            if(holder instanceof CampoIndicadorHolder){
                CampoIndicadorHolder campoIndicadorHolder =  (CampoIndicadorHolder)holder;
                campoIndicadorHolder.notifyChanged();
            }else if(holder instanceof BuscarCampoPadreHolder){
                //BuscarCampoPadreHolder buscarCampoPadreHolder =  (BuscarCampoPadreHolder)holder;
                //buscarCampoPadreHolder.notifyChanged();
            }else if(holder instanceof BuscarCampoHijoHolder){
                BuscarCampoHijoHolder buscarCampoHijoHolder =  (BuscarCampoHijoHolder)holder;
                buscarCampoHijoHolder.notifyChanged();
            }else if(holder instanceof BuscarCampoHolder){
                BuscarCampoHolder buscarCampoHolder =  (BuscarCampoHolder)holder;
                buscarCampoHolder.notifyChanged();
            }
        }

    }

    public void update(Object o){
        int poisition = items.indexOf(o);
        if(poisition!=-1){
            items.set(poisition, o);
            notifyItemChanged(poisition);
        }

    }


}
