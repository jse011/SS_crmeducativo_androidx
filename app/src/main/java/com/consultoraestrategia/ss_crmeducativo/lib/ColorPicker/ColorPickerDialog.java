package com.consultoraestrategia.ss_crmeducativo.lib.ColorPicker;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ColorPickerDialog extends DialogFragment {

    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.lay_selectpicker)
    RelativeLayout laySelectpicker;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    private ColorPicker colorPickerView;
    public static final String TITULOPICKERT = "ColorPicker.title";
    public static final String COLORPICKERT = "ColorPicker.color";
    private OnColorSelectedListener listener;

    public static ColorPickerDialog newInstance(String titulo, int color) {

        Bundle args = new Bundle();
        args.putString(TITULOPICKERT, titulo);
        args.putInt(COLORPICKERT, color);
        ColorPickerDialog fragment = new ColorPickerDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public ColorPickerDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewPadre = inflater.inflate(R.layout.dialog_fragment_pick_color, container, false);
        ButterKnife.bind(this, viewPadre);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        return viewPadre;
    }

    @Override
    public void onResume() {
        super.onResume();
        listener = (OnColorSelectedListener) getTargetFragment();
        colorPickerView = new ColorPicker(getContext());
        String titulo = "Selector de Color";
        Bundle bundle = getArguments();
        if (bundle != null) {
            titulo = bundle.getString(TITULOPICKERT);
            colorPickerView.setColor(bundle.getInt(COLORPICKERT));
        }
        txtTitulo.setText(titulo);
        laySelectpicker.addView(colorPickerView);

    }


    @OnClick({R.id.btn_cancelar, R.id.btn_aceptar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelar:
                dismiss();
                break;
            case R.id.btn_aceptar:
                int selectedColor = colorPickerView.getColor();
                listener.onColorSelected(selectedColor);
                dismiss();
                break;
        }
    }


    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }

}