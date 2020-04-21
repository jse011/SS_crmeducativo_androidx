package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComentarioCellViewHolder extends AbstractViewHolder {
    @BindView(R.id.imgResultado)
    ImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private OptionComentario optionComentario;

    public ComentarioCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(OptionComentario optionComentario) {
        this.optionComentario = optionComentario;
        if(TextUtils.isEmpty(optionComentario.getEvaluacionId())){
            imgResultado.setVisibility(View.GONE);
        }else {
            imgResultado.setVisibility(View.VISIBLE);
        }
    }

    public OptionComentario getOptionComentario() {
        return optionComentario;
    }
}
