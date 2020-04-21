package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.row;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class GrupoRowViewHolder extends AbstractViewHolder {
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubtittle)
    TextView txtSubtittle;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView((R.id.txt_trinagulo))
    ImageView txtTrinagulo;
    private GrupoEvaluacionUi grupoEvaluacionUi;

    public GrupoEvaluacionUi getGrupoEvaluacionUi() {
        return grupoEvaluacionUi;
    }

    public GrupoRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(GrupoEvaluacionUi grupoEvaluacionUi) {
        this.grupoEvaluacionUi = grupoEvaluacionUi;
        String nombre = grupoEvaluacionUi.getName();
        if(nombre != null){
            nombre = nombre.toUpperCase();
        }
        txtTitle.setText(nombre);
        txtSubtittle.setVisibility(View.GONE);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, imgProfile.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, imgProfile.getResources().getDisplayMetrics());
        imgProfile.getLayoutParams().width = width;
        imgProfile.getLayoutParams().height = height;
        Drawable myDrawable = imgProfile.getResources().getDrawable(R.drawable.toggle_ver);
        imgProfile.setImageDrawable(myDrawable);

        if (grupoEvaluacionUi.isTogle()) {
            imgProfile.clearAnimation();
            imgProfile.setRotation(180);
            imgProfile.setSelected(false);
        } else {
            imgProfile.clearAnimation();
            imgProfile.setRotation(0);
            //ViewCompat.animate(imgProfile).rotation(180).start();
            imgProfile.setSelected(true);
        }

        Drawable circle = ContextCompat.getDrawable(txtTrinagulo.getContext(), R.drawable.ic_side_triangle);
        circle.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        txtTrinagulo.setBackground(circle);
    }

}
