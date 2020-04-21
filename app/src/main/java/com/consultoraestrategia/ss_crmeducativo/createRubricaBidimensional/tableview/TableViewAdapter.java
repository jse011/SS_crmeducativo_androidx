package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder.CellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder.ColumnHeaderViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder.RowHeaderPictureViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder.RowHeaderViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder.SelectorImagenesColumnHeaderViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder.SelectorValoresColumnHeaderViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeaderWithPicture;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ColumnHeaderTipoEnumUi;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by @stevecampos on 9/02/2018.
 */

public class TableViewAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {

    private static final String TAG = TableViewAdapter.class.getSimpleName();

    public TableViewAdapter(Context p_jContext) {
        super(p_jContext);
    }

    public final int COLUM_SELECTOR_VALORES = 1, COLUM_SELECTOR_IMAGENES = 2, COLUM_SELECTOR_DEFECTO = 3;
    @Override
    public int getColumnHeaderItemViewType(int position) {
        int itemViewType = COLUM_SELECTOR_DEFECTO;
        ColumnHeader columnHeader = mColumnHeaderItems.get(position);
        if(columnHeader instanceof ColumnHeaderTipoEnumUi){
            ColumnHeaderTipoEnumUi columnHeaderTipoEnumUi = (ColumnHeaderTipoEnumUi) columnHeader;
            switch (columnHeaderTipoEnumUi.getTipoEnumUi()){
                case SELECTOR_VALORES:
                    itemViewType = COLUM_SELECTOR_VALORES;
                    break;
                case SELECTOR_IMGENES:
                    itemViewType = COLUM_SELECTOR_IMAGENES;
                    break;
            }
        }
        return itemViewType;
    }

    public static final int ROWHEADER_BOLD = 100;
    public static final int ROWHEADER_WITH_PICTURE = 101;

    @Override
    public int getRowHeaderItemViewType(int position) {
        int itemViewType = ROWHEADER_BOLD;
        RowHeader rowHeader = mRowHeaderItems.get(position);
        if (rowHeader instanceof RowHeaderWithPicture) {
            itemViewType = ROWHEADER_WITH_PICTURE;
        }
        return itemViewType;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        // Get cell xml layout
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_layout,
                parent, false);
        // Create a Custom ViewHolder for a Cell item.
        return new CellViewHolder(layout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
        Cell cell = (Cell) p_jValue;
        // Get the holder to update cell item text
        CellViewHolder viewHolder = (CellViewHolder) holder;
        viewHolder.bind(cell);
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        // Get Column Header xml Layout
        View layout;
        AbstractViewHolder holder;
        switch (viewType){
            case COLUM_SELECTOR_VALORES:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .table_view_column_header_layout, parent, false);
                holder = new SelectorValoresColumnHeaderViewHolder(layout);
                break;
            case COLUM_SELECTOR_IMAGENES:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .table_view_column_header_layout, parent, false);
                holder = new SelectorImagenesColumnHeaderViewHolder(layout);
                break;
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .table_view_column_header_layout, parent, false);
                holder = new ColumnHeaderViewHolder(layout);
                break;
        }
        // Create a ColumnHeader ViewHolder
        return holder;
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        ColumnHeader columnHeader = (ColumnHeader) p_jValue;
        if(holder instanceof SelectorValoresColumnHeaderViewHolder && p_jValue instanceof ColumnHeaderTipoEnumUi){
            SelectorValoresColumnHeaderViewHolder viewHolder = (SelectorValoresColumnHeaderViewHolder) holder;
            viewHolder.bind(columnHeader);
        }else if(holder instanceof SelectorImagenesColumnHeaderViewHolder && p_jValue instanceof ColumnHeaderTipoEnumUi){
            SelectorImagenesColumnHeaderViewHolder viewHolder = (SelectorImagenesColumnHeaderViewHolder) holder;
            viewHolder.bind(columnHeader);
        }else if(holder instanceof ColumnHeaderViewHolder){
            // Get the holder to update cell item text
            ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
            columnHeaderViewHolder.bind(columnHeader);
        }

    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        // Get Row Header xml Layout
        AbstractViewHolder holder = null;
        switch (viewType) {
            default:
                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .table_view_row_header_layout, parent, false);
                holder = new RowHeaderViewHolder(layout);
                break;
            case ROWHEADER_WITH_PICTURE:
                View layoutWithPicture = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .table_view_row_header_picture_layout, parent, false);
                holder = new RowHeaderPictureViewHolder(layoutWithPicture);
                break;
        }

        // Create a Row Header ViewHolder
        return holder;
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        RowHeader rowHeader = (RowHeader) p_jValue;
        // Get the holder to update row header item text
        if (rowHeader instanceof RowHeaderWithPicture) {
            RowHeaderPictureViewHolder viewHolder = (RowHeaderPictureViewHolder) holder;
            viewHolder.bind((RowHeaderWithPicture) rowHeader);
        } else {
            RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
            rowHeaderViewHolder.bind(rowHeader);
        }
    }

    @Override
    public View onCreateCornerView() {
        // Get Corner xml layout
        return LayoutInflater.from(mContext).inflate(R.layout.table_view_corner_layout, null);
    }
}
