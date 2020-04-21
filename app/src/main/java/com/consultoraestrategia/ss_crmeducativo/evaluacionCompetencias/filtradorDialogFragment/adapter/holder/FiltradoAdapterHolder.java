package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.listener.FiltradoCheckItemListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 11/04/2018.
 */

public class FiltradoAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.checkTipos)
    RadioButton checkBox;
    @BindView(R.id.itemVistas)
    ConstraintLayout itemVistas;
    @BindView(R.id.textViewTitulo)
    TextView textViewTitulo;
    private FiltradoUi filtradoUi;
    private FiltradoCheckItemListener listener;

    public FiltradoAdapterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemVistas.setOnClickListener(this);
    }

    public void bind(final FiltradoUi filtradoUi, final FiltradoCheckItemListener listener) {
        if (listener != null) {
            this.filtradoUi = filtradoUi;
            this.listener = listener;
            textViewTitulo.setText(filtradoUi.getTipo().getNombre());
        }
        if (filtradoUi.isaBooleanTemporal()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.itemVistas:
                validarCheck(filtradoUi);
                break;
            default:
                break;
        }
    }

    private void validarCheck(FiltradoUi filtradoUi) {
        listener.filtradoCheckItemListener(filtradoUi);
    }
}
