package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CapacidadUi;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderCapacidad extends SectioningAdapter.ItemViewHolder {
    @BindView(R.id.txtTitle)
    TextView txtTitle;

    public ViewHolderCapacidad(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CapacidadUi capacidadUi) {
        txtTitle.setText(capacidadUi.getTitulo());
    }
}
