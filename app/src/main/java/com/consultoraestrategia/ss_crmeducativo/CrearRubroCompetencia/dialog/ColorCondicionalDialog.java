package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialog;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("ValidFragment")
public class ColorCondicionalDialog extends DialogFragment {

    String TAG = "EditSelectorValDialog";
    private static String DIALOGPADRE = "RangoCalificacion";
    View viewPadre;
    @BindView(R.id.txt_desde)
    EditText txtDesde;
    @BindView(R.id.txt_hasta)
    EditText txtHasta;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;

    public static ColorCondicionalDialog newInstance(ColorCondicionalUi colorCondicionalUi) {
        ColorCondicionalDialog fragment = new ColorCondicionalDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DIALOGPADRE, Parcels.wrap(colorCondicionalUi));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.dialog_fragment_selector_valor, container, false);
        ButterKnife.bind(this, viewPadre);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        final ColorCondicionalUi colorCondicionalUi = Parcels.unwrap(getArguments().getParcelable(DIALOGPADRE));
        final RangoCalificacaion listener = (RangoCalificacaion) getTargetFragment();

        txtDesde.setText( String.valueOf(colorCondicionalUi.getDesde()));
        txtHasta.setText( String.valueOf(colorCondicionalUi.getHasta()));
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String porDefecto = "00";
                if(txtDesde.getText().toString().isEmpty()){
                    txtDesde.setText(porDefecto);
                }
                if(txtHasta.getText().toString().isEmpty()){
                    txtHasta.setText(porDefecto);
                }

                int desde = Integer.parseInt(txtDesde.getText().toString());
                int hasta = Integer.parseInt(txtHasta.getText().toString());
                colorCondicionalUi.setDesde(desde);
                colorCondicionalUi.setHasta(hasta);
                listener.onSaveRangoCalificacion(colorCondicionalUi);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCloseRangoCalificacion();
            }
        });


        return viewPadre;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public interface RangoCalificacaion{
        void onSaveRangoCalificacion(ColorCondicionalUi colorCondicionalUi);
        void onCloseRangoCalificacion();
    }
}



