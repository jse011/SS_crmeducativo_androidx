package com.consultoraestrategia.ss_crmeducativo.crearRubrica2.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.AppBarListener.AppBarStateChangeListener;
import com.consultoraestrategia.ss_crmeducativo.utils.ColorTransparentUtils;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PrincipalFragment extends Fragment {
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.expanded)
    RoundRectView expanded;
    @BindView(R.id.content_expanded)
    ConstraintLayout contentExpanded;
    @BindView(R.id.texto_expanded)
    TextView textoExpanded;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    private Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crear_rubrica_principal, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupToolbar();
        return view;
    }

    private void setupToolbar() {
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int offset) {
                Log.d(getTag(), "offset: "+offset);
                if(getContext()!=null)
                    switch (state){
                        case IDLE:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                expanded.setElevation(Utils.convertDpToPixel(0, getContext()));
                            }

                            if(offset<-60){
                                offset = 0;
                            }
                            int tansparencia = 100 + offset;

                            contentExpanded.setBackgroundColor(Color.parseColor(ColorTransparentUtils.transparentColor(ContextCompat.getColor(getContext(), R.color.white), tansparencia)));
                            break;
                        case EXPANDED:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                expanded.setElevation(Utils.convertDpToPixel(0, getContext()));
                            }
                            textoExpanded.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);;
                            contentExpanded.setBackgroundColor(Color.parseColor(ColorTransparentUtils.transparentColor(ContextCompat.getColor(getContext(), R.color.white), 0)));
                            break;
                        case COLLAPSED:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                expanded.setElevation(Utils.convertDpToPixel(8, getContext()));
                            }
                            textoExpanded.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                            contentExpanded.setBackgroundColor(Color.parseColor(ColorTransparentUtils.transparentColor(ContextCompat.getColor(getContext(), R.color.white), 100)));
                            break;
                    }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
