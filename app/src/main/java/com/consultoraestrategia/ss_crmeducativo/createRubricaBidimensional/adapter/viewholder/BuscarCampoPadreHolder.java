package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuscarCampoPadreHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.text_titulo)
    TextView textTitulo;

    public BuscarCampoPadreHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


    public void bind(CampoAccionUi campoAccionUi) {
        String title = campoAccionUi.getTitle();
        textTitulo.setText(title);
    }



}
