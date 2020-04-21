package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.Nivel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class CreateRubBidHeaderHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSub)
    TextView txtSub;
    @BindView(R.id.root)
    ConstraintLayout layout;

    public CreateRubBidHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Nivel nivel) {
        int bgColor = Color.WHITE;
        int txtColor = Color.BLACK;
        try {
            bgColor = Color.parseColor(nivel.getBgColor());
            txtColor = Color.parseColor(nivel.getTxtColor());
        }catch (Exception ignored){}

        layout.setBackgroundColor(bgColor);
        txtTitle.setTextColor(txtColor);
        txtSub.setTextColor(txtColor);

        txtTitle.setText(nivel.getTitulo());
        txtSub.setText(nivel.getSubtitulo());
    }
}
