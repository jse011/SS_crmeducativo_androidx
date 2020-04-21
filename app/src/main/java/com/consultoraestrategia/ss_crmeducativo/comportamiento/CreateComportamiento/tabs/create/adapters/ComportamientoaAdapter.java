package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComportamientoaAdapter extends RecyclerView.Adapter<ComportamientoaAdapter.Holder> implements Filterable {
    private TipoComportamientoListListener listener;
    private List<TipoComportamientoUi> tipoComportamientoList = new ArrayList<>();
    private List<TipoComportamientoUi> mFilteredList;

    public ComportamientoaAdapter(TipoComportamientoListListener listener) {
        this.listener = listener;
        mFilteredList = tipoComportamientoList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_comportamiento, parent, false));
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
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = tipoComportamientoList;
                } else {

                    List<TipoComportamientoUi> filteredList = new ArrayList<>();

                    for (TipoComportamientoUi tipoComportamientoUi : tipoComportamientoList) {
                            if (tipoComportamientoUi.getTitulo().toLowerCase().contains(charString)||
                                    tipoComportamientoUi.getDescripcion().toLowerCase().contains(charString)||
                                    String.valueOf(tipoComportamientoUi.getCantidad()).contains(charString)) {
                                filteredList.add(tipoComportamientoUi);
                            }
                    }

                    mFilteredList = filteredList;
                }

                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, Filter.FilterResults
                    filterResults) {
                mFilteredList = (List<TipoComportamientoUi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_titulo_comportamiento)
        TextView txtTituloComportamiento;
        @BindView(R.id.txt_descripcion_comportamiento)
        TextView txtDescripcionComportamiento;
        @BindView(R.id.txt_peso_comportamiento)
        TextView txtPesoComportamiento;
        private TipoComportamientoListListener listener;
        private TipoComportamientoUi tipoComportamiento;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        public void bind(TipoComportamientoUi tipoComportamiento,TipoComportamientoListListener listener) {
            this.listener = listener;
            this.tipoComportamiento = tipoComportamiento;
            txtTituloComportamiento.setText(tipoComportamiento.getTitulo());
            txtDescripcionComportamiento.setText(tipoComportamiento.getDescripcion());
            txtPesoComportamiento.setText(String.valueOf(tipoComportamiento.getCantidad()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                default:
                    if(listener!=null)listener.onClickTipoComportamiento(tipoComportamiento);
                    break;
            }
        }
    }

    public void setTipoComportamientoList(List<TipoComportamientoUi> tipoComportamientoList) {
        this.tipoComportamientoList.clear();
        this.tipoComportamientoList.addAll(tipoComportamientoList);
        Log.d(getClass().getSimpleName(), "size: " + tipoComportamientoList.size());
        notifyDataSetChanged();
    }

    public interface TipoComportamientoListListener {
        void onClickTipoComportamiento(TipoComportamientoUi tipoComportamientoUi);
    }
}
