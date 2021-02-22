package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.TutorialCentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TutorialCentProcesamiento extends Fragment implements TutorialCentView {


    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.txt_tutorial)
    TextView txtTutorial;
    @BindView(R.id.btn_tutorial)
    CardView btnTutorial;
    @BindView(R.id.fondo)
    ImageView img_fondo;
    @BindView(R.id.btn_comp_base)
    CardView btnCompBase;
    @BindView(R.id.btn_comp_transveral)
    CardView btnCompTransveral;
    @BindView(R.id.btn_atras)
    CardView btnAtras;

    private Unbinder unbinder;
    private CentProcesoPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_cent_procesamiento, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.btn_comp_transveral, R.id.btn_comp_base, R.id.btn_tutorial, R.id.btn_atras})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_comp_transveral:
                presenter.onClickTransversal();
                Navigation.findNavController(getView()).navigate(R.id.action_tutorial_to_registo_evaluacion);
                break;
            case R.id.btn_comp_base:
                presenter.onClickBase();
                Navigation.findNavController(getView()).navigate(R.id.action_tutorial_to_registo_evaluacion);
                break;
            case R.id.btn_tutorial:
                Navigation.findNavController(getView()).navigate(R.id.action_tutorial_to_contenido_tutorial);
                break;
            case R.id.btn_atras:
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setTitulo(String tituloCurso) {
        txtTitulo.setText(tituloCurso);
    }

    @Override
    public void setTema(String color1, String color2, String color3) {
        try {
            btnCompBase.setCardBackgroundColor(Color.parseColor(color3));
            btnCompTransveral.setCardBackgroundColor(Color.parseColor(color3));
            btnTutorial.setCardBackgroundColor(Color.parseColor(color1));
            txtTutorial.setTextColor(Color.parseColor(color1));
            btnAtras.setCardBackgroundColor(Color.parseColor(color3));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void setPresenter(CentProcesoPresenter presenter) {
        this.presenter = presenter;
    }
}
