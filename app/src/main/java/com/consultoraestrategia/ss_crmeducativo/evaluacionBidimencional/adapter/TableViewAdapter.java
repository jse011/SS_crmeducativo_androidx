package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.CornerHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.AlternoEvaluacionCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.DefaultCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.NotaCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.NotaTipoCompetenciaCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.SelectorIconosCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.SelectorValoresCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.SimpleEvaluacionCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.AlumnoColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.DefaultColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.GrupoColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.RubroEvaluacionColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.AlternoSelectorIconosRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.AlternoSelectorValoresRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.DefaultRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.NotaRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.RubroRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.SelectorIconosRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.SelectorValoresRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaTipoCompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.CellRecyclerViewAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.List;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class TableViewAdapter extends AbstractTableAdapter<RowHeader, ColumnHeader, Cell> {
    private static final String TAG = TableViewAdapter.class.getSimpleName();
    private final int ROW_SELECTOR_VALORES = 1, ROW_SELECTOR_ICONOS = 2, ROW_NOTA = 3, ROW_RUBRO = 4, ROW_ALTERNO_SELECTOR_ICONOS = 5, ROW_ALTERNO_SELECTOR_VALORES = 6;
    private final int CELL_SELECTOR = 1, CELL_NOTA = 2, CELL_SELECTOR_VALORES = 3, CELL_SELECTOR_ICONOS = 4, CELL_NOTATIPOCOMPETENCIA = 5, CELL_SELECTOR_AVANZADO = 6;
    private final int COLUMN_ALUMNO = 1, COLUMN_GRUPO = 2, COLUMN_RUBRO = 3;
    private View.OnClickListener listener;
    private boolean diseniotablaAlterno = false;

    public TableViewAdapter(Context p_jContext,  View.OnClickListener listener) {
        super(p_jContext);
        this.listener = listener;
    }


    @Override
    public int getColumnHeaderItemViewType(int position) {
        RowHeader rowHeader = (RowHeader)getColumnHeaderRecyclerViewAdapter().getItems().get(position);
        if (rowHeader instanceof ValorTipoNotaUi) {
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) rowHeader;
            TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();
            if (tipoNotaUi == null) return 0;
            switch (tipoNotaUi.getTipo()) {
                case SELECTOR_VALORES:
                    if(diseniotablaAlterno){
                        return ROW_ALTERNO_SELECTOR_VALORES;
                    }else {
                        return ROW_SELECTOR_VALORES;
                    }
                case SELECTOR_ICONOS:
                    if(diseniotablaAlterno){
                        return ROW_ALTERNO_SELECTOR_ICONOS;
                    }else {
                        return ROW_SELECTOR_ICONOS;
                    }
            }
        } else if (rowHeader instanceof NotaUi) {
            return ROW_NOTA;
        } else if (rowHeader instanceof RubEvalProcUi) {
            return ROW_RUBRO;
        }
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        ColumnHeader columnHeader = (ColumnHeader)getRowHeaderRecyclerViewAdapter().getItems().get(position);
        if (columnHeader instanceof AlumnoProcesoUi) {
            AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) columnHeader;
            return COLUMN_ALUMNO;
        } else if (columnHeader instanceof GrupoProcesoUi) {
            return COLUMN_GRUPO;
        } else if (columnHeader instanceof RubEvalProcUi) {
            return COLUMN_RUBRO;
        }

        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        List<List<Cell>> listList = (List<List<Cell>>)getCellRecyclerViewAdapter().getItems();

        int cantidad = listList.size();
        if (cantidad != 0) {
            Cell cell = listList.get(0).get(position);
            if (cell instanceof ValorTipoNotaUi) {
                if(diseniotablaAlterno){
                    return CELL_SELECTOR_AVANZADO;
                }else {
                    return CELL_SELECTOR;
                }
            } else if (cell instanceof NotaTipoCompetenciaUi) {
                Log.d(TAG, "CELL_NOTATIPOCOMPETENCIA");
                return CELL_NOTATIPOCOMPETENCIA;
            } else if (cell instanceof NotaUi) {
                Log.d(TAG, "CELL_NOTA");
                return CELL_NOTA;
            } else if (cell instanceof EvalProcUi) {
                EvalProcUi evalProcUi = (EvalProcUi) cell;
                Log.d(TAG, "SELECTOR_VALORES");
                switch (evalProcUi.getTipo()) {
                    case SELECTOR_VALORES:
                        return CELL_SELECTOR_VALORES;
                    case SELECTOR_ICONOS:
                        return CELL_SELECTOR_ICONOS;
                }
            }
        }
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case CELL_NOTA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_nota_layout, parent, false);
                return new NotaCellViewHolder(layout);
            case CELL_NOTATIPOCOMPETENCIA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_nota_competencia_layout, parent, false);
                return new NotaTipoCompetenciaCellViewHolder(layout);
            case CELL_SELECTOR:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_evaluacion_layout, parent, false);
                return new SimpleEvaluacionCellViewHolder(layout);
            case CELL_SELECTOR_AVANZADO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_alterno_evaluacion_layout, parent, false);
                return new AlternoEvaluacionCellViewHolder(layout);
            case CELL_SELECTOR_ICONOS:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_evaluacion_iconos_layout, parent, false);
                return new SelectorIconosCellViewHolder(layout);
            case CELL_SELECTOR_VALORES:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_evaluacion_valores_layout, parent, false);
                return new SelectorValoresCellViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_layout, parent, false);
                return new DefaultCellViewHolder(layout);
        }
    }


    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof NotaCellViewHolder && p_jValue instanceof NotaUi) {
            NotaCellViewHolder notaCellViewHolder = (NotaCellViewHolder) holder;
            NotaUi notaUi = (NotaUi) p_jValue;
            notaCellViewHolder.bind(notaUi);
        }
        if (holder instanceof NotaTipoCompetenciaCellViewHolder && p_jValue instanceof NotaTipoCompetenciaUi) {
            NotaTipoCompetenciaCellViewHolder notaTipoCompetenciaCellViewHolder = (NotaTipoCompetenciaCellViewHolder) holder;
            NotaTipoCompetenciaUi notaTipoCompetenciaUi = (NotaTipoCompetenciaUi) p_jValue;
            notaTipoCompetenciaCellViewHolder.bind(notaTipoCompetenciaUi);
        } else if (holder instanceof SimpleEvaluacionCellViewHolder && p_jValue instanceof ValorTipoNotaUi) {
            SimpleEvaluacionCellViewHolder simpleEvaluacionCellViewHolder = (SimpleEvaluacionCellViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            simpleEvaluacionCellViewHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof AlternoEvaluacionCellViewHolder && p_jValue instanceof ValorTipoNotaUi) {
            AlternoEvaluacionCellViewHolder alternoEvaluacionCellViewHolder = (AlternoEvaluacionCellViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            alternoEvaluacionCellViewHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof SelectorIconosCellViewHolder && p_jValue instanceof EvalProcUi) {
            SelectorIconosCellViewHolder selectorIconosCellViewHolder = (SelectorIconosCellViewHolder) holder;
            EvalProcUi evalProcUi = (EvalProcUi) p_jValue;
            selectorIconosCellViewHolder.bind(evalProcUi);
        } else if (holder instanceof SelectorValoresCellViewHolder && p_jValue instanceof EvalProcUi) {
            SelectorValoresCellViewHolder selectorValoresCellViewHolder = (SelectorValoresCellViewHolder) holder;
            EvalProcUi evalProcUi = (EvalProcUi) p_jValue;
            selectorValoresCellViewHolder.bind(evalProcUi);
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case ROW_NOTA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_nota_evaluacion_layout, parent, false);
                return new NotaRowViewHolder(layout);
            case ROW_SELECTOR_VALORES:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_selector_valores_layout, parent, false);
                return new SelectorValoresRowViewHolder(layout);
            case ROW_SELECTOR_ICONOS:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_selector_iconos_layout, parent, false);
                return new SelectorIconosRowViewHolder(layout);
            case ROW_RUBRO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_row_rubro_evaluacion_layout, parent, false);
                return new RubroRowViewHolder(layout);
            case ROW_ALTERNO_SELECTOR_ICONOS:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_alter_selector_iconos_layout, parent, false);
                return new AlternoSelectorIconosRowViewHolder(layout);
            case ROW_ALTERNO_SELECTOR_VALORES:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_alter_selector_valores_layout, parent, false);
                return new AlternoSelectorValoresRowViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_column_header_layout, parent, false);
                return new DefaultRowViewHolder(layout);
        }
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        if (holder instanceof NotaRowViewHolder && p_jValue instanceof NotaUi) {
            NotaRowViewHolder notaRowViewHolder = (NotaRowViewHolder) holder;
            NotaUi notaUi = (NotaUi) p_jValue;
            notaRowViewHolder.bind(notaUi);
        } else if (holder instanceof SelectorValoresRowViewHolder && p_jValue instanceof ValorTipoNotaUi) {
            SelectorValoresRowViewHolder selectorValoresRowViewHolder = (SelectorValoresRowViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            selectorValoresRowViewHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof SelectorIconosRowViewHolder && p_jValue instanceof ValorTipoNotaUi) {
            SelectorIconosRowViewHolder selectorIconosRowViewHolder = (SelectorIconosRowViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            selectorIconosRowViewHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof RubroRowViewHolder && p_jValue instanceof RubEvalProcUi) {
            RubroRowViewHolder rubroRowViewHolder = (RubroRowViewHolder) holder;
            RubEvalProcUi rubEvalProcUi = (RubEvalProcUi) p_jValue;
            rubroRowViewHolder.bind(rubEvalProcUi);
        } else if (holder instanceof AlternoSelectorValoresRowViewHolder && p_jValue instanceof ValorTipoNotaUi) {
            AlternoSelectorValoresRowViewHolder alternoSelectorValoresRowViewHolder = (AlternoSelectorValoresRowViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            alternoSelectorValoresRowViewHolder.bind(valorTipoNotaUi);
        } else if (holder instanceof AlternoSelectorIconosRowViewHolder && p_jValue instanceof ValorTipoNotaUi) {
            AlternoSelectorIconosRowViewHolder alternoSelectorIconosRowViewHolder = (AlternoSelectorIconosRowViewHolder) holder;
            ValorTipoNotaUi valorTipoNotaUi = (ValorTipoNotaUi) p_jValue;
            alternoSelectorIconosRowViewHolder.bind(valorTipoNotaUi);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case COLUMN_ALUMNO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum_alumno_layout, parent, false);
                return new AlumnoColumnViewHolder(layout);
            case COLUMN_GRUPO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum_grupo_layout, parent, false);
                return new GrupoColumnViewHolder(layout);
            case COLUMN_RUBRO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum_rubro_evaluacion_layout, parent, false);
                return new RubroEvaluacionColumnViewHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_row_header_layout, parent, false);
                return new DefaultColumnViewHolder(layout);
        }
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        if (holder instanceof AlumnoColumnViewHolder && p_jValue instanceof AlumnoProcesoUi) {
            AlumnoColumnViewHolder alumnoColumnViewHolder = (AlumnoColumnViewHolder) holder;
            AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) p_jValue;
            alumnoColumnViewHolder.bind(alumnoProcesoUi);
        } else if (holder instanceof GrupoColumnViewHolder && p_jValue instanceof GrupoProcesoUi) {
            GrupoColumnViewHolder grupoColumnViewHolder = (GrupoColumnViewHolder) holder;
            GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) p_jValue;
            grupoColumnViewHolder.bind(grupoProcesoUi);
        } else if (holder instanceof RubroEvaluacionColumnViewHolder && p_jValue instanceof RubEvalProcUi) {
            RubroEvaluacionColumnViewHolder rubroEvaluacionColumnViewHolder = (RubroEvaluacionColumnViewHolder) holder;
            RubEvalProcUi rubEvalProcUi = (RubEvalProcUi) p_jValue;
            rubroEvaluacionColumnViewHolder.bind(rubEvalProcUi);
        }
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_view_corner_layout_rubrica_individual, null);
    }

    @Override
    public void addRowRange(int p_nYPositionStart, List<ColumnHeader> p_jRowHeaderItem, List<List<Cell>> p_jCellItems) {
        Log.d(TAG, "addRowRange p_nYPositionStart" + p_nYPositionStart);
        mRowHeaderItems.addAll(p_nYPositionStart, p_jRowHeaderItem);
        mCellItems.addAll(p_nYPositionStart, p_jCellItems);
        setCellItems(mCellItems);

        setRowHeaderItems(mRowHeaderItems);
    }

    public void removeRowRange(int columPositionStart, List<ColumnHeader> p_jRowHeaderItem, List<List<Cell>> p_jCellItems) {
        mRowHeaderItems.removeAll(p_jRowHeaderItem);
        mCellItems.removeAll(p_jCellItems);
        setCellItems(mCellItems);
        dispatchCellDataSetChangesToListeners(mCellItems);
        setRowHeaderItems(mRowHeaderItems);
    }

    public void changeCellItemRange(int position, List<Cell> cell) {
        CellRecyclerViewAdapter cellRecyclerViewAdapter = getCellRecyclerViewAdapter();
        cellRecyclerViewAdapter.changeItemRange(position, cell);
    }

    public void setCornerView(String titulo, int m_nRowHeaderWidth, int m_nColumnHeaderHeight) {
        View view = onCreateCornerView();
        CornerHolder viewHolder = new CornerHolder(view, listener);
        getTableView().addView(view, new FrameLayout.LayoutParams(m_nRowHeaderWidth, m_nColumnHeaderHeight));
        viewHolder.bind(titulo);
    }

    public void setDiseniotablaAlterno(boolean diseniotablaAlterno) {
        this.diseniotablaAlterno = diseniotablaAlterno;
    }


    public List<List<Cell>> getCellItemList(){
        return this.mCellItems;
    }

    public List<ColumnHeader> getRowHeaderItemList(){
        return this.mRowHeaderItems;
    }

}
