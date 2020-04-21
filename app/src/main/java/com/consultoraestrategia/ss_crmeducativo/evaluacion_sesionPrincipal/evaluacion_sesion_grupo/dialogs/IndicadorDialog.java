package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.IndicadoreAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.listener.IndicadorListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class  IndicadorDialog extends DialogFragment  {
    private static String DIALOGPADRE = "Inidicador.Padre";
    @BindView(R.id.rc_indicadores)
    RecyclerView rcIndicadores;

    public static IndicadorDialog newInstance(List<IndicadorUi> indicadorUis) {
        Bundle args = new Bundle();
        IndicadorDialog fragment = new IndicadorDialog();
        args.putParcelable(DIALOGPADRE, Parcels.wrap(indicadorUis));
        fragment.setArguments(args);
        return fragment;
    }

    public IndicadorDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewPadre = inflater.inflate(R.layout.dialog_fragment_indicadores, container, false);
        ButterKnife.bind(this, viewPadre);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        return viewPadre;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    private void setupAdapter(){
        final IndicadorListener listener = (IndicadorListener) getTargetFragment();
        List<IndicadorUi> indicadorUis  = Parcels.unwrap(getArguments().getParcelable(DIALOGPADRE));
        if(indicadorUis != null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            IndicadoreAdapter indicadoreAdapter = new IndicadoreAdapter(indicadorUis,listener);
            indicadoreAdapter.setRecyclerView(rcIndicadores);
            rcIndicadores.setAdapter(indicadoreAdapter);
            rcIndicadores.setLayoutManager(linearLayoutManager);
            rcIndicadores.setHasFixedSize(true);
            int resId = R.anim.layout_animation_from_bottom;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
            rcIndicadores.setLayoutAnimation(animation);
        }

    }
}