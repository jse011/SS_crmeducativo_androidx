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
    @BindView(R.id.valorN2)
    TextView valorN2;


    public CellValorNotaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
    public void bind(NotaUi notaUi){
     if(notaUi.getValorTipoNotaUi()!=null){
         nota.setText(String.valueOf(notaUi.getValorTipoNotaUi().getTitulo()));
         nota.setVisibility(View.VISIBLE);
         valorN.setVisibility(View.VISIBLE);
         valorN2.setVisibility(View.GONE);
         valorN.setText(notaUi.getNota());
     } else {
         nota.setText("");
         nota.setVisibility(View.GONE);
         valorN.setVisibility(View.GONE);
         valorN2.setVisibility(View.VISIBLE);
         valorN2.setText(notaUi.getNota());
     }



    }
}
