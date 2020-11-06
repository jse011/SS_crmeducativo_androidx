package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CellTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.ColumnTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RowTableRegEvalUi;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

public class TableRegEvalAdapter extends AbstractTableAdapter<ColumnTableRegEvalUi, RowTableRegEvalUi, CellTableRegEvalUi> {

    public TableRegEvalAdapter(Context context) {
        super(context);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return mColumnHeaderItems.get(position).getTipo();
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return  0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return mCellItems.get(0).get(position).getTipo();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case CellTableRegEvalUi.ALUMNO:
                return new CellHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_alumno_registro_evaluacion, parent, false));

            case CellTableRegEvalUi.NOTA:
                return new CellHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_registro_evaluacion, parent, false));

            default:
                return new CellHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_registro_evaluacion, parent, false));

        }

    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {

    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ColumnTableRegEvalUi.ALUMNO:
                return new ColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_alumno_registro_evaluacion, parent,false));
            case ColumnTableRegEvalUi.NOTA:
                return new ColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_registro_evaluacion, parent,false));
            case ColumnTableRegEvalUi.FINAL:
                return new ColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_final_registro_evaluacion, parent,false));
            default:
                return new ColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_registro_evaluacion, parent,false));
        }

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {

    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        return new RowHolder(LayoutInflater.from(mContext).inflate(R.layout.table_row_registro_evaluacion, parent,false));
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {

    }

    @Override
    public View onCreateCornerView() {
        CornerHolder cornerHolder = new CornerHolder(LayoutInflater.from(mContext).inflate(R.layout.table_corner_registro_evaluacion, null)) ;
        return cornerHolder.getView();
    }


    public static class ColumnHolder extends AbstractViewHolder{
        public ColumnHolder(View itemView) {
            super(itemView);
        }
    }
    public static class RowHolder extends AbstractViewHolder{
        public RowHolder(View itemView) {
            super(itemView);
        }
    }
    public static class CellHolder extends AbstractViewHolder{
        public CellHolder(View itemView) {
            super(itemView);
        }
    }

    public static class CornerHolder {
        private final View view;

        private CornerHolder(View view) {
            this.view = view;
        }
        public void bind(){

        }

        public View getView() {
            return view;
        }
    }
}
