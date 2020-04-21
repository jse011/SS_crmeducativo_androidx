package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.IcdsUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 13/02/2018.
 */

public class ItemIcdsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.txt_indicador)
    TextView txtIndicador;
    public ItemIcdsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

    public void bind(IcdsUi icdsUi) {
        txtIndicador.setText(icdsUi.getTitulo());

    }
}
