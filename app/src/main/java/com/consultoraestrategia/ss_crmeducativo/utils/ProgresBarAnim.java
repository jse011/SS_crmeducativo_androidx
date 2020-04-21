package com.consultoraestrategia.ss_crmeducativo.utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

/**
 * Created by CCIE on 08/09/2017.
 */

public class ProgresBarAnim extends Animation {
    private ProgressBar progressBar;
    private float from;
    private float to;

    public ProgresBarAnim(ProgressBar progressBar, float from, float to) {
        super();
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
    }
}
