package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectorValoresHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_texto)
    TextView tipoTexto;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.detalle)
    TextView detalle;


    public SelectorValoresHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi, List<Cell> cellList, int position){
        tipoTexto.setText(valorTipoNotaUi.getTitle());
        nombre.setText(valorTipoNotaUi.getAlias());

        Log.d(getClass().getSimpleName(), "position"+ position);
        Log.d(getClass().getSimpleName(), "cell size"+ cellList.size());
            Cell cell1 = cellList.get(position);
            if (cell1 instanceof CriterioEvaluacionUi){
                CriterioEvaluacionUi criterioEvaluacionUi = (CriterioEvaluacionUi) cell1;
                if (criterioEvaluacionUi.getTitulo()!=null)detalle.setText(criterioEvaluacionUi.getTitulo());
        }

    }

}
