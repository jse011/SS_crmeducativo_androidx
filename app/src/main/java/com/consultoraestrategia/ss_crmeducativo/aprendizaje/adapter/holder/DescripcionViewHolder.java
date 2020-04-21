package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardSesionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class DescripcionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.txt_proposito)
    TextView txtProposito;

    public DescripcionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(CardSesionUi sesionUi) {
        txtTitulo.setText(sesionUi.getTitulo());
        String proposito = "<b>Propósito:</b> " + sesionUi.getProposito();
        txtProposito.setText(Html.fromHtml(proposito));
    }
}
