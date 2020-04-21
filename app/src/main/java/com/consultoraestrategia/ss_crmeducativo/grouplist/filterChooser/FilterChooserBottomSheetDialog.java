package com.consultoraestrategia.ss_crmeducativo.grouplist.filterChooser;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.TipoGrupoUi;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class FilterChooserBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener,  FilterChoserAdapter.OnItemClickListener {

    private String dialogTitle;
    private TipoGrupoUi[] tipoGrupoUis;
    private CallbackFilterChooserBottomDialog callbackFilter;
    private CoordinatorLayout.Behavior behavior;

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setItems(TipoGrupoUi[] tipoGrupoUis) {
        this.tipoGrupoUis = tipoGrupoUis;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {

        View contentView = View.inflate(getContext(), R.layout.dialog_seleccionar_grupo, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        behavior = params.getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                double SLIDEOFFSETHIDEN = -0.9f;

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {

                        case BottomSheetBehavior.STATE_COLLAPSED: {

                            Log.d("BSB", "collapsed");
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {

                            Log.d("BSB", "settling");
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {

                            Log.d("BSB", "expanded");
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {

                            Log.d("BSB", "hidden");
                        }
                        case BottomSheetBehavior.STATE_DRAGGING: {

                            Log.d("BSB", "dragging");
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    if (SLIDEOFFSETHIDEN >= slideOffset) dismiss();
                }
            });
        }
        TextView textViewTitulo = (TextView) contentView.findViewById(R.id.textViewTitulo);
        textViewTitulo.setText(dialogTitle);
        RecyclerView listView = (RecyclerView) contentView.findViewById(R.id.reciclador);
        listView.addItemDecoration(new DividerItemDecoration(listView.getContext(), DividerItemDecoration.VERTICAL));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setAdapter(new FilterChoserAdapter(tipoGrupoUis, this));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return  new BottomSheetDialog(getContext(), getTheme());
    }




    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
    }


    public void setCallbackFilter(CallbackFilterChooserBottomDialog callbackFilter) {
        this.callbackFilter = callbackFilter;
    }

    public void onStart() {
        super.onStart();
        if (behavior!=null&&behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior)behavior).setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @Override
    public void onItemClick(TipoGrupoUi tipoGrupoUi) {
        callbackFilter.onItemClickTipoGrupo(tipoGrupoUi);
        dismiss();
    }
}
