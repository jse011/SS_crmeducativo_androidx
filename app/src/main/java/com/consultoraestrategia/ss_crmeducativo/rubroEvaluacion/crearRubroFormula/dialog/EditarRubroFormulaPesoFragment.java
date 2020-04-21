package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EditarRubroFormulaPesoFragment extends DialogFragment {

    @BindView(R.id.txtNameRubro)
    TextView textViewNameRubro;
    @BindView(R.id.btnAceptar)
    Button buttonAceptar;
    @BindView(R.id.btnCancelar)
    Button buttonCancelar;
    @BindView(R.id.editTextPeso)
    EditText editTextPeso;
    @BindView(R.id.text_input_layout)
    TextInputLayout text_input_layout;

    Unbinder unbinder;
    ModificarRubroEvaluacionProcesoPesoListener listener;

    private static String TAG = EditarRubroFormulaPesoFragment.class.getSimpleName();

    public static EditarRubroFormulaPesoFragment newInstance(RubroProcesoUi rubroEvaluacionProcesoUi) {
        EditarRubroFormulaPesoFragment fragment = new EditarRubroFormulaPesoFragment();
        Bundle args = new Bundle();
        args.putParcelable("rubroEvaluacionProcesoUi", Parcels.wrap(rubroEvaluacionProcesoUi));
        fragment.setArguments(args);
        //fragment.setArguments(bundle);
        return fragment;
    }

    public EditarRubroFormulaPesoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_rubro_formula_peso, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        int dialogWidth = getResources().getDimensionPixelSize(R.dimen.dialog_profile_width);
        int dialogHeight = getResources().getDimensionPixelSize(R.dimen.dialog_rubroEvaluacion_edit_height);
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RubroProcesoUi rubroEvaluacionProcesoUi = Parcels.unwrap(getArguments().getParcelable("rubroEvaluacionProcesoUi"));
        //listener = (ModificarRubroEvaluacionProcesoPesoListener) getTargetFragment();
        textViewNameRubro.setText(rubroEvaluacionProcesoUi.getTitulo());
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = editTextPeso.getText().toString().trim();
                if (value.equals("")) {
                    text_input_layout.setError("No se permite campos vacios");
                } else {
                    Double aDouble = Double.parseDouble(value);
                    if (aDouble > 100) {
                        text_input_layout.setError("Solo se permite valores menores de 100");
                    } else {
                        rubroEvaluacionProcesoUi.setPeso(aDouble);
                        listener.onRubroEvaluacionProcesoSaved(rubroEvaluacionProcesoUi);
                        dismiss();
                    }
                }

            }
        });
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder = null;
    }


    public interface ModificarRubroEvaluacionProcesoPesoListener {
        void onRubroEvaluacionProcesoSaved(RubroProcesoUi rubroEvaluacionProcesoUi);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ModificarRubroEvaluacionProcesoPesoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
