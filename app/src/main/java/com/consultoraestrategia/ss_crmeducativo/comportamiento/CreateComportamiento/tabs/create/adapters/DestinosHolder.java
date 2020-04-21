package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.ListenerDestinosComport;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DestinosHolder  extends RecyclerView.ViewHolder {

    @BindView(R.id.url_picture)
    CircleImageView urlPicture;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.check)
    CheckBox check;

    public DestinosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(final UsuarioUi usuarioUi , final ListenerDestinosComport listener){
        String url = usuarioUi.getUrlpicture();

        if (!TextUtils.isEmpty(url)) {
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(urlPicture);
        }
        if(usuarioUi.isSelected()){
            check.setEnabled(false);
            seleccionar();
        }
        else deseleccionar();

        if(!usuarioUi.isEnabled())check.setEnabled(false);
        else check.setEnabled(true);

        nombre.setText(usuarioUi.getNombrePersona());
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionar();
                listener.seletedUsuario(usuarioUi);
            }
        });
    }

    private void deseleccionar() {
        check.setChecked(false);
    }

    private void seleccionar() {
        check.setChecked(true);
    }
}
