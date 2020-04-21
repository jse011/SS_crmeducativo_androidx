package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ValorTipoNotaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_valor)
    TextView txtValor;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.imgIcono)
    CircleImageView imgIcono;

    
    public ValorTipoNotaViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(final ValorTipoNotaUi valorTipoNotaUi) {
        Log.d("valorTipoNota", valorTipoNotaUi.getIcono()+valorTipoNotaUi.getTitle()+valorTipoNotaUi.getAlias());

        switch (valorTipoNotaUi.getTipoNotaUi().getTipo()){
            default:
                imgIcono.setVisibility(View.GONE);
                break;
            case SELECTOR_ICONOS:
                txtValor.setVisibility(View.GONE);
                imgIcono.setVisibility(View.VISIBLE);
                txtDescripcion.setText(valorTipoNotaUi.getAlias());
                Glide.with(itemView.getContext())
                        .load(valorTipoNotaUi.getIcono())
                        .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                        .into(imgIcono);
                imgIcono.setVisibility(View.VISIBLE);
                break;
            case SELECTOR_VALORES:
                imgIcono.setVisibility(View.GONE);
                txtValor.setVisibility(View.VISIBLE);
                txtValor.setText(valorTipoNotaUi.getTitle());
                txtDescripcion.setText(valorTipoNotaUi.getAlias());
                break;
        }
    }
}
