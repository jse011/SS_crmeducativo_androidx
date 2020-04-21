package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto.FilasViewHolder;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 11/05/2018.
 */

public class FilasAlumnosHolder extends FilasViewHolder<AlumnosUi> {
    @BindView(R.id.txtTitle)
    TextView textViewNombre;
    @BindView(R.id.txtSubtittle)
    TextView textViewApellidos;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    private AlumnosUi alumnosUi;
    @BindView(R.id.root)
    ConstraintLayout root;

    public FilasAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(AlumnosUi alumnosUi) {
        this.alumnosUi = alumnosUi;
        textViewNombre.setText(alumnosUi.getName());
        textViewApellidos.setText(alumnosUi.getLastName());
        Glide.with(itemView.getContext())
                .load(alumnosUi.getUrlProfile())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                .into(imgProfile);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, itemView.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, itemView.getResources().getDisplayMetrics());
        imgProfile.getLayoutParams().width = width;
        imgProfile.getLayoutParams().height = height;
        int color = ContextCompat.getColor(itemView.getContext(), R.color.white);
        if(!alumnosUi.isVigente())color= ContextCompat.getColor(itemView.getContext(), R.color.md_red_50);
        root.setBackgroundColor(color);
    }

    public AlumnosUi getAlumnosUi() {
        return alumnosUi;
    }
}
