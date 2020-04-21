package com.consultoraestrategia.ss_crmeducativo.main.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.AccesosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.ConfiguracionViewHolder;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.ProgramaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ConfiguracionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.MenuListener;

import java.util.List;

/**
 * Created by irvinmarin on 02/10/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int PROGRAMAEDUCATIVO = 1, ACCESOS = 2, CONFIGURACION = 3;
    private List<Object> objectList;
    private MenuListener listener;

    public MenuAdapter(List<Object> objectList, MenuListener listener) {
        this.objectList = objectList;
        this.listener = listener;

    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType){
            case PROGRAMAEDUCATIVO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accesos, parent, false);
                viewHolder = new ProgramaViewHolder(view);
                break;
            case ACCESOS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accesos, parent, false);
                viewHolder = new AccesosViewHolder(view);
                break;
            case CONFIGURACION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accesos, parent, false);
                viewHolder = new ConfiguracionViewHolder(view);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accesos, parent, false);
                viewHolder = new ConfiguracionViewHolder(view);
                break;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case PROGRAMAEDUCATIVO:
                ProgramaViewHolder programaViewHolder = (ProgramaViewHolder)holder;
                ProgramaEduactivosUI programaEduactivosUI = (ProgramaEduactivosUI)objectList.get(position);
                programaViewHolder.bind(programaEduactivosUI, listener);
                break;
            case ACCESOS:
                AccesosViewHolder accesosViewHolder = (AccesosViewHolder)holder;
                UsuarioAccesoUI usuarioAccesoUI = (UsuarioAccesoUI)objectList.get(position);
                accesosViewHolder.bind(usuarioAccesoUI,listener);
                break;
            case CONFIGURACION:
                ConfiguracionViewHolder configuracionViewHolder = (ConfiguracionViewHolder)holder;
                ConfiguracionUi configuracionUi = (ConfiguracionUi)objectList.get(position);
                configuracionViewHolder.bind(configuracionUi,listener);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return objectList.size();
    }


    public void setLista(List<Object> objectList) {
        this.objectList.clear();
        this.objectList.addAll(objectList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = objectList.get(position);
        if(o instanceof ProgramaEduactivosUI){
            return PROGRAMAEDUCATIVO;
        }else if(o instanceof UsuarioAccesoUI){
            return ACCESOS;
        }else if(o instanceof ConfiguracionUi) {
            return CONFIGURACION;
        }else {
            return -1;
        }
    }
}
