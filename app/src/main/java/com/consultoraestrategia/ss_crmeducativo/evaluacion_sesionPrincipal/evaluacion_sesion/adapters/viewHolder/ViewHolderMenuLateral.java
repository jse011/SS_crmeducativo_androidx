package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters.viewHolder;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.listener.RubroEvaluacionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderMenuLateral extends RecyclerView.ViewHolder {

    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.textTitle)
    TextView textTitle;

    public ViewHolderMenuLateral(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(final RubroEvaluacionListener listener, final RubroEvaluacionUi rubro) {
        boolean isSelected = rubro.isStatus();
        int color = R.color.white;
        if (isSelected) {
            root.setBackgroundResource(R.drawable.ic_fondo_calendario_selected);
            color = R.color.black;
        } else {
            root.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.holo_blue_dark));
            color = R.color.white;
        }
        textTitle.setTextColor(ContextCompat.getColor(itemView.getContext(), color));
        textTitle.setText(rubro.getTitulo());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemSelectRubro(rubro);
                }
            }
        });
    }
}
