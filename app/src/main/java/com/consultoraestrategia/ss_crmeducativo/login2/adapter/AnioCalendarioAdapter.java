package com.consultoraestrategia.ss_crmeducativo.login2.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnioCalendarioAdapter extends RecyclerView.Adapter<AnioCalendarioAdapter.ViewHolder> {
    private Listener listener;
    private List<CalendarioPeriodoUi> calendarioPeriodoUiList = new ArrayList<>();
    private final static String TAG = "AnioCalendarioAdapter";

    public AnioCalendarioAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_services_anio_calendario, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(calendarioPeriodoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return calendarioPeriodoUiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_bimestre)
        TextView txtBimestre;
        @BindView(R.id.txt_descripcion)
        TextView txtDescripcion;

        private Listener listener;
        private CalendarioPeriodoUi calendarioPeriodoUi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(CalendarioPeriodoUi calendarioPeriodoUi, Listener listener) {
            this.listener = listener;
            this.calendarioPeriodoUi = calendarioPeriodoUi;
            txtBimestre.setText(calendarioPeriodoUi.getNombre());
            String descripcion = "del " + Utils.f_fecha_letras_dos(calendarioPeriodoUi.getFechaInicio()) +" al "+Utils.f_fecha_letras_dos(calendarioPeriodoUi.getFechaFin());
            txtDescripcion.setText(descripcion);
        }

        @Override
        public void onClick(View v) {
            listener.onClickAnioCalendario(calendarioPeriodoUi);
        }
    }

    public interface Listener {
        void onClickAnioCalendario(CalendarioPeriodoUi calendarioPeriodoUi);
    }

    public void setList(List<CalendarioPeriodoUi> calendarioPeriodoUiList){
        this.calendarioPeriodoUiList.clear();
        this.calendarioPeriodoUiList.addAll(calendarioPeriodoUiList);
        Log.d(TAG,"calendarioPeriodoUiList"+ calendarioPeriodoUiList.size());
        notifyDataSetChanged();
    }
}
