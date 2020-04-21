package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaTipoCompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class NotaTipoCompetenciaCellViewHolder extends CellViewHolder<NotaTipoCompetenciaUi> {
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.root)
    ConstraintLayout root;
    public NotaTipoCompetenciaCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(NotaTipoCompetenciaUi cell) {
        String nota = "0";
        if(cell.getNota() != 0){
            nota = String.valueOf((int)cell.getNota());
        }
        txtNotaEvaluacion.setText(nota);
        switch (cell.getTipoCompenciaEnum()){
            case COMPETENCIA_BASE:
                switch (cell.getTipoIndicadorUi()){
                    case SABER:
                        Glide.with(itemView.getContext()).load(cell.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple()).into(imageView);
                        break;
                    case HACER:
                        Glide.with(itemView.getContext()).load(cell.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple()).into(imageView);
                        break;
                    case SER:
                        Glide.with(itemView.getContext()).load(cell.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple()).into(imageView);
                        break;
                    default:
                        imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_base));
                        break;
                }
                break;
            case COMPETENCIA_ENFQ:
                imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_enfoque_1));
                break;
            case COMPETENCIA_TRANS:
                imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_transversal));
                break;
        }
    }
}
