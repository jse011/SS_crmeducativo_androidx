package com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.Holder> implements Filterable {

    private Listener listener;
    private List<TipoCalendarioUi> tipoCalendarioUiList = new ArrayList<>();
    private List<TipoCalendarioUi> mFilteredList;

    public CalendarioAdapter(Listener listener) {
        this.listener = listener;
        mFilteredList = tipoCalendarioUiList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo_calendario, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(mFilteredList.get(position), listener);
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
                    mFilteredList = tipoCalendarioUiList;
                } else {

                    List<TipoCalendarioUi> filteredList = new ArrayList<>();

                    for (TipoCalendarioUi tipoCalendarioUi : tipoCalendarioUiList) {
                        String nombre = TextUtils.isEmpty(tipoCalendarioUi.getNombre())?"":tipoCalendarioUi.getNombre().toLowerCase();
                        String descripcion = TextUtils.isEmpty(tipoCalendarioUi.getDescripcion())?"": tipoCalendarioUi.getDescripcion().toLowerCase();

                        if (nombre.contains(charString) ||
                                descripcion.contains(charString)) {
                            filteredList.add(tipoCalendarioUi);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                mFilteredList = (List<TipoCalendarioUi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_titulo_calendario)
        TextView txtTituloCalendario;
        @BindView(R.id.txt_datos_carga_academica)
        TextView txtDatosCargaAcademica;

        private Listener listener;
        private TipoCalendarioUi tipoCalendarioUi;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        public void bind(TipoCalendarioUi tipoCalendarioUi, Listener listener) {
            this.listener = listener;
            this.tipoCalendarioUi = tipoCalendarioUi;
            txtTituloCalendario.setText(tipoCalendarioUi.getNombre());
            txtDatosCargaAcademica.setText(tipoCalendarioUi.getDescripcion());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                default:
                    if (listener != null) listener.onClickTipoCalendario(tipoCalendarioUi);
                    break;
            }
        }
    }

    public void setTipoComportamientoList(List<TipoCalendarioUi> tipoComportamientoList) {
        this.tipoCalendarioUiList.clear();
        this.tipoCalendarioUiList.addAll(tipoComportamientoList);
        Log.d(getClass().getSimpleName(), "size: " + tipoComportamientoList.size());
        notifyDataSetChanged();
    }

    public interface Listener {
        void onClickTipoCalendario(TipoCalendarioUi tipoComportamientoUi);
    }
}
