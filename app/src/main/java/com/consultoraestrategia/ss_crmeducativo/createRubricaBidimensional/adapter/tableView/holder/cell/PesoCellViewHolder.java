package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by SCIEV on 8/03/2018.
 */

public class PesoCellViewHolder extends CellViewHolder<IndicadorUi> {
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.root)
    ConstraintLayout root;
    private IndicadorUi indicadorUi;
    public PesoCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IndicadorUi cell) {
        this.indicadorUi = cell;
        txtNotaEvaluacion.setText(cell.getPeso());
        CompetenciaUi competenciaUi = cell.getCompetenciaOwner();
        if(competenciaUi == null)return;
        if(competenciaUi.isBase()){
            switch (cell.getTipoIndicadorUi()){
                case SABER:
                    setImagen();
                    break;
                case HACER:
                    setImagen();
                    break;
                case SER:
                    setImagen();
                    break;
                default:
                    imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_base));
                    break;
            }
        }
        if(competenciaUi.isTrans()){
            imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_transversal));
        }
        if(competenciaUi.isEnfoque()){
            imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_enfoque_1));
        }
    }

    public void setImagen(){
        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_base)).into(imageView);
    }
    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }
}
