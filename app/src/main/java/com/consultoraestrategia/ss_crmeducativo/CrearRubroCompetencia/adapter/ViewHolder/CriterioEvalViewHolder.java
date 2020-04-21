package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SCIEV on 18/10/2017.
 */

public class CriterioEvalViewHolder extends RecyclerView.ViewHolder  {
    @BindView(R.id.txt_valor)
    TextView txtValor;
    @BindView(R.id.txt_eval_descripcion)
    TextView txtEvalDescripcion;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.imgIcono)
    CircleImageView imgIcono;
    @BindView(R.id.view19)
    View view;

    public CriterioEvalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final CriterioEvalListener listener, final ValorTipoNotaUi valorTipoNotaUi, final List<ValorTipoNotaUi> valorTipoNotaUiList){
        final CriterioEvalUi criterioEvalUi = valorTipoNotaUi.getCriterioEvalUi();
        txtValor.setText(valorTipoNotaUi.getTitulo());
        txtDescripcion.setText(valorTipoNotaUi.getDescripcion());
        txtEvalDescripcion.setText(criterioEvalUi.getDescripcion());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickItemCriterioEval(criterioEvalUi, valorTipoNotaUiList);
            }
        });

        switch (valorTipoNotaUi.getTipoNota()){
            default:
                imgIcono.setVisibility(View.GONE);
                break;
            case IMAGEN:
                Glide.with(itemView.getContext())
                        .load(valorTipoNotaUi.getIcono())
                        .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                        .into(imgIcono);
                imgIcono.setVisibility(View.VISIBLE);
                break;
            case TEXTO:
                imgIcono.setVisibility(View.GONE);
                break;
        }
    }

}
