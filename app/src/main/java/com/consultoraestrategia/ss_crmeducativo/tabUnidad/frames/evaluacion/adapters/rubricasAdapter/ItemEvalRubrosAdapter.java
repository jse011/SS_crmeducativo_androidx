package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubricasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemEvalRubrosAdapter extends RecyclerView.Adapter<ItemEvalRubrosAdapter.ViewHolder> {

    List<Integer> integersRubros;


    public ItemEvalRubrosAdapter(List<Integer> integersRubros) {
        this.integersRubros = integersRubros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rubros_rubricas, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.bind(integersRubros.get(i));
    }

    @Override
    public int getItemCount() {
        return integersRubros.size();
    }

    public void setList(List<Integer> integer){
        this.integersRubros.clear();
        this.integersRubros.addAll(integer);
        notifyDataSetChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_cant_rubro)
        TextView txtCantRubro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int numero) {
            txtCantRubro.setText(String.valueOf(numero));
        }

    }
}
