package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by irvinmarin on 07/12/2017.
 */
public class AddRecursoDialog extends DialogFragment {


    private static final String TAG = AddRecursoDialog.class.getSimpleName();
    @BindView(R.id.txtTitulo)
    EditText txtTitulo;
    @BindView(R.id.txtDescripcion)
    EditText txtDescripcion;
    @BindView(R.id.btnAceptar)
    Button btnAceptar;
    @BindView(R.id.btnCancelarRecurso)
    Button btnCancelarRecurso;

    Unbinder unbinder;
    @BindView(R.id.spnTipoRecurso)
    Spinner spnTipoRecurso;
    public static InsertRecursoListener listener;
    int tipoRecurso;

    public static AddRecursoDialog newInstace(InsertRecursoListener listener) {
        AddRecursoDialog.listener = listener;
        return new AddRecursoDialog();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View viewPadre = inflater.inflate(R.layout.dialog_insertar_recurso, container, false);
        Log.d(TAG, "onCreateView");
        unbinder = ButterKnife.bind(this, viewPadre);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().setCancelable(false);

        List<String> tipoRecursoList = new ArrayList<>();
        tipoRecursoList.add("Materiales");


        spnTipoRecurso.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoRecursoList
                )
        );
        spnTipoRecurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoRecurso = 403;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return viewPadre;

    }

    @OnClick({R.id.btnAceptar, R.id.btnCancelarRecurso})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAceptar:

                error = validateFields(txtTitulo);
                error = validateFields(txtDescripcion);

                if (error == 0 && tipoRecurso > 0) {
                    listener.onAceptarRecursoClicked(
                            txtTitulo.getText().toString(),
                            txtDescripcion.getText().toString(),
                            tipoRecurso
                    );
                    dismiss();
                } else if (tipoRecurso == 0) {
                    Snackbar.make(view.getRootView(), "Elija una opción valida", Snackbar.LENGTH_LONG).show();
                }

                error = 0;
                break;
            case R.id.btnCancelarRecurso:

                dismiss();
                break;
        }
    }

    int error = 0;

    private int validateFields(EditText editText) {
        if (editText.getText().toString().equals("")) {
            editText.setError("Ingrese Este Campo");
            error++;
        }
        return error;
    }

    public interface InsertRecursoListener {
        void onAceptarRecursoClicked(String titulo, String descripcion, int tipoRecurso);
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
