package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.holders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.detalle_mensaje.DetalleMensajeActivity;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 04/07/2018.
 */

public class BandejaRecibidosViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = BandejaRecibidosViewHolder.class.getSimpleName();
    @BindView(R.id.contentItem)
    LinearLayout contentItem;
    @BindView(R.id.txtDestinatario)
    TextView txtDestinatario;
    @BindView(R.id.txtInicialName)
    TextView txtInicialName;
    @BindView(R.id.txtRemitente)
    TextView txtRemitente;
    @BindView(R.id.txtAsuntoMensaje)
    TextView txtAsuntoMensaje;
    @BindView(R.id.txtDescripcionMensaje)
    TextView txtDescripcionMensaje;
    @BindView(R.id.txtFechaMensaje)
    TextView txtFechaMensaje;

    public BandejaRecibidosViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }

    public void bind(final MensajeUsuarioC mensajeUsuario, final int position) {
        txtDestinatario.setVisibility(View.VISIBLE);

        try {
            String fullName = null;
            Usuario usuario = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.usuario.is(SessionUser.getCurrentUser().getUsername()))
                    .querySingle();
            Usuario usuarioRemitente;
            Persona personaUsuarioOrigen;

            Usuario usuarioDestino = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.usuarioId.is(mensajeUsuario.getUsuarioDestinoId()))
                    .querySingle();
            Persona personaDestino = null;
            if (usuarioDestino != null) {

                personaDestino = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.is(usuarioDestino.getPersonaId()))
                        .querySingle();

                txtDestinatario.setText(String.valueOf("Para :" + personaDestino.getApellidoPaterno() + " " + personaDestino.getApellidoMaterno() + " " + personaDestino.getNombres()));

            } else {
                txtDestinatario.setText("Para: -");

            }


            if (mensajeUsuario.getUsuarioDestinoId() == usuario.getUsuarioId()) {
                usuarioRemitente = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.usuarioId.is(usuario.getUsuarioId()))
                        .querySingle();

                personaUsuarioOrigen = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.is(usuarioRemitente.getPersonaId()))
                        .querySingle();
                fullName = "Yo";
            } else {
                usuarioRemitente = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.usuarioId.is(mensajeUsuario.getUsuarioDestinoId()))
                        .querySingle();
                personaUsuarioOrigen = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.is(usuarioRemitente.getPersonaId()))
                        .querySingle();
                fullName = personaUsuarioOrigen.getNombres() + " "
                        + personaUsuarioOrigen.getApellidoPaterno() + " "
                        + personaUsuarioOrigen.getApellidoPaterno();
            }


            String s = fullName.charAt(0) + "";

            txtInicialName.setText(s);

            txtRemitente.setText(fullName);

            final MensajeC mensaje = SQLite.select()
                    .from(MensajeC.class)
                    .where(MensajeC_Table.key.is(mensajeUsuario.getMensajeId()))
                    .querySingle();

            if (mensaje != null) {
                txtAsuntoMensaje.setText(mensaje.getAsunto());
                txtDescripcionMensaje.setText(mensaje.getContenido());
                txtFechaMensaje.setText(mensaje.getFechaEnvio());
            } else {
                txtAsuntoMensaje.setText("Faltan Datos");
                txtDescripcionMensaje.setText("Faltan Datos");
                txtFechaMensaje.setText("Faltan Datos");
            }


            contentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtDestinatario.setVisibility(View.VISIBLE);
                    DetalleMensajeActivity.launchActivityDetalle(v.getContext(), mensaje.getKey());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}