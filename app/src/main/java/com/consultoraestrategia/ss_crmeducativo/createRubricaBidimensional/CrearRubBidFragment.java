package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrearRubBidFragment extends Fragment {

/*
    @BindView(R.id.edtRubrica)
    TextInputEditText edtRubrica;
    @BindView(R.id.tilRubrica)
    TextInputLayout tilRubrica;
    @BindView(R.id.edtAlias)
    TextInputEditText edtAlias;
    @BindView(R.id.tilAlias)
    TextInputLayout tilAlias;
    @BindView(R.id.edtCompetencia)
    TextInputEditText edtCompetencia;
    @BindView(R.id.tilCompetencia)
    TextInputLayout tilCompetencia;
    @BindView(R.id.edtCampoAccion)
    TextInputEditText edtCampoAccion;
    @BindView(R.id.tilCampoAccion)
    TextInputLayout tilCampoAccion;
    @BindView(R.id.edtTipoNota)
    TextInputEditText edtTipoNota;
    @BindView(R.id.tilTipoNota)
    TextInputLayout tilTipoNota;
    @BindView(R.id.table)
    RecyclerView recyclerView;
    @BindView(R.id.root)
    ConstraintLayout root;
    Unbinder unbinder;
    @BindView(R.id.guideline)
    Guideline guideline;*/

    public CrearRubBidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rubrica_bidimensional, container, false);
        return view;
    }
