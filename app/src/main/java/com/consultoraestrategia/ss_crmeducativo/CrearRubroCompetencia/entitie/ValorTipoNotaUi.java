package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SCIEV on 7/11/2017.
 */
@Parcel
public class ValorTipoNotaUi  implements Serializable {
    public static final String GREY = "#90A4AE",AZUL ="#1976d2",ANARANJADO="#FF6D00",ROJO="#D32F2F",VERDE="#388e3c";
    String id;
    String titulo;
    String Descripcion;
    CriterioEvalUi criterioEvalUi;
    String icono;
    TipoNotaUi.TipoNota tipoNota;
    TipoNotaUi tipoNotaUi;
    public double valorDefecto;
    public List<PrecisionUi> precisionList;
    public boolean incluidoLInferior;
    public boolean incluidoLSuperior;
    public double limiteInferior;
    public double limiteSuperior;
    public double valorNumerico;
    public String color;


    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public ValorTipoNotaUi() {
        criterioEvalUi = new CriterioEvalUi();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CriterioEvalUi getCriterioEvalUi() {
        return criterioEvalUi;
    }

    public void setCriterioEvalUi(CriterioEvalUi criterioEvalUi) {
        this.criterioEvalUi = criterioEvalUi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public TipoNotaUi.TipoNota getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNotaUi.TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }


    public double getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(double valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public List<PrecisionUi> getPrecisionList() {
        return precisionList;
    }

    public void setPrecisionList(List<PrecisionUi> precisionList) {
        this.precisionList = precisionList;
    }

    public void setIncluidoLInferior(boolean incluidoLInferior) {
        this.incluidoLInferior = incluidoLInferior;
    }

    public boolean getIncluidoLInferior() {
        return incluidoLInferior;
    }

    public void setIncluidoLSuperior(boolean incluidoLSuperior) {
        this.incluidoLSuperior = incluidoLSuperior;
    }

    public boolean getIncluidoLSuperior() {
        return incluidoLSuperior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
