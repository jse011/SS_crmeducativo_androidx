package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.viewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.listener.IndicadorListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderIndicador extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.txt_inidicador)
    TextView txtInidicador;

    public ViewHolderIndicador(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(final IndicadorUi indicadorUi, final IndicadorListener indicadorListener){
        txtCount.setText(indicadorUi.getSelector());
        txtInidicador.setText(indicadorUi.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicadorListener.onInidicadorSelect(indicadorUi);
            }
        });
    }


}
