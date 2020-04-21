package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder.SesionHolderLandScape;

import java.util.List;

public class AdapterSesionesLandscape extends AdapterSesiones_v2{


    public AdapterSesionesLandscape(List<SesionAprendizajeUi> sesionesArrayList, AdapterUnidades.UnidadesListener listener, int backgroudColor, int programaEducativoId) {
        super(sesionesArrayList, listener, backgroudColor, programaEducativoId);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflo la vista (vista padre)
        this.mcontex = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sesiones_land_scape, parent, false);
        // creo el grupo de vistas
        return new SesionHolderLandScape(v);
    }

    // Reemplaza en contenido de la vista
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder vh, final int position) {

        vint_backgroudColor = ContextCompat.getColor(mcontex, R.color.md_blue_700);
        final SesionAprendizajeUi sesionAprendizaje = (SesionAprendizajeUi)sesionesArrayList.get(position);
        ((SesionHolderLandScape)vh).bind(mListener, vint_backgroudColor, sesionAprendizaje,calendar, programaEducativoId);

    }
}