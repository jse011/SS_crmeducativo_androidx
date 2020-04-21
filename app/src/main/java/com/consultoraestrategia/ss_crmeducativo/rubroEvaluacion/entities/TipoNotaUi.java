package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */
@Parcel
public class TipoNotaUi implements Serializable {
    public static final String GREY = "#90A4AE",AZUL ="#1976d2",ANARANJADO="#FF6D00",ROJO="#D32F2F",VERDE="#388e3c";
    public enum Tipo {SELECTOR_VALORES, SELECTOR_ICONOS,VALOR_NUMERICO,SELECTOR_NUMERICO}
    public int idTipo;
    public String nombre;
    public String idTipoNota;
    public EscalaEvaluacionUi escalaEvaluacionUi;
    public Tipo tipo;

    public TipoNotaUi() {
    }


    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdTipoNota() {
        return idTipoNota;
    }

    public void setIdTipoNota(String idTipoNota) {
        this.idTipoNota = idTipoNota;
    }

    @Override
    public String toString() {
        return "" + nombre + "";
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

    public List<ValorTipoNotaUi> valorTipoNotaList;

    public List<ValorTipoNotaUi> getValorTipoNotaList() {
        return valorTipoNotaList;
    }

    public void setValorTipoNotaList(List<ValorTipoNotaUi> valorTipoNotaList) {
        this.valorTipoNotaList = valorTipoNotaList;
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
        tipoNotaUi.setIdTipoNota(tipoNota.getTipoNotaId());
        tipoNotaUi.setNombre(tipoNota.getNombre());

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
            valorTipoNotaUi.setKey(valorTipoNota.getValorTipoNotaId());
            valorTipoNotaUi.setTitle(valorTipoNota.getTitulo());
            valorTipoNotaUi.setAlias(valorTipoNota.getAlias());
            valorTipoNotaUi.setValorDefecto(valorTipoNota.getValorNumerico());
            valorTipoNotaUi.setIcono(valorTipoNota.getIcono());
            valorTipoNotaUi.setTipoNotaUi(tipoNotaUi);
            valorTipoNotaUi.setIncluidoLInferior(valorTipoNota.isIncluidoLInferior());
            valorTipoNotaUi.setIncluidoLSuperior(valorTipoNota.isIncluidoLSuperior());
            valorTipoNotaUi.setLimiteInferior(valorTipoNota.getLimiteInferior());
            valorTipoNotaUi.setLimiteSuperior(valorTipoNota.getLimiteSuperior());
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
