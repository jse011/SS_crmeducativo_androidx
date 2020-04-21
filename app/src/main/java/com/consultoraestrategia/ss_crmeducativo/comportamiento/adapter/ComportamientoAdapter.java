package com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.holder.ComportamientoHolder;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.listener.ComportamientoListener;

import java.util.List;

public class ComportamientoAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<AlumnoUi>alumnoUiList;
    private ComportamientoListener listener;
    public ComportamientoAdapter(List<AlumnoUi> lista, ComportamientoListener listener) {
        this.alumnoUiList = lista;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comportamiento, parent, false);
        return new ComportamientoHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         AlumnoUi alumnoUi= (AlumnoUi)alumnoUiList.get(position);
        Log.d(getClass().getSimpleName(), " alumno "+alumnoUi.getLastName() );
         ComportamientoHolder comportamientoHolder= (ComportamientoHolder)holder;
         comportamientoHolder.bind(alumnoUi, listener);
    }

    @Override
    public int getItemCount() {
        return alumnoUiList.size();
    }


    public void setList(List<AlumnoUi>lista){
        alumnoUiList.clear();
        this.alumnoUiList=lista;
        notifyDataSetChanged();
    }


}
