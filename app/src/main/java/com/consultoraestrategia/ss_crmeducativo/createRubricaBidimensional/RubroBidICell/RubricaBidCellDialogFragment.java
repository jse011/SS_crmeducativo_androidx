package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.RubroBidICell;

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
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 19/02/2018.
 */

public class RubricaBidCellDialogFragment extends DialogFragment {
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

    public RubricaBidCellDialogFragment() {
    }

    public static final String ARG_X = "x";
    public static final String ARG_Y = "y";

    public static RubricaBidCellDialogFragment newInstance(CriterioEvaluacionUi cell, int x, int y) {
        RubricaBidCellDialogFragment frag = new RubricaBidCellDialogFragment();
        Bundle args = new Bundle();
        criterioEvaluacionUi = cell;
        args.putInt(ARG_X, x);
        args.putInt(ARG_Y, y);
        frag.setArguments(args);
        return frag;
    }

    private RubricaBidCellCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RubricaBidCellCallback) {
            callback = (RubricaBidCellCallback) context;
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
        View view = inflater.inflate(R.layout.fragment_rubbid_cell, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private static CriterioEvaluacionUi criterioEvaluacionUi;
    private int x;
    private int y;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(criterioEvaluacionUi == null)dismiss();
        x = getArguments().getInt(ARG_X);
        y = getArguments().getInt(ARG_Y);
        String title = "";
        IndicadorUi indicadorUi = criterioEvaluacionUi.getIndicadorUi();
        ValorTipoNotaUi valorTipoNota = criterioEvaluacionUi.getValorTipoNotaUi();
        if(indicadorUi != null && valorTipoNota != null)title = String.format(
                getString(R.string.create_rubbid_cell_title)
                ,indicadorUi.getAlias()
                ,valorTipoNota.getAlias());
        textTitle.setText(title);

        if(!TextUtils.isEmpty(criterioEvaluacionUi.getTitulo()))edtDesc.setText(criterioEvaluacionUi.getTitulo());

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
                callback.onBtnNegativeClicked();
                dismiss();
                break;
            case R.id.bttn_positive:
                String desc = edtDesc.getText().toString();
                criterioEvaluacionUi.setTitulo(desc);
                callback.onBtnPostiveCriterioEvalaucion(criterioEvaluacionUi, x, y);
                dismiss();
                break;
        }
    }
}
