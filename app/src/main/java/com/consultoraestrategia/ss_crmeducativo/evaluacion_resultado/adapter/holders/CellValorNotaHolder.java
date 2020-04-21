package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CellValorNotaHolder  extends AbstractViewHolder {

    @BindView(R.id.valorN)
    TextView valorN;
    @BindView(R.id.nota)
    TextView nota;


    public CellValorNotaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
    public void bind(NotaUi notaUi){
     if(notaUi.getValorTipoNotaUi().getTitulo()!=null)nota.setText(String.valueOf(notaUi.getValorTipoNotaUi().getTitulo()));
     else nota.setText("");
     valorN.setText(notaUi.getNota());

    }
}
