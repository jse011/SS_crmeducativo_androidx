package com.consultoraestrategia.ss_crmeducativo.programahorario.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder.CeldaHorarioHolder;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder.FilaBanerHolder;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder.FilaHoraHolder;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder.ColumnaDiaHolder;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoHorarioDiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class ProgramaHorarioTableAdapter extends AbstractTableAdapter<DiaUi, HoraUi,Object> {

    private static final int FILA_HORAS = 1, FILA_BANNER = 2;

    private View.OnClickListener listener;
    public ProgramaHorarioTableAdapter(Context p_jContext, View.OnClickListener listener) {
        super(p_jContext);
        this.listener = listener;
    }


    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;

    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        HoraUi o = mRowHeaderItems.get(position);
        if(o instanceof BanerUi)return FILA_BANNER;
        else return FILA_HORAS;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_horario, parent, false);
        return new CeldaHorarioHolder(layout);
    }


    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
        CeldaHorarioHolder celdaHorarioHolder = (CeldaHorarioHolder)holder;
        if(p_jValue instanceof CursoHorarioDiaUi)celdaHorarioHolder.bindHorario(((CursoHorarioDiaUi)p_jValue),p_nYPosition);
        else if(p_jValue instanceof BanerUi)celdaHorarioHolder.bindBanner(((BanerUi)p_jValue));

    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_horario_fila_dia, parent, false);
        return new ColumnaDiaHolder(layout);
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        ((ColumnaDiaHolder)holder).bind((DiaUi)p_jValue);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case FILA_HORAS:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_horario_column_hora, parent, false);
                return new FilaHoraHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_horario_column_banner, parent, false);
                return new FilaBanerHolder(layout);
        }
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        if(holder instanceof FilaBanerHolder && p_jValue instanceof BanerUi)((FilaBanerHolder)holder).bind((BanerUi)p_jValue);
        else if(holder instanceof FilaHoraHolder && p_jValue instanceof HoraUi)((FilaHoraHolder)holder).bind(((HoraUi)p_jValue), p_nYPosition);
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_horario_corner, null);
    }

    public void setCornerView(int m_nRowHeaderWidth, int m_nColumnHeaderHeight) {
        View view = onCreateCornerView();
        getTableView().addView(view, new FrameLayout.LayoutParams(m_nRowHeaderWidth, m_nColumnHeaderHeight));
    }



}
