package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogEstrategiasEvaluacion;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogEstrategiasEvaluacion extends DialogFragment implements View.OnClickListener{

    public static final String LISTA_ESTATEGIAS = "listaEstrategias";
    @BindView(R.id.list_estrategias)
    ListView listEstrategias;
    @BindView(R.id.btncancelar)
    TextView btncancelar;
    @BindView(R.id.txtvacio)
    TextView txtvacio;

    private String TAG = DialogEstrategiasEvaluacion.class.getSimpleName();
    private EstrategiaCallBack estrategiaCallBack;

    public static DialogEstrategiasEvaluacion newInstance(Bundle bundle) {
        DialogEstrategiasEvaluacion fragment = new DialogEstrategiasEvaluacion();
        fragment.setArguments(bundle);
        //fragment.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EstrategiaCallBack) {
            estrategiaCallBack = (EstrategiaCallBack) context;
        } else
            throw new ClassCastException(context.getClass().getSimpleName() + "must be implement estrategiaCallBack");
    }

    @Override
    public void onDetach() {
        estrategiaCallBack = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_estrategias_eval, container, false);
        ButterKnife.bind(this, view);
        btncancelar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        Bundle bundle = getArguments();
        List<EstrategiaEvalUi> estrategiaEvalUis = (ArrayList<EstrategiaEvalUi>) bundle.getSerializable(DialogEstrategiasEvaluacion.LISTA_ESTATEGIAS);
        Log.d(TAG, "estrategiaEvalUis " + estrategiaEvalUis.size());
        setListaEstrategias(estrategiaEvalUis);

        if(estrategiaEvalUis.isEmpty())txtvacio.setVisibility(View.VISIBLE);
        else txtvacio.setVisibility(View.GONE);


    }

    private void setListaEstrategias(List<EstrategiaEvalUi> estrategiaEvalUis) {

        ArrayAdapter<EstrategiaEvalUi> adaptador = new ArrayAdapter<EstrategiaEvalUi>(getContext(), R.layout.item_estrategias_evaluacion, R.id.item_estrategia, estrategiaEvalUis);
        listEstrategias.setAdapter(adaptador);

        listEstrategias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EstrategiaEvalUi item = (EstrategiaEvalUi) parent.getItemAtPosition(position);
                Log.d(TAG, "click estrategia " + item.getEstrategia());
                setOnclick(item);
            }
        });

    }

    private void setOnclick(EstrategiaEvalUi estrategiaEvalUiSelected) {
        estrategiaCallBack.onclickEstrategia(estrategiaEvalUiSelected);
        dismiss();
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
            dismiss();
            break;
        }
    }
}
