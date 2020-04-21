package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.detalle_mensaje;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
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

public class DetalleMensajeActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private static String idMensajeKey;
    @BindView(R.id.txtInicialName)
    TextView txtInicialName;
    @BindView(R.id.txtRemitente)
    TextView txtRemitente;
    @BindView(R.id.txtDestinatario)
    TextView txtDestinatario;
    @BindView(R.id.txtAsuntoMensaje)
    TextView txtAsuntoMensaje;
    @BindView(R.id.txtDescripcionMensaje)
    TextView txtDescripcionMensaje;
    @BindView(R.id.txtFechaMensaje)
    TextView txtFechaMensaje;
    @BindView(R.id.contentItem)
    LinearLayout contentItem;
    @BindView(R.id.btnResponder)
    Button btnResponder;
    @BindView(R.id.btnReeviar)
    Button btnReeviar;
    @BindView(R.id.txtDescripcionDetalle)
    TextView txtDescripcionDetalle;

    public static void launchActivityDetalle(Context context, String idMensajeKey) {
        DetalleMensajeActivity.idMensajeKey = idMensajeKey;
        context.startActivity(new Intent(context, DetalleMensajeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mensaje);
        ButterKnife.bind(this);

        hideViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mensaje Recibido");
        getDatosMensaje();

    }

    private void hideViews() {
        txtDescripcionMensaje.setVisibility(View.GONE);
        txtInicialName.setVisibility(View.GONE);

    }

    private void setupTextsViews(MensajeC mensaje, String nombresOrigen, String nombresDestino) {
        txtRemitente.setText("De : " + nombresOrigen);
        txtDestinatario.setText("Para : " + nombresDestino);
        txtAsuntoMensaje.setText("Asunto : " + mensaje.getAsunto());
        txtDescripcionDetalle.setText("Contenido :" + mensaje.getContenido());
        txtFechaMensaje.setText(getFechaDetalle(mensaje.getFechaEnvio()));

    }


    private void getDatosMensaje() {
        try{
            MensajeC mensaje = SQLite.select()
                    .from(MensajeC.class)
                    .where(MensajeC_Table.key.is(idMensajeKey))
                    .querySingle();

            if (mensaje==null)return;
            Usuario usuarioOrigen = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.usuarioId.is(mensaje.getUsuarioOrigenId()))
                    .querySingle();
            String nombresPersonaOrigen = null;
            if (usuarioOrigen != null) {
                Persona personaOrigen = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.is(usuarioOrigen.getPersonaId()))
                        .querySingle();
                nombresPersonaOrigen = personaOrigen.getApellidoPaterno() + " " + personaOrigen.getApellidoMaterno() + " " + personaOrigen.getNombres();
            } else {
                nombresPersonaOrigen = "Faltan Datos";
            }

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


            setupTextsViews(mensaje, nombresPersonaOrigen, personasDestino);
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
            SimpleDateFormat formatterUI = new SimpleDateFormat("d MMM \nyyyy", Locale.getDefault());
            formatterUIFinal = formatterUI.format(date);
            Log.d(TAG, "date : " + formatter.format(date));
            return formatterUIFinal;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Fecha no Valida";
        }

    }
}
