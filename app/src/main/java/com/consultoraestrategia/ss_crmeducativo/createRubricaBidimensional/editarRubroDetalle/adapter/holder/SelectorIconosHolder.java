package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectorIconosHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_imagen)
    ImageView tipoImagen;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.detalle)
    TextView detalle;

    public SelectorIconosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi, List<Cell> cellList, int position){
        Glide.with(itemView.getContext())
                .load(valorTipoNotaUi.getIcono())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                .into(tipoImagen);

        Cell cell1 = cellList.get(position);
        if (cell1 instanceof CriterioEvaluacionUi){
            CriterioEvaluacionUi criterioEvaluacionUi = (CriterioEvaluacionUi) cell1;
            if (criterioEvaluacionUi.getTitulo()!=null)detalle.setText(criterioEvaluacionUi.getTitulo());
        }

        nombre.setText(valorTipoNotaUi.getAlias());
    }

}
