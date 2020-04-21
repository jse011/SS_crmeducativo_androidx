package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.layoutmanager.CellLayoutManager;
import com.evrencoskun.tableview.layoutmanager.ColumnHeaderLayoutManager;

public class TableViewEvaluacion extends TableView {
    private int mRowHeaderWidth;
    private int mColumnHeaderHeight;
    private int mSelectedColor;
    private int mUnSelectedColor;
    private int mShadowColor;
    private int mSeparatorColor;
    private boolean mShowVerticalSeparators;
    private boolean mShowHorizontalSeparators;

    public TableViewEvaluacion(@NonNull Context context) {
        super(context);
        initialDefaultValues(null);
    }

    public TableViewEvaluacion(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialDefaultValues(attrs);
    }

    public TableViewEvaluacion(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialDefaultValues(null);
    }

    public void refreshColum() {


        for (int i = 0; i < getColumnHeaderRecyclerView().getAdapter().getItemCount(); i++) {
            // set cache width for single cell item.
            int cacheWidth = getColumnHeaderLayoutManager().getCacheWidth(i);
            getColumnHeaderLayoutManager().setCacheWidth(i, cacheWidth);
            getCellLayoutManager().setCacheWidth(i, cacheWidth);

        }

        //refreshColumnHeaderRecyclerView();
        //refreshRowHeaderRecyclerView();
        //refreshCellRecyclerView();

    }

    private void refreshRowHeaderRecyclerView(){
        // Set layout manager
        LinearLayoutManager mRowHeaderLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager
                .VERTICAL, false);

        getRowHeaderRecyclerView().setLayoutManager(mRowHeaderLayoutManager);

        // Set layout params
        LayoutParams layoutParams = new LayoutParams(mRowHeaderWidth, LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = mColumnHeaderHeight;
        getRowHeaderRecyclerView().setLayoutParams(layoutParams);
    }

    private void refreshColumnHeaderRecyclerView(){
        // Set layout manager
        ColumnHeaderLayoutManager mColumnHeaderLayoutManager = new ColumnHeaderLayoutManager(getContext(), this);

        getColumnHeaderRecyclerView().setLayoutManager(mColumnHeaderLayoutManager);

        // Set layout params
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                mColumnHeaderHeight);
        layoutParams.leftMargin = mRowHeaderWidth;
        getColumnHeaderRecyclerView().setLayoutParams(layoutParams);

    }

    private void refreshCellRecyclerView(){
        // Set layout manager

        CellLayoutManager mCellLayoutManager = new CellLayoutManager(getContext(), this);
        getCellRecyclerView().setLayoutManager(mCellLayoutManager);

        // Set layout params
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams
                .WRAP_CONTENT);
        layoutParams.leftMargin = mRowHeaderWidth;
        layoutParams.topMargin = mColumnHeaderHeight;
        getCellRecyclerView().setLayoutParams(layoutParams);
    }

    private void initialDefaultValues(AttributeSet attrs) {
        // Dimensions
        mRowHeaderWidth = (int) getResources().getDimension(com.evrencoskun.tableview.R.dimen.default_row_header_width);
        mColumnHeaderHeight = (int) getResources().getDimension(com.evrencoskun.tableview.R.dimen
                .default_column_header_height);

        // Colors
        mSelectedColor = ContextCompat.getColor(getContext(), com.evrencoskun.tableview.R.color
                .table_view_default_selected_background_color);
        mUnSelectedColor = ContextCompat.getColor(getContext(), com.evrencoskun.tableview.R.color
                .table_view_default_unselected_background_color);
        mShadowColor = ContextCompat.getColor(getContext(), com.evrencoskun.tableview.R.color
                .table_view_default_shadow_background_color);

        if (attrs == null) {
            // That means TableView is created programmatically.
            return;
        }

        // Get values from xml attributes
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, com.evrencoskun.tableview.R.styleable
                .TableView, 0, 0);
        try {
            // Dimensions
            mRowHeaderWidth = (int) a.getDimension(com.evrencoskun.tableview.R.styleable.TableView_row_header_width,
                    mRowHeaderWidth);
            mColumnHeaderHeight = (int) a.getDimension(com.evrencoskun.tableview.R.styleable
                    .TableView_column_header_height, mColumnHeaderHeight);

            // Colors
            mSelectedColor = a.getColor(com.evrencoskun.tableview.R.styleable.TableView_selected_color, mSelectedColor);
            mUnSelectedColor = a.getColor(com.evrencoskun.tableview.R.styleable.TableView_unselected_color, mUnSelectedColor);
            mShadowColor = a.getColor(com.evrencoskun.tableview.R.styleable.TableView_shadow_color, mShadowColor);
            mSeparatorColor = a.getColor(com.evrencoskun.tableview.R.styleable.TableView_separator_color, ContextCompat
                    .getColor(getContext(), com.evrencoskun.tableview.R.color.table_view_default_separator_color));

            // Booleans
            mShowVerticalSeparators = a.getBoolean(com.evrencoskun.tableview.R.styleable.TableView_show_vertical_separator,
                    mShowVerticalSeparators);
            mShowHorizontalSeparators = a.getBoolean(com.evrencoskun.tableview.R.styleable
                    .TableView_show_horizontal_separator, mShowHorizontalSeparators);

        } finally {
            a.recycle();
        }
    }


}
