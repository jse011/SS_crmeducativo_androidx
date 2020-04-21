package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column;

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
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class AlumnoColumnViewHolder extends ColumnHeaderViewHolder<AlumnoProcesoUi> {
    private final GlideImageLoader imageLoader;
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txt_numeracion)
    TextView txtNumeracion;
    @BindView(R.id.txtSubtittle)
    TextView txtSubtittle;
    @BindView(R.id.root)
    ConstraintLayout root;
    AlumnoProcesoUi alumnoProcesoUi;
    @BindView(R.id.txt_trinagulo)
    ImageView txtTrinagulo;
    @BindView(R.id.img_mensaje)
    ImageView imgMensaje;

    public AlumnoColumnViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        imageLoader = new GlideImageLoader(itemView.getContext());
    }

    @Override
    public void bind(AlumnoProcesoUi columnHeader) {
        alumnoProcesoUi = columnHeader;
        txtNumeracion.setText(String.valueOf(columnHeader.getPocision()));
        txtTitle.setText(columnHeader.getNombre());
        txtSubtittle.setText(columnHeader.getApellidos());
        txtSubtittle.setVisibility(View.VISIBLE);
        imageLoader.load(imgProfile, columnHeader.getUrlProfile());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, itemView.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, itemView.getResources().getDisplayMetrics());
        imgProfile.getLayoutParams().width = width;
        imgProfile.getLayoutParams().height = height;
        inciarDimension();
        iniciarComentario();
        int color = ContextCompat.getColor(itemView.getContext(), R.color.white);
        if(!columnHeader.isVigente())color= ContextCompat.getColor(itemView.getContext(), R.color.md_red_50);
        root.setBackgroundColor(color);
    }

    private void iniciarComentario() {
        if(alumnoProcesoUi.isComentario()){
            imgMensaje.setVisibility(View.VISIBLE);
        }else {
            imgMensaje.setVisibility(View.GONE);
        }
    }

    private void inciarDimension(){
        int colorFondo = Color.WHITE;
        try {
        DimensionObservadaUi dimensionObservadaUi = alumnoProcesoUi.getDimensionObservadaUi();
        DimensionUi dimensionUi = null;
        if(dimensionObservadaUi!=null)dimensionUi = dimensionObservadaUi.getDimensionUi();
        if(dimensionUi!=null)colorFondo = Color.parseColor(dimensionUi.getColor());
        }catch (Exception e){
            e.printStackTrace();
            colorFondo = Color.WHITE;
        }

        Drawable circle = ContextCompat.getDrawable(txtTrinagulo.getContext(), R.drawable.ic_side_triangle);
        if(circle!=null)circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
        txtTrinagulo.setBackground(circle);
    }

    public AlumnoProcesoUi getAlumnoProcesoUi() {
        return alumnoProcesoUi;
    }
}
