package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.DesempenioUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 13/02/2018.
 */

public class ItemDesempenioViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.view10)
    View view10;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.txtDescripcion)
    TextView txtDescripcion;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.txt_indicador)
    TextView txtIndicador;
    public ItemDesempenioViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

    public void bind(DesempenioUi desempenioUi) {
        txtDescripcion.setText(desempenioUi.getDescripcion());
        txtIndicador.setText(desempenioUi.getIcd());
        if(desempenioUi.isOcultaCabecera()){
            textView3.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);
        }else {
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
        }
    }
}
