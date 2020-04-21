package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.informacionValorTiponota;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.ValorTipoNotaUi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogValorTipoNota extends DialogFragment {

    public static final String TIPO_NOTA_BUNDLE = "tipoNotaDialog";
    public static final String TIPO_TITULO = "tituloDialog";
    @BindView(R.id.txt_detalle_escala)
    TextView txtDetalleEscala;
    @BindView(R.id.img_detalle_valor_numerico)
    TextView imgDetalleValorNumerico;
    @BindView(R.id.img_detalle_selector_numerico)
    TextView imgDetalleSelectorNumerico;
    @BindView(R.id.txt_titulo_detalle_tipo_nota)
    TextView txtTituloDetalleTipoNota;
    @BindView(R.id.rc_detalle_TipoNota)
    RecyclerView rcDetalleTipoNota;
    @BindView(R.id.text_title_conversion)
    TextView textTitleConversion;
    @BindView(R.id.btn_close)
    TextView btnClose;


    private ValorTipoNotaAdapter valorTipoNotaAdapterDetalle;
    private Unbinder unbinder;

    public static DialogValorTipoNota newInstance(String titulo, TipoNotaUi tipoNotaUi) {

        Bundle args = new Bundle();
        args.putString(TIPO_TITULO, titulo);
        args.putSerializable(TIPO_NOTA_BUNDLE, tipoNotaUi);
        DialogValorTipoNota fragment = new DialogValorTipoNota();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        }

        View view = inflater.inflate(R.layout.custom_dialog_create_rubrica_info_tipo_nota, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupDialogAdapter();
        return view;
    }


    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.90), (int) (size.y * 0.75));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        showTipoNotaSelected();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void setupDialogAdapter() {
        valorTipoNotaAdapterDetalle = new ValorTipoNotaAdapter(new ArrayList<ValorTipoNotaUi>(), ValorTipoNotaAdapter.TIPO_COMPLEJO);
        rcDetalleTipoNota.setAdapter(valorTipoNotaAdapterDetalle);
        rcDetalleTipoNota.setLayoutManager(new GridLayoutManager(rcDetalleTipoNota.getContext(), 2));
        rcDetalleTipoNota.setNestedScrollingEnabled(false);
        rcDetalleTipoNota.setHasFixedSize(false);
    }

    public void showTipoNotaSelected() {

        if (getArguments() != null) {
            String titulo = getArguments().getString(TIPO_TITULO);
            textTitleConversion.setText(titulo);

            TipoNotaUi tipoNota = (TipoNotaUi) getArguments().getSerializable(TIPO_NOTA_BUNDLE);
            if (tipoNota != null) {
                //Log.d(TAG, "showTipoNotaSelected: " + tipoNota.getTitle());

                String detalleTitulo = "Nivel logro: " + tipoNota.getNombre();
                txtTituloDetalleTipoNota.setText(detalleTitulo);

                EscalaEvaluacionUi escalaUi = tipoNota.getEscalaEvaluacionUi();
                String escala = "";
                String rango = "";
                String rangoEscalaDetalle = "Escala : ";
                if (escalaUi != null) {
                    escala = escalaUi.getNombre();
                    rango = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
                    rangoEscalaDetalle += escala + " " + rango;
                }
                txtDetalleEscala.setText(Html.fromHtml(rangoEscalaDetalle));
                switch (tipoNota.getTipo()) {
                    case SELECTOR_NUMERICO:


                        rcDetalleTipoNota.setVisibility(View.GONE);
                        imgDetalleValorNumerico.setVisibility(View.GONE);
                        imgDetalleSelectorNumerico.setVisibility(View.VISIBLE);
                        valorTipoNotaAdapterDetalle.setList(new ArrayList<ValorTipoNotaUi>());
                        break;
                    case VALOR_NUMERICO:


                        rcDetalleTipoNota.setVisibility(View.GONE);
                        imgDetalleSelectorNumerico.setVisibility(View.GONE);
                        imgDetalleValorNumerico.setVisibility(View.VISIBLE);
                        valorTipoNotaAdapterDetalle.setList(new ArrayList<ValorTipoNotaUi>());
                        break;
                    case SELECTOR_ICONOS:


                        imgDetalleSelectorNumerico.setVisibility(View.GONE);
                        imgDetalleValorNumerico.setVisibility(View.GONE);
                        rcDetalleTipoNota.setVisibility(View.VISIBLE);
                        valorTipoNotaAdapterDetalle.setList(tipoNota.getValorTipoNotaList());
                        break;
                    case SELECTOR_VALORES:

                        imgDetalleSelectorNumerico.setVisibility(View.GONE);
                        imgDetalleValorNumerico.setVisibility(View.GONE);
                        rcDetalleTipoNota.setVisibility(View.VISIBLE);
                        valorTipoNotaAdapterDetalle.setList(tipoNota.getValorTipoNotaList());
                        break;
                }
            }

        }
    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        dismiss();
    }
}
