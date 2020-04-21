package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities;

import java.io.Serializable;

/**
 * Created by SCIEV on 26/02/2018.
 */

@SuppressWarnings("serial")
public class EscalaUi  implements Serializable{
    private int id;
    private String descripcion;
    private double valorMinimo;
    private double valorMaximo;


    public EscalaUi() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
