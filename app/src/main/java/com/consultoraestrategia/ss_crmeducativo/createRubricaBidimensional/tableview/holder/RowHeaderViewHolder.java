package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder;

import android.graphics.Typeface;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 9/02/2018.
 */

public class RowHeaderViewHolder extends AbstractViewHolder {

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.root)
    ConstraintLayout root;


    public RowHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(RowHeader rowHeader) {
        // Get the holder to update row header item text

        String boldText = rowHeader.getBoldText() == null ? "" : rowHeader.getBoldText();
        String normalText = rowHeader.getTitle() == null ? "" : rowHeader.getTitle();
        SpannableString str = new SpannableString(boldText + normalText);
        str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textTitle.setText(str);
        /*root.getLayoutParams().height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        textTitle.requestLayout();*/
    }
}
