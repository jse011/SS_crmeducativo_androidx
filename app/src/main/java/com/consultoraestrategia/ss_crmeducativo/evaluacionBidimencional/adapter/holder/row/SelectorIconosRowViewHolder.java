package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class SelectorIconosRowViewHolder extends RowHeaderViewHolder<ValorTipoNotaUi>
{
    @BindView(R.id.imgResultado)
    CircleImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    public SelectorIconosRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(ValorTipoNotaUi rowHeader) {
        Glide.with(itemView.getContext())
                .load(rowHeader.getIcono())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle).override(50,50))
                .into(imgResultado);
    }
}
