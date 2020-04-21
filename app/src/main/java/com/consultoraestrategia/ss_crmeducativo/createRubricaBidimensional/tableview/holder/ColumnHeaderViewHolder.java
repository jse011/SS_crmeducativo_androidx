package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 9/02/2018.
 */

public class ColumnHeaderViewHolder extends AbstractSorterViewHolder {

    private static final String TAG = ColumnHeaderViewHolder.class.getSimpleName();

    @BindView(R.id.txtTitle)
    public TextView txtTitle;
    @BindView(R.id.textSubtitle)
    public TextView textSubtitle;
    @BindView(R.id.view_fondo)
    public View viewFondo;

    @BindView(R.id.root)
    public ConstraintLayout root;

    public ColumnHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * This method is calling from onBindColumnHeaderHolder on TableViewAdapter
     */
    public void bind(ColumnHeader columnHeader) {

        txtTitle.setText(columnHeader.getTitle());
        textSubtitle.setText(columnHeader.getSubtitle());


        @ColorRes int colorBg = columnHeader.getBackgroundColor();
        Log.d(TAG, "colorBg: " + colorBg);
        viewFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), colorBg != 0 ? colorBg : R.color.md_blue_grey_900));

        int textColor = ContextCompat.getColor(itemView.getContext(), columnHeader.getTextColor() != 0 ? columnHeader.getTextColor() : R.color.md_grey_500);
        if (textColor != 0) {
            txtTitle.setTextColor(textColor);
            textSubtitle.setTextColor(textColor);
        }


        root.getLayoutParams().width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        txtTitle.requestLayout();
        textSubtitle.requestLayout();
    }
}
