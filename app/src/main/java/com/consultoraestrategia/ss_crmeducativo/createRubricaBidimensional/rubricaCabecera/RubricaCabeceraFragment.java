package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoTematicoChooser.CampoTematicoChooserDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser.IndicadoresChoserDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by @stevecampos on 15/02/2018.
 */

public class RubricaCabeceraFragment implements CabeceraView, RecyclerTouchListener.ClickListener {

    public static final String TAG = RubricaCabeceraFragment.class.getSimpleName();
    private final Unbinder unbinder;
    @BindView(R.id.edtRubrica)
    EditText edtRubrica;
    @BindView(R.id.edtAlias)
    EditText edtAlias;
    @BindView(R.id.edtTipoEvaluacion)
    TextInputEditText edtTipoEvaluacion;
    @BindView(R.id.tilTipoEvaluacion)
    TextInputLayout tilTipoEvaluacion;
    @BindView(R.id.btnTipoEvaluacion)
    ImageView btnTipoEvaluacion;
    @BindView(R.id.edtFormaEvaluacion)
    TextInputEditText edtFormaEvaluacion;
    @BindView(R.id.tilFormaEvaluacion)
    TextInputLayout tilFormaEvaluacion;
    @BindView(R.id.btnFormaEvaluacion)
    ImageView btnFormaEvaluacion;
    /*@BindView(R.id.edtTipoNota)
    TextInputEditText edtTipoNota;*/
    @BindView(R.id.tilTipoNota)
    RecyclerView tilTipoNota;
    @BindView(R.id.btnTipoNota)
    ImageView btnTipoNota;
    @BindView(R.id.edtEscala)
    TextInputEditText edtEscala;
    @BindView(R.id.tilEscala)
    TextInputLayout tilEscala;
    @BindView(R.id.btnEscala)
    ImageButton btnEscala;
    @BindView(R.id.text_title_competencialist)
    TextView textTitleCompetencialist;
    @BindView(R.id.btnCompetenciaList)
    ImageView btnCompetenciaList;
    @BindView(R.id.text_competencialist)
    TextView textCompetencialist;
    @BindView(R.id.text_title_campoaccionlist)
    TextView textTitleCampoaccionlist;
    @BindView(R.id.btnCampoAccionList)
    ImageView btnCampoAccionList;
    @BindView(R.id.text_campoaccionlist)
    TextView textCampoaccionlist;
    @BindView(R.id.root)
    ConstraintLayout root;

    @BindView(R.id.btnEstrategia)
    ImageView btnEstrategia;
    @BindView(R.id.txt_tipo_nota)
    TextView txtTipoNota;
    @BindView(R.id.img_valor_numerico)
    TextView imgValorNumerico;
    @BindView(R.id.img_selector_numerico)
    TextView imgSelectorNumerico;
    @BindView(R.id.txt_escala)
    TextView txtEscala;
    @BindView(R.id.txt_titulo_tipo_nota)
    TextView txtTituloTipoNota;
    @BindView(R.id.cons_tipo_nota)
    ConstraintLayout consTipoNota;
    @BindView(R.id.txt_descripcion_escala)
    TextView txtDescripcionEscala;
    @BindView(R.id.img_more_nivel_logro)
    ImageView imgMoreNivelLogro;


    private RecyclerTouchListener recyclerTouchListener;
    private RubricaCabeceraListener listener;
    private BaseActivity baseActivity;
    private ValorTipoNotaAdapter valorTipoNotaAdapter;

