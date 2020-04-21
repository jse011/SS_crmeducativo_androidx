package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;

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

public class NombreRubrosAsociadosAdapter extends RecyclerView.Adapter<NombreRubrosAsociadosAdapter.ViewHolder> {
    private static String TAG = NombreRubrosAsociadosAdapter.class.getSimpleName();

    List<RubrosAsociadosUi> rubrosAsociadosUiList;

    public NombreRubrosAsociadosAdapter(List<RubrosAsociadosUi> rubrosAsociadosUiList) {
        this.rubrosAsociadosUiList = rubrosAsociadosUiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubro_select_asociados, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RubrosAsociadosUi rubrosAsociadosUi = rubrosAsociadosUiList.get(position);
        holder.bind(rubrosAsociadosUi);
    }

    @Override
    public int getItemCount() {
        if (rubrosAsociadosUiList == null) {
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
        @BindView(R.id.txt_nombre)
        TextView txtNombre;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(RubrosAsociadosUi rubrosAsociadosUi) {
            Log.d(TAG, "rubrosAsociadosUi: " + rubrosAsociadosUi.getNumeroRubroAsociado());
            String nombre = rubrosAsociadosUi.getNumeroRubroAsociado() + ". "+ rubrosAsociadosUi.getNombreRubroAsociado();
            txtNombre.setText(nombre);
        }


    }
}
