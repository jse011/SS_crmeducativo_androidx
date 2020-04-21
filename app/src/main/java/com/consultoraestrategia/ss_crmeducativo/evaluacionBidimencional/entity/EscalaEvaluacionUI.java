package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

/**
 * Created by SCIEV on 20/03/2018.
 */

public class EscalaEvaluacionUI {
    private int id;
    private String name;
    private double valorMinimo;
    private double valorMaximo;

    public EscalaEvaluacionUI() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "EscalaEvaluacionUI{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", valorMinimo=" + valorMinimo +
                ", valorMaximo=" + valorMaximo +
                '}';
    }
}