    public RubricaCabeceraFragment(BaseActivity activity, RubricaCabeceraListener listener) {
        unbinder = ButterKnife.bind(this,activity);
        this.baseActivity = activity;
        this.listener = listener;

        edtAlias.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( RubricaCabeceraFragment.this.listener!=null
                ) RubricaCabeceraFragment.this.listener.onTextChangedEditarAlias(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setupAdapter();

    }

    private void setupAdapter() {
        valorTipoNotaAdapter = new ValorTipoNotaAdapter(new ArrayList<ValorTipoNotaUi>(), ValorTipoNotaAdapter.TIPO_SIMPLE);
        tilTipoNota.setAdapter(valorTipoNotaAdapter);
        tilTipoNota.setLayoutManager(new GridLayoutManager(tilTipoNota.getContext(), 6));
        tilTipoNota.setVisibility(View.VISIBLE);
        tilTipoNota.setEnabled(true);
        tilTipoNota.setNestedScrollingEnabled(false);
        tilTipoNota.setHasFixedSize(false);

        if(recyclerTouchListener!=null)tilTipoNota.addOnItemTouchListener(recyclerTouchListener);
        recyclerTouchListener = new RecyclerTouchListener(root.getContext(), tilTipoNota, this);
        tilTipoNota.addOnItemTouchListener(recyclerTouchListener);

    }


    @OnClick({R.id.btnTipoEvaluacion, R.id.btnFormaEvaluacion, R.id.btnTipoNota, R.id.btnEscala, R.id.btnCompetenciaList, R.id.btnCampoAccionList,  R.id.btnEstrategia, R.id.txt_tipo_nota, R.id.cons_tipo_nota})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTipoEvaluacion:
                listener.onBtnTipoEvaluacionClicked();
                break;
            case R.id.btnFormaEvaluacion:
                listener.onBtnFormaEvaluacionClicked();
                break;
            case R.id.btnTipoNota:
                listener.onBtnTipoNotaClicked();
                break;
            case R.id.btnEscala:
                listener.onBtnEscalaClicked();
                break;
            case R.id.btnCompetenciaList:
                listener.onBtnCompetenciaListClicked();
                break;
            case R.id.btnCampoAccionList:
                listener.onBtnCampoAccionListClicked();
                break;
            case R.id.btnEstrategia:
                listener.onBtnEstrategiaEvaluacionClicked();
                break;
            case R.id.txt_tipo_nota:
                listener.onBtnTipoNotaClicked();
                break;
            case R.id.cons_tipo_nota:
                //listener.onBtnTipoNotaClicked();
                break;
        }

    }

    @Override
    public String getEdtRubrica() {
        return edtRubrica.getText().toString();
    }

    @Override
    public String getEdtAlias() {
        return edtAlias.getText().toString();
    }

    @Override
    public void showTipoNotaSelected(TipoNotaUi tipoNota) {
        Log.d(TAG, "showTipoNotaSelected: " + tipoNota.getTitle());
        txtTituloTipoNota.setText(tipoNota.getTitle());

        EscalaEvaluacionUi escalaUi = tipoNota.getEscalaEvaluacionUi();
        String escala = "";
        String rango = "";
        if(escalaUi!=null){
            escala = escalaUi.getNombre();
            rango = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
        }
        txtDescripcionEscala.setText(Html.fromHtml(rango));
        txtEscala.setText(escala);
        switch (tipoNota.getTipo()){
            case SELECTOR_NUMERICO:
                tilTipoNota.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                imgSelectorNumerico.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(new ArrayList<ValorTipoNotaUi>());

                break;
            case VALOR_NUMERICO:
                tilTipoNota.setVisibility(View.GONE);
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(new ArrayList<ValorTipoNotaUi>());

                break;
            case SELECTOR_ICONOS:
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                tilTipoNota.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(tipoNota.getValorTipoNotaList());

                break;
            case SELECTOR_VALORES:
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                tilTipoNota.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(tipoNota.getValorTipoNotaList());

                break;
        }


    }


    @Override
    public void showTipoEvaluacionSelected(String tipoEvaluacionTitulo) {
        Log.d(TAG, "showTipoEvaluacionSelected: " + tipoEvaluacionTitulo);
        edtTipoEvaluacion.setText(tipoEvaluacionTitulo);
    }

    @Override
    public void showFormaEvaluacionSelected(String formaEvaluacionTitulo) {
        Log.d(TAG, "showFormaEvaluacionSelected: " + formaEvaluacionTitulo);
        edtFormaEvaluacion.setText(formaEvaluacionTitulo);
    }

    @Override
    public void showEscalaSelected(String escalaTitulo) {
        Log.d(TAG, "showEscalaSelected: " + escalaTitulo);
        edtEscala.setText(escalaTitulo);
    }

    @Override
    public void showCompetenciaList(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "showCompetenciaList");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < competenciaList.size(); i++) {
            int pos = i + 1;
            text.append(String.valueOf(pos)).append(". ");
            text.append(competenciaList.get(i).getTitle());
            text.append("\n");
        }
        textCompetencialist.setText(text.toString());
        textTitleCompetencialist.setVisibility(View.VISIBLE);
        textCompetencialist.setVisibility(View.VISIBLE);
    }


    @Override
    public void showCampoAccionList(List<CampoAccionUi> campoAccionList) {
        Log.d(TAG, "showCampoAccionList");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < campoAccionList.size(); i++) {
            String title = campoAccionList.get(i).getTitulo();
            int indice = text.indexOf(title);
            if (indice == -1) {
                text.append("- ");
                text.append(title);
                text.append("\n");
            }
        }
        textCampoaccionlist.setText(text);
        textTitleCampoaccionlist.setVisibility(View.VISIBLE);
        textCampoaccionlist.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTipoNotaChooser(List<TipoNotaUi> tipoNotaList) {
        Log.d(TAG, "showTipoNotaChooser");
    }

    @Override
    public void showTipoEvaluacionChooser(List<TipoUi> tipoEvaluacionList) {
        Log.d(TAG, "showTipoEvaluacionChooser");
    }

    @Override
    public void showFormaEvaluacionChooser(List<TipoUi> formaEvaluacionList) {
        Log.d(TAG, "showFormaEvaluacionChooser");
    }

    @Override
    public void showEscalaChooser(List<EscalaEvaluacionUi> escalaEvaluacionList) {
        Log.d(TAG, "showEscalaChooser");
    }

    @Override
    public void showIndicadorChooser(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "showIndicadorChooser");
        FragmentManager fragmentManager = baseActivity.getSupportFragmentManager();

        IndicadoresChoserDialogFragment choserDialogFragment = IndicadoresChoserDialogFragment.newInstance(competenciaList);
        // SETS the target fragment for use later when sending results
        choserDialogFragment.show(fragmentManager, IndicadoresChoserDialogFragment.class.getSimpleName());
    }

    @Override
    public void showCampoAccionChooser( List<IndicadorUi> indicadorList) {
        Log.d(TAG, "showCampoAccionChooser");
        FragmentManager fragmentManager = baseActivity.getSupportFragmentManager();
        CampoTematicoChooserDialogFragment dialog = CampoTematicoChooserDialogFragment.newInstance(indicadorList);
        dialog.show(fragmentManager, IndicadoresChoserDialogFragment.class.getSimpleName());
    }


    @Override
    public void disabledFormaEvaluacion() {
      btnFormaEvaluacion.setEnabled(false);
    }

    @Override
    public void disabledNivelLogroRubrica() {
        btnTipoNota.setEnabled(false);
        txtTipoNota.setOnClickListener(null);
        txtTipoNota.setOnLongClickListener(null);
        txtTipoNota.setFocusable(false);
        String titulo = "Promedio logro";
        txtTipoNota.setHint(titulo);
        imgMoreNivelLogro.setVisibility(View.GONE);
    }

    @Override
    public void hideEstrategiaInput() {
        edtRubrica.setFocusable(true);
        btnEstrategia.setVisibility(View.GONE);
    }

    @Override
    public void showEstrategiaInput() {
        edtRubrica.setFocusable(false);
        btnEstrategia.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTituloEstrategiaSelected(String estrategia) {
        edtRubrica.setText(estrategia);
    }

    @Override
    public void setSubTitulo(String tituloTarea) {
        edtAlias.setText(tituloTarea);
    }

    @Override
    public void setTitulo(String tituloTarea) {
        edtRubrica.setText(tituloTarea);
    }

    @Override
    public void hideCompetenciaList() {

        textTitleCompetencialist.setVisibility(View.GONE);
        textCompetencialist.setVisibility(View.GONE);
    }

    @Override
    public void hideCampoAccionList() {
        textTitleCampoaccionlist.setVisibility(View.GONE);
        textCampoaccionlist.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view, int position) {
        //listener.onBtnTipoNotaClicked();
    }

    @Override
    public void onLongClick(View view, int position) {
        //listener.onBtnTipoNotaClicked();
    }





}
