package com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades;

/**
 * Created by kike on 16/02/2018.
 */

public class ValoresUi {
    public enum Valores {VALORES_SELECTOR_ICONOS,VALORES_SELECTOR_VALORES,VALORES_ASISTENCIA}

    private int contador;
    private String titulo;
    private String descripcion;
    private String valorNumerico;
    private String valorMinimo;
    private String valorMaximo;
    private TipoNotaUi tipoNotaUi;

    public ValoresUi() {
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(String valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(String valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(String valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }
}
