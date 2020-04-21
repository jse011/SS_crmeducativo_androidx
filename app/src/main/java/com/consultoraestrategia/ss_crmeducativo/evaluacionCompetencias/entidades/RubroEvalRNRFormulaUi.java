package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades;

/**
 * Created by kike on 17/04/2018.
 */

public class RubroEvalRNRFormulaUi {
    private int id;
    private int rubroIdPrincipal;
    private int rubroIdSecundario;
    private int contador;
    private double peso;
    private String orientacion;
    private String color;

    public RubroEvalRNRFormulaUi() {
    }

    public RubroEvalRNRFormulaUi(int id, int rubroIdPrincipal, int rubroIdSecundario, int contador, double peso, String orientacion) {
        this.id = id;
        this.rubroIdPrincipal = rubroIdPrincipal;
        this.rubroIdSecundario = rubroIdSecundario;
        this.contador = contador;
        this.peso = peso;
        this.orientacion = orientacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRubroIdPrincipal() {
        return rubroIdPrincipal;
    }

    public void setRubroIdPrincipal(int rubroIdPrincipal) {
        this.rubroIdPrincipal = rubroIdPrincipal;
    }

    public int getRubroIdSecundario() {
        return rubroIdSecundario;
    }

    public void setRubroIdSecundario(int rubroIdSecundario) {
        this.rubroIdSecundario = rubroIdSecundario;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }
}
