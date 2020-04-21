package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class NotaCellViewHolder extends CellViewHolder<NotaUi> {
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.img_publicar)
    ImageView imgPublicar;

    public NotaCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(NotaUi cell) {
        String nota = String.format("%.1f", 0.0);
        if (cell.getNota() != 0) {
            nota = String.format("%.1f", cell.getNota());
        }
        txtNotaEvaluacion.setText(nota);
        if (cell.isPublicar()) imgPublicar.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
        else  imgPublicar.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada));

        if(cell.isPublicarVisible())imgPublicar.setVisibility(View.VISIBLE);
        else imgPublicar.setVisibility(View.GONE);
    }
}
