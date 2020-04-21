package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
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
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class FirstBodyCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstBodyBinder<AlumnosEvaluacionUi> {


    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubtittle)
    TextView txtSubtittle;
    @BindView(R.id.root)
    ConstraintLayout root;
    AlumnosEvaluacionUi alumnosEvaluacionUi;
    @BindView(R.id.txt_trinagulo)
    ImageView txtTrinagulo;
    @BindView(R.id.txt_numero)
    TextView txtNumero;
    private Context context;

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
    public void bindFirstBody(AlumnosEvaluacionUi item, int row) {
        this.alumnosEvaluacionUi = item;
        txtTitle.setText(item.getName());
        txtSubtittle.setText(item.getLastName());
        Glide.with(context)
                .load(item.getFotoPerfil())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                .into(imgProfile);
        txtNumero.setText(String.valueOf(row + 1));
        if(!alumnosEvaluacionUi.isVigente()){
            root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_red_50));
        }else {
            root.setBackgroundColor(Color.WHITE);
        }
        /*textView.setTypeface(null, Typeface.BOLD_ITALIC);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        vg_root.setBackgroundResource(R.drawable.cell_gray_border_bottom_right_gray);*/

        inciarDimension();
    }

    private void inciarDimension() {
        int colorFondo;
        try {
            DimensionObservadaUi dimensionObservadaUi = alumnosEvaluacionUi.getDimensionObservadaUi();
            DimensionUi dimensionUi = dimensionObservadaUi.getDimensionUi();
            if (dimensionUi == null) return;
            colorFondo = Color.parseColor(dimensionUi.getColor());
        } catch (Exception e) {
            e.printStackTrace();
            colorFondo = Color.WHITE;
        }

        Drawable circle = ContextCompat.getDrawable(txtTrinagulo.getContext(), R.drawable.ic_side_triangle);
        circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
        txtTrinagulo.setBackground(circle);
    }
}

