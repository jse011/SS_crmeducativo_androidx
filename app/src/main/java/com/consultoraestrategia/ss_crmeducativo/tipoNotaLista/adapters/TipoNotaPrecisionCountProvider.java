package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters;

import android.content.Context;
import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

/**
 * Created by Jse on 02/10/2018.
 */

public class TipoNotaPrecisionCountProvider implements AutoColumnGridLayoutManager.ColumnCountProvider {
    @NonNull
    private final Context context;

    public TipoNotaPrecisionCountProvider(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public int getColumnCount(int recyclerViewWidth) {
        return columnsForWidth(recyclerViewWidth);
    }

    public int columnsForWidth(int widthPx) {
        int widthDp = dpFromPx(widthPx);
        if (widthDp >= 900) {
            return 16;
        } else if (widthDp >= 720) {
            return 12;
        } else if (widthDp >= 600) {
            return 9;
        } else if (widthDp >= 480) {
            return 8;
        } else if (widthDp >= 320) {
            return 6;
        } else {
            return 6;
        }
    }

    public int dpFromPx(float px) {
        return (int)(px / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}

