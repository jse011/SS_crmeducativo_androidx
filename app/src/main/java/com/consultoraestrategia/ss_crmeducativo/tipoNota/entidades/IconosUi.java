package com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades;

/**
 * Created by kike on 16/02/2018.
 */

public class IconosUi {
    private int contador;
    private String iconoImage;
    private String alias;
    private double valorNumerico;
    private double valorMinimo;
    private double valorMaximo;

    public IconosUi() {
    }

    public IconosUi(int contador, String iconoImage, String alias, double valorNumerico, double valorMinimo, double valorMaximo) {
        this.contador = contador;
        this.iconoImage = iconoImage;
        this.alias = alias;
        this.valorNumerico = valorNumerico;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getIconoImage() {
        return iconoImage;
    }

    public void setIconoImage(String iconoImage) {
        this.iconoImage = iconoImage;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
}
