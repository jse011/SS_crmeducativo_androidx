package com.consultoraestrategia.ss_crmeducativo.main.dialogProgress;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgressDialog extends DialogFragment implements ProgressDialogView {


    @BindView(R.id.animationView)
    LottieAnimationView animationView;
    @BindView(R.id.progress_init)
    ConstraintLayout progressInit;
    private Unbinder unbinder;
    private static final String TAG = ProgressDialog.class.getSimpleName();
    private MainPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_main_progress, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        }
        setCancelable(false);

        setupAnimation();

        return view;
    }

    private void setupAnimation() {
        animationView.setAnimation("constructionsite.json");
        animationView.setRepeatCount(ValueAnimator.INFINITE);
        progressInit.setVisibility(View.VISIBLE);
        animationView.playAnimation();
        //progressInit.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_in));
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onResume");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
