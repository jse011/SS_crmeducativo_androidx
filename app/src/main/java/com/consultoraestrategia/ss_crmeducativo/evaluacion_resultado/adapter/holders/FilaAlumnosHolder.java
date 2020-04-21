package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.consultoraestrategia.ss_crmeducativo.entities.SessionUser_Table.urlPicture;

public class FilaAlumnosHolder extends AbstractViewHolder {


    @BindView(R.id.img_picture)
    CircleImageView imgPicture;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.apellidos)
    TextView apellidos;
    @BindView(R.id.count)
    TextView count;


    public FilaAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(AlumnoUi alumnoUi, int position){
        String url = alumnoUi.getUrlProfile();

        if (!TextUtils.isEmpty(url)) {
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgPicture);
        }
        nombre.setText(String.valueOf(alumnoUi.getName()));
        count.setText(String.valueOf(position+1));
        apellidos.setText(String.valueOf(alumnoUi.getLastName()));
    }
}
