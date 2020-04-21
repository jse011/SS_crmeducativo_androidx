package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class SelectorIconosCellViewHolder extends CellViewHolder<EvalProcUi> {
    @BindView(R.id.imgResultado)
    CircleImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private EvalProcUi evalProcUi;

    public SelectorIconosCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(EvalProcUi cell) {
        this.evalProcUi = cell;
        if(StringUtils.isNullOrEmpty(cell.getEscala()) || StringUtils.isNullOrEmpty(cell.getEscala())){
            imgResultado.setVisibility(View.GONE);
        }else{
            imgResultado.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext())
                    .load(cell.getEscala())
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle).override(50,50))
                    .into(imgResultado);
        }
    }

    public EvalProcUi getEvalProcUi() {
        return evalProcUi;
    }

}
