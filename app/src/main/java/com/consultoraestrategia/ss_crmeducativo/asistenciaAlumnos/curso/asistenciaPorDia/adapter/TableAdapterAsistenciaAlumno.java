package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.ColumnaCornerAlumnosHolder;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

public class TableAdapterAsistenciaAlumno  extends AbstractTableAdapter<ValorTipoNotaUi, AlumnosUi,ValorTipoNotaUi> {


    public TableAdapterAsistenciaAlumno(Context context) {
        super(context);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_vt_asist_alumno, parent, false);
        return new CellAsistenciaAlumnoHolder(layout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        if(holder instanceof  CellAsistenciaAlumnoHolder && cellItemModel instanceof ValorTipoNotaUi){
            CellAsistenciaAlumnoHolder cellAsistenciaAlumnoHolder =  (CellAsistenciaAlumnoHolder)holder;
            cellAsistenciaAlumnoHolder.bind((ValorTipoNotaUi) cellItemModel);
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_column_vt_asist_alumno, parent, false);
        return new ColumnAsistenciaAlumnoHolder(layout);
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        if(holder instanceof  ColumnAsistenciaAlumnoHolder && columnHeaderItemModel instanceof ValorTipoNotaUi )((ColumnAsistenciaAlumnoHolder)holder).bind((ValorTipoNotaUi) columnHeaderItemModel);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_fila_evaluacion_resultado, parent, false);
        return new FilaAsistenciaAlumnoHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        if(holder instanceof  FilaAsistenciaAlumnoHolder && rowHeaderItemModel instanceof AlumnosUi )((FilaAsistenciaAlumnoHolder)holder).bind((AlumnosUi) rowHeaderItemModel, rowPosition);
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_corner_evaluacion_resultado, null);

    }

    public void setCornerView(int cantidad, int m_nRowHeaderWidth, int m_nColumnHeaderHeight, String color) {
        View view = onCreateCornerView();
        ColumnaCornerAlumnosHolder viewHolder = new ColumnaCornerAlumnosHolder(view);
        getTableView().addView(view, new FrameLayout.LayoutParams(m_nRowHeaderWidth, m_nColumnHeaderHeight));
        viewHolder.bind(cantidad, color);

    }

}
