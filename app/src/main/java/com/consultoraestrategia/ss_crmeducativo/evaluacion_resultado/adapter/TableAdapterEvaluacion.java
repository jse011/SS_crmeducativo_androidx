package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.CellNotaHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.CellValorNotaHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.ColumnaCornerAlumnosHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.ColumnaNotafinalHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.ColumnaRubroHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.FilaAlumnosHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.List;

public class TableAdapterEvaluacion extends AbstractTableAdapter<RubroEvaluacionUi, AlumnoUi, Object> {

    private static final int CELL_SELECTOR_VALORES = 2, CELL_VALOR_NUMERICO = 3, CELL_NOTA_FINAL = 4 ,COLUMNA = 0, COLUMNA_NOTA_FINAL = 1;
    private String TAG = TableAdapterEvaluacion.class.getSimpleName();

    public TableAdapterEvaluacion(Context context) {
        super(context);

    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        RubroEvaluacionUi rubroEvaluacionUi = (RubroEvaluacionUi)getColumnHeaderRecyclerViewAdapter().getItem(position);
        if (rubroEvaluacionUi.isTipo())return COLUMNA_NOTA_FINAL;
        else return COLUMNA;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        NotaUi notaUi = new NotaUi();
        List<Object> objectList = (List<Object>)getCellRecyclerViewAdapter().getItem(0);

        Object object = objectList.get(position);
        if (object instanceof NotaUi) notaUi = (NotaUi) object;
        switch (notaUi.getTipo()) {
            case VALOR_NUMERO:
                return CELL_VALOR_NUMERICO;
            case NOTA_FINAL:
                return CELL_NOTA_FINAL;
            default:
                return CELL_SELECTOR_VALORES;
        }

    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case CELL_VALOR_NUMERICO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_eval_resultado, parent, false);
                return new CellNotaHolder(layout);
            case CELL_NOTA_FINAL:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_eval_resultado_nota_final, parent, false);
                return new CellValorNotaHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_valornota_eval_resultado, parent, false);
                return new CellValorNotaHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        if (holder instanceof CellNotaHolder && cellItemModel instanceof NotaUi)
            ((CellNotaHolder) holder).bind((NotaUi) cellItemModel);
        if (holder instanceof CellValorNotaHolder && cellItemModel instanceof NotaUi)
            ((CellValorNotaHolder) holder).bind((NotaUi) cellItemModel);
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        Log.d(TAG, "onCreateColumnHeaderViewHolder0. viewType " + viewType);
        switch (viewType) {
            case COLUMNA_NOTA_FINAL:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum_nf_evaluacion_resultado, parent, false);
                return new ColumnaNotafinalHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum_rubro_evaluacion_resultado, parent, false);
                return new ColumnaRubroHolder(layout);
        }

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        if (holder instanceof ColumnaRubroHolder && columnHeaderItemModel instanceof RubroEvaluacionUi)
            ((ColumnaRubroHolder) holder).bind((RubroEvaluacionUi) columnHeaderItemModel);
        else if (holder instanceof ColumnaNotafinalHolder && columnHeaderItemModel instanceof RubroEvaluacionUi)
            ;
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_fila_evaluacion_resultado, parent, false);
        return new FilaAlumnosHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {

        if (holder instanceof FilaAlumnosHolder && rowHeaderItemModel instanceof AlumnoUi)
            ((FilaAlumnosHolder) holder).bind((AlumnoUi) rowHeaderItemModel, rowPosition);
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_corner_evaluacion_resultado, null);
    }

    public void setCornerView(int cantidad, int m_nRowHeaderWidth, int m_nColumnHeaderHeight) {
        View view = onCreateCornerView();
        ColumnaCornerAlumnosHolder viewHolder = new ColumnaCornerAlumnosHolder(view);
        getTableView().addView(view, new FrameLayout.LayoutParams(m_nRowHeaderWidth, m_nColumnHeaderHeight));
        viewHolder.bind(cantidad, null);
    }
}
