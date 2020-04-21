package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;
import com.consultoraestrategia.ss_crmeducativo.util.CircularTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectoPresicionHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.nav_bar_letra_profile)
    CircularTextView navBarLetraProfile;
    private NotaCircularUi notaCircularUi;
    public DirectoPresicionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(NotaCircularUi notaCircularUi){
        this.notaCircularUi = notaCircularUi;
        navBarLetraProfile.setText(notaCircularUi.getContenido());
        try {
            navBarLetraProfile.setStrokeWidth(2);
            navBarLetraProfile.setSolidColor("#ffffff");
            navBarLetraProfile.setStrokeColor(notaCircularUi.getContornoColor());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public NotaCircularUi getNotaCircularUi() {
        return notaCircularUi;
    }
}
