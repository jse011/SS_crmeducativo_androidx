package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CabeceraUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CabeceraTableRegEvalApadter extends RecyclerView.Adapter<CabeceraTableRegEvalApadter.ViewHolder> {
    private List<CabeceraUi> cabeceraUiList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CabeceraUi.ALUMNO:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_alumno_registo_evaluacion, parent, false));
            case CabeceraUi.COMPETENCIA:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_registo_evaluacion, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_registo_evaluacion, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case CabeceraUi.COMPETENCIA:
                holder.bind(cabeceraUiList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cabeceraUiList.size();
    }

    public void setList(List<CabeceraUi> cabeceraUiList) {
        this.cabeceraUiList.clear();
        this.cabeceraUiList.addAll(cabeceraUiList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.root)
        View root;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CabeceraUi cabeceraUi) {
            int whitch = 45 * (cabeceraUi.getTipo() > 0? cabeceraUi.getTipo(): 1);
            root.getLayoutParams().width = (int)Utils.convertPixelsToDp(whitch, root.getContext());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return cabeceraUiList.get(position).getTipo();
    }
}



