package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.RubroColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.RubroProcesoAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnStaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 21/12/2017.
 */

public class CapacidadHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recycler_two)
    RecyclerView recyclerViewClases;
    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.txt_cant_rubro)
    TextView txtCantRubro;


    public CapacidadHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CapacidadUi capacidadUi) {

        txtTitulo.setText(capacidadUi.getTitle());
        txtCantRubro.setText(String.valueOf(capacidadUi.getCantidadRubros()));
        RubroProcesoAdapter rubroProcesoAdapter = new RubroProcesoAdapter(capacidadUi.getIndicadorList());
        recyclerViewClases.setNestedScrollingEnabled(false);
        recyclerViewClases.setHasFixedSize(false);
        AutoColumnStaggeredGridLayoutManager autoColumnGridLayoutManager = new AutoColumnStaggeredGridLayoutManager(OrientationHelper.VERTICAL, itemView.getContext());
        RubroColumnCountProvider columnCountProvider = new RubroColumnCountProvider(itemView.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerViewClases.setLayoutManager(autoColumnGridLayoutManager);
        recyclerViewClases.setAdapter(rubroProcesoAdapter);
    }

}
