package com.consultoraestrategia.ss_crmeducativo.comentarios.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;

import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.holder.ComentarioHolderReceptor;
import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.holder.ComentariosHolderDia;
import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.holder.ComentariosHolderEmisor;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;


import java.util.List;

public class ComentarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int RECEPTORV = 1, EMISORV = 2, DIA = 3;

  ComentarioListener comentarioListener;
    private List<Object> itemComentarioList;
    private RecyclerView recy;



    public ComentarioAdapter(ComentarioListener comentarioListener, List<Object> itemComentarioList, RecyclerView recy) {
        this.comentarioListener = comentarioListener;
        this.itemComentarioList = itemComentarioList;
        this.recy = recy;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        switch (viewType) {
            case EMISORV:
                view = layoutInflater.inflate(R.layout.tabs_item_comentarios_emisor, viewGroup, false);
                viewHolder = new ComentariosHolderEmisor(view);
                break;
            case RECEPTORV:
                view = layoutInflater.inflate(R.layout.tabs_item_comentarios, viewGroup, false);
                viewHolder = new ComentarioHolderReceptor(view);
                break;
            case DIA:
                view = layoutInflater.inflate(R.layout.layout_comentarios_dia, viewGroup, false);
                viewHolder = new ComentariosHolderDia(view);
                break;
            default:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_linea, viewGroup, false);
                viewHolder = new ComentariosHolderEmisor(view);
                break;
        }
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case RECEPTORV:
                ComentarioHolderReceptor comentarioHolderReceptor= (ComentarioHolderReceptor) holder;
                ComentarioUi comentarioUi= (ComentarioUi) itemComentarioList.get(position);
                comentarioHolderReceptor.bind(comentarioUi, position, comentarioListener);
                break;
            case EMISORV:
                ComentariosHolderEmisor comentariosHolderEmisor = (ComentariosHolderEmisor) holder;
                ComentarioUi comentarioUis= (ComentarioUi) itemComentarioList.get(position);
                comentariosHolderEmisor.bind(comentarioUis,position,comentarioListener);
                break;
            case DIA:
                ComentariosHolderDia comentariosHolderDia = (ComentariosHolderDia) holder;
                Long dia = (Long)itemComentarioList.get(position);
                comentariosHolderDia.bind(dia);
                break;
        }

    }


    @Override
    public int getItemCount() {
        return itemComentarioList.size();
    }

    public void setComentarioList(List<Object> itemComentarioList) {
        this.itemComentarioList.clear();//Limpiamos la memoria (Objeto)
        this.itemComentarioList.addAll(itemComentarioList);//Agrega toda lista
        notifyDataSetChanged(); // Pinta otra ves la lista
        recy.scrollToPosition(getItemCount() - 1);
    }


    public void addComentarios(Object item) {
        this.itemComentarioList.add(item);
        notifyItemInserted(getItemCount() - 1);

    }

    public void clearComentarios() {
        this.itemComentarioList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = itemComentarioList.get(position);
        if (item instanceof ComentarioUi) {
            ComentarioUi comentarioUi = (ComentarioUi) item;
            switch (comentarioUi.getTipoUi().getTipo()){
                case EMISOR:
                    return EMISORV;
                case RECEPTOR:
                    return RECEPTORV;
                default:
                    return -1;
            }
        }else if (item instanceof Long){
            return DIA;
        }
        else {
            return -1;
        }
    }

}
