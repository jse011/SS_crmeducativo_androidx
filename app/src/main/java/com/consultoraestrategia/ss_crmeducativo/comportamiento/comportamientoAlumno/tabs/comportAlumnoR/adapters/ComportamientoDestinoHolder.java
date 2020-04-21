package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ComportamientoDestinoHolder   extends AbstractViewHolder {

    @BindView(R.id.img_tipo)
    ImageView imgTipo;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.fecha)
    TextView fecha;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.txtdescriocion)
    TextView descripcion;
    @BindView(R.id.recyclerArchivos)
    RecyclerView recyclerArchivos;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.txtvacioArchivos)
    TextView txtvacioArchivos;
    @BindView(R.id.url_foto)
    CircleImageView urlFoto;
    @BindView(R.id.nombrePersona)
    TextView nombrePersona;
    @BindView(R.id.subtipo)
    TextView subtipo;
    public ComportamientoDestinoHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(final ComportamientoUi comportamientoUi) {

        int ruta = 0;
        switch (comportamientoUi.getTipoConducta().getId()){
            case 541:
                ruta=R.drawable.medal;
                break;
            case 542:
                ruta= R.drawable.policeman;
                break;
            default:
                ruta= R.drawable.ic_graduacion;
                break;
        }
        imgTipo.setBackgroundResource(ruta);
        fecha.setText(Utils.f_fecha_letras(comportamientoUi.getFecha()));
        descripcion.setText(comportamientoUi.getDescripcion());
        nombre.setText(comportamientoUi.getTipoConducta().getNombre().toUpperCase());
        subtipo.setText(comportamientoUi.getTipoComportamientoUi().getTitulo());
        nombrePersona.setText(comportamientoUi.getUsuarioDestino().getNombrePersona().toUpperCase());
        String url = comportamientoUi.getUsuarioDestino().getUrlpicture();
        if (!TextUtils.isEmpty(url)) {
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(urlFoto);
        }
        if (comportamientoUi.getArchivoUiList() == null) {
            recyclerArchivos.setVisibility(View.GONE);
            txtvacioArchivos.setVisibility(View.VISIBLE);
        }
    }
}
