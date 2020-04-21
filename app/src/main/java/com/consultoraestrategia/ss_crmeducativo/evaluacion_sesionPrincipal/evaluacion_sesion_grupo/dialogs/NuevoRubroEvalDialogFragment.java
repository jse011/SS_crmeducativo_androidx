package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.dialogs;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("ValidFragment")
public class NuevoRubroEvalDialogFragment extends DialogFragment{

    String TAG = "NuevoRubroEvalDialogFragment";
    View viewPadre;
    @BindView(R.id.rv_actividades)
    RecyclerView rvactividades;
    @BindView(R.id.txt_titulo_sesion)
    TextView txttitulo_sesion;


    public static NuevoRubroEvalDialogFragment newInstance() {
        NuevoRubroEvalDialogFragment fragment = new NuevoRubroEvalDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Obtener instancia de la action bar
        ActionBar actionBar = ((AppCompatActivity) getActivity())
                .getSupportActionBar();

        if (actionBar != null) {
            // Habilitar el Up Button
            actionBar.setDisplayHomeAsUpEnabled(true);
            // Cambiar icono del Up Button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.dialog_fragment_lista_actividades, container, false);
        ButterKnife.bind(this, viewPadre);

        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        return viewPadre;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
               .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

}



