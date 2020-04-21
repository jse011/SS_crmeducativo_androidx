package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell.DefaultCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell.PesoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell.SelectorCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.column.DefaultColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.column.IndicadorColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.DefaultRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.PesoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.SelectorIconoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.SelectorValorRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;


/**
 * Created by SCIEV on 7/05/2018.
 */

public class TableViewAdapter extends AbstractTableAdapter<RowHeader, ColumnHeader, Cell> {

    private static final String TAG = TableViewAdapter.class.getSimpleName();
    private final int ROW_SELECTOR_VALORES = 1 ,ROW_SELECTOR_ICONOS = 2, ROW_PESO = 3 ;
    private final int CELL_SELECTOR = 1, CELL_PESO = 2;
    private final int COLUMN_INDICADOR = 1;

    public TableViewAdapter(Context p_jContext) {
        super(p_jContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {

        com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell rowHeader = mColumnHeaderItems.get(position);
        if(rowHeader instanceof ValorTipoNotaUi) {
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) rowHeader;
            TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();
            if(tipoNotaUi == null)return 0;
            switch (tipoNotaUi.getTipo()){
                case SELECTOR_VALORES:
                    return ROW_SELECTOR_VALORES;
                case SELECTOR_ICONOS:
                    return ROW_SELECTOR_ICONOS;
            }
        }else if(rowHeader instanceof IndicadorUi){
            return ROW_PESO;
        }
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell columnHeader = mRowHeaderItems.get(position);
        if(columnHeader instanceof IndicadorUi){
            return COLUMN_INDICADOR;
        }
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        int cantidad = mCellItems.size();
        if(cantidad != 0){
            com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell cell = mCellItems.get(0).get(position);
            if(cell instanceof CriterioEvaluacionUi) {
                return  CELL_SELECTOR;
            }else if(cell instanceof IndicadorUi){
                return CELL_PESO;
            }
        }
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType){
            case CELL_PESO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_nota_competencia_layout, parent, false);
                return new PesoCellViewHolder(layout);
            case CELL_SELECTOR:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_evaluacion_criterio_layout, parent, false);
                return new SelectorCellViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_layout, parent, false);
                return new DefaultCellViewHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
            if(holder instanceof PesoCellViewHolder && p_jValue instanceof IndicadorUi){
                PesoCellViewHolder notaCellViewHolder = (PesoCellViewHolder)holder;
                IndicadorUi indicadorUi = (IndicadorUi) p_jValue;
                notaCellViewHolder.bind(indicadorUi);
            }if(holder instanceof SelectorCellViewHolder && p_jValue instanceof CriterioEvaluacionUi){
                SelectorCellViewHolder selectorCellViewHolder = (SelectorCellViewHolder)holder;
                CriterioEvaluacionUi criterioEvaluacionUi = (CriterioEvaluacionUi) p_jValue;
                selectorCellViewHolder.bind(criterioEvaluacionUi);
            }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType){
            case ROW_SELECTOR_VALORES:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_selector_valores_layout, parent, false);
                return new SelectorValorRowViewHolder(layout);
            case ROW_SELECTOR_ICONOS:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_selector_iconos_layout, parent, false);
                return new SelectorIconoRowViewHolder(layout);
            case ROW_PESO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_btn_evaluacion_layout, parent, false);
                return new PesoRowViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_column_header_layout, parent, false);
                return new DefaultRowViewHolder(layout);
        }
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        if(holder instanceof SelectorValorRowViewHolder && p_jValue instanceof ValorTipoNotaUi){
            SelectorValorRowViewHolder selectorValorRowViewHolder = (SelectorValorRowViewHolder)holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            selectorValorRowViewHolder.bind(valorTipoNotaUi);
        }else if(holder instanceof SelectorIconoRowViewHolder && p_jValue instanceof ValorTipoNotaUi){
            SelectorIconoRowViewHolder selectorIconoRowViewHolder = (SelectorIconoRowViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            selectorIconoRowViewHolder.bind(valorTipoNotaUi);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType){
            case COLUMN_INDICADOR:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum_crear_rubrica_layout, parent, false);
                return new IndicadorColumnViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_row_header_layout, parent, false);
                return new DefaultColumnViewHolder(layout);
        }
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        if(holder instanceof IndicadorColumnViewHolder && p_jValue instanceof IndicadorUi){
            IndicadorColumnViewHolder alumnoColumnViewHolder = (IndicadorColumnViewHolder)holder;
            IndicadorUi indicadorUi = (IndicadorUi) p_jValue;
            alumnoColumnViewHolder.bind(indicadorUi);
        }
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_view_corner_layout, null);
    }


}
