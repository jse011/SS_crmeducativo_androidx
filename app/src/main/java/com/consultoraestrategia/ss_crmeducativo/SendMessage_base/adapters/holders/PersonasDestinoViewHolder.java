package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.holders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.PersonasDestinoAdapter;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.FirebaseMessage.getContactFromPhoneNumberExits;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public class PersonasDestinoViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = PersonasDestinoViewHolder.class.getSimpleName();
    @BindView(R.id.txtNroTelefono)
    TextView txtNroTelefono;
    @BindView(R.id.txtNombreDestino)
    TextView txtNombreDestino;
    @BindView(R.id.ibtnDelete)
    TextView ibtnDelete;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.contentChip)
    ConstraintLayout contentChip;
    @BindView(R.id.txtBtnEnviar)
    Button txtBtnEnviar;

    public PersonasDestinoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    boolean visibleBlueBackGround = false;

    @SuppressLint("SetTextI18n")
    public void bind(final PersonaRelacionesUI personaRelaciones, ImageLoader imageLoader, final PersonasDestinoAdapter.OnPersonaDestinoClickedListener destinoClickedListener, int tipoLayout) {
        Persona persona = personaRelaciones.getPersona();
        if (persona != null) imageLoader.loadWithCircular(imageView6, persona.getFoto());

        if (tipoLayout == 1) txtBtnEnviar.setVisibility(View.VISIBLE);
        else txtBtnEnviar.setVisibility(View.GONE);

        txtNroTelefono.setText(personaRelaciones.getParentezco());
        txtNombreDestino.setText(persona.getNombreCompleto());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (visibleBlueBackGround) {
                    txtNroTelefono.setText(personaRelaciones.getParentezco());
                    contentChip.setBackgroundResource(R.drawable.background_chip_imc);
                    changeStyleTextView(txtNroTelefono, Typeface.DEFAULT, "#000000");
                    changeStyleTextView(txtNombreDestino, Typeface.DEFAULT, "#000000");
                    changeStyleTextView(ibtnDelete, Typeface.DEFAULT, "#ff0000");
                    visibleBlueBackGround = false;
                } else {
                    contentChip.setBackgroundColor(Color.parseColor("#0051ff"));
                    changeStyleTextView(txtNroTelefono, Typeface.DEFAULT_BOLD, "#ffffff");
                    txtNroTelefono.setText("Cel: " + personaRelaciones.getPersona().getCelular());
                    changeStyleTextView(txtNombreDestino, Typeface.DEFAULT_BOLD, "#ffffff");
                    changeStyleTextView(ibtnDelete, Typeface.DEFAULT_BOLD, "#ff0000");
                    visibleBlueBackGround = true;
                }
                int tipoParentezco;
                if (personaRelaciones.getParentezco().equals("Alumno")) {
                    tipoParentezco = PersonaRelacionesUI.PARENTEZCO_ALUMNO;
                } else {
                    tipoParentezco = PersonaRelacionesUI.PARENTEZCO_PADRE;
                }
                destinoClickedListener.onClickShowPersonaDestino(tipoParentezco, personaRelaciones);
                validateNumber(personaRelaciones);
            }
        });

        ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tipoParentezco;
                if (personaRelaciones.getParentezco().equals("Alumno")) {
                    tipoParentezco = PersonaRelacionesUI.PARENTEZCO_ALUMNO;
                } else {
                    tipoParentezco = PersonaRelacionesUI.PARENTEZCO_PADRE;
                }
                destinoClickedListener.onClickDeletePersonaDestino(tipoParentezco, personaRelaciones);
            }
        });

        validateNumber(personaRelaciones);
        txtBtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinoClickedListener.onSendMessajeInidivdualClicked(personaRelaciones);

            }
        });

    }

    private void validateNumber(PersonaRelacionesUI personaRelaciones) {
        getContactFromPhoneNumberExits(personaRelaciones.getPersona().getCelular(), new VerifiContacFirebase() {
            @Override
            public void onContacExist() {
                itemView.setBackgroundResource(R.drawable.background_chip_imc_2);
            }

            @Override
            public void onContacDontExist() {
                itemView.setBackgroundResource(R.drawable.background_chip_imc);

            }
        });
    }

    public interface VerifiContacFirebase {
        void onContacExist();

        void onContacDontExist();
    }


    private void changeStyleTextView(TextView textView, Typeface aDefault, String color) {
        textView.setTextColor(Color.parseColor(color));
        textView.setTypeface(aDefault);
    }


}
