package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.holder;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.EscalaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class TipoNotaHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Runnable{
    @BindView(R.id.grid_selector)
    RecyclerView gridSelector;
    @BindView(R.id.txt_escala)
    TextView txtEscala;
    @BindView(R.id.txt_rango_escala)
    TextView txtRangoEscala;
    @BindView(R.id.txt_titulo_tiponota)
    TextView txtTituloTipoNota;
    @BindView(R.id.img_valor_numerico)
    TextView imgValorNumerico;
    @BindView(R.id.img_selector_numerico)
    TextView imgSelectorNumerico;
    @BindView(R.id.imgCheck)
    ImageView imgCheck;
    private TipoNotaListener listener;
    private TipoNotaUi tipoNotaUi;

    public TipoNotaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TipoNotaUi tipoNotaUi, TipoNotaListener listener){
        this.listener = listener;
        this.tipoNotaUi = tipoNotaUi;

        if(tipoNotaUi.isChecket() && listener != null){
            imgCheck.setVisibility(View.VISIBLE);

        }else {
            imgCheck.setVisibility(View.GONE);
        }

        txtTituloTipoNota.setText(tipoNotaUi.getNombre());
        EscalaUi escalaUi = tipoNotaUi.getEscalaUi();
        String rangoEscala = "";
        String escala = "";
        if (escalaUi!=null){
            escala = escalaUi.getDescripcion();
            rangoEscala = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
        }
        txtEscala.setText(escala);
        txtRangoEscala.setText(Html.fromHtml(rangoEscala));

        if(tipoNotaUi.isEnabled()) itemView.setOnClickListener(this);
        TipoUi tipo = tipoNotaUi.getTipoUi();

        imgSelectorNumerico.setVisibility(View.GONE);
        imgValorNumerico.setVisibility(View.GONE);
        gridSelector.setVisibility(View.GONE);
        switch (tipo.getTipo()){
            case SELECTOR_NUMERICO:
                imgSelectorNumerico.setVisibility(View.VISIBLE);
                break;
            case VALOR_NUMERICO:
                imgValorNumerico.setVisibility(View.VISIBLE);
                break;
            case SELECTOR_ICONOS:
                selectorValoresIconos(tipoNotaUi,tipo.getTipo());
                break;
            case SELECTOR_VALORES:
                selectorValoresIconos(tipoNotaUi,tipo.getTipo());
                break;
        }
    }

    private void selectorValoresIconos(TipoNotaUi tipoNotaUi, TipoUi.Tipo tipo) {
        List<ValorTipoNotaUi> valorTipoNotaUiList = tipoNotaUi.getValorTipoNotaUiList();
        if(valorTipoNotaUiList == null) return;
        ValorTipoNotaAdapter valorTipoNotaAdapter = new ValorTipoNotaAdapter(tipoNotaUi.getValorTipoNotaUiList(),tipo);
        gridSelector.setAdapter(valorTipoNotaAdapter);
        gridSelector.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
        gridSelector.setHasFixedSize(true);
        gridSelector.setVisibility(View.VISIBLE);
        gridSelector.setEnabled(true);
        gridSelector.setOnClickListener(this);
        //gridSelector.OnItemTouchListener();
    }

    @Override
    public void onClick(View view) {
        if(!tipoNotaUi.isChecket()){
            imgCheck.setVisibility(View.VISIBLE);
        }else {
            imgCheck.setVisibility(View.GONE);
        }
        imgCheck.post(this);
    }


    @Override
    public void run() {
        if(listener!= null) listener.onClickTipoNota(this ,tipoNotaUi);
    }
}
