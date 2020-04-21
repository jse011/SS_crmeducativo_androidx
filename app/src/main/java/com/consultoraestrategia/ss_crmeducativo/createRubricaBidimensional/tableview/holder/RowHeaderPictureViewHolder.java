package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeaderWithPicture;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 28/02/2018.
 */

public class RowHeaderPictureViewHolder extends AbstractViewHolder {

    @BindView(R.id.img_picture)
    CircleImageView imgPicture;
    @BindView(R.id.titulo)
    TextView textTitle;
    @BindView(R.id.root)
    ConstraintLayout root;

    public RowHeaderPictureViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(RowHeaderWithPicture rowHeader) {
        String urlPicture = rowHeader.getUrl();
        String title = rowHeader.getTitle();

        /*String boldText = rowHeader.getBoldText() == null ? "" : rowHeader.getBoldText();
        String normalText = rowHeader.getTitle() == null ? "" : rowHeader.getTitle();
        SpannableString str = new SpannableString(boldText + normalText);
        str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/


        if (!TextUtils.isEmpty(title)) {
            textTitle.setText(title);
        }

        if (!TextUtils.isEmpty(urlPicture)) {
            Glide.with(itemView.getContext())
                    .load(urlPicture)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgPicture);
        }

        /*root.getLayoutParams().width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        textTitle.requestLayout();*/
    }
}
