package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublicarCellViewHolder extends AbstractViewHolder {
    @BindView(R.id.imgResultado)
    ImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private OptionPublicar optionPublicar;

    public PublicarCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(OptionPublicar optionPublicar){
        this.optionPublicar = optionPublicar;

        if(TextUtils.isEmpty(optionPublicar.getEvaluacionId())){
            imgResultado.setVisibility(View.GONE);
        }else {
            imgResultado.setVisibility(View.VISIBLE);
        }

        if (optionPublicar.isSelected()) {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
        }else {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada));
        }
    }


    public void clickView(OptionPublicar optionPublicar) {

        if(TextUtils.isEmpty(optionPublicar.getEvaluacionId())){
            imgResultado.setVisibility(View.GONE);
        }else {
            imgResultado.setVisibility(View.VISIBLE);
        }

        if (!optionPublicar.isSelected()) {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
        }else {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada));
        }
    }

    public OptionPublicar getOptionPublicar() {
        return optionPublicar;
    }
}
