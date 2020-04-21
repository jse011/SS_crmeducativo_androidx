package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FilaAsistenciaAlumnoHolder  extends AbstractViewHolder {

    @BindView(R.id.img_picture)
    CircleImageView imgPicture;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.apellidos)
    TextView apellidos;
    @BindView(R.id.count)
    TextView count;

    public FilaAsistenciaAlumnoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(AlumnosUi alumnosUi, int position){
        String url = alumnosUi.getUrlProfile();

        if (!TextUtils.isEmpty(url)) {
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgPicture);
        }
        nombre.setText(String.valueOf(alumnosUi.getNombre()));
        count.setText(String.valueOf(position+1));
        apellidos.setText(String.valueOf(alumnosUi.getLastName()));
    }

}
