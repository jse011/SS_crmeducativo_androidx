package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder.SesionHolder;

import java.util.Calendar;
import java.util.List;


public class AdapterSesiones_v2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    String TAG= AdapterSesiones_v2.class.getSimpleName();
    protected Context mcontex;
    protected int vint_backgroudColor;
    protected Calendar calendar;
    protected List<SesionAprendizajeUi> sesionesArrayList;
    protected AdapterUnidades.UnidadesListener mListener;
    // Provee una referencia a cada item dentro de una vista y acceder a ellos facilmente

    protected int programaEducativoId;

    // Constructor
    public AdapterSesiones_v2(List<SesionAprendizajeUi> sesionesArrayList, AdapterUnidades.UnidadesListener listener, int backgroudColor, int programaEducativoId) {
        this.sesionesArrayList = sesionesArrayList;
        this.mListener = listener;
        this.vint_backgroudColor = backgroudColor;
        this.programaEducativoId = programaEducativoId;
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

    }

    // Create new views (invoked by the layout managxer)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflo la vista (vista padre)
        this.mcontex = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sesiones_v2, parent, false);
        // creo el grupo de vistas
        SesionHolder vh = new SesionHolder(v);
        return vh;
    }

    // Reemplaza en contenido de la vista
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder vh, final int position) {

        vint_backgroudColor = ContextCompat.getColor(mcontex, R.color.md_blue_700);
        final SesionAprendizajeUi sesionAprendizaje = (SesionAprendizajeUi)sesionesArrayList.get(position);
        ((SesionHolder)vh).bind(mListener, vint_backgroudColor, sesionAprendizaje,calendar, programaEducativoId);

    }

    // Retorna el tamano de nuestra data
    @Override
    public int getItemCount() {
        return sesionesArrayList.size();
    }

    public interface showMessageCollback{
        void onClickAceptar();
        void onClickCancelar();
    }



}