package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public  class FirstBodyCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstBodyBinder<Object> {

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
    public Context context;
    private Object item;

    public FirstBodyCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FirstBodyCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_firstbody, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindFirstBody(Object item, int row) {
        this.item = item;
        if(item instanceof AlumnosEvaluacionUi){
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) item;
            txtTitle.setText(alumnosEvaluacionUi.getName());
            txtSubtittle.setText(alumnosEvaluacionUi.getLastName());
            txtSubtittle.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(alumnosEvaluacionUi.getFotoPerfil())
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle).override(50,50))
                    .into(imgProfile);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, getResources().getDisplayMetrics());
            imgProfile.getLayoutParams().width = width;
            imgProfile.getLayoutParams().height = height;
            inciarDimension();
            if(!alumnosEvaluacionUi.isVigente()){
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_red_50));
            }else {
                root.setBackgroundColor(Color.WHITE);
            }

        }else if(item instanceof GrupoEvaluacionUi){
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi)item;
            String nombre = grupoEvaluacionUi.getName();
            if(nombre != null){
                nombre = nombre.toUpperCase();
            }
            txtTitle.setText(nombre);
            txtSubtittle.setVisibility(View.GONE);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            imgProfile.getLayoutParams().width = width;
            imgProfile.getLayoutParams().height = height;
            Drawable myDrawable = getResources().getDrawable(R.drawable.toggle_ver);
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

        }


    }

    private void inciarDimension(){
        int colorFondo;
        try {
            DimensionObservadaUi dimensionObservadaUi = ((AlumnosEvaluacionUi)item).getDimensionObservadaUi();
            DimensionUi dimensionUi = dimensionObservadaUi.getDimensionUi();
            colorFondo = Color.parseColor(dimensionUi.getColor());
        }catch (Exception e){
            e.printStackTrace();
            colorFondo = Color.WHITE;
        }

        Drawable circle = ContextCompat.getDrawable(txtTrinagulo.getContext(), R.drawable.ic_side_triangle);
        circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
        txtTrinagulo.setBackground(circle);
    }
}

