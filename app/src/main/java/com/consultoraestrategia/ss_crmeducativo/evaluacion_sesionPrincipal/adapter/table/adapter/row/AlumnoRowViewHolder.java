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

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AlumnoRowViewHolder  extends AbstractViewHolder {
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
    private AlumnosEvaluacionUi alumnosEvaluacionUi;

    public AlumnoRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(AlumnosEvaluacionUi alumnosEvaluacionUi) {
        this.alumnosEvaluacionUi = alumnosEvaluacionUi;
        txtTitle.setText(alumnosEvaluacionUi.getName());
        txtSubtittle.setText(alumnosEvaluacionUi.getLastName());
        txtSubtittle.setVisibility(View.VISIBLE);
        Glide.with(imgProfile)
                .load(alumnosEvaluacionUi.getFotoPerfil())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle).override(50,50))
                .into(imgProfile);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, imgProfile.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, imgProfile.getResources().getDisplayMetrics());
        imgProfile.getLayoutParams().width = width;
        imgProfile.getLayoutParams().height = height;
        inciarDimension();
        if(!alumnosEvaluacionUi.isVigente()){
            root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_red_50));
        }else {
            root.setBackgroundColor(Color.WHITE);
        }

    }

    private void inciarDimension(){
        int colorFondo = Color.WHITE;
        try {
            DimensionObservadaUi dimensionObservadaUi =  alumnosEvaluacionUi.getDimensionObservadaUi();
            if(dimensionObservadaUi!=null){
                DimensionUi dimensionUi = dimensionObservadaUi.getDimensionUi();
                colorFondo = Color.parseColor(dimensionUi.getColor());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Drawable circle = ContextCompat.getDrawable(txtTrinagulo.getContext(), R.drawable.ic_side_triangle);
        circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
        txtTrinagulo.setBackground(circle);
    }

    public AlumnosEvaluacionUi getAlumnosEvaluacionUi() {
        return alumnosEvaluacionUi;
    }
}
