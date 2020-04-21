package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.util.KeyboardUtils;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class Dialogkeyboard extends BottomSheetDialogFragment implements DialogkeyBoardView {

    public final static String DESCRIPCION = "titulo";
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btn_guardar)
    Button btnGuardar;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    private Unbinder unbinder;
    private CallBack listener;


    @Override
    public void setupDialog(Dialog dialog, int style) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
        bottomSheetDialog.setContentView(R.layout.dialog_keyboard);
        unbinder = ButterKnife.bind(this, dialog.getWindow().getDecorView());

        String descripcion = "";
        if(getArguments()!=null)descripcion = getArguments().getString(DESCRIPCION,"");
        Log.d(getClass().getSimpleName(), "titulo: " + descripcion);
        editText.setText(descripcion);

        editText.requestFocus(); //Asegurar que editText tiene focus
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        try {
            Field behaviorField = bottomSheetDialog.getClass().getDeclaredField("behavior");
            behaviorField.setAccessible(true);
            final BottomSheetBehavior behavior = (BottomSheetBehavior) behaviorField.get(bottomSheetDialog);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogKeyBoardStyle);
        listener.onCreateDialogKeyBoard(this);
    }


    @Override
    public void onDismiss(DialogInterface dialog)
    {
        editText.clearFocus();
        Log.d(getClass().getSimpleName(), "hideKeyboard");
        KeyboardUtils.hideKeyboard(editText.getContext(), editText);
        listener.onDismissDialogkeyboard();
        super.onDismiss(dialog);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                FrameLayout bottomSheet = (FrameLayout)
                        dialog.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setPeekHeight(0);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (CallBack) getTargetFragment();
        if (listener == null) {
            throw new ClassCastException(
                    context.toString() + "must implement CallBack");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_guardar, R.id.btn_cancelar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_guardar:
                listener.onClickAceptarDialogkeyboard(editText.getText().toString());
                //dismiss();
                break;
            case R.id.btn_cancelar:
                dismiss();
                break;
        }
    }

    public interface CallBack{
        void onClickAceptarDialogkeyboard(String contenido);

        void onDismissDialogkeyboard();

        void onCreateDialogKeyBoard(DialogkeyBoardView view);

    }

    @Override
    public void dialogDissmit() {
        dismiss();
    }

    @Override
    public void errorText() {
        editText.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
    }

}
