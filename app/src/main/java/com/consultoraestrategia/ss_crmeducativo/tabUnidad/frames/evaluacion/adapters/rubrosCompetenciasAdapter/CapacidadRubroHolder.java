package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubrosCompetenciasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubricasAdapter.RubricasAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionUnidadListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CapacidadRubroHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.capacidad)
    TextView capacidad;
    @BindView(R.id.recyRubros)
    RecyclerView recyRubros;
    RubricasAdapter rubBidAdapter;
    EvaluacionUnidadListener unidadListener;
    GridLayoutManager gridLayoutManager;

    public CapacidadRubroHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CapacidadUi object ,EvaluacionUnidadListener unidadListener) {
        capacidad.setText(object.getNombre());
        this.unidadListener=unidadListener;
        initAdapter(object.getRubricaUis());
        Log.d(getClass().getSimpleName(), "competnecia "+object.getNombre()+" rubros size "+ object.getRubricaUis().size());
    }

    private void initAdapter(List<RubricaUi> rubricaUiList) {
        int cantidad=1;
        if (rubricaUiList.size()>3)cantidad=2;
        rubBidAdapter = new RubricasAdapter(new ArrayList<RubricaUi>(), unidadListener);
        gridLayoutManager= new GridLayoutManager(itemView.getContext(), cantidad, GridLayoutManager.HORIZONTAL, false);
        recyRubros.setLayoutManager(gridLayoutManager);
        recyRubros.setAdapter(rubBidAdapter);
        rubBidAdapter.setListRubricas(rubricaUiList);
    }
}
