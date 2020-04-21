package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubrosAsociadosUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 18/02/2018.
 */

public class RubrosAsociadosAdapter extends RecyclerView.Adapter<RubrosAsociadosAdapter.ViewHolder> {
    private static String TAG = RubrosAsociadosAdapter.class.getSimpleName();

    List<RubrosAsociadosUi> rubrosAsociadosUiList;


    public RubrosAsociadosAdapter(List<RubrosAsociadosUi> rubrosAsociadosUiList) {
        this.rubrosAsociadosUiList = rubrosAsociadosUiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle_rubro_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RubrosAsociadosUi rubrosAsociadosUi = rubrosAsociadosUiList.get(position);
        holder.bind(rubrosAsociadosUi);
    }

    @Override
    public int getItemCount() {
        if (rubrosAsociadosUiList==null){
            return 0;
        }
        return rubrosAsociadosUiList.size();
    }

    public void setRubrosAsocidadosList(List<RubrosAsociadosUi> rubrosAsociadosUis) {
        this.rubrosAsociadosUiList.clear();
        this.rubrosAsociadosUiList.addAll(rubrosAsociadosUis);
        notifyDataSetChanged();
    }

    public void deleteRubroProceso(RubrosAsociadosUi rubrosAsociadosUi) {
        int position = this.rubrosAsociadosUiList.indexOf(rubrosAsociadosUi);
        if (position != -1) {
            this.rubrosAsociadosUiList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_cant_rubro)
        TextView txtCantRubro;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(RubrosAsociadosUi rubrosAsociadosUi) {
            Log.d(TAG,"rubrosAsociadosUi: "+rubrosAsociadosUi.getNumeroRubroAsociado());
            txtCantRubro.setText(rubrosAsociadosUi.getNumeroRubroAsociado() + "");
        }


    }
}
