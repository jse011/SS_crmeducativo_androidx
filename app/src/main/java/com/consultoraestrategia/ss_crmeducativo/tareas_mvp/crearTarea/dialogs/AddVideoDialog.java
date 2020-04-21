package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by irvinmarin on 07/12/2017.
 */

public class AddVideoDialog extends DialogFragment {


    private static final String TAG = AddVideoDialog.class.getSimpleName();

    Unbinder unbinder;

    public InsertVideoListener listener;
    int tipoRecurso;
    @BindView(R.id.txtUrlVideo)
    EditText txtUrlVideo;
    @BindView(R.id.btnAceptar)
    Button btnAceptar;
    @BindView(R.id.btnCancelarVideo)
    Button btnCancelarVideo;
    @BindView(R.id.txtTituloVideo)
    EditText txtTituloVideo;

    public static AddVideoDialog newInstace() {
        return new AddVideoDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View viewPadre = inflater.inflate(R.layout.dialog_insertar_video, container, false);
        Log.d(TAG, "onCreateView");
        unbinder = ButterKnife.bind(this, viewPadre);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().setCancelable(false);
        return viewPadre;

    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        super.onStart();
    }

    @OnClick({R.id.btnAceptar, R.id.btnCancelarVideo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAceptar:
                error = validateFields(txtUrlVideo);
                error = validateFields(txtTituloVideo);
                tipoRecurso = obtenerTipoRecurso(txtUrlVideo.getText().toString());
                if (error == 0 && tipoRecurso > 0) {
                    listener.onAceptarVideoClicked(txtTituloVideo.getText().toString(),
                            txtUrlVideo.getText().toString(),
                            tipoRecurso
                    );
                    dismiss();
                } else if (tipoRecurso == 0) {
                    Snackbar.make(view.getRootView(), "Elija una opción valida", Snackbar.LENGTH_LONG).show();
                }
                error = 0;
                break;
            case R.id.btnCancelarVideo:
                dismiss();
                break;
        }
    }

    private int obtenerTipoRecurso(String txtUrlVideo) {
        Log.d(TAG,"txtUrlVideo: " + txtUrlVideo);
        String[] linkYoutube = {"youtube.com", "youtu.be"};
        if(txtUrlVideo.contains(linkYoutube[0])){
            return 379;
        }else if(txtUrlVideo.contains(linkYoutube[1])){
            return 379;
        }else {
            return 380;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (InsertVideoListener) getTargetFragment();
        if (listener != null) return;
        if (context instanceof InsertVideoListener) {
            listener = (InsertVideoListener) context;
        } else {
            throw new ClassCastException(
                    context.toString() + "must implement InsertVideoListener");
        }
    }

    public interface InsertVideoListener {
        void onAceptarVideoClicked(String titulo, String Url, int tipo);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
