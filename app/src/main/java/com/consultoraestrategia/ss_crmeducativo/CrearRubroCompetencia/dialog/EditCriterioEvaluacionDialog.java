package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialog;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class EditCriterioEvaluacionDialog extends DialogFragment {

    private static String TAG = "EditSelectorValDialog";
    View viewPadre;
    private static String DIALOGPADRE = "criterio";
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    Unbinder unbind;
    @BindView(R.id.txt_Titulo)
    TextView txtTitulo;
    @BindView(R.id.txt_TipoNota)
    TextView txtTipoNota;
    @BindView(R.id.txtCriterioEvaluacion)
    EditText txtCriterioEvaluacion;

    public static EditCriterioEvaluacionDialog newInstance(ValorTipoNotaUi valorTipoNotaUi) {
        EditCriterioEvaluacionDialog fragment = new EditCriterioEvaluacionDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DIALOGPADRE, Parcels.wrap(valorTipoNotaUi));
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.dialog_fragment_criterio_evaluacion, container, false);
        unbind = ButterKnife.bind(this, viewPadre);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return viewPadre;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ValorTipoNotaUi valorTipoNotaUi = Parcels.unwrap(getArguments().getParcelable(DIALOGPADRE));
        final EditCriterioEvaluacionDialogListener listener = (EditCriterioEvaluacionDialogListener) getTargetFragment();
        String CriterioEvaluacion = "<b>"+valorTipoNotaUi.getTitulo() + "</b> " + valorTipoNotaUi.getDescripcion();

        txtTipoNota.setText(Html.fromHtml(CriterioEvaluacion));

        txtCriterioEvaluacion.setText(valorTipoNotaUi.getCriterioEvalUi().getDescripcion());
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorTipoNotaUi.getCriterioEvalUi().setDescripcion(txtCriterioEvaluacion.getText().toString());
                listener.onCriterioSaved(valorTipoNotaUi);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onCriterioClose();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    public interface EditCriterioEvaluacionDialogListener {
        void onCriterioSaved(ValorTipoNotaUi valorTipoNotaUi);
        void onCriterioClose();
    }

}



