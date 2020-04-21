package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.changePesoIndicador;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 19/02/2018.
 */

public class PesoIndicadorCellDialogFragment extends DialogFragment {
    @BindView(R.id.titulo)
    TextView textTitle;
    @BindView(R.id.edt_desc)
    TextInputEditText edtDesc;
    @BindView(R.id.til_desc)
    TextInputLayout tilDesc;
    @BindView(R.id.bttn_negative)
    Button bttnNegative;
    @BindView(R.id.bttn_positive)
    Button bttnPositive;
    Unbinder unbinder;

    public PesoIndicadorCellDialogFragment() {
    }

    public static final String ARG_X = "x";
    public static final String ARG_Y = "y";

    public static PesoIndicadorCellDialogFragment newInstance(IndicadorUi cell, int x, int y) {
        PesoIndicadorCellDialogFragment frag = new PesoIndicadorCellDialogFragment();
        Bundle args = new Bundle();
        indicadorUi = cell;
        args.putInt(ARG_X, x);
        args.putInt(ARG_Y, y);
        frag.setArguments(args);
        return frag;
    }

    private PesoIndicadorCellCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PesoIndicadorCellCallback) {
            callback = (PesoIndicadorCellCallback) context;
        } else
            throw new ClassCastException(context.getClass().getSimpleName() + "must be implement RubricaBidCellCallback");
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        super.onStart();
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rubbid_cell_peso_indicador, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private static IndicadorUi indicadorUi;
    private int x;
    private int y;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(indicadorUi == null)dismiss();
        x = getArguments().getInt(ARG_X);
        y = getArguments().getInt(ARG_Y);
        String title = String.format(
                getString(R.string.create_rubbid_cell_indicador_peso_title)
                ,indicadorUi.getAlias());
        textTitle.setText(title);
        if(!TextUtils.isEmpty(indicadorUi.getPeso()))edtDesc.setText(String.valueOf(isInteger(indicadorUi.getPeso())));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bttn_negative, R.id.bttn_positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bttn_negative:
                callback.onBtnNegativeClickedPesoIndicadorCell();
                dismiss();
                break;
            case R.id.bttn_positive:
                String desc = edtDesc.getText().toString();
                indicadorUi.setPeso(desc);
                callback.onBtnPostivePesoIndicadorCell(indicadorUi, x, y);
                dismiss();
                break;
        }
    }

    public int isInteger(String numero){
        try{
            return Integer.parseInt(numero);
        }catch(NumberFormatException e){
            return 0;
        }
    }
}
