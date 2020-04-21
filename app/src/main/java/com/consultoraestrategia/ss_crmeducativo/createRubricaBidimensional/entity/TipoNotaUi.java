package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public class TipoNotaUi implements Serializable {

    private static final String GREY = "#90A4AE",AZUL ="#1976d2",ANARANJADO="#FF6D00",ROJO="#D32F2F",VERDE="#388e3c";
    public enum Tipo {SELECTOR_VALORES, SELECTOR_ICONOS,VALOR_NUMERICO,SELECTOR_NUMERICO}

    private String id;
    private String title;
    private List<ValorTipoNotaUi> valorTipoNotaList = new ArrayList<>();
    private EscalaEvaluacionUi escalaEvaluacionUi;
    private Tipo tipo = Tipo.SELECTOR_VALORES;

    public TipoNotaUi() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaList() {
        return valorTipoNotaList;
    }

    public void setValorTipoNotaList(List<ValorTipoNotaUi> valorTipoNotaList) {
        this.valorTipoNotaList.clear();
        this.valorTipoNotaList.addAll(valorTipoNotaList);
    }

    @Override
    public String toString() {
        return title;
    }

    public EscalaEvaluacionUi getEscalaEvaluacionUi() {
        return escalaEvaluacionUi;
    }

    public void setEscalaEvaluacionUi(EscalaEvaluacionUi escalaEvaluacionUi) {
        this.escalaEvaluacionUi = escalaEvaluacionUi;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }



    public static List<TipoNotaUi> transform(List<TipoNotaC> tipoNotas) {
        List<TipoNotaUi> tipoNotaUiList = new ArrayList<>();
        for (TipoNotaC tipoNota : tipoNotas) {
            tipoNotaUiList.add(transform(tipoNota));
        }
        return tipoNotaUiList;
    }

    public static TipoNotaUi transform(TipoNotaC tipoNota) {
        TipoNotaUi tipoNotaUi = new TipoNotaUi();
        tipoNotaUi.setId(tipoNota.getTipoNotaId());
        tipoNotaUi.setTitle(tipoNota.getNombre());

        switch (tipoNota.getTipoId()) {
            case TipoNotaC.SELECTOR_VALORES:
                tipoNotaUi.setTipo(TipoNotaUi.Tipo.SELECTOR_VALORES);
                break;
            case TipoNotaC.SELECTOR_ICONOS:
                tipoNotaUi.setTipo(TipoNotaUi.Tipo.SELECTOR_ICONOS);
                break;
            case TipoNotaC.SELECTOR_NUMERICO:
                tipoNotaUi.setTipo(Tipo.SELECTOR_NUMERICO);
                break;
            case TipoNotaC.VALOR_NUMERICO:
                tipoNotaUi.setTipo(Tipo.VALOR_NUMERICO);
                break;
        }

        List<ValorTipoNotaC> valorTipoNotas = tipoNota.getValorTipoNotaList();
        int count = 0;
        List<ValorTipoNotaUi> valorTipoNotaUiList = new ArrayList<>();
        for (ValorTipoNotaC valorTipoNota : valorTipoNotas) {

            ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
            valorTipoNotaUi.setId(valorTipoNota.getValorTipoNotaId());
            valorTipoNotaUi.setTitle(valorTipoNota.getTitulo());
            valorTipoNotaUi.setAlias(valorTipoNota.getAlias());
            valorTipoNotaUi.setValorDefecto(valorTipoNota.getValorNumerico());
            valorTipoNotaUi.setIcono(valorTipoNota.getIcono());
            valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
            valorTipoNotaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
            valorTipoNotaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
            valorTipoNotaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
            valorTipoNotaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
            valorTipoNotaUi.setValorNumerico(valorTipoNota.getValorNumerico());
            switch (count) {
                case 0:
                    valorTipoNotaUi.setColor(AZUL);
                    break;
                case 1:
                    valorTipoNotaUi.setColor(VERDE);
                    break;
                case 2:
                    valorTipoNotaUi.setColor(ANARANJADO);
                    break;
                case 3:
                    valorTipoNotaUi.setColor(ROJO);
                    break;
                default:
                    valorTipoNotaUi.setColor(GREY);
                    break;
            }
            count++;
            valorTipoNotaUiList.add(valorTipoNotaUi);


        }

        tipoNotaUi.setValorTipoNotaList(valorTipoNotaUiList);
        return tipoNotaUi;
    }
}
