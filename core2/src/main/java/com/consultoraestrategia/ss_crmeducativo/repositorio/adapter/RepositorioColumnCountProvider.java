package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter;

import android.content.Context;
import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

public class RepositorioColumnCountProvider implements AutoColumnGridLayoutManager.ColumnCountProvider {
    @NonNull
    private final Context context;

    public RepositorioColumnCountProvider(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public int getColumnCount(int recyclerViewWidth) {
        return columnsForWidth(recyclerViewWidth);
    }

    public int columnsForWidth(int widthPx) {
        int widthDp = dpFromPx(widthPx);
        if (widthDp >= 900) {
            return 4;
        } else if (widthDp >= 720) {
            return 3;
        } else if (widthDp >= 600) {
            return 2;
        } else if (widthDp >= 480) {
            return 1;
        } else if (widthDp >= 320) {
            return 1;
        } else {
            return 1;
        }
    }

    public int dpFromPx(float px) {
        return (int)(px / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
