package com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter;

import android.content.Context;
import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

public class CasoColumnGridLayoutManager  implements AutoColumnGridLayoutManager.ColumnCountProvider {

    @NonNull
    private final Context context;

    public CasoColumnGridLayoutManager(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public int getColumnCount(int recyclerViewWidth) {
        return columnsForWidth(recyclerViewWidth);
    }


    public int columnsForWidth(int widthPx) {
        int widthDp = dpFromPx(widthPx);
        if (widthDp >= 500) {
            return 2;
        } else {
            return 1;
        }
    }
    public int dpFromPx(float px) {
        return (int)(px / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
