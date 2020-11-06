package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TutorialCentProcesamiento extends Fragment {

    @BindView(R.id.btn_comenzar)
    View btn_comenzar;

    private Unbinder unbinder;

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

    @OnClick({R.id.btn_comenzar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_comenzar:
                Navigation.findNavController(btn_comenzar).navigate(R.id.action_tutorial_to_registo_evaluacion);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
