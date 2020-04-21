package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.holders;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.detalle_mensaje.DetalleMensajeActivity;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 04/07/2018.
 */

public class BandejaEnviadosViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = BandejaEnviadosViewHolder.class.getSimpleName();
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

    public BandejaEnviadosViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }

    public void bind(final MensajeC mensaje, final int position) {
        txtRemitente.setVisibility(View.GONE);
        txtDestinatario.setVisibility(View.VISIBLE);
        txtInicialName.setVisibility(View.GONE);


        try{
            String fullName = null;
            List<MensajeUsuarioC> mensajeUsuarioList = SQLite.select()
                    .from(MensajeUsuarioC.class)
                    .where(MensajeUsuarioC_Table.mensajeId.is(mensaje.getKey()))
                    .queryList();
            int count = 0;
            List<String> stringListPersonaDestino = new ArrayList<>();
            for (MensajeUsuarioC mensajeUsuario : mensajeUsuarioList) {
                Usuario usuarioDestino = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.usuarioId.is(mensajeUsuario.getUsuarioDestinoId()))
                        .querySingle();
                if (usuarioDestino == null) return;
                Persona personaDestino = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.is(usuarioDestino.getPersonaId()))
                        .querySingle();
                if (personaDestino == null) return;
                stringListPersonaDestino.add(personaDestino.getNombreCompleto());
                count++;
            }


            String personasDestino = (stringListPersonaDestino.toString()).substring(1, stringListPersonaDestino.toString().length() - 1);

            Usuario usuarioOrigen = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.usuarioId.is(SessionUser.getCurrentUser().getUserId()))
                    .querySingle();
            Usuario usuarioRemitente;
            Persona personaUsuarioRemitente;

            String firtsLetterName = "";

            if (mensaje.getUsuarioOrigenId() == usuarioOrigen.getUsuarioId()) {
                fullName = "Yo";
                firtsLetterName=fullName;
            } else {
                usuarioRemitente = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.usuarioId.is(mensaje.getUsuarioOrigenId()))
                        .querySingle();
                if (usuarioRemitente != null) {

                    personaUsuarioRemitente = SQLite.select()
                            .from(Persona.class)
                            .where(Persona_Table.personaId.is(usuarioRemitente.getPersonaId()))
                            .querySingle();
                    if (personaUsuarioRemitente != null)
                        fullName = personaUsuarioRemitente.getNombres() + " "
                                + personaUsuarioRemitente.getApellidoPaterno() + " "
                                + personaUsuarioRemitente.getApellidoPaterno();
                    firtsLetterName = fullName.charAt(0) + "";

                }
            }

            txtInicialName.setText(firtsLetterName);
            txtRemitente.setText("De :" + fullName);
            txtDestinatario.setText("Para : " + personasDestino);
            txtAsuntoMensaje.setText("Asunto : " + mensaje.getAsunto());
            txtDescripcionMensaje.setText(mensaje.getContenido());
            txtFechaMensaje.setText(getFechaDetalle(mensaje.getFechaEnvio()));
            contentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                holder.txtDestinatario.setVisibility(View.VISIBLE);
                    DetalleMensajeActivity.launchActivityDetalle(v.getContext(), mensaje.getKey());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private String getFechaDetalle(String fecha) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;
        String formatterUIFinal = null;
        try {
            date = formatter.parse(fecha);
            SimpleDateFormat formatterUI = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
            formatterUIFinal = formatterUI.format(date);
            Log.d(TAG, "date : " + formatter.format(date));
            return formatterUIFinal;
        } catch (ParseException e) {
//            e.printStackTrace();
            return "Fecha no Valida";
        }

    }
}