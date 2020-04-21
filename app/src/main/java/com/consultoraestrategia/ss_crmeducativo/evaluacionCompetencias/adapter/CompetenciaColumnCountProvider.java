package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter;

import android.content.Context;
import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

public class CompetenciaColumnCountProvider implements AutoColumnGridLayoutManager.ColumnCountProvider {


    @NonNull
    private final Context context;

    public CompetenciaColumnCountProvider(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public int getColumnCount(int recyclerViewWidth) {
        return columnsForWidth(recyclerViewWidth);
    }

    public int columnsForWidth(int widthPx) {
        int widthDp = dpFromPx(widthPx);
        if (widthDp >= 900) {
            return 6;
        } else if (widthDp >= 720) {
            return 5;
        } else if (widthDp >= 600) {
            return 4;
        } else if (widthDp >= 480) {
            return 3;
        } else if (widthDp >= 320) {
            return 2;
        } else {
            return 2;
        }
    }

    public int dpFromPx(float px) {
        return (int)(px / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}