/*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRubBidAdapter();
    }

    private void setupRubBidAdapter() {
        RubBidAdapterBuilder builder = new RubBidAdapterBuilder(getContext());
        recyclerView.setVisibility(View.VISIBLE);
        FixedGridLayoutManager layoutManager = new FixedGridLayoutManager();
        layoutManager.setTotalColumnCount(6);
        recyclerView.setLayoutManager(layoutManager);

        List<Object> items = new ArrayList<>();
        items.add("Categorías");//title
        items.add(new Nivel("1", "Peso", "100%", "#FFFFFF", "#000000", 17.0, 20.0, 0.00, 65.0));
        items.add(new Nivel("2", "Destacado", "20", "#2196F3", "#FFFFFF", 17.0, 20.0, 0.00, 65.0));
        items.add(new Nivel("3", "Previsto", "15", "#4CAF50", "#FFFFFF", 13.0, 17.0, 0.00, 65.0));
        items.add(new Nivel("4", "En Proceso", "10", "#FF9800", "#FFFFFF", 10.0, 13.0, 0.00, 65.0));
        items.add(new Nivel("5", "En inicio", "5", "#F48FB1", "#B71C1C", 0.0, 10.0, 0.00, 65.0));

        items.add(new RubroEvaluacion("1", "<b>Traduce:</b> Traduce datos a expresiones algebráicas"));
        items.add(new NivelPorRubro("2", "30%"));
        items.add(new NivelPorRubro("3", "Traduce los datos en términos algebraicos acorde al problema"));
        items.add(new NivelPorRubro("4", "Traduce los datos en términos algebraicos acorde al problema en un 80%"));
        items.add(new NivelPorRubro("5", "Traduce los datos en términos algebraicos acorde al problema en un 50%"));
        items.add(new NivelPorRubro("6", "No traduce"));

        items.add(new RubroEvaluacion("1", "<b>Traduce:</b> Traduce datos a expresiones algebráicas"));
        items.add(new NivelPorRubro("2", "30%"));
        items.add(new NivelPorRubro("3", "Traduce los datos en términos algebraicos acorde al problema"));
        items.add(new NivelPorRubro("4", "Traduce los datos en términos algebraicos acorde al problema en un 80%"));
        items.add(new NivelPorRubro("5", "Traduce los datos en términos algebraicos acorde al problema en un 50%"));
        items.add(new NivelPorRubro("6", "No traduce"));

        items.add(new RubroEvaluacion("1", "<b>Traduce:</b> Traduce datos a expresiones algebráicas"));
        items.add(new NivelPorRubro("2", "30%"));
        items.add(new NivelPorRubro("3", "Traduce los datos en términos algebraicos acorde al problema"));
        items.add(new NivelPorRubro("4", "Traduce los datos en términos algebraicos acorde al problema en un 80%"));
        items.add(new NivelPorRubro("5", "Traduce los datos en términos algebraicos acorde al problema en un 50%"));
        items.add(new NivelPorRubro("6", "No traduce"));

        items.add(new RubroEvaluacion("1", "<b>Traduce:</b> Traduce datos a expresiones algebráicas"));
        items.add(new NivelPorRubro("2", "30%"));
        items.add(new NivelPorRubro("3", "Traduce los datos en términos algebraicos acorde al problema"));
        items.add(new NivelPorRubro("4", "Traduce los datos en términos algebraicos acorde al problema en un 80%"));
        items.add(new NivelPorRubro("5", "Traduce los datos en términos algebraicos acorde al problema en un 50%"));
        items.add(new NivelPorRubro("6", "No traduce"));

        items.add(new RubroEvaluacion("1", "<b>Traduce:</b> Traduce datos a expresiones algebráicas"));

        items.add(new NivelPorRubro("2", "30%"));
        items.add(new NivelPorRubro("3", "Traduce los datos en términos algebraicos acorde al problema"));
        items.add(new NivelPorRubro("4", "Traduce los datos en términos algebraicos acorde al problema en un 80%"));
        items.add(new NivelPorRubro("5", "Traduce los datos en términos algebraicos acorde al problema en un 50%"));
        items.add(new NivelPorRubro("6", "No traduce"));

        CreateRubBidAdapter adapter = new CreateRubBidAdapter(items);

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public TextView createTextView(String text, Context context, LinearLayout.LayoutParams params) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setLayoutParams(params);
        return textView;
    }

    public TextView createCheckbock(String text, Context context, LinearLayout.LayoutParams params) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText(text);
        checkBox.setLayoutParams(params);
        return checkBox;
    }


    public AlertDialog createCampoAccion(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ScrollView view = (ScrollView) LayoutInflater.from(context).inflate(R.layout.fragment_campoaccion_content, root, false);
        LinearLayout linearLayout = view.findViewById(R.id.fragment_root);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayout.addView(createTextView("Traduce", context, params));
        linearLayout.addView(createCheckbock("Ecuaciones", context, params));
        linearLayout.addView(createCheckbock("Inecuaciones", context, params));

        linearLayout.addView(createTextView("Ordena y Secuencia", context, params));
        linearLayout.addView(createCheckbock("Ecuaciones", context, params));
        linearLayout.addView(createCheckbock("Inecuaciones", context, params));

        linearLayout.addView(createTextView("Infiere", context, params));
        linearLayout.addView(createCheckbock("Relaciones", context, params));
        linearLayout.addView(createCheckbock("Funciones", context, params));
        linearLayout.addView(createCheckbock("Radicales", context, params));
        linearLayout.addView(createCheckbock("Regla de tres simple", context, params));


        builder.setTitle("Campos de Acción")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClickTipoComportamiento(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClickTipoComportamiento(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }

    public AlertDialog createTipoNotaChoice(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        CharSequence[] items = new CharSequence[6];

        items[0] = "Selector de Valores - 4";
        items[1] = "Selector de Valores - 3";
        items[2] = "Selector de Valores - 2";
        items[3] = "Selector de Íconos - 4";
        items[4] = "Selector de Íconos - 3";
        items[5] = "Selector de Íconos - 2";

        builder.setTitle("Tipo de Nota")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClickTipoComportamiento(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClickTipoComportamiento(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClickTipoComportamiento(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }


    @OnClick(R.id.btnCompetencia)
    public void onCompetenciaIconClicked() {
        createMultipleListDialog(getContext()).show();
    }

    @OnClick(R.id.btnCampoAccion)
    public void onCampoAccionIconClicked() {
        createCampoAccion(getActivity()).show();
    }

    @OnClick(R.id.btnTipoNota)
    public void onTipoNotaIconClicked() {
        createTipoNotaChoice(getActivity()).show();
    }*/

}
